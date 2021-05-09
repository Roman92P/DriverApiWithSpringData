package com.pashkov.driverapi.app.config;

import com.pashkov.driverapi.app.model.TopicModel;
import com.pashkov.driverapi.app.projection.TopicProjection;
import com.pashkov.driverapi.app.repository.TopicRepository;
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
                    .withIdMapping(TopicModel::getTopicDescription)
                    .withLookup(TopicRepository::findByTopicDescription);

        config.getProjectionConfiguration()
                    .addProjection(TopicProjection.class);

        exposureConfiguration.forDomainType(TopicModel.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH).disable(HttpMethod.OPTIONS).disable(HttpMethod.PUT));
    }
}
