package com.example.usermanagementsystem.init;

import com.example.usermanagementsystem.model.entity.UserEntity;
import com.example.usermanagementsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;

    public DBInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) {
            initUser("Dido", "Nikolov", LocalDate.now(), "0896201921", "dido@gmail.com");
            initUser("Pesho", "Ivanov", LocalDate.now(), "0884201973", "pesho@abv.bg");
        }
    }

    private void initUser(String firstName, String lastName, LocalDate dateOfBirth, String phoneNumber, String email) {
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(String.valueOf(dateOfBirth));
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);

        userRepository.save(user);
    }
}
