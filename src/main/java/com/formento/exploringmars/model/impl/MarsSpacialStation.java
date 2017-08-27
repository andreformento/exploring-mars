package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.SpacialStation;

public class MarsSpacialStation implements SpacialStation {

    private final Planet planet;

    public MarsSpacialStation(Planet planet) {
        this.planet = planet;
    }

    @Override
    public GroundProbe deployGroundProbeOnPlanet(Direction direction) {
        return new ExplorerGroundProbe(planet, direction);
    }

    @Override
    public void move(DriveCommand driveCommand, GroundProbe groundProbe) {
        driveCommand.move(groundProbe);
    }

}
