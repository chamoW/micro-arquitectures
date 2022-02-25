package com.dc.clean.user.entity.impl;

import com.dc.clean.user.entity.UserFactory;
import com.dc.clean.user.entity.dto.UserDto;

public class CommonUserFactory implements UserFactory {

    @Override
    public UserDto create(String name, String password) {
        return new UserDto(name, password);
    }
}
