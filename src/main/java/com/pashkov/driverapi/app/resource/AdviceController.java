package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.AdviceService;
import com.pashkov.driverapi.app.service.TopicService;
import com.pashkov.driverapi.app.service.TrainingService;
import com.pashkov.driverapi.app.service.UserService;
import com.pashkov.driverapi.app.util.TrainingUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/advices")
public class AdviceController {

    @Autowired
    private AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;

    @Autowired
    private TrainingRepresentationModelAssembler trainingRepresentationModelAssembler;

    @Autowired
    private TopicRepresentationModelAssembler topicRepresentationModelAssembler;

    private final AdviceService adviceService;
    private final TrainingService trainingService;
    private final UserService userService;
    private final TrainingUtil trainingUtil;
    private final TopicService topicService;

    public AdviceController(AdviceService adviceService, TrainingService trainingService, UserService userService, TrainingUtil trainingUtil, TopicService topicService) {
        this.adviceService = adviceService;
        this.trainingService = trainingService;
        this.userService = userService;
        this.trainingUtil = trainingUtil;
        this.topicService = topicService;
    }

    @GetMapping(path = "/{title}", produces = "application/json")
    public ResponseEntity<AdviceModel> getAdviceWithTitle(@PathVariable String title) {
        return adviceService.getAdviceByTitle(title)
                .map(adviceRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CollectionModel<AdviceModel>> getAllAdvices() {
        List<Advice> all = adviceService.getAll();
        return new ResponseEntity<>(
                adviceRepresentationModelAssembler.toCollectionModel(all), HttpStatus.OK);
    }

    @ApiOperation(value = "Get random advice for home page")
    @GetMapping(path = "/randomAdvice", produces = "application/hal+json")
    public ResponseEntity<AdviceModel> getRandomAdvice(Authentication authentication) {
        return adviceService.getRandomAdvice().map(
                adviceRepresentationModelAssembler::toModel)
                .map(adviceModel -> adviceModel
                                .add(
                                        linkTo(methodOn(AdviceController.class).getRandomAdvice(authentication)).withSelfRel(),
                                        linkTo(methodOn(UserController.class).getUserIncompletedAdvices(authentication)).withRel("logedUserUncopleteAdviceTraining"))
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/training", produces = "application/json")
    public ResponseEntity<TrainingModel> getAdviceTraining(@PathVariable long id) {
        Advice advice = adviceService.getAdviceById(id).orElseThrow(ResourceNotFoundException::new);
        Training training = advice.getTraining();
        return new ResponseEntity<>(
                trainingRepresentationModelAssembler.toModel(training), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/topic", produces = "application/json")
    public ResponseEntity<TopicModel> getAdviceTopic(@PathVariable long id) {
        return topicService.getTopicById(id)
                .map(topicRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/adviceWithTraining")
    public ResponseEntity<AdviceModel> getadviceWithTraining(Authentication authentication){
        return null;
    }
}
