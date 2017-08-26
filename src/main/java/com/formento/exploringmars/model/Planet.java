package com.formento.exploringmars.model;

public interface Planet {

    void landing(final GroundProbe groundProbe, final Position position);

    void changePosition(final Position currentPosition, final Position newPosition);

}
