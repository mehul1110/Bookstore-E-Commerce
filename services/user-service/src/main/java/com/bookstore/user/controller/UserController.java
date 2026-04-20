package com.bookstore.user.controller;

import com.bookstore.common.util.HeaderNames;
import com.bookstore.common.security.JwtService;
import com.bookstore.user.dto.UserRequests;
import com.bookstore.user.dto.UserResponse;
import com.bookstore.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRequests.RegisterRequest request) {
        UserResponse user = userService.register(request);
        String token = jwtService.generateToken(user.email(), Map.of("userId", user.id(), "role", user.role().name()));
        return ResponseEntity.ok(Map.of("message", "User registered", "token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UserRequests.LoginRequest request) {
        UserResponse user = userService.login(request.email());
        String token = jwtService.generateToken(user.email(), Map.of("userId", user.id(), "role", user.role().name()));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> profile(@RequestHeader(HeaderNames.USER_EMAIL) String email) {
        return ResponseEntity.ok(userService.getProfile(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(
            @RequestHeader(HeaderNames.USER_EMAIL) String email,
            @Valid @RequestBody UserRequests.UpdateProfileRequest request) {
        return ResponseEntity.ok(userService.updateProfile(email, request));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword() {
        return ResponseEntity.ok(Map.of("message", "Password changed"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
