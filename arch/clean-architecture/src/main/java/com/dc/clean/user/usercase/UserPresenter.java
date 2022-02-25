package com.dc.clean.user.usercase;

import com.dc.clean.user.usercase.dto.UserResponseModel;

public interface UserPresenter {
    UserResponseModel prepareSuccessView(UserResponseModel user);

    UserResponseModel prepareFailView(String error);
}
