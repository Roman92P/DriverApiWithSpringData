package com.pashkov.driverapi.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pashkov.driverapi.app.config.LoginCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldRetunOkStatusWhenGivenCorrectUserCredentials() throws Exception {

        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setPassword("admin");
        loginCredentials.setUsername("Roman");


        this.mvc.perform(post("/login").with(csrf())
        .contentType(MediaType.APPLICATION_JSON)

        .content(asJsonString(loginCredentials)))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}