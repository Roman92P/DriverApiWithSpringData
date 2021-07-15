package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@ApiModel(description = "Driver advice entity")
public class Advice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adviceTitle;

    @Lob
    @Column(length=1000)
    @ApiModelProperty(notes = "Driver advice content")
    private String content;

    //@JsonBackReference and @JsonManagedReference to avoid stackoverflow recurcion advice->training and again and again
    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    private Training training;

    @ManyToOne
    @ApiModelProperty(notes = "Tag name of Driver Advice")
    private Topic topic;

    @ColumnDefault("0")
    @Column(name = "likes")
    private int likesCount;

    @ColumnDefault("0")
    @Column(name = "shares")
    private int sharesCount;

    public Advice() {
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
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
                ", training=" + training +
                ", topic=" + topic +
                ", likesCount=" + likesCount +
                ", sharesCount=" + sharesCount +
                '}';
    }
}
