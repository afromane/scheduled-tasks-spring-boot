package com.wbt.reminder_api.service;

import com.wbt.reminder_api.enums.RecurrencePattern;
import com.wbt.reminder_api.model.Reminder;
import com.wbt.reminder_api.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReminderSchedulerService {

    private final ReminderRepository reminderRepository;

    @Scheduled(fixedRate = 60_000) // 1 min
    public void processReminders() {
        final var now = LocalDateTime.now();
        var pageable = PageRequest.of(0, 100); // batch processing
        Page<Reminder> dueReminders;
        do {
            dueReminders = this.reminderRepository.findByIsActiveTrueAndTriggerTimeBefore(now, pageable);
            dueReminders.getContent().forEach(reminder -> {
                final var message = """
                        ________________________________________________________
                        | Reminder: %s
                        | Title: %s
                        | Message: %s
                        | Pattern: %s
                        | Trigger: %s
                        |_______________________________________________________
                        """.formatted(reminder.getId(), reminder.getTitle(), reminder.getMessage(), reminder.getPattern(), reminder.getTriggerTime());
                System.out.println(message);

                // PROCEED WITH BUSINESS LOGIC HERE
                handleRecurrence(reminder);
            });
            pageable = pageable.next();
        } while (!dueReminders.isEmpty());
    }

    private void handleRecurrence(Reminder reminder) {
        if (reminder.getPattern() == RecurrencePattern.NONE) {
            reminder.setActive(false);
        } else {
            // Calculate next trigger time based on pattern
            LocalDateTime nextTrigger = calculateNextTrigger(reminder.getTriggerTime(), reminder.getPattern());
            reminder.setTriggerTime(nextTrigger);
        }
        reminderRepository.save(reminder);
    }

    private LocalDateTime calculateNextTrigger(LocalDateTime current, RecurrencePattern pattern) {
        return switch (pattern) {
            case DAILY -> current.plusDays(1);
            case WEEKLY -> current.plusWeeks(1);
            case MONTHLY -> current.plusMonths(1);
            case ANNUALLY -> current.plusYears(1);
            default -> current;
        };
    }
}
