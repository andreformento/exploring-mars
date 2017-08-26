package com.formento.exploringmars.model;

import java.util.UUID;

public interface SpacialStation {

    void sendGroundProbeToThePlanet(final GroundProbe groundProbe);

    void moveTo(final DriveCommand driveCommand, final UUID id);

}
