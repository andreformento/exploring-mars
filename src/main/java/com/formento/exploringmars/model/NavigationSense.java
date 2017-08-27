package com.formento.exploringmars.model;

import java.io.Serializable;

public interface NavigationSense extends Serializable {

    Position goForward(Position currentPosition);

    NavigationSense turnLeft();

    NavigationSense turnRight();

}
