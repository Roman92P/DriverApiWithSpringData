package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.model.AdviceModel;
import com.pashkov.driverapi.app.model.AdviceRepresentationModelAssembler;
import com.pashkov.driverapi.app.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/advice")
public class AdviceController {

    @Autowired
    private AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;

    private final AdviceService adviceService;

    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @GetMapping(value = "/{title}", produces = "application/json")
    public ResponseEntity <AdviceModel> getAdviceWithTitle(@PathVariable String title){
        return adviceService.getAdviceByTitle(title)
                .map(adviceRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/all",produces = "application/json")
    public ResponseEntity<CollectionModel<AdviceModel>> getAllAdvices(){
        List<Advice> all = adviceService.getAll();
        return new ResponseEntity<>(
                adviceRepresentationModelAssembler.toCollectionModel(all), HttpStatus.OK);
    }
    //@ApiIgnore
    @GetMapping(value = "/incompleteAdviceWithTraining", produces = "application/json")
    public CollectionModel<AdviceModel> getIncompletedAdviceWithTraining() {
        List<Advice> list = adviceService.getAll();
        return CollectionModel.of(adviceRepresentationModelAssembler.toCollectionModel(list),
                linkTo(methodOn(AdviceController.class)
                        .getIncompletedAdviceWithTraining())
                        .withSelfRel());
    }
}
