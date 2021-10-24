package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Topic;
import com.pashkov.driverapi.app.model.TopicModel;
import com.pashkov.driverapi.app.model.TopicRepresentationModelAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TopicController.class, useDefaultFilters = false)
@WithMockUser
class TopicControllerTest {

    @Autowired
    MockMvc mvc;

    @InjectMocks
    TopicRepresentationModelAssembler topicRepresentationModelAssembler;

    @MockBean
    TopicController topicController;

    @BeforeEach
    public void createTopicMockResponseEntity() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaTypes.HAL_JSON);

        Topic topic = new Topic();
        topic.setTopicDescription("Opis1");
        Topic topic1 = new Topic();
        topic1.setTopicDescription("Opis2");
        Set<Topic> topics = new HashSet<>();
        topics.add(topic);
        topics.add(topic);

        ResponseEntity<CollectionModel<TopicModel>> responseEntity = new ResponseEntity<>(
                topicRepresentationModelAssembler.toCollectionModel(topics),
                header,
                HttpStatus.OK
        );

        ResponseEntity<TopicModel> responseEntity1 = new ResponseEntity<>(
                topicRepresentationModelAssembler.toModel(topic),
                header,
                HttpStatus.OK
        );

        ResponseEntity<TopicModel> responseEntity2 = new ResponseEntity<>(
                header,
                HttpStatus.NOT_FOUND
        );

        when(topicController.returnAllExistingTopics()).thenReturn(responseEntity);
        when(topicController.getTopicRepresentationByTitle("Opis1")).thenReturn(responseEntity1);
        when(topicController.getTopicRepresentationByTitle("Opis3")).thenReturn(responseEntity2);
    }

    @Test
    public void shouldReturnAllTopics() throws Exception {

        this.mvc.perform(get("/topics").contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON));
    }

    @Test
    public void shouldReturnCorrectTopicByTopicTitle() throws Exception {
        //{"topicDescription":"Opis1","_links":{"self":{"href":"/topics/Opis1"},"All topics":{"href":"/topics"}}}
        this.mvc.perform(get("/topics/Opis1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.topicDescription", is("Opis1")));
    }

    @Test
    public void shouldReturnNotFoundIhNoSuchTopicDescription() throws Exception {
        this.mvc.perform(get("/topics/Opis3")).andExpect(status().isNotFound());
    }
}