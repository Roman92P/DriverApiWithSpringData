package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.Role;
import com.pashkov.driverapi.app.model.RoleModel;
import com.pashkov.driverapi.app.model.RoleRepresentationModelAssembler;
import com.pashkov.driverapi.app.service.RoleService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RoleController.class, useDefaultFilters = false)
@WithMockUser
class RoleControllerTest {

    @Autowired
    public MockMvc mvc;

    @MockBean
    private RoleController roleController;

    @InjectMocks
    private RoleRepresentationModelAssembler roleRepresentationModelAssembler;

    @MockBean
    private RoleService roleServiceMock;

    @Test
    public void shouldReturnCTestString() throws Exception {
        ResponseEntity<String> responseEntity = new ResponseEntity("Hello",HttpStatus.OK);
        when(roleController.getForTest()).thenReturn(responseEntity);
        MvcResult hello = this.mvc.perform(get("/roles/test"))
                .andExpect(content().string(containsString("Hello"))).andReturn();
        assertEquals(hello.getResponse().getContentAsString(),"Hello");

    }

    @Test
    public void shouldReturnCollectionOfUserRoles() throws Exception {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaTypes.HAL_JSON);


        Role role = new Role();
        role.setName("User");
        Role role1 = new Role();
        role1.setName("SuperUser");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        roles.add(role1);

        System.out.println(roleRepresentationModelAssembler.toCollectionModel(roles).toString());

        ResponseEntity<CollectionModel<RoleModel>> responseEntity = new ResponseEntity<>(
                roleRepresentationModelAssembler.toCollectionModel(roles),
                header,
                HttpStatus.OK
        );

        when(roleController.getAllPossibleRoles()).thenReturn(responseEntity);

        MockHttpServletResponse response = this.mvc.perform(get("/roles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON)).andReturn().getResponse();

        System.out.println("result: "+response.getContentAsString().length());


    }
}