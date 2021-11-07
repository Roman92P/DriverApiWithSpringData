package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "Advice")
@Relation(collectionRelation = "advices")
@JsonInclude(Include.NON_NULL)
public class AdviceModel extends RepresentationModel<AdviceModel> {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("adviceTitle")
    private String adviceTitle;
    @JsonProperty("content")
    private String content;
    @JsonProperty("likes")
    private int likes;
    @JsonProperty("shares")
    private int shares;
    @JsonProperty("topic")
    private TopicModel topic;
    @JsonProperty("training")
    private TrainingModel training;

}
