package com.formento.exploringmars.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.SpacialStation;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MarsSpacialStationServiceTest {

    @InjectMocks
    private MarsSpacialStationService marsSpacialStationService;
    @Mock
    private SpacialStation spacialStation;

    @Test
    public void shouldExploreMarsPlanet() {
        final Direction initialDirection = mock(Direction.class);
        final DriveCommand driveCommand = mock(DriveCommand.class);
        final List<DriveCommand> driveCommands = ImmutableList.<DriveCommand>builder().add(driveCommand).build();
        final GroundProbe groundProbe = mock(GroundProbe.class);
        final Direction finalDirection = mock(Direction.class);

        when(spacialStation.deployGroundProbeOnPlanet(initialDirection)).thenReturn(groundProbe);
        when(groundProbe.getCurrentDirection()).thenReturn(finalDirection);
        final Direction result = marsSpacialStationService.explorePlanet(initialDirection, driveCommands);

        assertThat(result).isEqualTo(finalDirection);
        verify(driveCommand, only()).move(groundProbe);
    }

}
