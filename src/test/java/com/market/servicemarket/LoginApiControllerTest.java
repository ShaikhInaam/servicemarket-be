package com.market.servicemarket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.servicemarket.business.base.LoginBusiness;
import com.market.servicemarket.business.impl.LoginBusinessImpl;
import com.market.servicemarket.controller.LoginController;
import com.market.servicemarket.request.LoginRequest;
import com.market.servicemarket.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginBusinessImpl loginService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateNewUser() throws Exception {
        given(loginService.authenticateUser(any(LoginRequest.class))).willAnswer((invocation) -> invocation.getArgument(0));


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("inaam");
        loginRequest.setPassword("inaam123");
        this.mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is(loginRequest.getUsername())))
                .andExpect(jsonPath("$.password", is(loginRequest.getPassword())))
                .andExpect(jsonPath("$.latitude", is(loginRequest.getLatitude())))
        ;
    }


    @Test
    void whenValidInput_thenReturns200() throws Exception {
//        UserResource user = new UserResource("Zaphod", "zaphod@galaxy.net");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("inaam");
        loginRequest.setPassword("inaam123");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .param(loginRequest)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());

    }
}
