package com.formento.exploringmars.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class GroundProbe {

    private final Planet planet;
    private String id;
    private Position position;
    private NavigationSense navigationSense;

    public GroundProbe(Planet planet, Direction direction) {
        this(planet, direction.getPosition(), direction.getNavigationSense());
    }

    public GroundProbe(Planet planet, Position position, NavigationSense navigationSense) {
        this.planet = planet;
        this.position = position;
        this.navigationSense = navigationSense;

        this.planet.landing(position, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Position goForward() {
        final Position newPosition = navigationSense.goForward(position);
        planet.changePosition(position, newPosition);
        this.position = newPosition;
        return position;
    }

    public NavigationSense turnLeft() {
        this.navigationSense = navigationSense.turnLeft();
        return this.navigationSense;
    }

    public NavigationSense turnRight() {
        this.navigationSense = navigationSense.turnRight();
        return this.navigationSense;
    }

    @JsonUnwrapped
    public Direction getCurrentDirection() {
        return new Direction(position, navigationSense);
    }

}
