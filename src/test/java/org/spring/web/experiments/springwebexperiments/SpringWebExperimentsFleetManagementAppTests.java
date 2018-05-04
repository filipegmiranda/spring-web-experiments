package org.spring.web.experiments.springwebexperiments;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.web.experiments.springwebexperiments.model.FleetEngineersResponse;
import org.spring.web.experiments.springwebexperiments.model.ScootersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

/**
 * Demonstrating here how to test the HTTP Layer using the SprintTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringWebExperimentsFleetManagementAppTests {

    private static final String URI = "fleet-engineers/minimum-engineers";

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void findMinimumEngineersForScootersInDistrictsShouldBeCalculated() {
        ScootersRequest requestWith2Districts = new ScootersRequest(new int[]{15,10},12,5 );
        ScootersRequest requestWith3Districts = new ScootersRequest(new int[]{11,15,13},9,5 );
        webTestClient
				.post()
                .uri(URI)
				.accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWith2Districts))
				.exchange()
				.expectStatus()
                 .isOk()
                .expectBody(FleetEngineersResponse.class)
                .isEqualTo(new FleetEngineersResponse(3));
		webTestClient
				.post()
                .uri(URI)
				.accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWith3Districts))
				.exchange()
				.expectStatus()
                .isOk()
                .expectBody(FleetEngineersResponse.class)
                .isEqualTo(new FleetEngineersResponse(7));
	}

    @Test
    public void requestWithNoDistrictsOrScootersShouldFail() {
        ScootersRequest requestWithNoDistrictsOrScooters = new ScootersRequest(new int[]{}, 10, 10);
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithNoDistrictsOrScooters))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void requestWithHigherNumberOfDistrictsThanAllowedShouldFail() {
        int[] scooters =  new int[101];
        ScootersRequest requestWithHigherNumberOfDistrictsThanAllowed = new ScootersRequest(scooters, 10, 10);
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithHigherNumberOfDistrictsThanAllowed))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void requestWithNegativeNrScootersShouldFail() {
        ScootersRequest requestWithNegativeNrScooters = new ScootersRequest(new int[]{-1,10},12,5 );
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithNegativeNrScooters))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void requestWithNrScootersGreaterThanAllowedShouldFail() {
        ScootersRequest requestWithNrScootersGreaterThanAllowed = new ScootersRequest(new int[]{2000}, 10, 10);
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithNrScootersGreaterThanAllowed))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @Test
    public void requestWithLessThanMinScootersForManagerShouldFail( ) {
        ScootersRequest requestWithLessThanMinScootersForManager = new ScootersRequest(new int[]{0,1,1}, 0, 1);
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithLessThanMinScootersForManager))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Test
    public void requestWithGreaterThanMaxScootersForManagerShouldFail() {
        ScootersRequest requestWithGreaterThanMaxScootersForManager = new ScootersRequest(new int[]{0,1,1}, 1000, 1);
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithGreaterThanMaxScootersForManager))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public void requestWithLessThanMinScootersForEngineerShouldFail() {
        ScootersRequest requestWithLessThanMinScootersForEngineer = new ScootersRequest(new int[]{0,1,1}, 1, 0);
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithLessThanMinScootersForEngineer))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public void requestWithGreaterThanMaxScootersForEngineerShouldFail() {
        ScootersRequest requestWithGreaterThanMaxScootersForEngineer = new ScootersRequest(new int[]{0,1,1}, 1000, 1001);
        webTestClient
                .post()
                .uri(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestWithGreaterThanMaxScootersForEngineer))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }







}
