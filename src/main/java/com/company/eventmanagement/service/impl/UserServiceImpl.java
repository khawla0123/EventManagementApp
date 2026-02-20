package com.company.eventmanagement.service.impl;


import com.company.eventmanagement.model.enums.Role;
import com.company.eventmanagement.model.User;
import com.company.eventmanagement.repository.UserRepository;
import com.company.eventmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Récupérer tous les utilisateurs
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Changer le rôle (ADMIN seulement)
    @Override
    public User changeUserRole(Long userId, String newRole) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = Role.valueOf(newRole.toUpperCase());
        user.setRole(role);

        return userRepository.save(user);
    }
}
