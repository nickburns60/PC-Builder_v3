package com.pcBuilder.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    private String email;
}
