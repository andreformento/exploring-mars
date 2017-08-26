package com.formento.exploringmars.model.impl;

import com.formento.exploringmars.model.NavigationSense;
import com.formento.exploringmars.model.Position;
import java.util.function.Function;

public enum WindRose implements NavigationSense {
    NORTH(
        currentPosition -> new MarsPosition(currentPosition.getX(), currentPosition.getY() + 1)) {
        public NavigationSense turnLeft() {
            return WEST;
        }

        public NavigationSense turnRight() {
            return EAST;
        }
    },

    EAST(
        currentPosition -> new MarsPosition(currentPosition.getX() + 1, currentPosition.getY())) {
        public NavigationSense turnLeft() {
            return NORTH;
        }

        public NavigationSense turnRight() {
            return SOUTH;
        }
    },

    SOUTH(
        currentPosition -> new MarsPosition(currentPosition.getX(), currentPosition.getY() - 1)) {
        public NavigationSense turnLeft() {
            return EAST;
        }

        public NavigationSense turnRight() {
            return WEST;
        }
    },
    WEST(
        currentPosition -> new MarsPosition(currentPosition.getX() - 1, currentPosition.getY())) {
        public NavigationSense turnLeft() {
            return SOUTH;
        }

        public NavigationSense turnRight() {
            return NORTH;
        }
    };

    private final Function<Position, Position> goForward;

    WindRose(Function<Position, Position> goForward) {
        this.goForward = goForward;
    }

    @Override
    public final Position goForward(Position currentPosition) {
        return goForward.apply(currentPosition);
    }
}
