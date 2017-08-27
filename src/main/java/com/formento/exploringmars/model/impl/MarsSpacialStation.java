package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.Position;
import com.formento.exploringmars.model.SpacialStation;

public class MarsSpacialStation implements SpacialStation {

    private final Planet planet;

    public MarsSpacialStation(Planet planet) {
        this.planet = planet;
    }

    @Override
    public GroundProbe deployGroundProbeOnPlanet(Position position, NavigationSense navigationSense) {
        return new ExplorerGroundProbe(planet, position, navigationSense);
    }

    @Override
    public void move(DriveCommand driveCommand, GroundProbe groundProbe) {
        driveCommand.move(groundProbe);
    }

}
