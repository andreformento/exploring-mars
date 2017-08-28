package com.formento.exploringmars.service;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.SpacialStation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MarsSpacialStationServiceTest {

    @InjectMocks
    private MarsSpacialStationService marsSpacialStationService;
    @Mock
    private SpacialStation spacialStation;

    @Test
    public void shouldExploreMarsPlanet() {
        final Direction initialDirection = mock(Direction.class);
        final List<DriveCommand> driveCommands = mock(List.class);
        final GroundProbe groundProbe = mock(GroundProbe.class);
        final Direction finalDirection = mock(Direction.class);

        when(spacialStation.deployGroundProbeOnPlanet(initialDirection)).thenReturn(groundProbe);
        when(groundProbe.getCurrentDirection()).thenReturn(finalDirection);
        final Direction result = marsSpacialStationService.explorePlanet(initialDirection, driveCommands);

        assertThat(result).isEqualTo(finalDirection);
        verify(driveCommands, only()).forEach(any(Consumer.class));
    }

}
