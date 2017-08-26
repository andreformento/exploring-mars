package com.formento.exploringmars.model.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExplorerGroundProbeTest {

    @InjectMocks
    private ExplorerGroundProbe explorerGroundProbe;

    @Mock
    private Planet planet;
    @Mock
    private Position position;
    @Mock
    private NavigationSense navigationSense;

    @Test
    public void shouldGoForward() {
        final Position newPosition = mock(Position.class);

        when(navigationSense.goForward(position)).thenReturn(newPosition);
        final Position result = explorerGroundProbe.goForward();

        assertThat(result).isEqualTo(newPosition);
        verify(planet, atMost(1)).changePosition(position, newPosition);
    }

    @Test
    public void shouldLandingOnPlanet() {
        final Planet planet = mock(Planet.class);
        final Position position = mock(Position.class);
        final ExplorerGroundProbe explorerGroundProbe = new ExplorerGroundProbe(planet, position, mock(NavigationSense.class));

        verify(planet, only()).landing(position, explorerGroundProbe);
    }

    @Test
    public void shouldTurnLeft() {
        final NavigationSense newNavigationSense = mock(NavigationSense.class);

        when(navigationSense.turnLeft()).thenReturn(newNavigationSense);
        final NavigationSense result = explorerGroundProbe.turnLeft();

        assertThat(result).isEqualTo(newNavigationSense);
    }

    @Test
    public void shouldTurnRight() {
        final NavigationSense newNavigationSense = mock(NavigationSense.class);

        when(navigationSense.turnRight()).thenReturn(newNavigationSense);
        final NavigationSense result = explorerGroundProbe.turnRight();

        assertThat(result).isEqualTo(newNavigationSense);
    }

}