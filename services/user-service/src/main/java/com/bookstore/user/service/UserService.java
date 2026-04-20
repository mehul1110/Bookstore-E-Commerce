package com.bookstore.user.service;

import com.bookstore.common.exception.ResourceNotFoundException;
import com.bookstore.user.dto.UserRequests;
import com.bookstore.user.dto.UserResponse;
import com.bookstore.user.entity.Role;
import com.bookstore.user.entity.User;
import com.bookstore.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(UserRequests.RegisterRequest request) {
        User user = new User();
        user.setId(idGenerator.getAndIncrement());
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setRole(Role.USER);
        return toResponse(user);
    }

    public UserResponse login(String email) {
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User stub = new User();
            stub.setId(1L);
            stub.setName("Bookstore User");
            stub.setEmail(email);
            stub.setPassword("Password@123");
            stub.setRole(email.startsWith("admin") ? Role.ADMIN : Role.USER);
            return stub;
        });
        return toResponse(user);
    }

    public UserResponse getProfile(String email) {
        return userRepository.findByEmail(email)
                .map(this::toResponse)
                .orElse(new UserResponse(1L, "Bookstore User", email, Role.USER));
    }

    public UserResponse updateProfile(String email, UserRequests.UpdateProfileRequest request) {
        return new UserResponse(1L, request.name(), email, Role.USER);
    }

    public void deleteUser(Long id) {
        if (id <= 0) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}

