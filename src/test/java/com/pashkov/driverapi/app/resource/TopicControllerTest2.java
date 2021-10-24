package com.pashkov.driverapi.app.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TopicControllerTest2 {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnEntityNotFoundExceptionIfThereIsNoSuchTopic() throws Exception {
        this.mvc.perform(get("/topics/Prędkośćsadasdad")).andExpect(status().isNotFound());
    }
}