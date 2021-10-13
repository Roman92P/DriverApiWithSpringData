package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Advice;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AdviceService {

    Optional<Advice> getAdviceById(Long id);

    Optional<Advice> getRandomAdvice();

    List<Advice> getAll();

    Optional<Advice> getAdviceByTitle(String adviceTitle);
    
}
