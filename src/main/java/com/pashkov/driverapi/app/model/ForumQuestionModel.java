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
@JsonRootName(value = "forumQuestion")
@Relation(collectionRelation = "forumQuestions")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForumQuestionModel extends RepresentationModel<ForumQuestionModel> {

    private String text;
}
