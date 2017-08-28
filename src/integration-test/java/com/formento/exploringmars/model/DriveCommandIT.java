package com.formento.exploringmars.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriveCommandIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        assertThat(objectMapper.writeValueAsString(DriveCommand.MOVE)).isEqualTo("\"M\"");
        assertThat(objectMapper.writeValueAsString(DriveCommand.TURN_LEFT)).isEqualTo("\"L\"");
        assertThat(objectMapper.writeValueAsString(DriveCommand.TURN_RIGHT)).isEqualTo("\"R\"");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        assertThat(objectMapper.readValue("\"M\"", DriveCommand.class)).isEqualTo(DriveCommand.MOVE);
        assertThat(objectMapper.readValue("\"L\"", DriveCommand.class)).isEqualTo(DriveCommand.TURN_LEFT);
        assertThat(objectMapper.readValue("\"R\"", DriveCommand.class)).isEqualTo(DriveCommand.TURN_RIGHT);
    }

}
