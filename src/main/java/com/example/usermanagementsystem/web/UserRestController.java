package com.example.usermanagementsystem.web;


import com.example.usermanagementsystem.exception.UserNotFoundException;
import com.example.usermanagementsystem.model.dto.UserDTO;
import com.example.usermanagementsystem.model.entity.UserEntity;
import com.example.usermanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(required = false) String searchParam,
                                @PageableDefault(size = 3, sort = {"lastName", "dateOfBirth"}) Pageable pageable) {
        Page<UserDTO> users;
        if(searchParam != null && !searchParam.isEmpty()) {
            users = userService.searchUsers(searchParam, pageable);
        } else {
            users = userService.getAllUsers(pageable);

        }


        return ResponseEntity.ok(users.getContent());
    }


    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        UserEntity userEntity = userService.getUserById(userId);
        return ResponseEntity.ok(mapToUserDTO(userEntity));
    }


    @PostMapping(value = "/api/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserEntity user = userService.createUser(userDTO);
        return ResponseEntity.created(
                URI.create("/users/" + user.getId())
        ).body(mapToUserDTO(user));
    }


    @PutMapping("/api/users/edit/{userId}")
    public ResponseEntity<UserDTO> editUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) throws UserNotFoundException {
        UserEntity updatedUser = userService.getUserById(userId);

        updatedUser.setFirstName(userDTO.getFirstName());
        updatedUser.setLastName(userDTO.getLastName());
        updatedUser.setDateOfBirth(userDTO.getDateOfBirth());
        updatedUser.setPhoneNumber(userDTO.getPhoneNumber());
        updatedUser.setEmail(userDTO.getEmail());

        userService.updateUser(updatedUser);

        return ResponseEntity.ok(mapToUserDTO(updatedUser));

    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") Long userId) {
        UserEntity userEntity = userService.deleteUserById(userId);
        return ResponseEntity.ok(mapToUserDTO(userEntity));
    }

    private UserDTO mapToUserDTO(UserEntity userEntity) {
        return new UserDTO(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDateOfBirth(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail()
        );
    }
}
