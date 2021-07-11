package com.pashkov.driverapi.app.projection;

import com.pashkov.driverapi.app.model.Question;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "Basic question", types = Question.class)
public interface QuestionProjection {
    String getQuestionContent();
}
