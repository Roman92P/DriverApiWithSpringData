package com.pashkov.driverapi.app.util;

import com.pashkov.driverapi.app.model.Question;
import com.pashkov.driverapi.app.model.QuestionModel;
import com.pashkov.driverapi.app.model.QuestionRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionSetToModelListUtil {

    @Autowired
    private QuestionRepresentationModelAssembler assembler;

    public List<QuestionModel> convertQuestionCollectionToQustionModels(Collection<Question> questionsCollection) {
        return questionsCollection.stream().map(o -> QuestionModel.builder()
                .questionContent(o.getQuestionContent())
                .possibleAnswerOne(o.getPossibleAnswerOne())
                .possibleAnswerTwo(o.getPossibleAnswerTwo())
                .possibleAnswerThree(o.getPossibleAnswerThree())
                .build())
                .collect(Collectors.toList());
    }
}
