package com.formento.exploringmars.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpacialStationTest {

    @InjectMocks
    private SpacialStation spacialStation;

    @Mock
    private Planet planet;

    @Test
    public void shouldDeployGroundProbeOnMars() {
        assertThat(spacialStation.deployGroundProbeOnPlanet(mock(Direction.class))).
                isNotNull();
    }

    @Test
    public void shouldMoveGroundProbe() {
        final DriveCommand driveCommand = DriveCommand.MOVE;
        final GroundProbe groundProbe = mock(GroundProbe.class);
        spacialStation.move(driveCommand, groundProbe);
        verify(groundProbe, only()).goForward();
    }

}
