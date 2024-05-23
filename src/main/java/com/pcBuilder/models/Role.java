package com.pcBuilder.models;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Min(value = 5, message = "Username must be at least 5 characters")
    private String username;

    @Min(value = 5, message = "Role must be at least 5 characters")
    private String role;
}
