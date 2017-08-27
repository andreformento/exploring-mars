package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.infra.BusinessException;
import com.formento.exploringmars.infra.NotFoundException;
import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.PlanetArea;
import com.formento.exploringmars.model.Position;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MarsPlanet implements Planet {

    private final PlanetArea planetArea;
    private final Map<Position, GroundProbe> groundProbes;

    public MarsPlanet(final PlanetArea planetArea) {
        this.planetArea = planetArea;
        this.groundProbes = new HashMap<>();
    }

    @Override
    public void landing(Position position, GroundProbe groundProbe) {
        validateNewPosition(position);
        putOn(position, groundProbe);
    }

    @Override
    public void changePosition(Position currentPosition, Position newPosition) {
        validateNewPosition(newPosition);
        final GroundProbe groundProbe = removeFrom(currentPosition);
        putOn(newPosition, groundProbe);
    }

    private void validateNewPosition(Position newPosition) {
        if (groundProbes.containsKey(newPosition)) {
            throw new BusinessException("The position " + newPosition.toString() + " is busy");
        }
        planetArea.validateBoundary(newPosition);
    }

    private GroundProbe removeFrom(Position currentPosition) {
        if (!groundProbes.containsKey(currentPosition)) {
            throw new NotFoundException("It is not possible remove because position " + currentPosition.toString() + " is empty");
        }
        return groundProbes.remove(currentPosition);
    }

    private void putOn(Position newPosition, GroundProbe groundProbe) {
        groundProbes.put(newPosition, groundProbe);
    }

    @Override
    public List<GroundProbe> getGroundProbes() {
        return ImmutableList.copyOf(groundProbes.values());
    }

    @Override
    public Optional<GroundProbe> getByPosition(Position position) {
        return Optional.ofNullable(groundProbes.get(position));
    }

}
