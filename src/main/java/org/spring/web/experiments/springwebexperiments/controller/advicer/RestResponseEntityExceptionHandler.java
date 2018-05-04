package org.spring.web.experiments.springwebexperiments.controller.advicer;

import org.spring.web.experiments.springwebexperiments.service.impl.FleetEngineersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({ FleetEngineersServiceImpl.FleetBusinessRulesValidationException.class})
    protected ResponseEntity<Object> handleBusinessViolations(RuntimeException ex) {
        return new ResponseEntity<>( "There was a validation that failed on your input", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
