package com.formento.exploringmars.model.impl;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formento.exploringmars.api.v1.mapper.Journey;
import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Position;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JourneyIT {

    private static final String JSON = "{\n"
        + "    \"x\": 1,\n"
        + "    \"y\": 2,\n"
        + "    \"navigationSense\": \"N\",\n"
        + "    \"driveCommands\": [\"M\",\"L\",\"R\",\"R\"]\n"
        + "}";

    @Autowired
    private ObjectMapper objectMapper;

    private Journey journey;

    @Before
    public void init() {
        final Position position = new MarsPosition(1, 2);
        final NavigationSense navigationSense = WindRose.NORTH;
        final MarsDirection marsDirection = new MarsDirection(position, navigationSense);
        final List<DriveCommandDefault> driveCommands = ImmutableList.<DriveCommandDefault>builder().
            add(DriveCommandDefault.MOVE).
            add(DriveCommandDefault.TURN_LEFT).
            add(DriveCommandDefault.TURN_RIGHT).
            add(DriveCommandDefault.TURN_RIGHT).
            build();

        this.journey = new Journey(marsDirection, driveCommands);
    }

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        assertThatJson(objectMapper.writeValueAsString(journey)).isEqualTo(JSON);
    }

    @Test
    public void shouldDeserialize() throws IOException {
        final Journey journey = objectMapper.readValue(JSON, Journey.class);
        assertThat(journey).
            isNotNull().
            hasFieldOrProperty("direction").
            hasFieldOrProperty("driveCommands");
    }

}
