package com.formento.exploringmars.model.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
public class WindRoseIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSerialize() throws JsonProcessingException {
        assertThat(objectMapper.writeValueAsString(WindRose.NORTH)).isEqualTo("\"N\"");
        assertThat(objectMapper.writeValueAsString(WindRose.EAST)).isEqualTo("\"E\"");
        assertThat(objectMapper.writeValueAsString(WindRose.SOUTH)).isEqualTo("\"S\"");
        assertThat(objectMapper.writeValueAsString(WindRose.WEST)).isEqualTo("\"W\"");
    }

    @Test
    public void shouldDeserialize() throws IOException {
        assertThat(objectMapper.readValue("\"N\"", WindRose.class)).isEqualTo(WindRose.NORTH);
        assertThat(objectMapper.readValue("\"E\"", WindRose.class)).isEqualTo(WindRose.EAST);
        assertThat(objectMapper.readValue("\"S\"", WindRose.class)).isEqualTo(WindRose.SOUTH);
        assertThat(objectMapper.readValue("\"W\"", WindRose.class)).isEqualTo(WindRose.WEST);
    }

}
