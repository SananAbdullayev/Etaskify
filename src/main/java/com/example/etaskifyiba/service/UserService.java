package com.example.etaskifyiba.service;

import com.example.etaskifyiba.dto.UserDTO;
import com.example.etaskifyiba.dto.request.UserRequest;
import com.example.etaskifyiba.dto.response.UserResponse;
import com.example.etaskifyiba.model.entity.User;

import java.util.Optional;

public interface UserService {
    UserResponse getAllUsers();

    UserDTO getUser(long id);

    Optional<User> getUserByEmail(String email);

    void save(UserRequest userRequest);

    void update(long id, UserRequest userRequest);

    void delete(long id);

}
