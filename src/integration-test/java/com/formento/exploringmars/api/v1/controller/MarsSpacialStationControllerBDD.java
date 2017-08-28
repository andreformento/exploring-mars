package com.formento.exploringmars.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestAsyncSender;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

class MarsSpacialStationControllerBDD {

    private static final String PATH = "/spacial-station";

    private final MockMvcRequestSpecification given;

    MarsSpacialStationControllerBDD(WebApplicationContext context) {
        final MockMvc mvc = webAppContextSetup(context).build();
        this.given = RestAssuredMockMvc.given().mockMvc(mvc).contentType(MediaType.APPLICATION_JSON_VALUE).accept(ContentType.JSON);
    }

    When given() {
        return new When(given.when());
    }

    When givenBody(String json) {
        return new When(given.body(json).when());
    }

    When givenSimpleBody() {
        return givenBody("{\n"
            + "    \"x\": 1,\n"
            + "    \"y\": 2,\n"
            + "    \"navigationSense\": \"N\",\n"
            + "    \"driveCommands\": [\"M\",\"L\",\"R\",\"R\"]\n"
            + "}");
    }

    static class When {

        private final MockMvcRequestAsyncSender when;

        When(MockMvcRequestAsyncSender when) {
            this.when = when;
        }

        Then whenDoGet() {
            return new Then(when.get(PATH).then());
        }

        Then whenDeployGroundProbeOnPlanet() {
            return new Then(when.post(PATH).then());
        }

        Then whenExplorePlanet() {
            return new Then(when.post(PATH + "/explore-planet").then());
        }

        Then whenExplorePlanet(Integer x, Integer y) {
            return new Then(when.put(PATH + "/explore-planet/" + x + "/" + y).then());
        }
    }

    static class Then {

        private final ValidatableMockMvcResponse then;

        Then(ValidatableMockMvcResponse then) {
            this.then = then;
        }

        Then thenResultOK() {
            then.statusCode(is(HttpStatus.OK.value()));
            return this;
        }

        Then thenResultCreated() {
            then.statusCode(is(HttpStatus.CREATED.value()));
            return this;
        }

        void thenNotFoundWith(String message) {
            then.
                statusCode(is(HttpStatus.NOT_FOUND.value())).
                body("message", equalTo(message));
        }

        void thenBadRequestWith(String message) {
            then.
                statusCode(is(HttpStatus.BAD_REQUEST.value())).
                body("message", equalTo(message));
        }

        void thenHaveAFinalDirection(Integer x, Integer y, String navigationSense) {
            then.
                body("x", equalTo(x)).
                body("y", equalTo(y)).
                body("navigationSense", equalTo(navigationSense));
        }

        void thenHaveAFinalDirection() {
            thenHaveAFinalDirection(1, 3, "E");
        }

        Then thenHasItem(Integer x, Integer y, String navigationSense) {
            then.
                assertThat().
                body("x", hasItem(x)).
                body("y", hasItem(y)).
                body("navigationSense", hasItem(navigationSense));
            return this;
        }

        Then thenHasSize(Integer size) {
            then.body("size()", is(size));
            return this;
        }
    }

}
