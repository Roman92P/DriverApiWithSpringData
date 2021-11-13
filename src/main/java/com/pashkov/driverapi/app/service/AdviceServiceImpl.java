package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AdviceServiceImpl implements AdviceService {

    private final AdviceRepository adviceRepository;

    public AdviceServiceImpl(AdviceRepository adviceRepository) {
        this.adviceRepository = adviceRepository;
    }

    @Override
    public Optional<Advice> getAdviceById(Long id) {
        return adviceRepository.findById(id);
    }

    @Override
    public Optional<Advice> getRandomAdvice() {
        long count = adviceRepository.count();
        Random random = new Random();
        int i = random.nextInt((int) count)+1;
        return getAdviceById((long) i);
    }

    @Override
    public List<Advice> getAll() {
        Iterable<Advice> all = adviceRepository.findAll();
        List<Advice>result = new ArrayList<>();
        for(Advice a : all){
            result.add(a);
        }
        return result;
    }

    @Override
    public Optional<Advice> getAdviceByTitle(String adviceTitle) {
        return adviceRepository.findAdviceByAdviceTitle(adviceTitle);
    }
}
