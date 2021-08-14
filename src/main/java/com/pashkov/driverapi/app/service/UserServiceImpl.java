package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.model.Role;
import com.pashkov.driverapi.app.model.User;
import com.pashkov.driverapi.app.repository.RoleRepository;
import com.pashkov.driverapi.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findRoleByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByid(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getLikedAdvices(long id) {
        return userRepository.getUserByIdAndLikedAdvices(id);
    }

    @Override
    public Optional<User> getSharedAdvices(long id) {
        return userRepository.getUserByIdAndSharedAdvices(id);
    }

    @Override
    public Optional<User> getUserCompleteTrainings(long id) {
        return userRepository.getUserByIdAndCompleteTrainings(id);
    }
}
