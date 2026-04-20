package com.bookstore.user.dto;

import com.bookstore.user.entity.Role;

public record UserResponse(Long id, String name, String email, Role role) {
}

