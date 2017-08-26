package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.PlanetArea;
import com.formento.exploringmars.model.Position;

public class PlanetAreaImpl implements PlanetArea {
    private final Position leftBottom;
    private final Position rightTop;

    public PlanetAreaImpl(Position leftBottom, Position rightTop) {
        this.leftBottom = leftBottom;
        this.rightTop = rightTop;
    }

    @Override
    public void validateBoundary(Position position) {

    }
}
