package com.formento.exploringmars.service;

import com.formento.exploringmars.infra.NotFoundException;
import com.formento.exploringmars.model.*;
import com.formento.exploringmars.repository.GroundProbeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarsSpacialStationService {

    private final SpacialStation spacialStation;
    private final GroundProbeRepository groundProbeRepository;

    @Autowired
    public MarsSpacialStationService(SpacialStation spacialStation, GroundProbeRepository groundProbeRepository) {
        this.spacialStation = spacialStation;
        this.groundProbeRepository = groundProbeRepository;
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

    public GroundProbe deployGroundProbeOnPlanet(Direction direction) {
        final GroundProbe groundProbe = spacialStation.deployGroundProbeOnPlanet(direction);
        return groundProbeRepository.save(groundProbe);
    }

    public Direction explorePlanet(Position position, List<? extends DriveCommand> driveCommands) {
        final GroundProbe groundProbe = spacialStation.
                getByPosition(position).
                orElseThrow(() -> new NotFoundException("Ground probe not found at position " + position.toString()));
        return explorePlanet(groundProbe, driveCommands);
    }

    public GroundProbe getById(String id) {
        return groundProbeRepository.
                getById(id).
                orElseThrow(() -> new NotFoundException("Ground probe not found by id " + id));
    }

}
