package com.company.eventmanagement.repository;

import com.company.eventmanagement.model.Event;
import com.company.eventmanagement.model.enums.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);
    List<Event> findByCreatedById(Long userId);
    List<Event> findByAssignedToId(Long userId);
}