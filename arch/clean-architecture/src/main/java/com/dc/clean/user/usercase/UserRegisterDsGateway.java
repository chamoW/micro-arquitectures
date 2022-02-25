package com.dc.clean.user.usercase;

import com.dc.clean.user.usercase.dto.UserDsRequestModel;

public interface UserRegisterDsGateway {

    boolean existsByName(String name);

    void save(UserDsRequestModel requestModel);
}
