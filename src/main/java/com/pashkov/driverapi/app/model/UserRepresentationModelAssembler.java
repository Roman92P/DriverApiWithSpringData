package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.UserController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<User, UserModel> {

    public UserRepresentationModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User entity) {
        UserModel userModel = instantiateModel(entity);
        userModel.setUserNick(entity.getUserNick());
        userModel.setUserScore(entity.getUserScore());
        userModel.add(
                linkTo(methodOn(UserController.class).getLoggedUserInfo(null))
                .withSelfRel());
        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        CollectionModel<UserModel> userModels = super.toCollectionModel(entities);
        return userModels;
    }
}
