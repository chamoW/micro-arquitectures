package com.dc.clean.user.adapter.controller;

import com.dc.clean.user.usercase.UserInputBoundary;
import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.dto.UserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
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
