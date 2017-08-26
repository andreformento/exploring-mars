package com.formento.exploringmars.model;

public interface GroundProbe {

    Position goForward(final Planet planet);

    Position turnLeft(final Planet planet);

    Position turnRight(final Planet planet);

}
