package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.Position;

public class ExplorerGroundProbe implements GroundProbe {

    private final Planet planet;
    private Position position;
    private NavigationSense navigationSense;

    public ExplorerGroundProbe(Planet planet, Position position, NavigationSense navigationSense) {
        this.planet = planet;
        this.position = position;
        this.navigationSense = navigationSense;

        this.planet.landing(position, this);
    }

    @Override
    public Position goForward() {
        final Position newPosition = navigationSense.goForward(position);
        planet.changePosition(position, newPosition);
        this.position = newPosition;
        return position;
    }

    @Override
    public NavigationSense turnLeft() {
        this.navigationSense = navigationSense.turnLeft();
        return this.navigationSense;
    }

    @Override
    public NavigationSense turnRight() {
        this.navigationSense = navigationSense.turnRight();
        return this.navigationSense;
    }

}
