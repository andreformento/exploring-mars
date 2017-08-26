package com.formento.exploringmars.model.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MarsSpacialStationTest {

    @InjectMocks
    private MarsSpacialStation marsSpacialStation;

    @Mock
    private MarsPlanet marsPlanet;

    @Test
    public void shouldDeployGroundProbeOnMars() {
        assertThat(marsSpacialStation.deployGroundProbeOnPlanet(mock(Position.class), mock(NavigationSense.class))).
            isNotNull();
    }

    @Test
    public void shouldMoveGroundProbe() {
        final DriveCommand driveCommand = mock(DriveCommand.class);
        final GroundProbe groundProbe = mock(GroundProbe.class);
        marsSpacialStation.move(driveCommand, groundProbe);
        verify(driveCommand).move(groundProbe);
    }

}
