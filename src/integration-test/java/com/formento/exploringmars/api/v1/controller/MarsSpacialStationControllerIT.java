package com.formento.exploringmars.api.v1.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    private void simpleNavigationOnMars() {
        bdd.
            givenSimpleBody().
            whenExplorePlanet().
            thenResultOK().
            thenHaveAFinalDirection();
    }

    @Test
    public void shouldNavigateOnMars() {
        simpleNavigationOnMars();
    }

    @Test
    public void shouldNavigateOnMarsAfterAnotherGroundProbe() {
        simpleNavigationOnMars();

        bdd.
            givenBody("{\n"
                + "    \"x\": 1,\n"
                + "    \"y\": 2,\n"
                + "    \"navigationSense\": \"W\",\n"
                + "    \"driveCommands\": [\"M\",\"R\"]\n"
                + "}").
            whenExplorePlanet().
            thenResultOK().
            thenHaveAFinalDirection(0, 2, "N");
    }

    @Test
    public void shouldNotNavigateInTheSamePosition() {
        shouldNavigateOnMars();

        bdd.
            givenSimpleBody().
            whenExplorePlanet().
            thenBadRequestWith("The position (1, 3) is busy");
    }

    @Test
    public void shouldReportGroundProbe() {
        shouldNavigateOnMarsAfterAnotherGroundProbe();

        bdd.
            given().
            whenDoGet().
            thenHasSize(2).
            thenHasItem(1, 3, "E").
            thenHasItem(0, 2, "N");
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
            thenResultCreated().
            thenHaveAFinalDirection(3, 4, "S");
    }

}
