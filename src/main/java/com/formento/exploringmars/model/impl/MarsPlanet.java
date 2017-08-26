package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.GroundProbe;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.Position;

public class MarsPlanet implements Planet {

    private final Position leftBottom;
    private final Position rightTop;

    public MarsPlanet(Position leftBottom, Position rightTop) {
        this.leftBottom = leftBottom;
        this.rightTop = rightTop;
    }

    @Override
    public void landing(GroundProbe groundProbe, Position position) {

    }

    @Override
    public void changePosition(Position currentPosition, Position newPosition) {

    }

}
