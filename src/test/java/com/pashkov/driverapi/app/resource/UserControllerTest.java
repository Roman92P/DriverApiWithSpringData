package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TopicController.class, useDefaultFilters = false)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserController userController;

    @InjectMocks
    UserRepresentationModelAssembler userRepresentationModelAssembler;

    @BeforeEach
    public void createUserResponseEntity(){

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaTypes.HAL_JSON);

        Question question = new Question();
        question.setId(1l);
        question.setCorrectAnswer("testCorrectAnswer");
        question.setPossibleAnswerOne("one");
        question.setPossibleAnswerTwo("two");
        question.setPossibleAnswerThree("three");
        question.setQuestionContent("testQuestionContent");
        question.setScore("5");

        Topic topic = new Topic();
        topic.setTopicDescription("testTopicDescription");
        topic.setId(1L);
        question.setTopicModel(topic);

        Advice advice = new Advice();
        advice.setAdviceTitle("testAdviceTitle");
        advice.setContent("testAdviceContent");
        advice.setId(1L);
        advice.setTopic(topic);
        advice.setLikesCount(1);
        advice.setSharesCount(1);

        Training training = new Training();
        training.setId(1l);
        training.setTrainingTitle("testTrainingTitle");
        training.setAdvice(advice);
        advice.setTraining(training);
        Set<Question> questionSet = new HashSet<>();
        training.setQuestions(questionSet);

        User user = new User();
        user.setEnabled(true);
        user.setPassword("password");
        user.setUsername("testUser");
        user.setId(1L);
        Set<Advice> adviceSet = new HashSet<>();
        user.setSharedAdvices(adviceSet);
        user.setLikedAdvices(adviceSet);
        user.setUserNick("testUserNick");
        user.setUserScore(100);
        Set<Training>trainingSet = new HashSet<>();
        trainingSet.add(training);
        user.setCompleteTrainings(trainingSet);

        ResponseEntity <UserModel> userResponseEntity = new ResponseEntity<>(
                userRepresentationModelAssembler.toModel(user),
                header,
                HttpStatus.OK
        );

        when(userController.getLoggedUserInfo(any())).thenReturn(userResponseEntity);
    }

    @Test
    @WithMockUser
    public void shouldReturnUserInfoIfUserIsLoggedIn() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }
}