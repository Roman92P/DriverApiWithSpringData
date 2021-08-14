package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    private final UserRepresentationModelAssembler userRepresentationModelAssembler;

    @Autowired
    private AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;

    @Autowired
    private TrainingRepresentationModelAssembler trainingRepresentationModelAssembler;

    public UserController(UserService userService, UserRepresentationModelAssembler userRepresentationModelAssembler) {
        this.userService = userService;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getLoggedUserInfo(Authentication authentication) {
        User byUserName = null;
        try {
            String name = authentication.getName();
            byUserName = userService.findByUserName(name);

        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return new ResponseEntity<>(
                userRepresentationModelAssembler
                        .toModel(byUserName), HttpStatus.OK);
    }

    @GetMapping(path = "/likedAdvices", produces = "application/json")
    public ResponseEntity<CollectionModel<AdviceModel>> getUserLikedAdvices(Authentication authentication){
        if(authentication==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = userService.getLikedAdvices(userService.findByUserName(authentication.getName()).getId()).get();
        Set<Advice> likedAdvices = user.getLikedAdvices();
        return new ResponseEntity<>(adviceRepresentationModelAssembler.toCollectionModel(likedAdvices), HttpStatus.OK);
    }

    @GetMapping(path = "/sharedAdvices", produces = "application/json")
    public ResponseEntity<CollectionModel<AdviceModel>> getUserSharedAdvices(Authentication authentication){
        if(authentication==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String name = authentication.getName();
        Long id = userService.findByUserName(name).getId();
        Optional<User> sharedAdvices = userService.getSharedAdvices(id);
        if(sharedAdvices.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Set<Advice> resultForSharedAdvices = sharedAdvices.get().getSharedAdvices();
        return new ResponseEntity<>(adviceRepresentationModelAssembler.toCollectionModel(resultForSharedAdvices), HttpStatus.OK);
    }

    @GetMapping(path = "/completedTrainings", produces = "application/json")
    public ResponseEntity<CollectionModel<TrainingModel>> getUserCompleteTrainings(Authentication authentication){
        if(authentication==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String name = authentication.getName();
        Long id = userService.findByUserName(name).getId();
        Optional<User> completeTrainings = userService.getUserCompleteTrainings(id);
        if(completeTrainings.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Set<Training> resultForCompleteTrainings = completeTrainings.get().getCompleteTrainings();
        return new ResponseEntity<>(trainingRepresentationModelAssembler.toCollectionModel(resultForCompleteTrainings), HttpStatus.OK);

    }
}
