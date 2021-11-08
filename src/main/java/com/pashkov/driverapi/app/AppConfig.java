package com.pashkov.driverapi.app;

import com.pashkov.driverapi.app.converter.AdviceConverter;
import com.pashkov.driverapi.app.model.UserDetail;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/login").setViewName("admin/login");
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
