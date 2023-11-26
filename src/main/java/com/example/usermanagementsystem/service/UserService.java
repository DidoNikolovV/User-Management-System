package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.model.dto.UserDTO;
import com.example.usermanagementsystem.model.entity.UserEntity;
import com.example.usermanagementsystem.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with " + userDTO.getEmail() + " already exists");
        }

        UserEntity user = new UserEntity();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);
        return user;
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with " + id + " not found"));
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {

        Page<UserDTO> users = userRepository.findAll(pageable).map(userEntity -> new UserDTO(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDateOfBirth(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail()
        ));

        return users;
    }

    public Page<UserDTO> searchUsers(String searchParam, Pageable pageable) {
        return userRepository.searchUsers(searchParam, pageable).map(this::mapAsUserDTO);
    }



    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }


    public UserEntity deleteUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(user.getId());

        return user;
    }


    private UserDTO mapAsUserDTO(UserEntity userEntity) {
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
