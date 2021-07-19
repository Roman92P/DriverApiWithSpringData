package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.ForumQuestionController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ForumQuestionRepresentationModelAssembler
        extends RepresentationModelAssemblerSupport<ForumQuestion, ForumQuestionModel> {

    public ForumQuestionRepresentationModelAssembler() {
        super(ForumQuestionController.class, ForumQuestionModel.class);
    }

    @Override
    public ForumQuestionModel toModel(ForumQuestion entity) {
        ForumQuestionModel forumQuestionModel = instantiateModel(entity);
        forumQuestionModel.setText(entity.getText());
        return forumQuestionModel;
    }

}
