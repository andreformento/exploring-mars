package com.formento.exploringmars.model;

import java.util.List;

public interface SpacialStation {

    GroundProbe deployGroundProbeOnPlanet(Direction direction);

    void move(DriveCommand driveCommand, GroundProbe groundProbe);

    List<GroundProbe> getGroundProbes();
}
