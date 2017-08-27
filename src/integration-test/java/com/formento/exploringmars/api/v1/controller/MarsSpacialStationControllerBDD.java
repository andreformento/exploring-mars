package com.formento.exploringmars.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
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

    private final MockMvcRequestSpecification given;

    MarsSpacialStationControllerBDD(WebApplicationContext context) {
        final MockMvc mvc = webAppContextSetup(context).build();
        this.given = RestAssuredMockMvc.given().mockMvc(mvc).contentType(MediaType.APPLICATION_JSON_VALUE).accept(ContentType.JSON);
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

    class When {

        private final MockMvcRequestAsyncSender when;

        When(MockMvcRequestAsyncSender when) {
            this.when = when;
        }

        Then whenCall() {
            return new Then(when.post("/spacial-station").then());
        }
    }

    class Then {

        private final ValidatableMockMvcResponse then;

        Then(ValidatableMockMvcResponse then) {
            this.then = then;
        }


        void thenSuccessSimpleBody() {
            thenSuccessWith(1, 3, "E");
        }

        void thenSuccessWith(Integer x, Integer y, String navigationSense) {
            then.
                statusCode(is(HttpStatus.OK.value())).
                body("x", equalTo(x)).
                body("y", equalTo(y)).
                body("navigationSense", equalTo(navigationSense));
        }

        void thenBadRequestWith(String message) {
            then.
                statusCode(is(HttpStatus.BAD_REQUEST.value())).
                body("message", equalTo(message));
        }
    }

}
