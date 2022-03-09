package com.dc.clean.user.usercase.port.in;

import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.dto.UserResponseModel;

/**
 * Incomming port to comunicate the domain with a incomming adapter
 */
public interface UserInputBoundary {
    UserResponseModel create(UserRequestModel requestModel);
}
