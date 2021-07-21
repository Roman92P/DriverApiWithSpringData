package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.AdviceService;
import com.pashkov.driverapi.app.service.TopicService;
import com.pashkov.driverapi.app.service.TrainingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Api(description = "Resource for Home page. Returns one Advice, list of all topics, not completed advice with training")
@RequestMapping(path = "/home")
public class HomeResource {

    private final Logger logger = LoggerFactory.getLogger(HomeResource.class);

    private final TopicService topicService;

    private final AdviceService adviceService;

    private final TrainingService trainingService;

    @Autowired
    TopicRepresentationModelAssembler topicRepresentationModelAssembler;

    @Autowired
    AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;


    public HomeResource(TopicService topicService, AdviceService adviceService, TrainingService trainingService) {
        this.topicService = topicService;
        this.adviceService = adviceService;
        this.trainingService = trainingService;
    }

    @ApiOperation(value = "Get home page with random advice")
    @GetMapping(produces = "application/hal+json")
    public EntityModel<AdviceModel> getRandomAdvice() {
        Advice randomAdvice = adviceService.getRandomAdvice();
        return EntityModel.of(adviceRepresentationModelAssembler.toModel(randomAdvice)
                , linkTo(methodOn(HomeResource.class).getRandomAdvice()).withSelfRel()
                , linkTo(methodOn(AdviceController.class).getIncompletedAdviceWithTraining()).withRel("incompleteAdvicesWithTraining"));
    }


}
