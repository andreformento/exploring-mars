package com.formento.exploringmars.model;

import java.util.List;

public interface Planet {

    void landing(Position position, GroundProbe groundProbe);

    void changePosition(Position currentPosition, Position newPosition);

    List<GroundProbe> getGroundProbes();

}
