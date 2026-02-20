package com.company.eventmanagement.model;

import com.company.eventmanagement.model.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.*;
@Entity
@Getter
@Setter

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate eventDate;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User assignedTo;

    private LocalDateTime createdAt;
}
