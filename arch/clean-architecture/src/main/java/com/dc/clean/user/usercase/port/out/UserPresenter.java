package com.dc.clean.user.usercase.port.out;

import com.dc.clean.user.usercase.dto.UserResponseModel;

/**
 * outcomming port to comunicate the domain with a outcomming adapter
 */
public interface UserPresenter {
    UserResponseModel prepareSuccessView(UserResponseModel user);

    UserResponseModel prepareFailView(String error);
}
