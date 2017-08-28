package com.formento.exploringmars.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// implement mutant test -> http://pitest.org
public class PositionTest {

    private Position marsPosition;

    @Before
    public void init() {
        this.marsPosition = new Position(1, 2);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Position.class).verify();
    }

    @Test
    public void shouldBeEqualWithXAndY() {
        final Position position = new Position(1, 2);
        assertThat(marsPosition.isGreaterThanOrEqualTo(position)).isTrue();
        assertThat(marsPosition.isLessThanOrEqualTo(position)).isTrue();
    }

    @Test
    public void shouldBeGreaterWithXAndY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new Position(0, 0))).isTrue();
    }

    @Test
    public void shouldBeGreaterWithX() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new Position(1, 0))).isTrue();
    }

    @Test
    public void shouldBeGreaterWithY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new Position(0, 1))).isTrue();
    }

    @Test
    public void shouldNotBeGreaterWithXAndY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new Position(2, 3))).isFalse();
    }

    @Test
    public void shouldNotBeGreaterWithX() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new Position(2, 0))).isFalse();
    }

    @Test
    public void shouldNotBeGreaterWithY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new Position(0, 3))).isFalse();
    }

    @Test
    public void shouldBeLessWithXAndY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new Position(2, 3))).isTrue();
    }

    @Test
    public void shouldBeLessWithX() {
        assertThat(marsPosition.isLessThanOrEqualTo(new Position(2, 2))).isTrue();
    }

    @Test
    public void shouldBeLessWithY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new Position(1, 3))).isTrue();
    }

    @Test
    public void shouldNotBeLessWithXAndY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new Position(0, 0))).isFalse();
    }

    @Test
    public void shouldNotBeLessWithX() {
        assertThat(marsPosition.isLessThanOrEqualTo(new Position(1, 0))).isFalse();
    }

    @Test
    public void shouldNotBeLessWithY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new Position(0, 1))).isFalse();
    }

    @Test
    public void shouldComparePosition() {
        assertThat(marsPosition.compareTo(new Position(1, 2))).isEqualTo(0);

        assertThat(marsPosition.compareTo(new Position(4, 2))).isEqualTo(-1);
        assertThat(marsPosition.compareTo(new Position(1, 3))).isEqualTo(-1);

        assertThat(marsPosition.compareTo(new Position(1, 0))).isEqualTo(1);
        assertThat(marsPosition.compareTo(new Position(0, 2))).isEqualTo(1);
    }
}
