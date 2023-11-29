package com.example.usermanagementsystem.advice;

import com.example.usermanagementsystem.exception.UserAlreadyRegisteredException;
import com.example.usermanagementsystem.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleBusinessException(UserNotFoundException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("errorMessage", ex.getMessage());
        return errorsMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public Map<String, String> handleUserRegisteredException(UserAlreadyRegisteredException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("errorMessage", ex.getMessage());

        return errorsMap;
    }

}
