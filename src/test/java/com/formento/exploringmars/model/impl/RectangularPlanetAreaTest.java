package com.formento.exploringmars.model.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.formento.exploringmars.model.Position;
import com.formento.exploringmars.model.impl.MarsPlanet;
import org.junit.Before;
import org.mockito.Mock;

public class RectangularPlanetAreaTest {
    @Mock
    private Position leftBottom;
    @Mock
    private Position rightTop;

    @Before
    public void init() {
        when(leftBottom.getX()).thenReturn(0);
        when(leftBottom.getY()).thenReturn(0);
        when(rightTop.getX()).thenReturn(3);
        when(rightTop.getY()).thenReturn(4);
    }
}
