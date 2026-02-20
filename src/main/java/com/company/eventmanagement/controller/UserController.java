package com.company.eventmanagement.controller;


import com.company.eventmanagement.model.User;
import com.company.eventmanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ ADMIN : voir tous les utilisateurs
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ✅ ADMIN : changer le rôle d’un utilisateur
    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> changeUserRole(
            @PathVariable Long id,
            @RequestParam String role
    ) {
        return ResponseEntity.ok(userService.changeUserRole(id, role));
    }
}