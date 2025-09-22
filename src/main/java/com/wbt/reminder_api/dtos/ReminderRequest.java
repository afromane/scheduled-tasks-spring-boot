package com.wbt.reminder_api.dtos;

import com.wbt.reminder_api.enums.RecurrencePattern;

import java.time.LocalDateTime;

public record ReminderRequest(
        String title,
        String message,
        RecurrencePattern pattern,
        LocalDateTime triggerTime
) {
}
