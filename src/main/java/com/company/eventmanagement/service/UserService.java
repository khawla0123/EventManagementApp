package com.company.eventmanagement.service;


import com.company.eventmanagement.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User changeUserRole(Long userId, String newRole);
}