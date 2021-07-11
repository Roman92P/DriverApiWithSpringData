package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.model.Topic;
import com.pashkov.driverapi.app.service.AdviceService;
import com.pashkov.driverapi.app.service.TopicService;
import com.pashkov.driverapi.app.service.TrainingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Api(description = "Resource for Home page. Returns one Advice, list of all topics, not completed advice with training")
@RequestMapping(path = "/home")
public class HomeResource {

    private final Logger logger = LoggerFactory.getLogger(HomeResource.class);

    private final TopicService topicService;

    private final AdviceService adviceService;

    private final TrainingService trainingService;


    public HomeResource(TopicService topicService, AdviceService adviceService, TrainingService trainingService) {
        this.topicService = topicService;
        this.adviceService = adviceService;
        this.trainingService = trainingService;
    }

//    @GetMapping("/admin")
//    @ResponseBody
//    public String admin2(@AuthenticationPrincipal UserDetails customUser) {
//        return "this is user " + customUser;
//    }

    //@ApiIgnore
    @GetMapping(value = "/incompleteAdviceWithTraining", produces = "application/json")
    public CollectionModel<EntityModel<Advice>> getIncompletedAdviceWithTraining() {
        List<EntityModel<Advice>> list = adviceService.getAll()
                .stream().map(advice ->
                        EntityModel.of(advice,
                                linkTo(methodOn(HomeResource.class)
                                        .getIncompletedAdviceWithTraining())
                                        .withRel("Incomplete advices"),
                                linkTo(methodOn(AdviceController.class)
                                        .getAdviceWithTitle(advice.getAdviceTitle()))
                                        .withRel("Advice by title"),
                                linkTo(methodOn(AdviceController.class).getAllAdvices())
                                        .withRel("All advices")))
                .collect(Collectors.toList());


        return CollectionModel.of(list,
                linkTo(methodOn(HomeResource.class)
                        .getIncompletedAdviceWithTraining())
                        .withSelfRel());
    }


    @GetMapping(value = "/getAdviceByTitle", produces = "application/json")
    private EntityModel<Advice> getAdvice(@RequestParam(name = "id") Long id) {
        Optional<Advice> advice = adviceService.getAdviceById(id);
        return EntityModel.of(advice.get());
    }

    @ApiIgnore
    @GetMapping(value = "/topics", produces = "application/json")
    public Set<Topic> getAllTopics() {
        return topicService.getAllTopic();
    }

    @ApiOperation(value = "Get home page with random advice")
    @GetMapping(produces = "application/hal+json")
    public EntityModel getRandomAdvice() {
        Advice randomAdvice = adviceService.getRandomAdvice();
        return EntityModel.of(randomAdvice
                , linkTo(methodOn(HomeResource.class).getRandomAdvice()).withSelfRel()
                , linkTo(methodOn(HomeResource.class).getAllTopics()).withRel("All topics")
                , linkTo(methodOn(HomeResource.class).getIncompletedAdviceWithTraining()).withRel("Incomplete Advices with trainings"));
    }


}
