package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.AdviceModel;

import java.util.Optional;

public interface AdviceService {
    Optional<AdviceModel> getAdviceById(Long id);

}
