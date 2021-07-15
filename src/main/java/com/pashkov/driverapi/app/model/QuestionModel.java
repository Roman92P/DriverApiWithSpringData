package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "Question")
@Relation(collectionRelation = "questions")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionModel extends RepresentationModel<QuestionModel> {

    private String questionContent;
    private String possibleAnswerOne;
    private String possibleAnswerTwo;
    private String possibleAnswerThree;

}
