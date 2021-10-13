package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Role;
import com.pashkov.driverapi.app.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.io.StreamTokenizer;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getAllRoles() {
        Iterable<Role> all = roleRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toSet());
    }
}
