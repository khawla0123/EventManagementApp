package com.company.eventmanagement.controller;


import com.company.eventmanagement.model.Event;
import com.company.eventmanagement.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event,
                                             Authentication auth) {
        return ResponseEntity.ok(
                eventService.createEvent(event, auth.getName())
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Event> changeStatus(@PathVariable Long id,
                                              @RequestParam String status,
                                              Authentication auth) {

        return ResponseEntity.ok(
                eventService.changeStatus(id, status, auth.getName())
        );
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }
}