package com.formento.exploringmars.api.v1.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MarsSpacialStationControllerIT {

    private static final String PATH = "/spacials-stations/ground-probes";

    @Autowired
    private WebApplicationContext context;

    private MockMvcRequestSpecification given;

    @Before
    public void init() {
        final MockMvc mvc = webAppContextSetup(context).build();
        this.given = RestAssuredMockMvc.given().mockMvc(mvc).contentType(MediaType.APPLICATION_JSON_VALUE).accept(ContentType.JSON);
    }

    @Test
    public void shouldNavigateOnMars() {
        given.
                body("{\n"
                        + "    \"x\": 1,\n"
                        + "    \"y\": 2,\n"
                        + "    \"navigationSense\": \"N\",\n"
                        + "    \"driveCommands\": [\"M\",\"L\",\"R\",\"R\"]\n"
                        + "}").
                post(PATH + "/explore-planet-by-jorney").
                then().
                assertThat().
                statusCode(is(HttpStatus.OK.value())).
                body("x", equalTo(1)).
                body("y", equalTo(3)).
                body("navigationSense", equalTo("E"));
    }

    @Test
    public void shouldNavigateOnMarsAfterAnotherGroundProbe() {
        shouldNavigateOnMars();

        given.
                body("{\n"
                        + "    \"x\": 1,\n"
                        + "    \"y\": 2,\n"
                        + "    \"navigationSense\": \"W\",\n"
                        + "    \"driveCommands\": [\"M\",\"R\"]\n"
                        + "}").
                post(PATH + "/explore-planet-by-jorney").
                then().
                assertThat().
                statusCode(is(HttpStatus.OK.value())).
                body("x", equalTo(0)).
                body("y", equalTo(2)).
                body("navigationSense", equalTo("N"));
    }

    @Test
    public void shouldNavigateOnMarsWithMultiMoviments() {
        given.
                body("{\n"
                        + "    \"x\": 1,\n"
                        + "    \"y\": 2,\n"
                        + "    \"navigationSense\": \"N\",\n"
                        + "    \"driveCommands\": [\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"M\"]\n"
                        + "}").
                post(PATH + "/explore-planet-by-jorney").
                then().
                assertThat().
                statusCode(is(HttpStatus.OK.value())).
                body("x", equalTo(1)).
                body("y", equalTo(3)).
                body("navigationSense", equalTo("N"));

        given.
                body("{\n"
                        + "    \"x\": 3,\n"
                        + "    \"y\": 3,\n"
                        + "    \"navigationSense\": \"E\",\n"
                        + "    \"driveCommands\": [\"M\",\"M\",\"R\",\"M\",\"M\",\"R\",\"M\",\"R\",\"R\",\"M\"]\n"
                        + "}").
                post(PATH + "/explore-planet-by-jorney").
                then().
                assertThat().
                statusCode(is(HttpStatus.OK.value())).
                body("x", equalTo(5)).
                body("y", equalTo(1)).
                body("navigationSense", equalTo("E"));

        given.
                get(PATH).then().
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

        given.
                body("{\n"
                        + "    \"x\": 1,\n"
                        + "    \"y\": 2,\n"
                        + "    \"navigationSense\": \"N\",\n"
                        + "    \"driveCommands\": [\"M\",\"L\",\"R\",\"R\"]\n"
                        + "}").
                post(PATH + "/explore-planet-by-jorney").
                then().
                assertThat().
                statusCode(is(HttpStatus.BAD_REQUEST.value())).
                body("message", equalTo("The position (1, 3) is busy"));
    }

    @Test
    public void shouldReportGroundProbe() {
        shouldNavigateOnMarsAfterAnotherGroundProbe();

        given.
                get(PATH).then().
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
        given.
                body("{\n"
                        + "    \"x\": 3,\n"
                        + "    \"y\": 4,\n"
                        + "    \"navigationSense\": \"S\"\n"
                        + "}").
                post(PATH).
                then().
                assertThat().
                statusCode(is(HttpStatus.CREATED.value())).
                body("x", equalTo(3)).
                body("y", equalTo(4)).
                body("navigationSense", equalTo("S"));
    }

    @Test
    public void shouldExplorePlanetWithCreatedGroundProbe() {
        shouldNavigateOnMars();
        given.
                body("[\"L\",\"M\",\"M\",\"L\"]").
                put(PATH + "/1/3/explore-planet-by-position"). // E
                then().
                assertThat().
                statusCode(is(HttpStatus.OK.value())).
                body("x", equalTo(1)).
                body("y", equalTo(5)).
                body("navigationSense", equalTo("W"));
    }

    @Test
    public void shouldNotExplorePlanetGroundProbeNotExistsOnPosition() {
        given.
                body("[\"L\"]").
                put(PATH + "/1/3/explore-planet-by-position"). // E
                then().
                statusCode(is(HttpStatus.NOT_FOUND.value())).
                body("message", equalTo("Ground probe not found at position (1, 3)"));
    }

    @Test
    public void shouldNotDeployOutsideOfSecurityAreaBeforeLeftBottom() {
        given.
                body("{\n"
                        + "    \"x\": -1,\n"
                        + "    \"y\": 0,\n"
                        + "    \"navigationSense\": \"S\"\n"
                        + "}").
                post(PATH).
                then().
                assertThat().
                statusCode(is(HttpStatus.BAD_REQUEST.value())).
                body("message", equalTo("The position (-1, 0) is outside of the area that begin at (0, 0)"));
    }

    @Test
    public void shouldNotDeployOutsideOfSecurityAreaBeforeRightTop() {
        given.
                body("{\n"
                        + "    \"x\": 200,\n"
                        + "    \"y\": 300,\n"
                        + "    \"navigationSense\": \"S\"\n"
                        + "}").
                post(PATH).
                then().
                assertThat().
                statusCode(is(HttpStatus.BAD_REQUEST.value())).
                body("message", equalTo("The position (200, 300) is outside of the area that end at (5, 5)"));
    }

    @Test
    public void shouldGetById() {
        final Map<String, Object> map = given.
                body("{\n"
                        + "    \"x\": 3,\n"
                        + "    \"y\": 4,\n"
                        + "    \"navigationSense\": \"S\"\n"
                        + "}").
                post(PATH).
                then().
                assertThat().
                statusCode(is(HttpStatus.CREATED.value())).
                body("id", not(isEmptyOrNullString())).
                extract().body().as(Map.class);

        final String id = (String) map.get("id");

        given.
                get(PATH + "/" + id).then().
                statusCode(is(HttpStatus.OK.value())).
                body("x", equalTo(3)).
                body("y", equalTo(4)).
                body("navigationSense", equalTo("S"));
    }

    @Test
    public void shouldNotGetByIdWhenNotExists() {
        final String id = "this-id-never-be-exists";

        given.
                get(PATH + "/" + id).then().
                statusCode(is(HttpStatus.NOT_FOUND.value())).
                body("message", equalTo("Ground probe not found by id " + id));
    }

}
