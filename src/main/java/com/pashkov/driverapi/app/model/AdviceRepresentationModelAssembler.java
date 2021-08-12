package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.AdviceController;
import com.pashkov.driverapi.app.resource.TopicController;
import com.pashkov.driverapi.app.resource.TrainingController;
import com.pashkov.driverapi.app.util.QuestionSetToModelListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AdviceRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Advice, AdviceModel> {

    @Autowired
    private QuestionSetToModelListUtil questionSetToModelListUtil;

    @Autowired
    private QuestionRepresentationModelAssembler questionRepresentationModelAssembler;

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
        adviceModel.add(
                linkTo(methodOn(AdviceController.class).getAdviceTraining(entity.getId())).withRel("adviceTraining"),
                linkTo(methodOn(AdviceController.class).getAdviceTopic(entity.getId())).withRel("adviceTopic")
        );
        return adviceModel;
    }

    private TrainingModel trainingToModel(Training training) {
        if (training == null) {
            return new TrainingModel();
        }
        return TrainingModel.builder()
                .trainingTitle(training.getTrainingTitle())
                .questionModels(questionSetToModelListUtil.convertQuestionCollectionToQustionModels(training.getQuestions()))
                .build()
                .add(linkTo(methodOn(TrainingController.class).getTrainingByTitle(training.getTrainingTitle()))
                        .withRel("trainingByName"))
                .add(linkTo(methodOn(TrainingController.class).getAllTrainingsModel())
                        .withRel("getAlltrainings"));
    }

    private TopicModel topicToModel(Topic topic) {
        if (topic == null) {
            return new TopicModel();
        }
        return TopicModel.builder()
                .topicDescription(topic.getTopicDescription())
                .build()
                .add(linkTo(methodOn(TopicController.class)
                                .getTopicRepresentationByTitle(topic.getTopicDescription()))
                                .withRel("Topic by topic title"),
                        linkTo(methodOn(TopicController.class)
                                .returnAllExistingTopics())
                                .withRel("All topics"));

    }


    @Override
    public CollectionModel<AdviceModel> toCollectionModel(Iterable<? extends Advice> entities) {
        CollectionModel<AdviceModel> adviceModels = super.toCollectionModel(entities);
        adviceModels.add(
                linkTo(methodOn(AdviceController.class).getAllAdvices()).withSelfRel());
        return adviceModels;
    }
}
