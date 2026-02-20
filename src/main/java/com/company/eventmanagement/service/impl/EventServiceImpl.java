package com.company.eventmanagement.service.impl;

import com.company.eventmanagement.model.*;
import com.company.eventmanagement.model.enums.*;
import com.company.eventmanagement.repository.*;
import com.company.eventmanagement.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final LogRepository logRepository;

    public EventServiceImpl(EventRepository eventRepository,
                            UserRepository userRepository,
                            LogRepository logRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    @Override
    public Event createEvent(Event event, String email) {

        User creator = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        event.setStatus(EventStatus.DRAFT);
        event.setCreatedBy(creator);
        event.setCreatedAt(LocalDateTime.now());

        return eventRepository.save(event);
    }

    @Override
    public Event changeStatus(Long eventId, String newStatus, String email) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EventStatus oldStatus = event.getStatus();
        EventStatus next = EventStatus.valueOf(newStatus.toUpperCase());

        validateTransition(oldStatus, next, user.getRole());

        event.setStatus(next);
        eventRepository.save(event);

        Log log = new Log();
        log.setEvent(event);
        log.setOldStatus(oldStatus);
        log.setNewStatus(next);
        log.setChangedBy(user);
        log.setTimestamp(LocalDateTime.now());

        logRepository.save(log);

        return event;
    }

    private void validateTransition(EventStatus oldStatus,
                                    EventStatus next,
                                    Role role) {

        if (oldStatus == EventStatus.CLOSED)
            throw new RuntimeException("Closed event cannot be modified");

        switch (oldStatus) {

            case DRAFT -> {
                if (next != EventStatus.SUBMITTED || role != Role.MANAGER)
                    throw new RuntimeException("Only MANAGER can submit draft");
            }

            case SUBMITTED -> {
                if (role != Role.ADMIN ||
                        (next != EventStatus.APPROVED && next != EventStatus.REJECTED))
                    throw new RuntimeException("Only ADMIN can approve/reject");
            }

            case APPROVED, REJECTED -> {
                if (next != EventStatus.CLOSED)
                    throw new RuntimeException("Only CLOSED allowed");
            }
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}