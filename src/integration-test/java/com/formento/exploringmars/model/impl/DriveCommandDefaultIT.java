package com.formento.exploringmars.model.impl;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriveCommandDefaultIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        assertThat(objectMapper.writeValueAsString(DriveCommandDefault.MOVE)).isEqualTo("\"M\"");
        assertThat(objectMapper.writeValueAsString(DriveCommandDefault.TURN_LEFT)).isEqualTo("\"L\"");
        assertThat(objectMapper.writeValueAsString(DriveCommandDefault.TURN_RIGHT)).isEqualTo("\"R\"");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        assertThat(objectMapper.readValue("\"M\"", DriveCommandDefault.class)).isEqualTo(DriveCommandDefault.MOVE);
        assertThat(objectMapper.readValue("\"L\"", DriveCommandDefault.class)).isEqualTo(DriveCommandDefault.TURN_LEFT);
        assertThat(objectMapper.readValue("\"R\"", DriveCommandDefault.class)).isEqualTo(DriveCommandDefault.TURN_RIGHT);
    }

}
