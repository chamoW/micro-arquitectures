package com.dc.clean.user.adapter.in.controller.response;

import com.dc.clean.user.usercase.dto.UserResponseModel;
import com.dc.clean.user.usercase.port.out.UserPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserResponseFormatter implements UserPresenter {

    @Override
    public UserResponseModel prepareSuccessView(UserResponseModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }

    @Override
    public UserResponseModel prepareFailView(String error) {
        throw new  ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
