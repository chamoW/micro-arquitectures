package com.dc.clean.user.usercase.impl;

import com.dc.clean.user.entity.UserFactory;
import com.dc.clean.user.entity.dto.UserDto;
import com.dc.clean.user.usercase.UserInputBoundary;
import com.dc.clean.user.usercase.UserPresenter;
import com.dc.clean.user.usercase.UserRegisterDsGateway;
import com.dc.clean.user.usercase.dto.UserDsRequestModel;
import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.dto.UserResponseModel;

import java.time.LocalDateTime;

public class UserRegisterInteractor implements UserInputBoundary {

    final UserRegisterDsGateway userDsGateway;
    final UserPresenter userPresenter;
    final UserFactory userFactory;

    public UserRegisterInteractor(UserRegisterDsGateway userDsGateway, UserPresenter userPresenter, UserFactory userFactory) {
        this.userDsGateway = userDsGateway;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public UserResponseModel create(UserRequestModel requestModel) {
        if (userDsGateway.existsByName(requestModel.getName())) {
            return userPresenter.prepareFailView("User already exists.");
        }
        UserDto userDto = userFactory.create(requestModel.getName(), requestModel.getPassword());
        if (!userDto.passwordIsValid()) {
            return userPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        LocalDateTime now = LocalDateTime.now();
        UserDsRequestModel userDsModel = new UserDsRequestModel(userDto.getName(), userDto.getPassword(), now);

        userDsGateway.save(userDsModel);

        UserResponseModel accountResponseModel = new UserResponseModel(userDto.getName(), now.toString());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
