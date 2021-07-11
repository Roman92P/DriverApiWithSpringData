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
@JsonRootName(value = "Advice")
@Relation(collectionRelation = "advices")
@JsonInclude(Include.NON_NULL)
public class AdviceModel extends RepresentationModel<AdviceModel> {

    private Long id;
    private String adviceTitle;
    private String content;
    private int likes;
    private int shares;
    private Topic topic;
    private Training training;

}
