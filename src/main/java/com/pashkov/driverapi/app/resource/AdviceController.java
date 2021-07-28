package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.model.AdviceModel;
import com.pashkov.driverapi.app.model.AdviceRepresentationModelAssembler;
import com.pashkov.driverapi.app.model.Training;
import com.pashkov.driverapi.app.service.AdviceService;
import com.pashkov.driverapi.app.service.TrainingService;
import com.pashkov.driverapi.app.service.UserService;
import com.pashkov.driverapi.app.util.TrainingUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private final AdviceService adviceService;
    private final TrainingService trainingService;
    private final UserService userService;
    private final TrainingUtil trainingUtil;

    public AdviceController(AdviceService adviceService, TrainingService trainingService, UserService userService, TrainingUtil trainingUtil) {
        this.adviceService = adviceService;
        this.trainingService = trainingService;
        this.userService = userService;
        this.trainingUtil = trainingUtil;
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

    @GetMapping(path = "/uncompleted", produces = "application/json")
    public ResponseEntity<CollectionModel<AdviceModel>> getUserIncompletedAdvices(Authentication authentication) {
        List<Advice> allAdvices = adviceService.getAll();
        Set<Training> notCompletedTrainings = trainingUtil.findNotCompletedTrainings
                (trainingService.completeUserTrainings(userService.findByUserName(authentication.getName()).getId()), trainingService.getAllTrainings());
        Set<Advice> resultSet = new HashSet<>();
        for ( Advice advice : allAdvices ) {
            if (advice.getTraining() != null) {
                if (notCompletedTrainings.stream().anyMatch(training -> training.getTrainingTitle()
                        .equals(advice.getTraining().getTrainingTitle()))) {
                    resultSet.add(advice);
                }
            }
        }
        return new ResponseEntity<>(
                adviceRepresentationModelAssembler.toCollectionModel(resultSet), HttpStatus.OK);
    }

    @ApiOperation(value = "Get random advice for home page")
    @GetMapping(path = "/randomAdvice", produces = "application/hal+json")
    public ResponseEntity<AdviceModel> getRandomAdvice(Authentication authentication) {
        return adviceService.getRandomAdvice().map(
                adviceRepresentationModelAssembler::toModel)
                .map(adviceModel -> adviceModel
                        .add(linkTo(methodOn(AdviceController.class).getRandomAdvice(authentication)).withSelfRel(),
                                linkTo(methodOn(AdviceController.class).getUserIncompletedAdvices(authentication))
                                        .withRel("userIncompleteAdvices")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
