package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.exception.UserAlreadyRegisteredException;
import com.example.usermanagementsystem.exception.UserNotFoundException;
import com.example.usermanagementsystem.model.dto.UserCreationDTO;
import com.example.usermanagementsystem.model.entity.UserEntity;
import com.example.usermanagementsystem.model.view.UserDisplayView;
import com.example.usermanagementsystem.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDisplayView createUser(UserCreationDTO userCreationDTO) {
        if(userRepository.findByEmail(userCreationDTO.getEmail()).isPresent()) {
            throw new UserAlreadyRegisteredException("User with " + userCreationDTO.getEmail() + " already exists");
        }

        if(userRepository.findByPhoneNumber(userCreationDTO.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyRegisteredException("User with " + userCreationDTO.getPhoneNumber() + " already exists");
        }


        UserEntity user = new UserEntity();
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setDateOfBirth(userCreationDTO.getDateOfBirth());
        user.setPhoneNumber(userCreationDTO.getPhoneNumber());
        user.setEmail(userCreationDTO.getEmail());


        userRepository.save(user);

        UserDisplayView userDisplayView = new UserDisplayView(
                user.getId(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getPhoneNumber(), user.getEmail());
        return userDisplayView;
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public Page<UserDisplayView> getAllUsers(Pageable pageable) {

        Page<UserDisplayView> users = userRepository.findAll(pageable).map(userEntity -> new UserDisplayView(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDateOfBirth(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail()
        ));

        return users;
    }

    public Page<UserDisplayView> searchUsers(String searchParam, Pageable pageable) {
        return userRepository.searchUsers(searchParam, pageable).map(this::mapAsUserDisplayView);
    }



    public void updateUser(UserEntity userEntity) throws UserNotFoundException{
        userRepository.save(userEntity);
    }


    public void deleteUserById(Long id)  {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.deleteById(user.getId());
    }

    private UserDisplayView mapAsUserDisplayView(UserEntity userEntity) {
        return new UserDisplayView(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDateOfBirth(),
                userEntity.getPhoneNumber(),
                userEntity.getEmail()
        );
    }
}
