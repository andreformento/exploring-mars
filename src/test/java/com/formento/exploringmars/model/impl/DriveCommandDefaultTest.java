package com.formento.exploringmars.model.impl;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import com.formento.exploringmars.model.GroundProbe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DriveCommandDefaultTest {

    private DriveCommandDefault driveCommandDefault;
    @Mock
    private GroundProbe groundProbe;

    @Test
    public void shouldGoForward() {
        DriveCommandDefault.MOVE.move(groundProbe);
        verify(groundProbe, only()).goForward();
    }

    @Test
    public void shouldTurnLeft() {
        DriveCommandDefault.TURN_LEFT.move(groundProbe);
        verify(groundProbe, only()).turnLeft();
    }

    @Test
    public void shouldTurnRight() {
        DriveCommandDefault.TURN_RIGHT.move(groundProbe);
        verify(groundProbe, only()).turnRight();
    }

}
