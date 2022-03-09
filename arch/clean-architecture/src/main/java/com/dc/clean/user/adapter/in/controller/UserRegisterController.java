package com.dc.clean.user.adapter.in.controller;

import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.dto.UserResponseModel;
import com.dc.clean.user.usercase.port.in.UserInputBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
/**
 * UserRegisterController incomming adapter that uses the incomming port UserInputBoundary
 */
public class UserRegisterController {

    private final UserInputBoundary userInput;

    @Autowired
    public UserRegisterController(UserInputBoundary userInput) {
        this.userInput = userInput;
    }


    @PostMapping("/")
    UserResponseModel create(@RequestBody UserRequestModel requestModel) {
        return userInput.create(requestModel);
    }
}
