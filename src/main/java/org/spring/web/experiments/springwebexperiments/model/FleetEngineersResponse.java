package org.spring.web.experiments.springwebexperiments.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents the Response containing the nr engineers that is minimum to the given problem ScootersRequest
 * @see ScootersRequest
 */
@Data
@AllArgsConstructor
public class FleetEngineersResponse {
    private int fleetEngineers;
}
