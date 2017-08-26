package com.formento.exploringmars.model;

public interface SpacialStation {

    GroundProbe deployGroundProbeOnPlanet(Position position, NavigationSense navigationSense);

    void move(DriveCommand driveCommand, GroundProbe groundProbe);

}
