package com.example.usermanagementsystem.web;

import com.example.usermanagementsystem.exception.UserNotFoundException;
import com.example.usermanagementsystem.model.dto.UserDetailsDTO;
import com.example.usermanagementsystem.model.entity.UserEntity;
import com.example.usermanagementsystem.model.view.UserDisplayView;
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
        Page<UserDisplayView> allUsers = userService.getAllUsers(pageable);


        model.addAttribute("allUsers", allUsers);
        return "index";
    }


    @GetMapping("/users/{userId}")
    public String userDetails(Model model, @PathVariable("userId") Long userId) throws UserNotFoundException {
        UserDetailsDTO user = mapToUserDetailsDTO(userService.getUserById(userId));

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
        UserDisplayView userDTO = mapToUserDisplayView(userEntity);
        model.addAttribute("user", userDTO);

        return "user-edit";
    }

    private UserDisplayView mapToUserDisplayView(UserEntity userEntity) {
        return new UserDisplayView(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDateOfBirth(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail()
        );
    }

    private UserDetailsDTO mapToUserDetailsDTO(UserEntity userEntity) {
        return new UserDetailsDTO(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDateOfBirth(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail()
        );
    }
}
