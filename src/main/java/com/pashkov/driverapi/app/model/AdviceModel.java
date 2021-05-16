package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@ApiModel(description = "Driver advice entity")
public class AdviceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adviceTitle;

    @Lob
    @Column(length=1000)
    @ApiModelProperty(notes = "Driver advice content")
    private String content;

    @ManyToOne
    @ApiModelProperty(notes = "Tag name of Driver Advice")
    private TopicModel topic;

    public AdviceModel() {
    }
    public String getAdviceTitle() {
        return adviceTitle;
    }

    public void setAdviceTitle(String adviceTitle) {
        this.adviceTitle = adviceTitle;
    }
    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String description) {
        this.content = description;
    }

    public TopicModel getTopic() {
        return topic;
    }

    public void setTopic(TopicModel topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "AdviceModel{" +
                "id=" + id +
                ", adviceTitle='" + adviceTitle + '\'' +
                ", content='" + content + '\'' +
                ", topic=" + topic +
                '}';
    }

}
