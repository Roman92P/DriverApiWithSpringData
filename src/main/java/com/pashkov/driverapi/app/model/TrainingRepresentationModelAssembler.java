package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.AdviceController;
import com.pashkov.driverapi.app.resource.TopicController;
import com.pashkov.driverapi.app.resource.TrainingController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
                linkTo(methodOn(TrainingController.class).getTrainingByTitle(entity.getTrainingTitle())).withRel("Training by training title"),
                linkTo(methodOn(TopicController.class).getTopicRepresentationByTitle(entity.getTopic().getTopicDescription()))
                        .withRel("Training topic"),
                linkTo(methodOn(AdviceController.class).getAdviceWithTitle(entity.getAdvice().getAdviceTitle())).withRel("Training advice")
        );
        Set<Question> questions = entity.getQuestions();
        trainingModel.setQuestionModels(questionsToModel(questions));
        return trainingModel;
    }

    private List<QuestionModel> questionsToModel(Set<Question> questions) {
        if (questions.isEmpty()) return Collections.emptyList();
        return questions.stream()
                .map(question -> QuestionModel.builder()
                        .questionContent(question.getQuestionContent())
                        .possibleAnswerOne(question.getPossibleAnswerOne())
                        .possibleAnswerTwo(question.getPossibleAnswerTwo())
                        .possibleAnswerThree(question.getPossibleAnswerThree())
                        .build())
                .collect(Collectors.toList());
    }
}
