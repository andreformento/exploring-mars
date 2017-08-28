package com.formento.exploringmars.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationSenseTest {

    // goForward
    @Test
    public void shouldGoToTheNorth() {
        assertThat(NavigationSense.NORTH.goForward(new Position(1, 5))).isEqualTo(new Position(1, 6));
    }

    @Test
    public void shouldGoToTheSouth() {
        assertThat(NavigationSense.SOUTH.goForward(new Position(1, 5))).isEqualTo(new Position(1, 4));
    }

    @Test
    public void shouldGoToTheWest() {
        assertThat(NavigationSense.WEST.goForward(new Position(1, 5))).isEqualTo(new Position(0, 5));
    }

    @Test
    public void shouldGoToTheEast() {
        assertThat(NavigationSense.EAST.goForward(new Position(1, 5))).isEqualTo(new Position(2, 5));
    }

    // turn left
    @Test
    public void shouldPointAtWestWhenTurnLeftOnNorth() {
        assertThat(NavigationSense.NORTH.turnLeft()).isEqualTo(NavigationSense.WEST);
    }

    @Test
    public void shouldPointAtEastWhenTurnLeftOnSouth() {
        assertThat(NavigationSense.SOUTH.turnLeft()).isEqualTo(NavigationSense.EAST);
    }

    @Test
    public void shouldPointAtSouthWhenTurnLeftOnWest() {
        assertThat(NavigationSense.WEST.turnLeft()).isEqualTo(NavigationSense.SOUTH);
    }

    @Test
    public void shouldPointAtNorthWhenTurnLeftOnEast() {
        assertThat(NavigationSense.EAST.turnLeft()).isEqualTo(NavigationSense.NORTH);
    }

    // turn right
    @Test
    public void shouldPointAtEastWhenTurnRightOnNorth() {
        assertThat(NavigationSense.NORTH.turnRight()).isEqualTo(NavigationSense.EAST);
    }

    @Test
    public void shouldPointAtWestWhenTurnRightOnSouth() {
        assertThat(NavigationSense.SOUTH.turnRight()).isEqualTo(NavigationSense.WEST);
    }

    @Test
    public void shouldPointAtNorthWhenTurnRightOnWest() {
        assertThat(NavigationSense.WEST.turnRight()).isEqualTo(NavigationSense.NORTH);
    }

    @Test
    public void shouldPointAtSouthWhenTurnRightOnEast() {
        assertThat(NavigationSense.EAST.turnRight()).isEqualTo(NavigationSense.SOUTH);
    }

}
