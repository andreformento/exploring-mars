package com.formento.exploringmars.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MarsSpacialStationControllerIT {

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
        final String json = "{\n"
            + "    \"x\": 1,\n"
            + "    \"y\": 2,\n"
            + "    \"navigationSense\": \"N\",\n"
            + "    \"driveCommands\": [\"M\",\"L\",\"R\",\"R\"]\n"
            + "}";

        given.
            body(json).
        when().
            post("/spacial-station").
        then().
            statusCode(is(HttpStatus.OK.value())).
            body("x", equalTo(1)).
            body("y", equalTo(3)).
            body("navigationSense", equalTo("E"));
    }

    @Test
    public void shouldNavigateOnMarsAfterAnotherGroundProbe() {
        shouldNavigateOnMars();

        final String json = "{\n"
            + "    \"x\": 1,\n"
            + "    \"y\": 2,\n"
            + "    \"navigationSense\": \"W\",\n"
            + "    \"driveCommands\": [\"M\",\"R\"]\n"
            + "}";


        given.
            body(json).
        when().
            post("/spacial-station").
        then().
            statusCode(is(HttpStatus.OK.value())).
            body("x", equalTo(0)).
            body("y", equalTo(2)).
            body("navigationSense", equalTo("N"));
    }

}
