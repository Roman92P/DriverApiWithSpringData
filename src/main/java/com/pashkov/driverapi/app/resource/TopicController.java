package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Topic;
import com.pashkov.driverapi.app.model.TopicModel;
import com.pashkov.driverapi.app.model.TopicRepresentationModelAssembler;
import com.pashkov.driverapi.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicRepresentationModelAssembler topicRepresentationModelAssembler;

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CollectionModel<TopicModel>> returnAllExistingTopics(){
        Set<Topic> allTopic = topicService.getAllTopic();
        return new ResponseEntity<>(
                topicRepresentationModelAssembler.toCollectionModel(allTopic), HttpStatus.OK);
    }

    @GetMapping(value = "/{title}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TopicModel>getTopicRepresentationByTitle(@PathVariable String title){
        return topicService.getTopicByName(title)
                .map(topicRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
