package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Quiz;

import java.util.Optional;

public interface QuizService {

    Optional<Quiz> getQuizByName(String quizName);

    Optional<Quiz> getQuizById(Long quizId);

    void saveQuiz(String name, int neededScore);
}
