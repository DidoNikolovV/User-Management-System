package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    @Query("SELECT u FROM UserEntity u")
    Page<UserEntity> findAll(Pageable pageable);

    @Query("SELECT u FROM UserEntity u WHERE " +
            "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchParam, '%')) OR " +
            "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchParam, '%')) OR " +
            "LOWER(u.dateOfBirth) LIKE LOWER(CONCAT('%', :searchParam, '%')) OR " +
            "LOWER(u.phoneNumber) LIKE LOWER(CONCAT('%', :searchParam, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchParam, '%'))")
    Page<UserEntity> searchUsers(@Param("searchParam") String searchParam, Pageable pa);

    Optional<UserEntity> findByEmail(String email);

    Optional<Object> findByPhoneNumber(String phoneNumber);
}
