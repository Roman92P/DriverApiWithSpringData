//package com.pashkov.driverapi.app.model;
//
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.LinkRelation;
//import org.springframework.hateoas.server.RepresentationModelProcessor;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
//
//public class AdviceProcessor implements RepresentationModelProcessor<EntityModel<Advice>> {
//
//    @Override
//    public EntityModel<Advice> process(EntityModel<Advice> model) {
//        model.add(
//                Link.of("/advice/{id}").withRel(LinkRelation.of("Advice by id")) //
//                        .expand(Objects.requireNonNull(model.getContent()).getId()));
//        return model;
//    }
//}
//
