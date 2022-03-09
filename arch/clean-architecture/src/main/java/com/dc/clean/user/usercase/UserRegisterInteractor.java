package com.dc.clean.user.usercase;


import com.dc.clean.user.entity.UserFactory;
import com.dc.clean.user.entity.dto.UserDto;
import com.dc.clean.user.usercase.dto.UserDsRequestModel;
import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.dto.UserResponseModel;
import com.dc.clean.user.usercase.port.in.UserInputBoundary;
import com.dc.clean.user.usercase.port.out.UserPresenter;
import com.dc.clean.user.usercase.port.out.UserRegisterDsGateway;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * UserRegistrerInteractor use case implements the incoming port UserInputBoundary
 * and uses de outcomming ports: UserRegisterDsGateway and UserPresenter
 */
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
        LocalDateTime  now = LocalDateTime.now();
        UserDsRequestModel userDsModel = new UserDsRequestModel(userDto.getName(), userDto.getPassword(), now);

        userDsGateway.save(userDsModel);

        UserResponseModel accountResponseModel = new UserResponseModel(userDto.getName(), now.toString());
        return userPresenter.prepareSuccessView(accountResponseModel);
    }
}
