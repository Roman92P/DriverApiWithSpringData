package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.AdviceModel;
import com.pashkov.driverapi.app.model.AdviceRepresentationModelAssembler;
import com.pashkov.driverapi.app.model.TopicRepresentationModelAssembler;
import com.pashkov.driverapi.app.service.AdviceService;
import com.pashkov.driverapi.app.service.TopicService;
import com.pashkov.driverapi.app.service.TrainingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<AdviceModel> getRandomAdvice() {
        return adviceService.getRandomAdvice().map(
                adviceRepresentationModelAssembler::toModel)
                .map(adviceModel -> adviceModel
                        .add(
                                linkTo(methodOn(AdviceController.class)
                                        .getIncompletedAdviceWithTraining())
                                        .withRel("incompleteAdvicesWithTraining"),
                                linkTo(methodOn(HomeResource.class).getRandomAdvice()).withSelfRel()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
