package org.spring.web.experiments.springwebexperiments.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The representation of districts and each nr of scooters present in them, plus the limits
 * provided for Manager and Engineers to maintain scooters
 */
@Data
@AllArgsConstructor
public class ScootersRequest {
    private int scooters[];
    private int C;
    private int P;
}
