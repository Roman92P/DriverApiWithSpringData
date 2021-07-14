package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.repository.TrainingRepository;
import com.pashkov.driverapi.app.resource.TrainingController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class TrainingRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Training, TrainingModel> {


    public TrainingRepresentationModelAssembler() {
        super(TrainingController.class, TrainingModel.class);
    }

    @Override
    public TrainingModel toModel(Training entity) {
        TrainingModel trainingModel = instantiateModel(entity);
        trainingModel.setTrainingTitle(entity.getTrainingTitle());
        trainingModel.add(
                linkTo(methodOn(TrainingController.class).getAllTrainingsModel()).withRel("All trainings"),
                linkTo(methodOn(TrainingController.class).getTrainingByTitle(entity.getTrainingTitle())).withRel("Training by training title")
        );
        return trainingModel;
    }
}
