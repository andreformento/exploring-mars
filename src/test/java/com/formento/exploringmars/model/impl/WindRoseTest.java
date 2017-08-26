package com.formento.exploringmars.model.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WindRoseTest {

    // goForward
    @Test
    public void shouldGoToTheNorth() {
        assertThat(WindRose.NORTH.goForward(new MarsPosition(1, 5))).isEqualTo(new MarsPosition(1, 6));
    }

    @Test
    public void shouldGoToTheSouth() {
        assertThat(WindRose.SOUTH.goForward(new MarsPosition(1, 5))).isEqualTo(new MarsPosition(1, 4));
    }

    @Test
    public void shouldGoToTheWest() {
        assertThat(WindRose.WEST.goForward(new MarsPosition(1, 5))).isEqualTo(new MarsPosition(0, 5));
    }

    @Test
    public void shouldGoToTheEast() {
        assertThat(WindRose.EAST.goForward(new MarsPosition(1, 5))).isEqualTo(new MarsPosition(2, 5));
    }

    // turn left
    @Test
    public void shouldPointAtWestWhenTurnLeftOnNorth() {
        assertThat(WindRose.NORTH.turnLeft()).isEqualTo(WindRose.WEST);
    }

    @Test
    public void shouldPointAtEastWhenTurnLeftOnSouth() {
        assertThat(WindRose.SOUTH.turnLeft()).isEqualTo(WindRose.EAST);
    }

    @Test
    public void shouldPointAtSouthWhenTurnLeftOnWest() {
        assertThat(WindRose.WEST.turnLeft()).isEqualTo(WindRose.SOUTH);
    }

    @Test
    public void shouldPointAtNorthWhenTurnLeftOnEast() {
        assertThat(WindRose.EAST.turnLeft()).isEqualTo(WindRose.NORTH);
    }

    // turn right
    @Test
    public void shouldPointAtEastWhenTurnRightOnNorth() {
        assertThat(WindRose.NORTH.turnRight()).isEqualTo(WindRose.EAST);
    }

    @Test
    public void shouldPointAtWestWhenTurnRightOnSouth() {
        assertThat(WindRose.SOUTH.turnRight()).isEqualTo(WindRose.WEST);
    }

    @Test
    public void shouldPointAtNorthWhenTurnRightOnWest() {
        assertThat(WindRose.WEST.turnRight()).isEqualTo(WindRose.NORTH);
    }

    @Test
    public void shouldPointAtSouthWhenTurnRightOnEast() {
        assertThat(WindRose.EAST.turnRight()).isEqualTo(WindRose.SOUTH);
    }

}
