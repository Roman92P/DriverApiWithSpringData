package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainingTitle;

    @ManyToMany
    private Set<Question> Questions;

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingTitle='" + trainingTitle + '\'' +
                ", Questions=" + Questions +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainingTitle() {
        return trainingTitle;
    }

    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }

    public Set<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(Set<Question> Questions) {
        this.Questions = Questions;
    }
}
