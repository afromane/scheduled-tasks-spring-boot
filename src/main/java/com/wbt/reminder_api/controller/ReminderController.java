package com.wbt.reminder_api.controller;

import com.wbt.reminder_api.dtos.ReminderRequest;
import com.wbt.reminder_api.model.Reminder;
import com.wbt.reminder_api.service.ReminderService;
import com.wbt.reminder_api.specifications.filters.ReminderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(path = {"/api/v1/reminders"})
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService service;

    @GetMapping
    public ResponseEntity<Page<Reminder>> allReminders(final @ModelAttribute ReminderFilter filter) {
        final var reminders = this.service.all(filter);
        return ResponseEntity.ok(reminders);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Reminder> getById(final @PathVariable(name = "id") Long reminderId) {
        return ResponseEntity.ok(this.service.get(reminderId));
    }

    @PostMapping
    public ResponseEntity<Reminder> add(final @RequestBody ReminderRequest request, final UriComponentsBuilder uriComponents) {
        final var saved = this.service.save(request);
        final var uri = uriComponents.path("/api/v1/reminders/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }
}
