package com.formento.exploringmars.model;

public interface GroundProbe {

    Position goForward();

    NavigationSense turnLeft();

    NavigationSense turnRight();

    Direction getCurrentDirection();

}
