package com.formento.exploringmars.config;

import com.formento.exploringmars.component.ExploringMarsBuilder;
import com.formento.exploringmars.config.MarsPlanetAreaConfig.Limit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ExploringMarsConfigTest {

    @InjectMocks
    private ExploringMarsConfig exploringMarsConfig;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private ExploringMarsBuilder exploringMarsBuilder;

    private MarsPlanetAreaConfig marsPlanetAreaConfig;

    @Before
    public void init() {
        marsPlanetAreaConfig = new MarsPlanetAreaConfig();
        final Limit limit = new Limit();
        limit.setX(7);
        limit.setY(8);
        marsPlanetAreaConfig.setLimit(limit);
    }

    @Test
    public void shouldBuildEnvironmentWithParameters() {
        assertThat(exploringMarsConfig.getSpacialStation(marsPlanetAreaConfig, exploringMarsBuilder)).isNotNull();
    }

}
