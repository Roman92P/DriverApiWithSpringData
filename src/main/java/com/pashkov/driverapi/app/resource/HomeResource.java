package com.pashkov.driverapi.app.resource;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Resource for Home page. Returns one Advice, list of all topics, not completed advice with training")
@RequestMapping(path = "/api_driver/home")
public class HomeResource {


}
