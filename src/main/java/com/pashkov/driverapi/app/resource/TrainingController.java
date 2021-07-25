package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Training;
import com.pashkov.driverapi.app.model.TrainingModel;
import com.pashkov.driverapi.app.model.TrainingRepresentationModelAssembler;
import com.pashkov.driverapi.app.service.TrainingService;
import com.pashkov.driverapi.app.util.TrainingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/trainings")
public class TrainingController {

    @Autowired
    private TrainingUtil trainingUtil;

    private final TrainingService trainingService;

    @Autowired
    private TrainingRepresentationModelAssembler trainingRepresentationModelAssembler;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
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

    @GetMapping(value = "/userCompleteTraining/{id}", produces = "application/json")
    public ResponseEntity<TrainingModel> getUserCompleteTraining(@PathVariable long id){
        Set<Training> trainings = trainingService.completeUserTrainings(id);
        return new ResponseEntity(
                trainingRepresentationModelAssembler.toCollectionModel(trainings), HttpStatus.OK);
    }

    @GetMapping(value = "/userIncompleteTraining/{id}", produces = "application/json")
    public ResponseEntity<TrainingModel> getUserIncompleteTraining(@PathVariable long id){
        Set<Training> trainings = trainingService.completeUserTrainings(id);
        List<Training> allTrainings = trainingService.getAllTrainings();
        Set<Training> notCompletedTrainings = trainingUtil.findNotCompletedTrainings(trainings, allTrainings);
        return new ResponseEntity(
                trainingRepresentationModelAssembler.toCollectionModel(notCompletedTrainings), HttpStatus.OK);
    }

}
