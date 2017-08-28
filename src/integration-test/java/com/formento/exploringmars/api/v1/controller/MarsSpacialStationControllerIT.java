package com.formento.exploringmars.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MarsSpacialStationControllerIT {

    @Autowired
    private WebApplicationContext context;
    private MarsSpacialStationControllerBDD bdd;

    @Before
    public void init() {
        this.bdd = new MarsSpacialStationControllerBDD(context);
    }

    @Test
    public void shouldNavigateOnMars() {
        bdd.
            givenSimpleBody().
            whenExplorePlanet().
            assertThat().
            statusCode(is(HttpStatus.OK.value())).
            body("x", equalTo(1)).
            body("y", equalTo(3)).
            body("navigationSense", equalTo("E"));
    }

    @Test
    public void shouldNavigateOnMarsAfterAnotherGroundProbe() {
        shouldNavigateOnMars();

        bdd.
            givenBody("{\n"
                + "    \"x\": 1,\n"
                + "    \"y\": 2,\n"
                + "    \"navigationSense\": \"W\",\n"
                + "    \"driveCommands\": [\"M\",\"R\"]\n"
                + "}").
            whenExplorePlanet().
            assertThat().
            statusCode(is(HttpStatus.OK.value())).
            body("x", equalTo(0)).
            body("y", equalTo(2)).
            body("navigationSense", equalTo("N"));
    }

    @Test
    public void shouldNavigateOnMarsWithMultiMoviments() {
        bdd.
            givenBody("{\n"
                + "    \"x\": 1,\n"
                + "    \"y\": 2,\n"
                + "    \"navigationSense\": \"N\",\n"
                + "    \"driveCommands\": [\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"M\"]\n"
                + "}").
            whenExplorePlanet().
            assertThat().
            statusCode(is(HttpStatus.OK.value())).
            body("x", equalTo(1)).
            body("y", equalTo(3)).
            body("navigationSense", equalTo("N"));

        bdd.
            givenBody("{\n"
                + "    \"x\": 3,\n"
                + "    \"y\": 3,\n"
                + "    \"navigationSense\": \"E\",\n"
                + "    \"driveCommands\": [\"M\",\"M\",\"R\",\"M\",\"M\",\"R\",\"M\",\"R\",\"R\",\"M\"]\n"
                + "}").
            whenExplorePlanet().
            assertThat().
            statusCode(is(HttpStatus.OK.value())).
            body("x", equalTo(5)).
            body("y", equalTo(1)).
            body("navigationSense", equalTo("E"));

        bdd.
            given().
            whenDoGet().
            assertThat().
            statusCode(is(HttpStatus.OK.value())).
            body("size()", is(2)).
            body("x", hasItem(1)).
            body("y", hasItem(3)).
            body("navigationSense", hasItem("N")).
            body("x", hasItem(5)).
            body("y", hasItem(1)).
            body("navigationSense", hasItem("E"));
    }

    @Test
    public void shouldNotNavigateInTheSamePosition() {
        shouldNavigateOnMars();

        bdd.
            givenSimpleBody().
            whenExplorePlanet().
            assertThat().
            statusCode(is(HttpStatus.BAD_REQUEST.value())).
            body("message", equalTo("The position (1, 3) is busy"));
    }

    @Test
    public void shouldReportGroundProbe() {
        shouldNavigateOnMarsAfterAnotherGroundProbe();

        bdd.
            given().
            whenDoGet().
            statusCode(is(HttpStatus.OK.value())).
            body("size()", is(2)).
            body("x", hasItem(1)).
            body("y", hasItem(3)).
            body("navigationSense", hasItem("E")).
            body("x", hasItem(0)).
            body("y", hasItem(2)).
            body("navigationSense", hasItem("N"));
    }

    @Test
    public void shouldDeployGroundProbeOnPlanet() {
        bdd.
            givenBody("{\n"
                + "    \"x\": 3,\n"
                + "    \"y\": 4,\n"
                + "    \"navigationSense\": \"S\"\n"
                + "}").
            whenDeployGroundProbeOnPlanet().
            assertThat().
            statusCode(is(HttpStatus.CREATED.value())).
            body("x", equalTo(3)).
            body("y", equalTo(4)).
            body("navigationSense", equalTo("S"));
    }

    @Test
    public void shouldExplorePlanetWithCreatedGroundProbe() {
        shouldNavigateOnMars();
        bdd.
            givenBody("[\"L\",\"M\",\"M\",\"L\"]").
            whenExplorePlanet(1, 3). // E
            assertThat().
            statusCode(is(HttpStatus.OK.value())).
            body("x", equalTo(1)).
            body("y", equalTo(5)).
            body("navigationSense", equalTo("W"));
    }

    @Test
    public void shouldNotExplorePlanetGroundProbeNotExistsOnPosition() {
        bdd.
            givenBody("[\"L\"]").
            whenExplorePlanet(1, 3). // EassertThat().
            statusCode(is(HttpStatus.NOT_FOUND.value())).
            body("message", equalTo("Ground probe not found at position (1, 3)"));
    }

}
