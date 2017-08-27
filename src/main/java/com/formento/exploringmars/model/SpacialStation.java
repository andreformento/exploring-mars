package com.formento.exploringmars.model;

public interface SpacialStation {

    GroundProbe deployGroundProbeOnPlanet(Direction direction);

    void move(DriveCommand driveCommand, GroundProbe groundProbe);

}
