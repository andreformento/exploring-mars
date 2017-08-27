package com.formento.exploringmars.model;

import java.io.Serializable;

public interface Position extends Comparable<Position>, Serializable {

    Integer getX();

    Integer getY();

    Boolean isGreaterThanOrEqualTo(Position position);

    Boolean isLessThanOrEqualTo(Position position);

}
