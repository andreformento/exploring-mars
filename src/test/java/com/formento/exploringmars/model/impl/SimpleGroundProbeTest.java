package com.formento.exploringmars.model.impl;

import static org.assertj.core.api.Assertions.assertThat;
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
public class SimpleGroundProbeTest {

    @InjectMocks
    private SimpleGroundProbe simpleGroundProbe;

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
        final Position result = simpleGroundProbe.goForward();

        assertThat(result).isEqualTo(newPosition);
        verify(planet, only()).changePosition(position, newPosition);
    }

    @Test
    public void shouldTurnLeft() {
        final NavigationSense newNavigationSense = mock(NavigationSense.class);

        when(navigationSense.turnLeft()).thenReturn(newNavigationSense);
        final NavigationSense result = simpleGroundProbe.turnLeft();

        assertThat(result).isEqualTo(newNavigationSense);
    }

    @Test
    public void shouldTurnRight() {
        final NavigationSense newNavigationSense = mock(NavigationSense.class);

        when(navigationSense.turnRight()).thenReturn(newNavigationSense);
        final NavigationSense result = simpleGroundProbe.turnRight();

        assertThat(result).isEqualTo(newNavigationSense);
    }

}
