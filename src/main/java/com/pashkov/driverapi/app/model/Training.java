package com.pashkov.driverapi.app.model;

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

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingTitle='" + trainingTitle + '\'' +
                ", Questions=" + Questions +
                ", topic=" + topic +
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

    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
