package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.GroundProbe;
import java.util.function.Consumer;

public enum DriveCommandDefault implements DriveCommand {
    MOVE(GroundProbe::goForward),
    TURN_LEFT(GroundProbe::turnLeft),
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