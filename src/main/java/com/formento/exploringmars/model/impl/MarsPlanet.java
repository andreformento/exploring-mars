package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.PlanetArea;
import com.formento.exploringmars.model.Position;

public class MarsPlanet implements Planet {

    private final PlanetArea planetArea;

    public MarsPlanet(final PlanetArea planetArea) {
        this.planetArea = planetArea;
    }

    @Override
    public void landing(GroundProbe groundProbe, Position position) {

    }

    @Override
    public void changePosition(Position currentPosition, Position newPosition) {

    }

    private void boundaryValidation(Position position) {
    }

    private void removeFrom(Position currentPosition) {
    }

    private void putOn(Position eq, GroundProbe any) {
    }
}
