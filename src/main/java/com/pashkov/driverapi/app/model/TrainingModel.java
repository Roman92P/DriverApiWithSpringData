package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "Training")
@Relation(collectionRelation = "trainings")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainingModel extends RepresentationModel<TrainingModel> {
    private String trainingTitle;
    private TopicModel topicModel;
    //private List<QuestionModel> questionModels;
}
