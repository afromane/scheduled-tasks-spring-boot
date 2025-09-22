package com.wbt.reminder_api.model;

import com.wbt.reminder_api.enums.RecurrencePattern;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(
        name = "reminders",
        indexes = {@Index(name = "idx_on_active_and_trigger_time", columnList = "isActive, triggerTime")}
)
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime triggerTime;

    @Enumerated(EnumType.STRING)
    private RecurrencePattern pattern;

    private boolean isActive = true;
}
