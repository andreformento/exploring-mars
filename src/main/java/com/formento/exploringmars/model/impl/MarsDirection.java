package com.formento.exploringmars.model.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Position;

public class MarsDirection implements Direction {

    private static final long serialVersionUID = -3446981827308500686L;
    private final Position position;
    private final NavigationSense navigationSense;

    @JsonCreator
    public MarsDirection(
        @JsonProperty(value = "position") @JsonUnwrapped @JsonDeserialize(as = MarsPosition.class) Position position,
        @JsonProperty(value = "navigationSense") @JsonDeserialize(as = WindRose.class) NavigationSense navigationSense
    ) {
        this.position = position;
        this.navigationSense = navigationSense;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public NavigationSense getNavigationSense() {
        return navigationSense;
    }
}
