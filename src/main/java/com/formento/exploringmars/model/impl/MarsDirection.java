package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Position;
import java.beans.ConstructorProperties;

public class MarsDirection implements Direction {

    private static final long serialVersionUID = -3446981827308500686L;
    private final Position position;
    private final NavigationSense navigationSense;

    @ConstructorProperties({"direction", "driveCommands"})
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
