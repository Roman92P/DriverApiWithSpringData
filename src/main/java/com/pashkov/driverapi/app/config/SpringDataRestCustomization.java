package com.pashkov.driverapi.app.config;

import com.pashkov.driverapi.app.model.Question;
import com.pashkov.driverapi.app.model.Topic;
import com.pashkov.driverapi.app.model.Training;
import com.pashkov.driverapi.app.projection.TopicProjection;
import com.pashkov.driverapi.app.repository.TopicRepository;
import org.aopalliance.aop.Advice;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        ExposureConfiguration exposureConfiguration = config.getExposureConfiguration();

        config.withEntityLookup()
                    .forRepository(TopicRepository.class)
                    .withIdMapping(Topic::getTopicDescription)
                    .withLookup(TopicRepository::findByTopicDescription);

        config.getProjectionConfiguration()
                    .addProjection(TopicProjection.class);

        exposureConfiguration.forDomainType(Topic.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH).disable(HttpMethod.OPTIONS));
        exposureConfiguration.forDomainType(Topic.class).disablePutForCreation();

        exposureConfiguration.forDomainType(Advice.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH).disable(HttpMethod.OPTIONS));
        exposureConfiguration.forDomainType(Advice.class).disablePutForCreation();

        exposureConfiguration.forDomainType(Question.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH).disable(HttpMethod.OPTIONS));
        exposureConfiguration.forDomainType(Question.class).disablePutForCreation();

        exposureConfiguration.forDomainType(Training.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH).disable(HttpMethod.OPTIONS));
        exposureConfiguration.forDomainType(Training.class).disablePutForCreation();
    }
}
