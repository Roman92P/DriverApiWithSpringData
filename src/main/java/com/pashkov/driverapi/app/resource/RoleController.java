package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Role;
import com.pashkov.driverapi.app.model.RoleModel;
import com.pashkov.driverapi.app.model.RoleRepresentationModelAssembler;
import com.pashkov.driverapi.app.service.RoleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    private final RoleRepresentationModelAssembler roleRepresentationModelAssembler;

    public RoleController(RoleService roleService, RoleRepresentationModelAssembler roleRepresentationModelAssembler) {
        this.roleService = roleService;
        this.roleRepresentationModelAssembler = roleRepresentationModelAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CollectionModel<RoleModel>> getAllPossibleRoles(){
        Set<Role> allRoles = roleService.getAllRoles();
        return new ResponseEntity<>(
          roleRepresentationModelAssembler.toCollectionModel(allRoles), HttpStatus.OK);
    }
    @ApiIgnore
    @GetMapping(path = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getForTest(){
        return new ResponseEntity<>(
                "Hello", HttpStatus.OK);
    }
}
