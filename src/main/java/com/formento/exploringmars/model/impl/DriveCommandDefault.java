package com.formento.exploringmars.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import java.util.function.Consumer;

public enum DriveCommandDefault implements DriveCommand {
    @JsonProperty("M")
    MOVE(GroundProbe::goForward),

    @JsonProperty("L")
    TURN_LEFT(GroundProbe::turnLeft),

    @JsonProperty("R")
    TURN_RIGHT(GroundProbe::turnRight);

    private final Consumer<GroundProbe> moviment;

    DriveCommandDefault(Consumer<GroundProbe> moviment) {
        this.moviment = moviment;
    }

    @Override
    public final void move(GroundProbe groundProbe) {
        moviment.accept(groundProbe);
    }

}
