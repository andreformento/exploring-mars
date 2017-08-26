package com.formento.exploringmars.model;

@FunctionalInterface
public interface DriveCommand {

    void move(final GroundProbe groundProbe);

}
