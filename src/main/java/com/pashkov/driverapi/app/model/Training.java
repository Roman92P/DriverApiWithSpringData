package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Training implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final int SCORE_TO_COMPLETE = 3;

    private String trainingTitle;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Question> Questions;

    @ManyToOne
    private Topic topic;

    @JsonBackReference
    @OneToOne(mappedBy = "training")
    private Advice advice;

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingTitle='" + trainingTitle + '\'' +
                ", Questions=" + Questions +
                ", topic=" + topic +
                ", advice=" + advice +
                '}';
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
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

    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
