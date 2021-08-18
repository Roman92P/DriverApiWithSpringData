package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.AdviceService;
import com.pashkov.driverapi.app.service.TopicService;
import com.pashkov.driverapi.app.service.TrainingService;
import com.pashkov.driverapi.app.util.TrainingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/trainings")
public class TrainingController {

    @Autowired
    private TrainingUtil trainingUtil;

    private final TrainingService trainingService;
    private final TopicService topicService;
    private final AdviceService adviceService;

    @Autowired
    private TrainingRepresentationModelAssembler trainingRepresentationModelAssembler;

    @Autowired
    private TopicRepresentationModelAssembler topicRepresentationModelAssembler;

    @Autowired
    private AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;

    public TrainingController(TrainingService trainingService, TopicService topicService, AdviceService adviceService) {
        this.trainingService = trainingService;
        this.topicService = topicService;
        this.adviceService = adviceService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CollectionModel<TrainingModel>> getAllTrainingsModel(){
        List<Training> allTrainings = trainingService.getAllTrainings();
        return new ResponseEntity<>(
                trainingRepresentationModelAssembler.toCollectionModel(allTrainings), HttpStatus.OK);
    }

    @GetMapping(value = "/{title}", produces = "application/json")
    public ResponseEntity<TrainingModel> getTrainingByTitle(@PathVariable String title){
        return trainingService.getTrainingByTitle(title)
                .map(trainingRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/topic", produces = "application/json")
    public ResponseEntity<TopicModel> getTrainingTopic(@PathVariable long id){
        Optional<Training> trainingById = trainingService.getTrainingById(id);
        if(trainingById.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Topic topic = trainingById.get().getTopic();
        return new ResponseEntity<>(
                topicRepresentationModelAssembler.toModel(topic),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/advice", produces = "application/json")
    public ResponseEntity<AdviceModel> getTrainingAdvice(@PathVariable long id){
        Optional<Training> trainingById = trainingService.getTrainingById(id);
        if(trainingById.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Advice advice = trainingById.get().getAdvice();
        return new ResponseEntity<>(adviceRepresentationModelAssembler.toModel(advice),
                HttpStatus.OK);
    }

}
