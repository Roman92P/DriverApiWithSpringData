package com.pashkov.driverapi.app.util;

import com.pashkov.driverapi.app.model.Training;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class TrainingUtil {


    public Set<Training> findNotCompletedTrainings(Collection<Training> col1, Collection<Training> col2){
        Set<Training>result = new HashSet<>();
        for ( Training t : col2 ) {
            if(col1.stream().noneMatch(training -> training.getTrainingTitle().equals(t.getTrainingTitle()))) result.add(t);
        }
        return result;
    }
}
