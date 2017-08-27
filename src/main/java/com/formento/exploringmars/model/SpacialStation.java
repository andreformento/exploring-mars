package com.formento.exploringmars.model;

import java.util.List;
import java.util.Optional;

public interface SpacialStation {

    GroundProbe deployGroundProbeOnPlanet(Direction direction);

    void move(DriveCommand driveCommand, GroundProbe groundProbe);

    List<GroundProbe> getGroundProbes();

    Optional<GroundProbe> getByPosition(Position position);
}
