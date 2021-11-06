package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = TopicController.class, useDefaultFilters = false)
class UserControllerTest {

    private static Traverson traverson;

    @Autowired
    MockMvc mvc;

    @MockBean
    UserController userController;

    @InjectMocks
    UserRepresentationModelAssembler userRepresentationModelAssembler;

    @InjectMocks
    AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;

    @BeforeEach
    public void createUserResponseEntity() {

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
        //advice.setTraining(training);
        Set<Question> questionSet = new HashSet<>();
        training.setQuestions(questionSet);

        User user = new User();
        user.setEnabled(true);
        user.setPassword("password");
        user.setUsername("testUser");
        user.setId(1L);
        Set<Advice> adviceSet = new HashSet<>();
        adviceSet.add(advice);
        user.setSharedAdvices(adviceSet);
        user.setLikedAdvices(adviceSet);
        user.setUserNick("testUserNick");
        user.setUserScore(100);
        Set<Training> trainingSet = new HashSet<>();
        trainingSet.add(training);
        user.setCompleteTrainings(trainingSet);

        ResponseEntity<UserModel> userResponseEntity = new ResponseEntity<>(
                userRepresentationModelAssembler.toModel(user),
                header,
                HttpStatus.OK
        );

        ResponseEntity<CollectionModel<AdviceModel>> userLikedAdvicesResponseEntity = new ResponseEntity<>(
                adviceRepresentationModelAssembler.toCollectionModel(adviceSet),
                header,
                HttpStatus.OK
        );

        when(userController.getLoggedUserInfo(any())).thenReturn(userResponseEntity);
        when(userController.getUserLikedAdvices(any())).thenReturn(userLikedAdvicesResponseEntity);
    }

    @Test
    @WithMockUser
    public void shouldReturnUserInfoIfUserIsLoggedIn() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userNick", is("testUserNick")))
                .andExpect(jsonPath("$.userScore", is(100)))
                .andExpect(jsonPath("$._links.self.href", is("/users")));
    }

    @Test
    public void shouldReturn401StatusWhenUserIsUnauthorized() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void shouldReturnCollectionWithUserLikedAdvices() throws Exception {
//        {"_embedded":{"advices":[{"adviceTitle":"testAdviceTitle","content":"testAdviceContent","likes":1,
//         "shares":1,"topic":{"topicDescription":"testTopicDescription","_links":
//         {"Topic by topic title":{"href":"/topics/testTopicDescription"},"All topics":{"href":"/topics"}}},
//         "training":{},"_links":{"self":{"href":"/advices/testAdviceTitle"},"adviceTraining":
//         {"href":"/advices/1/training"},"adviceTopic":{"href":"/advices/1/topic"}}}]},"
//         _links":{"self":{"href":"/advices"}}}
        mvc.perform(get("/users/likedAdvices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$._embedded.advices[0].adviceTitle", is("testAdviceTitle")))
                .andExpect(jsonPath(
                        "$._embedded.advices[0].topic.topicDescription", is("testTopicDescription")))

        ;
    }
}