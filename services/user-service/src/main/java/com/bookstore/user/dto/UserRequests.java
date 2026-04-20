package com.bookstore.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public final class UserRequests {
    private UserRequests() {
    }

    public record RegisterRequest(
            @NotBlank(message = "Name is required") String name,
            @Email(message = "Invalid email") @NotBlank String email,
            @Size(min = 8, message = "Password must be at least 8 characters")
            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain an uppercase letter")
            String password
    ) {
    }

    public record LoginRequest(@Email String email, @NotBlank String password) {
    }

    public record UpdateProfileRequest(@NotBlank String name, String phone) {
    }
}

