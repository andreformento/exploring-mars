package com.formento.exploringmars.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavigationSenseIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        assertThat(objectMapper.writeValueAsString(NavigationSense.NORTH)).isEqualTo("\"N\"");
        assertThat(objectMapper.writeValueAsString(NavigationSense.EAST)).isEqualTo("\"E\"");
        assertThat(objectMapper.writeValueAsString(NavigationSense.SOUTH)).isEqualTo("\"S\"");
        assertThat(objectMapper.writeValueAsString(NavigationSense.WEST)).isEqualTo("\"W\"");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        assertThat(objectMapper.readValue("\"N\"", NavigationSense.class)).isEqualTo(NavigationSense.NORTH);
        assertThat(objectMapper.readValue("\"E\"", NavigationSense.class)).isEqualTo(NavigationSense.EAST);
        assertThat(objectMapper.readValue("\"S\"", NavigationSense.class)).isEqualTo(NavigationSense.SOUTH);
        assertThat(objectMapper.readValue("\"W\"", NavigationSense.class)).isEqualTo(NavigationSense.WEST);
    }

}
