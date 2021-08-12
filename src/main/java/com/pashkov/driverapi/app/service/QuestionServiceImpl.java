package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Question;
import com.pashkov.driverapi.app.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<Question> getQuestionByTopicName(String topicName) {
        return questionRepository.findByTopic_TopicDescription(topicName);
    }

    @Override
    public Optional<Question> getQuestionById(long id) {
        return questionRepository.findById(id);
    }
}
