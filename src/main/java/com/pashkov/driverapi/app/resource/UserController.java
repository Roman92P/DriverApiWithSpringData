package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    private final UserRepresentationModelAssembler userRepresentationModelAssembler;

    @Autowired
    private AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;

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

    @GetMapping(path = "/{id}/likedAdvices", produces = "application/json")
    public ResponseEntity<CollectionModel<AdviceModel>> getUserLikedAdvices(@PathVariable long id, Authentication authentication){
        Optional<User> userByid = userService.findUserByid(id);
        if(userByid.isEmpty()){
            throw new ResourceNotFoundException();
        }
        if(authentication==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(userService.findByUserName(authentication.getName()).getId() != id){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        System.out.println(authentication.getName());
        User user = userService.getLikedAdvices(id).get();
        Set<Advice> likedAdvices = user.getLikedAdvices();
        return new ResponseEntity<>(adviceRepresentationModelAssembler.toCollectionModel(likedAdvices), HttpStatus.OK);
    }
}
