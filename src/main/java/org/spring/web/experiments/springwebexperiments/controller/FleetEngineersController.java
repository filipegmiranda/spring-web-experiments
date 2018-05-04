package org.spring.web.experiments.springwebexperiments.controller;


import org.spring.web.experiments.springwebexperiments.model.FleetEngineersResponse;
import org.spring.web.experiments.springwebexperiments.model.ScootersRequest;
import org.spring.web.experiments.springwebexperiments.service.FleetEngineersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Layer to receive HTTP requests to calculate minimum nr engineers
 */
@RestController
@RequestMapping("/fleet-engineers")
public class FleetEngineersController {

    @Autowired
    private FleetEngineersService service;

    @PostMapping(value = "/minimum-engineers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public FleetEngineersResponse findMininumEngineersForScooters(@RequestBody ScootersRequest scootersRequest) {
        return service.findMinimumEngineersForScooter(scootersRequest);
    }
}
