package com.formento.exploringmars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.function.Consumer;

public enum DriveCommand {
    @JsonProperty("M")
    MOVE(groundProbe -> {
        groundProbe.goForward();
    }),

    @JsonProperty("L")
    TURN_LEFT(groundProbe -> {
        groundProbe.turnLeft();
    }),

    @JsonProperty("R")
    TURN_RIGHT(GroundProbe::turnRight);

    private final Consumer<GroundProbe> moviment;

    DriveCommand(Consumer<GroundProbe> moviment) {
        this.moviment = moviment;
    }

    public final void move(GroundProbe groundProbe) {
        moviment.accept(groundProbe);
    }

}
