package com.dc.clean.user.entity.impl;

import com.dc.clean.user.entity.dto.UserDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {


    @Test
    void testPassword() {
        UserDto user = new UserDto("Wladimir", "1234");
        assertFalse(user.passwordIsValid());
    }

}