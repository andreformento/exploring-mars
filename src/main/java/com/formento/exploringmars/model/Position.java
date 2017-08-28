package com.formento.exploringmars.model;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {

    private static final long serialVersionUID = 8482059431419557367L;
    private final Integer x;
    private final Integer y;

    @ConstructorProperties({"x", "y"})
    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public Boolean isGreaterThanOrEqualTo(Position that) {
        return this.getX().compareTo(that.getX()) >= 0 &&
                this.getY().compareTo(that.getY()) >= 0;
    }

    public Boolean isLessThanOrEqualTo(Position that) {
        return this.getX().compareTo(that.getX()) <= 0 &&
                this.getY().compareTo(that.getY()) <= 0;

    }

    public int compareTo(Position that) {
        final int result = this.getX().compareTo(that.getX());
        if (result == 0) {
            return this.getY().compareTo(that.getY());
        } else {
            return result;
        }
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position that = (Position) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    public final int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
