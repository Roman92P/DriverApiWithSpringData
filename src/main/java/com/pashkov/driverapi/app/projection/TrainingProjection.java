package com.pashkov.driverapi.app.projection;

import com.pashkov.driverapi.app.model.Question;
import com.pashkov.driverapi.app.model.Training;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "Basic Training", types = Training.class)
public interface TrainingProjection {
    String getTrainingTitle();
    List<Question>getQuestions();

}
