package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Advice;

import java.util.Optional;

public interface AdviceService {
    Optional<Advice> getAdviceById(Long id);

}
