package com.pashkov.driverapi.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonRootName(value = "Topic")
@Relation(collectionRelation = "topics")
@JsonInclude(Include.NON_NULL)
public class TopicModel extends RepresentationModel<TopicModel> {

    private String topicDescription;
}
