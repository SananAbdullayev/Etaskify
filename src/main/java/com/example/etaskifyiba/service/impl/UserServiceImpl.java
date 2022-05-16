package com.example.etaskifyiba.service.impl;

import com.example.etaskifyiba.dto.UserDTO;
import com.example.etaskifyiba.dto.request.UserRequest;
import com.example.etaskifyiba.dto.response.UserResponse;
import com.example.etaskifyiba.exception.CustomNotFoundException;
import com.example.etaskifyiba.model.entity.User;
import com.example.etaskifyiba.exception.handling.ErrorCodeEnum;
import com.example.etaskifyiba.model.enums.Role;
import com.example.etaskifyiba.repository.UserRepository;
import com.example.etaskifyiba.service.UserService;
import com.example.etaskifyiba.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserResponse getAllUsers() {
        List<UserDTO> userList = userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return makeUserResponse(userList);
    }


    @Override
    public UserDTO getUser(long id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND));
    }

    @Override
    public void save(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(PasswordGenerator.generate())
                .role(Role.USER)

                .build();
        userRepository.save(user);
    }

    @Override
    public void update(long id, UserRequest userRequest) {
        User user = getUserById(id);
        user = User.builder()
                .id(user.getId())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .password(user.getPassword())
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    private UserDTO convertToDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .tasks(user.getTasks())
                .build();
    }


    private UserResponse makeUserResponse(List<UserDTO> users) {
        return UserResponse.builder()
                .users(users)
                .build();
    }

    private User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.USER_NOT_FOUND));
    }
}
