package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.AdviceController;
import com.pashkov.driverapi.app.resource.TopicController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AdviceRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Advice, AdviceModel> {

    public AdviceRepresentationModelAssembler() {
        super(AdviceController.class, AdviceModel.class);
    }

    @Override
    public AdviceModel toModel(Advice entity) {
        AdviceModel adviceModel = instantiateModel(entity);
        adviceModel.add(linkTo(
                methodOn(AdviceController.class)
                        .getAdviceWithTitle(entity.getAdviceTitle()))
                .withSelfRel());
        adviceModel.setAdviceTitle(entity.getAdviceTitle());
        adviceModel.setContent(entity.getContent());
        adviceModel.setLikes(entity.getLikesCount());
        adviceModel.setShares(entity.getSharesCount());
        TopicModel topicModel = topicToModel(entity.getTopic());
        adviceModel.setTopic(topicModel);
        Training training = entity.getTraining();
        TrainingModel trainingModel = trainingToModel(training);
        adviceModel.setTraining(trainingModel);
        return adviceModel;
    }

    private TrainingModel trainingToModel(Training training) {
        if(training==null){
            return new TrainingModel();
        }
        return TrainingModel.builder().trainingTitle(training.getTrainingTitle()).build();
    }

    private TopicModel topicToModel(Topic topic) {
        if(topic == null){
            return new TopicModel();
        }
        return TopicModel.builder()
                .topicDescription(topic.getTopicDescription())
                .build()
                .add(linkTo(methodOn(TopicController.class).getTopicRepresentationByTitle(topic.getTopicDescription())).withSelfRel());

    }


    @Override
    public CollectionModel<AdviceModel> toCollectionModel(Iterable<? extends Advice> entities) {
        CollectionModel<AdviceModel> adviceModels = super.toCollectionModel(entities);
        adviceModels.add(
                linkTo(methodOn(AdviceController.class).getAllAdvices()).withSelfRel());
        return  adviceModels;
    }
}
