package com.formento.exploringmars.model.impl;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import com.formento.exploringmars.infra.BusinessException;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.PlanetArea;
import com.formento.exploringmars.model.Position;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MarsPlanetTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private MarsPlanet planet;

    @Mock
    private PlanetArea planetArea;

    @Test
    public void shouldLandingNewGroundProbe() {
        final Position position = mock(Position.class);
        final GroundProbe groundProbe = mock(GroundProbe.class);

        planet.landing(groundProbe, position);

        verify(planetArea, only()).validateBoundary(position);
    }

    @Test
    public void shouldChangePositionOfGroundProbe() {
        final Position currentPosition = mock(Position.class);
        final Position newPosition = mock(Position.class);

        planet.changePosition(currentPosition, newPosition);

        verify(planetArea, only()).validateBoundary(newPosition);
    }

    @Test
    public void shouldNotChangePositionOfGroundProbeWhenValidateBoundaryFail() {
        final Position currentPosition = mock(Position.class);
        final Position newPosition = mock(Position.class);

        expectedException.expect(BusinessException.class);
        final String messageException = "The position is out of the security area";
        expectedException.expectMessage(contains(messageException));

        doThrow(new BusinessException(messageException)).
            when(planetArea).
            validateBoundary(newPosition);

        planet.changePosition(currentPosition, newPosition);
    }

    @Test
    public void shouldNotPutAGroundProbeWhereIsBusy() {
        final GroundProbe groundProbe = mock(GroundProbe.class);
        final Position newPosition = mock(Position.class);

        expectedException.expect(BusinessException.class);
        final String messageException = "The new position is busy";
        expectedException.expectMessage(contains(messageException));

        doThrow(new BusinessException(messageException)).
            when(planetArea).
            validateBoundary(newPosition);

        planet.landing(groundProbe, newPosition);
    }

}
