package com.dc.clean.user.adapter.in.controller;

import com.dc.clean.user.adapter.in.controller.UserRegisterController;
import com.dc.clean.user.usercase.dto.UserRequestModel;
import com.dc.clean.user.usercase.port.in.UserInputBoundary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserRegisterController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegisterControllerTest {


    @Autowired
    private MockMvc client;

    @MockBean
    private UserInputBoundary userInputBoundary;

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
        client.perform(post("/api/user/").content(mapper.writeValueAsString(dto))
        .contentType(MediaType.APPLICATION_JSON)
        )



        //then
                .andDo(print())
                .andExpect(status().isOk());
    }

}