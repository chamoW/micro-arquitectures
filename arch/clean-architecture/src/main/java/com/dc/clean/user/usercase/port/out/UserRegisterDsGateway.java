package com.dc.clean.user.usercase.port.out;

import com.dc.clean.user.usercase.dto.UserDsRequestModel;

/**
 * outcomming port to comunicate the domain with a outcomming adapter
 */
public interface UserRegisterDsGateway {

    boolean existsByName(String name);

    void save(UserDsRequestModel requestModel);
}
