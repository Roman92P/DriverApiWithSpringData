package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Quiz;
import com.pashkov.driverapi.app.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizServiceImpl {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz saveQuiz(String name) {
        Quiz quiz = new Quiz();
        quiz.setName(name);
        quiz.setScoreToComplete(10);
        quizRepository.save(quiz);
        return quiz;
    }

}
