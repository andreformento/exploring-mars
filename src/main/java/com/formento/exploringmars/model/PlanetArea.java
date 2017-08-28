package com.formento.exploringmars.model;

import com.formento.exploringmars.infra.BusinessException;

public class PlanetArea {

    private final Position leftBottom;
    private final Position rightTop;

    public PlanetArea(Position leftBottom, Position rightTop) {
        this.leftBottom = leftBottom;
        this.rightTop = rightTop;
    }

    public void validateBoundary(Position position) {
        if (!position.isGreaterThanOrEqualTo(leftBottom)) {
            throw new BusinessException("The position " + position.toString() + " is outside of the area that begin at " + leftBottom.toString());
        } else if (!position.isLessThanOrEqualTo(rightTop)) {
            throw new BusinessException("The position " + position.toString() + " is outside of the area that end at " + rightTop.toString());
        }
    }

}
