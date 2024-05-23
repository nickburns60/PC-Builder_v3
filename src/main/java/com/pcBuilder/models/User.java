package com.pcBuilder.models;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Min(value = 5, message = "Username must be at least 5 characters")
    private String username;

    @Min(value = 5, message = "Password must be at least 5 characters")
    private String password;

    @Min(value = 5, message = "Email must be at least 5 characters")
    private String email;
}
