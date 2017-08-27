package com.formento.exploringmars.model;

import java.util.List;
import java.util.Optional;

public interface Planet {

    void landing(Position position, GroundProbe groundProbe);

    void changePosition(Position currentPosition, Position newPosition);

    List<GroundProbe> getGroundProbes();

    Optional<GroundProbe> getByPosition(Position position);

}
