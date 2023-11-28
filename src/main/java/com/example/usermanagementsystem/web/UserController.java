package com.example.usermanagementsystem.web;

import com.example.usermanagementsystem.exception.UserNotFoundException;
import com.example.usermanagementsystem.model.dto.UserDTO;
import com.example.usermanagementsystem.model.entity.UserEntity;
import com.example.usermanagementsystem.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String dashBoard(Model model,
                            @PageableDefault(size = 3, sort = {"lastName", "dateOfBirth"}) Pageable pageable) {
        Page<UserDTO> allUsers = userService.getAllUsers(pageable);


        model.addAttribute("allUsers", allUsers);
        return "index";
    }


    @GetMapping("/users/{userId}")
    public String userDetails(Model model, @PathVariable("userId") Long userId) throws UserNotFoundException {
        UserDTO user = mapToUserDTO(userService.getUserById(userId));

        model.addAttribute("user", user);

        return "user-details";
    }



    @GetMapping("/users/create")
    public String userCreate() {
        return "user-create";
    }

    @GetMapping("/users/edit/{userId}")
    public String userEdit(@PathVariable("userId") Long userId, Model model) throws UserNotFoundException {
        UserEntity userEntity = userService.getUserById(userId);
        UserDTO userDTO = mapToUserDTO(userEntity);
        model.addAttribute("user", userDTO);

        return "user-edit";
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
