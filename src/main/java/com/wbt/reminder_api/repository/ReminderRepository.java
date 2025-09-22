package com.wbt.reminder_api.repository;

import com.wbt.reminder_api.model.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;

public interface ReminderRepository extends JpaRepository<Reminder, Long>, JpaSpecificationExecutor<Reminder> {
    Page<Reminder> findByIsActiveTrueAndTriggerTimeBefore(LocalDateTime time, Pageable pageable);

    Page<Reminder> findByIsActiveTrue(Pageable pageable);
}
