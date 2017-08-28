package com.formento.exploringmars.service;

import com.formento.exploringmars.infra.NotFoundException;
import com.formento.exploringmars.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarsSpacialStationService {

    private final SpacialStation spacialStation;

    @Autowired
    public MarsSpacialStationService(SpacialStation spacialStation) {
        this.spacialStation = spacialStation;
    }

    private Direction explorePlanet(final GroundProbe groundProbe, List<? extends DriveCommand> driveCommands) {
        driveCommands.forEach(g -> g.move(groundProbe));
        return groundProbe.getCurrentDirection();
    }

    public Direction explorePlanet(Direction initialDirection, List<? extends DriveCommand> driveCommands) {
        final GroundProbe groundProbe = spacialStation.deployGroundProbeOnPlanet(initialDirection);
        return explorePlanet(groundProbe, driveCommands);
    }

    public List<Direction> getGroundProbes() {
        return spacialStation.
                getGroundProbes().
                stream().
                map(GroundProbe::getCurrentDirection).
                collect(Collectors.toList());
    }

    public Direction deployGroundProbeOnPlanet(Direction direction) {
        return spacialStation.
                deployGroundProbeOnPlanet(direction).
                getCurrentDirection();
    }

    public Direction explorePlanet(Position position, List<? extends DriveCommand> driveCommands) {
        final GroundProbe groundProbe = spacialStation.
                getByPosition(position).
                orElseThrow(() -> new NotFoundException("Ground probe not found at position " + position.toString()));
        return explorePlanet(groundProbe, driveCommands);
    }

}
