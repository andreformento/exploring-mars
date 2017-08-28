package com.formento.exploringmars.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GroundProbeTest {

    private GroundProbe groundProbe;

    @Mock
    private Planet planet;

    private Position position;
    private NavigationSense navigationSense;

    @Before
    public void init() {
        this.position = new Position(1, 2);
        this.navigationSense = NavigationSense.NORTH;
        this.groundProbe = new GroundProbe(planet, position, navigationSense);
    }

    @Test
    public void shouldGoForward() {
        assertThat(groundProbe.goForward()).isEqualTo(new Position(1, 3));
    }

    @Test
    public void shouldLandingOnPlanet() {
        final Planet planet = mock(Planet.class);
        final Position position = mock(Position.class);
        final GroundProbe groundProbe = new GroundProbe(planet, position, mock(NavigationSense.class));

        verify(planet, only()).landing(position, groundProbe);
    }

    @Test
    public void shouldTurnLeft() {
        assertThat(groundProbe.turnLeft()).isEqualTo(NavigationSense.WEST);
    }

    @Test
    public void shouldTurnRight() {
        assertThat(groundProbe.turnRight()).isEqualTo(NavigationSense.EAST);
    }

    @Test
    public void shouldGenerateCurrentDirection() {
        assertThat(groundProbe.getCurrentDirection()).isNotNull();
    }

}
