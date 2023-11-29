package com.example.usermanagementsystem.web;


import com.example.usermanagementsystem.exception.UserAlreadyRegisteredException;
import com.example.usermanagementsystem.exception.UserNotFoundException;
import com.example.usermanagementsystem.model.dto.UserDTO;
import com.example.usermanagementsystem.model.entity.UserEntity;
import com.example.usermanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@SecurityRequirement(name="basicAuth")
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @Operation(
            description = "Get list of users sorted by lastName then by dateOfBirth",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(
            @Parameter(description = "Search parameter")
            @RequestParam(required = false) String searchParam,
            @Parameter(description = "Sort parameter", example = "lastName,dateOfBirth")
            @RequestParam(defaultValue = "lastName,dateOfBirth") String sort,
            @Parameter(description = "Page size", example = "3")
            @RequestParam(defaultValue = "3") int size,
            @Parameter(hidden = true)
                @PageableDefault(size = 3, sort = {"lastName", "dateOfBirth"}) Pageable pageable) {
        Page<UserDTO> users;
        pageable = PageRequest.of(0, size, Sort.by(sort.split(",")));
        if(searchParam != null && !searchParam.isEmpty()) {
            users = userService.searchUsers(searchParam, pageable);
        } else {
            users = userService.getAllUsers(pageable);

        }


        return ResponseEntity.ok(users.getContent());
    }

    @Operation(
            description = "Get one user by identifier",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "User not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(
            @Parameter(description = "User Id", example = "4")
            @PathVariable("userId") Long userId) throws UserNotFoundException {
        UserEntity userEntity = userService.getUserById(userId);
        return ResponseEntity.ok(mapToUserDTO(userEntity));
    }


    @Operation(
            description = "Create user",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = " \"firstName\": \"Test\"," +
                                                    " \"lastName\": \"Test\"," +
                                                    " \"dateOfBirth\": \"1990-01-01\"," +
                                                    " \"phoneNumber\": \"1339587787\"," +
                                                    " \"email\": \"test@example.com\" " +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws UserAlreadyRegisteredException {
        UserEntity user = userService.createUser(userDTO);

        return ResponseEntity.created(
                URI.create("/users/" + user.getId())
        ).body(mapToUserDTO(user));

    }


    @Operation(
            description = "Update user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = " \"firstName\": \"Test\"," +
                                                    " \"lastName\": \"Test\"," +
                                                    " \"dateOfBirth\": \"1990-01-01\"," +
                                                    " \"phoneNumber\": \"1339587787\"," +
                                                    " \"email\": \"test@example.com\" " +
                                                    "}"
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
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


    @Operation(
            description = "Delete user by identifier",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "User not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
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



