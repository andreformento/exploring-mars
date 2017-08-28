package com.formento.exploringmars.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DriveCommandTest {

    @Mock
    private GroundProbe groundProbe;

    @Test
    public void shouldGoForward() {
        DriveCommand.MOVE.move(groundProbe);
        verify(groundProbe, only()).goForward();
    }

    @Test
    public void shouldTurnLeft() {
        DriveCommand.TURN_LEFT.move(groundProbe);
        verify(groundProbe, only()).turnLeft();
    }

    @Test
    public void shouldTurnRight() {
        DriveCommand.TURN_RIGHT.move(groundProbe);
        verify(groundProbe, only()).turnRight();
    }

}
