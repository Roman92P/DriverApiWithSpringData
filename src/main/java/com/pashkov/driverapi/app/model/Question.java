package com.pashkov.driverapi.app.model;

import javax.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionContent;

    private String correctAnswer;

    private String possibleAnswerOne;
    private String possibleAnswerTwo;
    private String possibleAnswerThree;

    private String score;

    @ManyToOne
    private Topic topic;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionContent='" + questionContent + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", possibleAnswerOne='" + possibleAnswerOne + '\'' +
                ", possibleAnswerTwo='" + possibleAnswerTwo + '\'' +
                ", possibleAnswerThree='" + possibleAnswerThree + '\'' +
                ", score='" + score + '\'' +
                ", topic=" + topic +
                '}';
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getPossibleAnswerOne() {
        return possibleAnswerOne;
    }

    public void setPossibleAnswerOne(String possibleAnswerOne) {
        this.possibleAnswerOne = possibleAnswerOne;
    }

    public String getPossibleAnswerTwo() {
        return possibleAnswerTwo;
    }

    public void setPossibleAnswerTwo(String possibleAnswerTwo) {
        this.possibleAnswerTwo = possibleAnswerTwo;
    }

    public String getPossibleAnswerThree() {
        return possibleAnswerThree;
    }

    public void setPossibleAnswerThree(String possibleAnswerThree) {
        this.possibleAnswerThree = possibleAnswerThree;
    }

    public Topic getTopicModel() {
        return topic;
    }

    public void setTopicModel(Topic topic) {
        this.topic = topic;
    }
}
