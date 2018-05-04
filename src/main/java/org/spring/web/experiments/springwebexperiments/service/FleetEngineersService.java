package org.spring.web.experiments.springwebexperiments.service;

import org.spring.web.experiments.springwebexperiments.model.FleetEngineersResponse;
import org.spring.web.experiments.springwebexperiments.model.ScootersRequest;

/**
 * Service Layer to separate the Business Logic and Algorithm for testing from the Rest/HTTP Layer
 */
public interface FleetEngineersService {
    FleetEngineersResponse findMinimumEngineersForScooter(ScootersRequest scootersRequest);
}
