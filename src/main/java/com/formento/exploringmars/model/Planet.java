package com.formento.exploringmars.model;

public interface Planet {

    void landing(Position position, GroundProbe groundProbe);

    void changePosition(Position currentPosition, Position newPosition);

}
