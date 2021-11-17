package com.pashkov.driverapi.app.resource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pashkov.driverapi.app.DTOs.AdviceForLikeDTO;
import com.pashkov.driverapi.app.model.UserDetail;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerAcceptanceTest {

    @Autowired
    UserDetail userDetail;

    @Autowired
    MockMvc mvc;

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnLikedUserAdviceCollection() throws Exception {
        MockHttpServletRequestBuilder request
                = MockMvcRequestBuilders.get("/users/likedAdvices");

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect
                        (MockMvcResultMatchers.jsonPath("$._embedded.advices[0].adviceTitle",
                                is("Title3")))
                .andExpect
                        (MockMvcResultMatchers.jsonPath("$._embedded.advices[0].likes",
                                is(0)))
                .andExpect
                        (MockMvcResultMatchers.jsonPath("$._embedded.advices[0].shares",
                                is(0)));
    }

    @Test
    @WithUserDetails("RomanTrzy")
    public void shouldThrowNotFoundResourceIfUserDontHaveLikedAdvices() throws Exception {
        MockHttpServletRequestBuilder request
                = MockMvcRequestBuilders.get("/users/likedAdvices");

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnCorrectInfoOfLoggedUser() throws Exception {
        MockHttpServletRequestBuilder request
                = MockMvcRequestBuilders.get("/users");
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.jsonPath("$.userNick",
                        is("ADMIN1")));
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.jsonPath("$.userScore",
                        is(43)));
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnNotFoundStatusIfCollectionOfLoggedUserSharedAdvicesIsEmpty() throws Exception {
        MockHttpServletRequestBuilder request
                = MockMvcRequestBuilders.get("/users/sharedAdvices");
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithUserDetails("RomanDwa")
    public void shouldReturnCollectionOfCompleteUserTrainings() throws Exception {
        MockHttpServletRequestBuilder request
                = MockMvcRequestBuilders.get("/driver_api/users/completedTrainings");
        request = request.contextPath("/driver_api");
        MockHttpServletResponse response = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        response.setCharacterEncoding("UTF-8");
        JsonObject jsonObject = new Gson().fromJson(response.getContentAsString(), JsonObject.class);
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        JsonObject asJsonObject = new JsonObject();
        for ( Map.Entry<String, JsonElement> map : entries ) {
            JsonElement value = map.getValue();
            if (value.getAsJsonObject().has("trainings")) {
                asJsonObject = value.getAsJsonObject();
            }
        }
        JsonElement trainings = asJsonObject.get("trainings");
        JsonArray asJsonArray = trainings.getAsJsonArray();
        assertThat(asJsonArray.size()).isEqualTo(2);
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnCreatedStatusWhenUserPostLikeAdvice() throws Exception {
        AdviceForLikeDTO adviceForLikeDTO = new AdviceForLikeDTO();
        adviceForLikeDTO.setAdviceTitle("Tytuł");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(adviceForLikeDTO);

        MockHttpServletRequestBuilder postRequest
                = MockMvcRequestBuilders.post("/driver_api/users/likedAdvices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);
        postRequest = postRequest.contextPath("/driver_api");

        mvc.perform(postRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnCreatedStatusIfUserPostSharedAdvice() throws Exception {
        AdviceForLikeDTO adviceForLikeDTO = new AdviceForLikeDTO();
        adviceForLikeDTO.setAdviceTitle("Tytuł");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(adviceForLikeDTO);

        MockHttpServletRequestBuilder postRequest
                = MockMvcRequestBuilders.post("/driver_api/users/sharedAdvices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);
        postRequest = postRequest.contextPath("/driver_api");

        mvc.perform(postRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnFoundStatusIfFoundUserScore() throws Exception{
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/score");
        mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    @WithUserDetails("Roman")
    public void shouldReturnCorrectContentType() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/score");
        mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE));
    }

    @Test@WithUserDetails("Roman")
    public void shouldReturnResponseWithUserRoleWithCorrectMediaType() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/role");
        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE));
    }
}
