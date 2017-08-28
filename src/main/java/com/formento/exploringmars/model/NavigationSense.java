package com.formento.exploringmars.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.function.Function;

public enum NavigationSense {
    @JsonProperty("N")
    NORTH(
            currentPosition -> new Position(currentPosition.getX(), currentPosition.getY() + 1)) {
        public NavigationSense turnLeft() {
            return WEST;
        }

        public NavigationSense turnRight() {
            return EAST;
        }
    },

    @JsonProperty("E")
    EAST(
            currentPosition -> new Position(currentPosition.getX() + 1, currentPosition.getY())) {
        public NavigationSense turnLeft() {
            return NORTH;
        }

        public NavigationSense turnRight() {
            return SOUTH;
        }
    },

    @JsonProperty("S")
    SOUTH(
            currentPosition -> new Position(currentPosition.getX(), currentPosition.getY() - 1)) {
        public NavigationSense turnLeft() {
            return EAST;
        }

        public NavigationSense turnRight() {
            return WEST;
        }
    },

    @JsonProperty("W")
    WEST(
            currentPosition -> new Position(currentPosition.getX() - 1, currentPosition.getY())) {
        public NavigationSense turnLeft() {
            return SOUTH;
        }

        public NavigationSense turnRight() {
            return NORTH;
        }
    };

    private transient final Function<Position, Position> goForward;

    NavigationSense(Function<Position, Position> goForward) {
        this.goForward = goForward;
    }

    public final Position goForward(Position currentPosition) {
        return goForward.apply(currentPosition);
    }

    public abstract NavigationSense turnLeft();

    public abstract NavigationSense turnRight();
}
