package com.pashkov.driverapi.app.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @Test
    public void shouldReturnStatusOkIfTopicExist() throws Exception {
        this.mvc.perform(get("/topics/Prędkość")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnCollectionOfTopics() throws Exception {
        this.mvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"topics\":")));
    }
}