package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.RoleController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<Role, RoleModel> {

    public RoleRepresentationModelAssembler() {
        super(RoleController.class, RoleModel.class);
    }

    @Override
    public RoleModel toModel(Role entity) {
        RoleModel roleModel = new RoleModel();
        roleModel.setRole(entity.getName());
        roleModel.add(linkTo(methodOn(RoleController.class).getAllPossibleRoles()).withRel("Get all roles"));
        return roleModel;
    }
}
