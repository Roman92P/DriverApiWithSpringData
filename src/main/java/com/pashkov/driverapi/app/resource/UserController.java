package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.DTOs.AdviceForLikeDTO;
import com.pashkov.driverapi.app.config.IAuthenticationFacade;
import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.AdviceService;
import com.pashkov.driverapi.app.service.TrainingService;
import com.pashkov.driverapi.app.service.UserService;
import com.pashkov.driverapi.app.util.TrainingUtil;
import com.pashkov.driverapi.app.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final AdviceService adviceService;

    private final UserRepresentationModelAssembler userRepresentationModelAssembler;

    private final TrainingService trainingService;

    private final RoleRepresentationModelAssembler roleRepresentationModelAssembler;

    @Autowired
    TrainingUtil trainingUtil;

    @Autowired
    private AdviceRepresentationModelAssembler adviceRepresentationModelAssembler;

    @Autowired
    private TrainingRepresentationModelAssembler trainingRepresentationModelAssembler;

    public UserController(UserService userService, AdviceService adviceService, UserRepresentationModelAssembler userRepresentationModelAssembler, TrainingService trainingService, RoleRepresentationModelAssembler roleRepresentationModelAssembler) {
        this.userService = userService;
        this.adviceService = adviceService;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
        this.trainingService = trainingService;
        this.roleRepresentationModelAssembler = roleRepresentationModelAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserModel> getLoggedUserInfo(Authentication authentication) {
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

    @GetMapping(path = "/likedAdvices", produces = MediaTypes.HAL_JSON_VALUE)
    public @ResponseBody ResponseEntity<CollectionModel<AdviceModel>> getUserLikedAdvices(){
        Authentication authentication = authenticationFacade.getAuthentication();
        if(authentication==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<User> userOptional = userService.getLikedAdvices(userService.findByUserName(authentication.getName()).getId());
        if(userOptional.isEmpty()){
            throw  new ResourceNotFoundException();
        }
        Set<Advice> likedAdvices = userOptional.get().getLikedAdvices();
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

    @GetMapping(path = "/completedTrainings", produces = MediaTypes.HAL_JSON_VALUE)
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

    @GetMapping(path = "/uncompletedTraining", produces = "application/json")
    public ResponseEntity<CollectionModel<AdviceModel>> getUserIncompletedAdvices(Authentication authentication) {
        if(authentication==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Advice> allAdvices = adviceService.getAll();
        Set<Training> notCompletedTrainings = trainingUtil.findNotCompletedTrainings
                (trainingService.completeUserTrainings(userService.findByUserName(authentication.getName()).getId()), trainingService.getAllTrainings());
        Set<Advice> resultSet = new HashSet<>();
        for ( Advice advice : allAdvices ) {
            if (advice.getTraining() != null) {
                if (notCompletedTrainings.stream().anyMatch(training -> training.getTrainingTitle()
                        .equals(advice.getTraining().getTrainingTitle()))) {
                    resultSet.add(advice);
                }
            }
        }
        return new ResponseEntity<>(
                adviceRepresentationModelAssembler.toCollectionModel(resultSet), HttpStatus.OK);
    }

    @PostMapping(path = "/likedAdvices", consumes = "application/json", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void userLikeAdvice(Authentication authentication, @RequestBody AdviceForLikeDTO adviceForLikeDTO){
        if(UserUtil.checkIfAuthenticated(authentication)){
            Optional<Advice> adviceByTitle = adviceService.getAdviceByTitle(adviceForLikeDTO.getAdviceTitle());
            if(adviceByTitle.isEmpty()){
                throw new ResourceNotFoundException("Can't find advice by this title");
            }
            Optional<User> userOptional = userService.getLikedAdvices(userService.findByUserName(authentication.getName()).getId());
            if(userOptional.isPresent()){
                Set<Advice> likedAdvices = userOptional.get().getLikedAdvices();
                likedAdvices.add(adviceByTitle.get());
                userService.saveUser(userOptional.get());
            }
        }
    }

    @PostMapping(path = "/sharedAdvices", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void userShareAdvice(Authentication authentication, @RequestBody AdviceForLikeDTO adviceForShare)  {
        if(authentication == null){
            throw new AuthorizationServiceException("No found user");
        }
        Optional<User> user = userService.getSharedAdvices(userService.findByUserName(authentication.getName()).getId());
        Optional<Advice> adviceByTitle = adviceService.getAdviceByTitle(adviceForShare.getAdviceTitle());
        if(adviceByTitle.isEmpty()){
            throw  new EntityNotFoundException();
        }
        if(user.isEmpty()){
            User byUserName = userService.findByUserName(authentication.getName());
            Set<Advice> advicesSet = new HashSet<>();
            advicesSet.add(adviceByTitle.get());
            byUserName.setSharedAdvices(advicesSet);
            userService.saveUser(byUserName);
        }else{
            User userWithAlreadySharedAdvices = user.get();
            Set<Advice> sharedAdvices = userWithAlreadySharedAdvices.getSharedAdvices();
            sharedAdvices.add(adviceByTitle.get());
            userService.saveUser(userWithAlreadySharedAdvices);
        }
    }
    @GetMapping(path = "/score", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getUserScore(Authentication authentication){
        if(authentication ==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User byUserName = userService.findByUserName(authentication.getName());
        return new ResponseEntity<>(String.valueOf(byUserName.getUserScore()), HttpStatus.FOUND);
    }

    @GetMapping(path = "/role", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<RoleModel>> getUserRole(Authentication authentication){
        if(authentication ==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User byUserName = userService.findByUserName(authentication.getName());
        return new ResponseEntity<>(roleRepresentationModelAssembler.toCollectionModel(byUserName.getRoles()),HttpStatus.FOUND);
    }
}
