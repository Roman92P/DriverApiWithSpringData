package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Question;

import java.util.Optional;

public interface QuestionService {

    Optional<Question> getQuestionByTopicName(String topicName);

}
