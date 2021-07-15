package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.QuestionController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class QuestionRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Question, QuestionModel> {


    public QuestionRepresentationModelAssembler() {
        super(QuestionController.class, QuestionModel.class);
    }

    @Override
    public QuestionModel toModel(Question entity) {
        QuestionModel questionModel = instantiateModel(entity);
        questionModel.setQuestionContent(entity.getQuestionContent());
        questionModel.setPossibleAnswerOne(entity.getPossibleAnswerOne());
        questionModel.setPossibleAnswerTwo(entity.getPossibleAnswerTwo());
        questionModel.setPossibleAnswerThree(entity.getPossibleAnswerThree());
        return questionModel;
    }
}
