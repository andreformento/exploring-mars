package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Position;

public class MarsDirection implements Direction {

    private final Position position;
    private final NavigationSense navigationSense;

    public MarsDirection(Position position, NavigationSense navigationSense) {
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
