package com.wbt.reminder_api.specifications;

import com.wbt.reminder_api.enums.RecurrencePattern;
import com.wbt.reminder_api.model.Reminder;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ReminderSpecification {

    public static Specification<Reminder> hasTitle(final String title) {
        return (root, query, cb) -> {
            if (title == null) return cb.conjunction();
            return cb.equal(root.get("title"), title);
        };
    }

    public static Specification<Reminder> hasTitleLike(final String title) {
        return (root, query, cb) -> {
            if (title == null) return cb.conjunction();
            return cb.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Reminder> hasMessageLike(final String message) {
        return (root, query, cb) -> {
            if (message == null) return cb.conjunction();
            return cb.like(root.get("message"), "%" + message + "%");
        };
    }

    public static Specification<Reminder> hasMessage(final String message) {
        return (root, query, cb) -> {
            if (message == null) return cb.conjunction();
            return cb.equal(root.get("message"), message);
        };
    }

    public static Specification<Reminder> hasPattern(final RecurrencePattern pattern) {
        return (root, query, cb) -> {
            if (pattern == null) return cb.conjunction();
            return cb.equal(root.get("pattern"), pattern);
        };
    }

    public static Specification<Reminder> isActive(final Boolean isActive) {
        return (root, query, cb) -> {
            if (isActive == null) return cb.conjunction();
            return cb.equal(root.get("isActive"), isActive);
        };
    }

    public static Specification<Reminder> hasTriggerTime(final LocalDateTime triggerTime) {
        return (root, query, cb) -> {
            if (triggerTime == null) return cb.conjunction();
            return cb.equal(root.get("triggerTime"), triggerTime);
        };
    }

}
