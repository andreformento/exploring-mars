//package com.formento.exploringmars.model.impl;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.only;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import com.formento.exploringmars.infra.BusinessException;
//import com.formento.exploringmars.model.GroundProbe;
//import com.formento.exploringmars.model.PlanetArea;
//import com.formento.exploringmars.model.Position;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class MarsPlanetTest {
//
//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();
//
//    @InjectMocks
//    private MarsPlanet planet;
//
//    @Mock
//    private PlanetArea planetArea;
//
//    @Test
//    public void shouldLandingNewGroundProbe() {
//        final Position position = mock(Position.class);
//        final GroundProbe groundProbe = mock(GroundProbe.class);
//
//        planet.landing(position, groundProbe);
//
//        verify(planetArea, only()).validateBoundary(position);
//    }
//
//    @Test
//    public void shouldLandingNewGroundProbeinDifferentPlace() {
//        final Position position = new MarsPosition(1, 2);
//        final GroundProbe groundProbe = mock(GroundProbe.class);
//        final Position position2 = new MarsPosition(3, 4);
//        final GroundProbe groundProbe2 = mock(GroundProbe.class);
//
//        planet.landing(position, groundProbe);
//        planet.landing(position2, groundProbe2);
//
//        verify(planetArea, times(2)).validateBoundary(any(Position.class));
//    }
//
//    @Test
//    public void shouldLandingNewGroundProbeAfterPositionBeFree() {
//        final Position position = new MarsPosition(1, 2);
//        final GroundProbe groundProbe = mock(GroundProbe.class);
//        final Position position2 = new MarsPosition(3, 4);
//        final GroundProbe groundProbe2 = mock(GroundProbe.class);
//
//        planet.landing(position, groundProbe);
//        planet.changePosition(position, position2);
//        planet.landing(position, groundProbe2);
//
//        verify(planetArea, times(3)).validateBoundary(any(Position.class));
//    }
//
//    @Test
//    public void shouldChangePositionOfGroundProbe() {
//        final Position currentPosition = new MarsPosition(0, 2);
//        final Position newPosition = new MarsPosition(1, 2);
//        final GroundProbe groundProbe = mock(GroundProbe.class);
//
//        planet.landing(currentPosition, groundProbe);
//        planet.changePosition(currentPosition, newPosition);
//
//        verify(planetArea, times(2)).validateBoundary(any(Position.class));
//    }
//
//    @Test
//    public void shouldNotChangePositionOfGroundProbeWhenValidateBoundaryFail() {
//        final Position currentPosition = mock(Position.class);
//        final Position newPosition = new MarsPosition(9, 10);
//
//        expectedException.expect(BusinessException.class);
//        final String messageException = "The position is out of the security area";
//        expectedException.expectMessage(messageException);
//
//        doThrow(new BusinessException(messageException)).
//            when(planetArea).
//            validateBoundary(newPosition);
//
//        planet.changePosition(currentPosition, newPosition);
//    }
//
//    @Test
//    public void shouldNotPutAGroundProbeWhereDestinationIsBusy() {
//        final GroundProbe groundProbe = mock(GroundProbe.class);
//        final Position position = new MarsPosition(9, 10);
//        final Position duplicatedPosition = new MarsPosition(9, 10);
//
//        expectedException.expect(BusinessException.class);
//        expectedException.expectMessage("The position (9, 10) is busy");
//
//        planet.landing(position, groundProbe);
//        planet.landing(duplicatedPosition, groundProbe);
//
//        verify(planetArea, only()).validateBoundary(position);
//    }
//
//    @Test
//    public void shouldNotChangeGroundProbeWhereOriginIsEmpty() {
//        final Position emptyPosition = new MarsPosition(8, 10);
//        final Position newPosition = new MarsPosition(9, 10);
//
//        expectedException.expect(BusinessException.class);
//        expectedException.expectMessage("The position (8, 10) is empty");
//
//        planet.changePosition(emptyPosition, newPosition );
//    }
//
//}
