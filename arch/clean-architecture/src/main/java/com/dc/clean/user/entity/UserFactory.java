package com.dc.clean.user.entity;

import com.dc.clean.user.entity.dto.UserDto;

public interface UserFactory {
    UserDto create(String name, String password);
}
