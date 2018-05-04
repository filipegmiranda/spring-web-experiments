package org.spring.web.experiments.springwebexperiments.service.impl;


import org.spring.web.experiments.springwebexperiments.model.FleetEngineersResponse;
import org.spring.web.experiments.springwebexperiments.model.ScootersRequest;
import org.spring.web.experiments.springwebexperiments.service.FleetEngineersService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * This Service implementation class demonstrates how to isolate business code that vary from
 * implementation to implementation. This Service Implementation works providing validation and the algorithm
 * that resolves the requested problem
 */
@Service
public class FleetEngineersServiceImpl implements FleetEngineersService {
    /*
     *
     Those constants below define the invariants that the input should obey, so that it can be validated before processing
     */

    /*[]scooters will contain between 1 and 100 elements */
    private static final int SCOOTERS_DISTRICTS_MINIMUM = 1;
    private static final int SCOOTERS_DISTRICTS_THREESHOLD = 100;

    /*Each element in scooters will be between 0 and 1000.*/
    private static final int SCOOTERS_PER_DISTRICT_MINIMUM = 0;
    private static final int SCOOTERS_PER_DISTRICT_THRESHOLD = 1000;

    /*C will be between 1 and 999*/
    private static final int SCOOTERS_FOR_MANAGER_MINIMUM = 1;
    private static final int SCOOTERS_FOR_MANAGER_THRESHOLD = 999;

    /*P will be between 1 and 1000.*/
    private static final int SCOOTERS_FOR_ENGINEER_MINUMUM = 1;
    private static final int SCOOTERS_FOR_ENGINEER_THRESHOLD = 1000;

    @Override
    public FleetEngineersResponse findMinimumEngineersForScooter(ScootersRequest scootersRequest) {
        validateScootersRequest(scootersRequest);
        int total = 0;
        int saved = 0;
        int [] scooters = scootersRequest.getScooters();
        // districtScooters is the index that is given for accessing the nr scooters in each element
        for (int districtScooters = 0; districtScooters < scooters.length; districtScooters++) {
            int nrEngineers = ((Double)Math.ceil((double)scooters[districtScooters] / (double)scootersRequest.getP())).intValue();
            total += nrEngineers;
            int nrEngManagers = ((Double)Math.ceil( Math.max(((double)scooters[districtScooters] - scootersRequest.getC()) / (double) scootersRequest.getP(), 0) )).intValue();
            int maybeSaved = nrEngineers - nrEngManagers;
            if (maybeSaved > saved) {
                saved = maybeSaved;
            }
        }
        int minimum = total - saved;
        return new FleetEngineersResponse(minimum);
    }


    /**
      The Functions below could be moved to an Utility class for reusing this style of code validation.
      This style piece was inspired in Scala require function, although Scala receives the argument by name, and in this
      version it is given by value
     */
    private void validateScootersRequest(ScootersRequest scootersRequest) {
        require(scootersRequest.getScooters().length >= SCOOTERS_DISTRICTS_MINIMUM && scootersRequest.getScooters().length <= SCOOTERS_DISTRICTS_THREESHOLD, "scooters must be between 1 and 100 elements");
        /*
         This validation below for the number of scooters in each district can also be done lazily, if the array is very large, it may be interesting to check that the constraint hold while evaluating the main function findMinimumEngineersForScooter
         For such a small input size, it should be ok (100)
         */
        require(Arrays.stream(scootersRequest.getScooters()).allMatch(s -> (s >= SCOOTERS_PER_DISTRICT_MINIMUM && s <= SCOOTERS_PER_DISTRICT_THRESHOLD)), "Number of Scooters should be between 0 and 1000");
        require(scootersRequest.getC() >= SCOOTERS_FOR_MANAGER_MINIMUM && scootersRequest.getC() <= SCOOTERS_FOR_MANAGER_THRESHOLD, "C should be between 1 and 999");
        require(scootersRequest.getP() >= SCOOTERS_FOR_ENGINEER_MINUMUM && scootersRequest.getP() <= SCOOTERS_FOR_ENGINEER_THRESHOLD, "P should be between 1 and 1000");
    }

    private void require(boolean condition, String msg) throws RuntimeException {
        if(!condition) throw new FleetBusinessRulesValidationException("Validation Failed. " + msg);
    }

    public static class FleetBusinessRulesValidationException extends RuntimeException {
        FleetBusinessRulesValidationException(String msg) {
            super(msg);
        }
    }

}
