package com.company.eventmanagement.model;

import com.company.eventmanagement.model.enums.EventStatus;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    @Enumerated(EnumType.STRING)
    private EventStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private EventStatus newStatus;

    @ManyToOne
    private User changedBy;

    private LocalDateTime timestamp;

}
