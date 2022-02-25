package com.dc.clean.user.adapter.controller;

import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.dto.UserResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest(UserRegisterController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegisterControllerIntegrationTest {


    @Autowired
    private TestRestTemplate client;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
      //  this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.mapper = new ObjectMapper();


    }

    @Test
    void save () throws Exception {


        //given
        UserRequestModel dto = new UserRequestModel();
        dto.setName("Wladimir");
        dto.setPassword("545");

        //when
        ResponseEntity<UserResponseModel> response =  client.postForEntity("/api/user/", dto, UserResponseModel.class);


        //then
        assertNotNull(response);
    }

}