package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@ApiModel(description = "Driver advice entity")
public class Advice {
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
    private Topic topic;

    public Advice() {
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", adviceTitle='" + adviceTitle + '\'' +
                ", content='" + content + '\'' +
                ", topic=" + topic +
                '}';
    }

}
