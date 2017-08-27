package com.formento.exploringmars.component.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.formento.exploringmars.model.SpacialStation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExploringMarsBuilderTest {

    @InjectMocks
    private ExploringMarsBuilder exploringMarsBuilder;

    @Test
    public void shouldBuildEnvironment() {
        final SpacialStation spacialStation = exploringMarsBuilder.
            withPlanetAreaLimits(3, 4).
            withPlanet().
            withSpacialStation().
            build();
        assertThat(spacialStation).isNotNull();
    }

}
