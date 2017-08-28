package com.formento.exploringmars.model;

import com.formento.exploringmars.infra.BusinessException;
import com.formento.exploringmars.infra.NotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlanetTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private Planet planet;

    @Mock
    private PlanetArea planetArea;

    @Test
    public void shouldLandingNewGroundProbe() {
        final Position position = mock(Position.class);
        final GroundProbe groundProbe = mock(GroundProbe.class);

        planet.landing(position, groundProbe);

        verify(planetArea, only()).validateBoundary(position);
    }

    @Test
    public void shouldLandingNewGroundProbeinDifferentPlace() {
        final Position position = new Position(1, 2);
        final GroundProbe groundProbe = mock(GroundProbe.class);
        final Position position2 = new Position(3, 4);
        final GroundProbe groundProbe2 = mock(GroundProbe.class);

        planet.landing(position, groundProbe);
        planet.landing(position2, groundProbe2);

        verify(planetArea, times(2)).validateBoundary(any(Position.class));
    }

    @Test
    public void shouldLandingNewGroundProbeAfterPositionBeFree() {
        final Position position = new Position(1, 2);
        final GroundProbe groundProbe = mock(GroundProbe.class);
        final Position position2 = new Position(3, 4);
        final GroundProbe groundProbe2 = mock(GroundProbe.class);

        planet.landing(position, groundProbe);
        planet.changePosition(position, position2);
        planet.landing(position, groundProbe2);

        verify(planetArea, times(3)).validateBoundary(any(Position.class));
    }

    @Test
    public void shouldChangePositionOfGroundProbe() {
        final Position currentPosition = new Position(0, 2);
        final Position newPosition = new Position(1, 2);
        final GroundProbe groundProbe = mock(GroundProbe.class);

        planet.landing(currentPosition, groundProbe);
        planet.changePosition(currentPosition, newPosition);

        verify(planetArea, times(2)).validateBoundary(any(Position.class));
    }

    @Test
    public void shouldNotChangePositionOfGroundProbeWhenValidateBoundaryFail() {
        final Position currentPosition = mock(Position.class);
        final Position newPosition = new Position(9, 10);

        expectedException.expect(BusinessException.class);
        final String messageException = "The position is out of the security area";
        expectedException.expectMessage(messageException);

        doThrow(new BusinessException(messageException)).
            when(planetArea).
            validateBoundary(newPosition);

        planet.changePosition(currentPosition, newPosition);
    }

    @Test
    public void shouldNotPutAGroundProbeWhereDestinationIsBusy() {
        final GroundProbe groundProbe = mock(GroundProbe.class);
        final Position position = new Position(9, 10);
        final Position duplicatedPosition = new Position(9, 10);

        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("The position (9, 10) is busy");

        planet.landing(position, groundProbe);
        planet.landing(duplicatedPosition, groundProbe);

        verify(planetArea, only()).validateBoundary(position);
    }

    @Test
    public void shouldNotChangeGroundProbeWhereOriginIsEmpty() {
        final Position emptyPosition = new Position(8, 10);
        final Position newPosition = new Position(9, 10);

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("It is not possible remove because position (8, 10) is empty");

        planet.changePosition(emptyPosition, newPosition);
    }

}
