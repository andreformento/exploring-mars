package com.formento.exploringmars.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

public class Direction implements Serializable {

    private static final long serialVersionUID = -3446981827308500686L;
    private final Position position;
    private final NavigationSense navigationSense;

    @JsonCreator
    public Direction(
            @JsonProperty(value = "position") @JsonUnwrapped Position position,
            @JsonProperty(value = "navigationSense") NavigationSense navigationSense
    ) {
        this.position = position;
        this.navigationSense = navigationSense;
    }

    public Position getPosition() {
        return position;
    }

    public NavigationSense getNavigationSense() {
        return navigationSense;
    }

}
