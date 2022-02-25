package com.dc.clean.user.adapter.repository.impl;

import com.dc.clean.user.adapter.model.UserDataMapper;
import com.dc.clean.user.adapter.repository.JpaUserRepository;
import com.dc.clean.user.usercase.UserRegisterDsGateway;
import com.dc.clean.user.usercase.dto.UserDsRequestModel;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaUser implements UserRegisterDsGateway {

    @Autowired
    private JpaUserRepository repository;


    @Override
    public boolean existsByName(String name) {
        return repository.existsById(name);
    }

    @Override
    public void save(UserDsRequestModel requestModel) {
        UserDataMapper user = new UserDataMapper(requestModel.getName(), requestModel.getPassword(), requestModel.getCreationTime());

        repository.save(user);

    }
}
