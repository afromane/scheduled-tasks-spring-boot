package com.wbt.reminder_api.service;

import com.wbt.reminder_api.dtos.ReminderRequest;
import com.wbt.reminder_api.model.Reminder;
import com.wbt.reminder_api.specifications.filters.ReminderFilter;
import org.springframework.data.domain.Page;

public interface ReminderService {
    Page<Reminder> all(ReminderFilter filter);

    Reminder get(Long id);

    Reminder save(ReminderRequest request);
}
