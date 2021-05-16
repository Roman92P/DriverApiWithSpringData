package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.AdviceModel;
import com.pashkov.driverapi.app.repository.AdviceRepository;

import java.util.Optional;

public class AdviceServiceImpl implements AdviceService {

    private final AdviceRepository adviceRepository;

    public AdviceServiceImpl(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    @Override
    public Optional<AdviceModel> getAdviceById(Long id) {
        return adviceRepository.findById(id);
    }
}
