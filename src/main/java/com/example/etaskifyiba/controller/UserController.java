package com.example.etaskifyiba.controller;

import com.example.etaskifyiba.dto.UserDTO;
import com.example.etaskifyiba.dto.request.UserRequest;
import com.example.etaskifyiba.dto.response.UserResponse;
import com.example.etaskifyiba.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserResponse getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable(name = "id") long id) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void createUser(@Valid @RequestBody UserRequest userRequest) {
        userService.save(userRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateUser(@PathVariable(name = "id") long id,
                           @RequestBody UserRequest userRequest) {
        userService.update(id, userRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable(name = "id") long id) {
        userService.delete(id);
    }




}
