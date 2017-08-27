package com.formento.exploringmars.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarsPlanetAreaConfigIT {

    @Autowired
    private MarsPlanetAreaConfig marsPlanetAreaConfig;

    @Test
    public void shouldConfigure() {
        assertThat(marsPlanetAreaConfig.getLimit()).
            isNotNull().
            hasFieldOrPropertyWithValue("x", 5).
            hasFieldOrPropertyWithValue("y", 6);
    }

}
