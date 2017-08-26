package com.formento.exploringmars.model;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.formento.exploringmars.model.impl.MarsPlanet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlanetTest {

    private MarsPlanet planet;

    @Mock
    private Position leftBottom;
    @Mock
    private Position rightTop;

    @Before
    public void init() {
        when(leftBottom.getX()).thenReturn(0);
        when(leftBottom.getY()).thenReturn(0);
        when(rightTop.getX()).thenReturn(3);
        when(rightTop.getY()).thenReturn(4);

        this.planet = new MarsPlanet(leftBottom, rightTop);
    }

    @Test
    public void shouldLandingNewGroundProbe() {
        final Position position = mock(Position.class);
        final GroundProbe groundProbe = mock(GroundProbe.class);

        when(position.getX()).thenReturn(1);
        when(position.getY()).thenReturn(2);

        planet.landing(groundProbe, position);

        verify(planet, only()).boundaryValidation(position);
        verify(planet, only()).putOn(position, groundProbe);
    }

    @Test
    public void shouldChangePositionOfGroundProbe() {
        final Position currentPosition = mock(Position.class);
        final Position newPosition = mock(Position.class);

        when(currentPosition.getX()).thenReturn(1);
        when(currentPosition.getY()).thenReturn(2);
        when(newPosition.getX()).thenReturn(2);
        when(newPosition.getY()).thenReturn(2);

        planet.changePosition(currentPosition, newPosition);

        verify(planet, only()).removeFrom(currentPosition);
        verify(planet, only()).boundaryValidation(newPosition);
        verify(planet, only()).putOn(eq(newPosition), any(GroundProbe.class));
    }

}
