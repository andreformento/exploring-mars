package com.formento.exploringmars.model;

public interface NavigationSense {

    Position goForward(Position currentPosition);

    NavigationSense turnLeft();

    NavigationSense turnRight();

}
