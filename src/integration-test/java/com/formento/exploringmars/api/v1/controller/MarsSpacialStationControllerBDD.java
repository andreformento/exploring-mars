package com.formento.exploringmars.api.v1.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestAsyncSender;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
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

        ValidatableMockMvcResponse whenDoGet() {
            return when.get(PATH).then();
        }

        ValidatableMockMvcResponse whenDeployGroundProbeOnPlanet() {
            return when.post(PATH).then();
        }

        ValidatableMockMvcResponse whenExplorePlanet() {
            return when.post(PATH + "/explore-planet").then();
        }

        ValidatableMockMvcResponse whenExplorePlanet(Integer x, Integer y) {
            return when.put(PATH + "/explore-planet/" + x + "/" + y).then();
        }
    }

}
