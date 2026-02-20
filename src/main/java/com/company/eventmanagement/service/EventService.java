package com.company.eventmanagement.service;


import com.company.eventmanagement.model.Event;

import java.util.List;

public interface EventService {

    Event createEvent(Event event, String userEmail);

    Event changeStatus(Long eventId, String newStatus, String userEmail);

    List<Event> getAllEvents();

    Event getEvent(Long id);
}