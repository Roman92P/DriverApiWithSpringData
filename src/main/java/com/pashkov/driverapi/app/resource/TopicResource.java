package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Topic;
import com.pashkov.driverapi.app.service.TopicService;
import io.swagger.annotations.Api;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Api(description = "This is basic Topic entity resource")
@RequestMapping("/topics")
public class TopicResource {

    private final TopicService topicService;

    public TopicResource(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(path = "/{topicName}",produces = "application/hal+json")
    @ResponseBody
    public EntityModel<Topic> getTopicByTopicName(@PathVariable String topicName){
        if(topicService.getTopicByName(topicName).isEmpty()){
            throw new ResourceNotFoundException();
        }
        return EntityModel.of(topicService.getTopicByName(topicName).get(),
                linkTo(methodOn(TopicResource.class).getTopicByTopicName(topicName)).withSelfRel());
    }

}
