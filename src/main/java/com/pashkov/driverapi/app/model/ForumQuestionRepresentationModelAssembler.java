package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.ForumQuestionController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
        forumQuestionModel.add(
                linkTo(methodOn(ForumQuestionController.class)
                        .getForumQuestionByid(entity.getId()))
                        .withSelfRel(),
                linkTo(methodOn(ForumQuestionController.class)
                        .deleteForumQuestion(entity.getId()))
                        .withRel("deleteForumQuestion"),
                linkTo(methodOn(ForumQuestionController.class)
                        .editForumQuestion(null,entity.getId()))
                        .withRel("updateForumQuestion"),
                linkTo(methodOn(ForumQuestionController.class)
                        .getAllForumQuestions())
                        .withRel("getAllForumQuestions"));
        return forumQuestionModel;
    }
}
