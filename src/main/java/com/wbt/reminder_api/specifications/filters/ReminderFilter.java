package com.wbt.reminder_api.specifications.filters;

import com.wbt.reminder_api.enums.RecurrencePattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReminderFilter extends BaseFilter {
    private String title;
    private String message;
    private LocalDateTime triggerTime;
    private RecurrencePattern pattern;
    private boolean active = true;
}
