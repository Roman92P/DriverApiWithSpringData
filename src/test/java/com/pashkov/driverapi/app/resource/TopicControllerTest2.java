package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class TopicControllerTest2 {

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserDetail userDetail;

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnEntityNotFoundExceptionIfThereIsNoSuchTopic() throws Exception {
        this.mvc.perform(get("/topics/Prędkośćsadasdad")).andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnStatusOkIfTopicExist() throws Exception {
        this.mvc.perform(get("/topics/Prędkość")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnCollectionOfTopics() throws Exception {
        this.mvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"topics\":")));
    }
}