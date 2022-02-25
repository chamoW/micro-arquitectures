package com.dc.clean.user.usercase;

import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.dto.UserResponseModel;

public interface UserInputBoundary {
    UserResponseModel create(UserRequestModel requestModel);
}
