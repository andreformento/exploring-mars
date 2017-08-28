package com.formento.exploringmars.model;

import com.formento.exploringmars.infra.BusinessException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanetAreaTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PlanetArea planetArea;
    @Mock
    private Position leftBottom;
    @Mock
    private Position rightTop;
    @Mock
    private Position position;

    @Before
    public void init() {
        this.planetArea = new PlanetArea(leftBottom, rightTop);
    }

    @Test
    @edu.umd.cs.findbugs.annotations.SuppressWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
    public void shouldBeValidWhenIsInsideOfBoundary() {
        when(position.isGreaterThanOrEqualTo(leftBottom)).thenReturn(true);
        when(position.isLessThanOrEqualTo(rightTop)).thenReturn(true);

        planetArea.validateBoundary(position);

        verify(position).isGreaterThanOrEqualTo(eq(leftBottom));
        verify(position).isLessThanOrEqualTo(eq(rightTop));
    }

    @Test
    public void shouldNotBeValidWhenIsOutsideOfLeftBottomBoundary() {
        when(position.isGreaterThanOrEqualTo(leftBottom)).thenReturn(false);
        when(position.isLessThanOrEqualTo(rightTop)).thenReturn(true);

        when(position.toString()).thenReturn("(1, 2)");
        when(leftBottom.toString()).thenReturn("(3, 4)");

        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("The position (1, 2) is outside of the area that begin at (3, 4)");

        planetArea.validateBoundary(position);
    }

    @Test
    public void shouldNotBeValidWhenIsOutsideOfRightBottomBoundary() {
        when(position.isGreaterThanOrEqualTo(leftBottom)).thenReturn(true);
        when(position.isLessThanOrEqualTo(rightTop)).thenReturn(false);

        when(position.toString()).thenReturn("(100, 200)");
        when(rightTop.toString()).thenReturn("(7, 8)");

        expectedException.expect(BusinessException.class);
        expectedException.expectMessage("The position (100, 200) is outside of the area that end at (7, 8)");

        planetArea.validateBoundary(position);
    }

}
