package com.wbt.reminder_api.service.impl;

import com.wbt.reminder_api.dtos.ReminderRequest;
import com.wbt.reminder_api.exceptions.ResourceNotFoundException;
import com.wbt.reminder_api.model.Reminder;
import com.wbt.reminder_api.repository.ReminderRepository;
import com.wbt.reminder_api.service.ReminderService;
import com.wbt.reminder_api.specifications.ReminderSpecification;
import com.wbt.reminder_api.specifications.filters.ReminderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository repository;

    @Override
    public Page<Reminder> all(final ReminderFilter filter) {
        final var pageable = PageRequest.of(filter.getPage(), filter.getSize());
        return this.repository.findAll(buildSpecifications(filter), pageable);
    }

    @Override
    public Reminder get(final Long id) {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find any reminder with ID: " + id));
    }

    @Override
    public Reminder save(final ReminderRequest request) {
        final var newReminder = new Reminder();
        BeanUtils.copyProperties(request, newReminder);
        return this.repository.save(newReminder);
    }

    private Specification<Reminder> buildSpecifications(final ReminderFilter filer) {
        return Specification.where(
                ReminderSpecification.hasTitleLike(filer.getTitle())
                        .and(ReminderSpecification.hasMessageLike(filer.getMessage()))
                        .and(ReminderSpecification.hasTriggerTime(filer.getTriggerTime()))
                        .and(ReminderSpecification.hasPattern(filer.getPattern()))
                        .and(ReminderSpecification.isActive(filer.isActive()))
        );
    }
}
