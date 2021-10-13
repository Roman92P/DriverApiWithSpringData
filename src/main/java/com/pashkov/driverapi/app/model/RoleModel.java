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
@JsonRootName(value = "Role")
@Relation(collectionRelation = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleModel extends RepresentationModel<RoleModel> {

    private String role;
}
