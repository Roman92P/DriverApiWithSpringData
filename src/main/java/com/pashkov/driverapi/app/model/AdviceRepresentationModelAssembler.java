package com.pashkov.driverapi.app.model;

import com.pashkov.driverapi.app.resource.AdviceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AdviceRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Advice, AdviceModel> {

    @Autowired
    TopicRepresentationModelAssembler topicRepresentationModelAssembler;

    public AdviceRepresentationModelAssembler() {
        super(AdviceController.class, AdviceModel.class);
    }

    @Override
    public AdviceModel toModel(Advice entity) {
        AdviceModel adviceModel = instantiateModel(entity);

        adviceModel.add(linkTo(
                methodOn(AdviceController.class)
                        .getAdviceWithTitle(entity.getAdviceTitle()))
                .withSelfRel());
        adviceModel.setAdviceTitle(entity.getAdviceTitle());
        adviceModel.setContent(entity.getContent());
        adviceModel.setLikes(entity.getLikesCount());
        adviceModel.setShares(entity.getSharesCount());

        TopicModel topicModel = topicRepresentationModelAssembler.toModel(entity.getTopic());
        adviceModel.setTopic(topicModel);

        return adviceModel;
    }



}
