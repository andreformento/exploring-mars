package com.formento.exploringmars.model;

import java.util.List;
import java.util.Optional;

public class SpacialStation {

    private final Planet planet;

    public SpacialStation(Planet planet) {
        this.planet = planet;
    }

    public GroundProbe deployGroundProbeOnPlanet(Direction direction) {
        return new GroundProbe(planet, direction);
    }

    public void move(DriveCommand driveCommand, GroundProbe groundProbe) {
        driveCommand.move(groundProbe);
    }

    public List<GroundProbe> getGroundProbes() {
        return planet.getGroundProbes();
    }

    public Optional<GroundProbe> getByPosition(Position position) {
        return planet.getByPosition(position);
    }

}
