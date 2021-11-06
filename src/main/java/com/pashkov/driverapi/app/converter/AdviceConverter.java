package com.pashkov.driverapi.app.converter;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class AdviceConverter implements Converter<String, Advice> {

    @Autowired
    private AdviceRepository adviceRepository;

    @Override
    public Advice convert(String source) {
        return adviceRepository.findAdviceByAdviceTitle(source).get();
    }
}
