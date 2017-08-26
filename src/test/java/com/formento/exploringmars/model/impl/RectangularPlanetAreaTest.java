package com.formento.exploringmars.model.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.formento.exploringmars.infra.BusinessException;
import com.formento.exploringmars.model.Position;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RectangularPlanetAreaTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private RectangularPlanetArea planetArea;
    @Mock
    private Position leftBottom;
    @Mock
    private Position rightTop;

    @Test
    public void shouldBeValidWhenIsInsideOfBoundary() {
        final Position position = mock(Position.class);

        when(position.isGreaterThanOrEqualTo(leftBottom)).thenReturn(true);
        when(position.isLessThanOrEqualTo(rightTop)).thenReturn(true);

        planetArea.validateBoundary(position);

        verify(position, only()).isGreaterThanOrEqualTo(leftBottom);
        verify(position, only()).isLessThanOrEqualTo(rightTop);
    }

    @Test
    public void shouldNotBeValidWhenIsOutsideOfLeftBottomBoundary() {
        final Position position = mock(Position.class);

        when(position.isGreaterThanOrEqualTo(leftBottom)).thenReturn(false);
        when(position.isLessThanOrEqualTo(rightTop)).thenReturn(true);

        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("The point is less than left bottom");

        planetArea.validateBoundary(position);
    }

    @Test
    public void shouldNotBeValidWhenIsOutsideOfRightBottomBoundary() {
        final Position position = mock(Position.class);

        when(position.isGreaterThanOrEqualTo(leftBottom)).thenReturn(true);
        when(position.isLessThanOrEqualTo(rightTop)).thenReturn(false);

        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("The point is less than right top");

        planetArea.validateBoundary(position);
    }

}
