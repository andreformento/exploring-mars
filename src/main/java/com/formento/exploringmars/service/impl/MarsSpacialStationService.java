package com.formento.exploringmars.service.impl;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.SpacialStation;
import com.formento.exploringmars.service.SpacialStationService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarsSpacialStationService implements SpacialStationService {

    private final SpacialStation spacialStation;

    @Autowired
    public MarsSpacialStationService(SpacialStation spacialStation) {
        this.spacialStation = spacialStation;
    }

    @Override
    public Direction explorePlanet(Direction initialDirection, List<? extends DriveCommand> driveCommands) {
        final GroundProbe groundProbe = spacialStation.deployGroundProbeOnPlanet(initialDirection);
        driveCommands.forEach(g -> g.move(groundProbe));
        return groundProbe.getCurrentDirection();
    }

    public List<Direction> getGroundProbes() {
        return spacialStation.
            getGroundProbes().
            stream().
            map(GroundProbe::getCurrentDirection).
            collect(Collectors.toList());
    }

    @Override
    public Direction deployGroundProbeOnPlanet(Direction direction) {
        return spacialStation.
            deployGroundProbeOnPlanet(direction).
            getCurrentDirection();
    }
}
