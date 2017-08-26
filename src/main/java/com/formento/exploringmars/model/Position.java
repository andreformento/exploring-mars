package com.formento.exploringmars.model;

public interface Position extends Comparable<Position> {

    Integer getX();

    Integer getY();


    Boolean isGreaterThanOrEqualTo(Position position);

    Boolean isLessThanOrEqualTo(Position position);

}
