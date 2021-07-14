package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.TopicController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TopicRepresentationModelAssembler
        extends RepresentationModelAssemblerSupport<Topic, TopicModel> {


    public TopicRepresentationModelAssembler() {
        super(TopicController.class, TopicModel.class);
    }

    @Override
    public TopicModel toModel(Topic entity) {
        TopicModel topicModel = instantiateModel(entity);
        topicModel.add(
                linkTo(methodOn(TopicController.class)
                .getTopicRepresentationByTitle(entity.getTopicDescription()))
                        .withSelfRel()
                ,linkTo(methodOn(TopicController.class)
                .returnAllExistingTopics())
                        .withRel("All topics"));
        topicModel.setTopicDescription(entity.getTopicDescription());
        return topicModel;
    }

    @Override
    public CollectionModel<TopicModel> toCollectionModel(Iterable<? extends Topic> entities) {
        CollectionModel<TopicModel> topicModels = super.toCollectionModel(entities);
        topicModels.add(linkTo(methodOn(TopicController.class).returnAllExistingTopics()).withSelfRel());
        return topicModels;
    }

}
