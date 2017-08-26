package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.Position;
import java.util.Objects;

public class MarsPosition implements Position {

    private final Integer x;
    private final Integer y;

    public MarsPosition(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer getX() {
        return this.x;
    }

    @Override
    public Integer getY() {
        return this.y;
    }

    @Override
    public Boolean isGreaterThanOrEqualTo(Position that) {
        return this.getX().compareTo(that.getX()) >= 0 &&
            this.getY().compareTo(that.getY()) >= 0;
    }

    @Override
    public Boolean isLessThanOrEqualTo(Position that) {
        return this.getX().compareTo(that.getX()) <= 0 &&
            this.getY().compareTo(that.getY()) <= 0;

    }

    @Override
    public int compareTo(Position that) {
        final int result = this.getX().compareTo(that.getX());
        if (result == 0) {
            return this.getY().compareTo(that.getY());
        } else {
            return result;
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarsPosition)) {
            return false;
        }
        MarsPosition that = (MarsPosition) o;
        return Objects.equals(x, that.x) &&
            Objects.equals(y, that.y);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
