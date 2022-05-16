package com.example.etaskifyiba.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;
    @Size(min = 6, max = 50, message = "The minimum password length must be 6")
    private String password;
}