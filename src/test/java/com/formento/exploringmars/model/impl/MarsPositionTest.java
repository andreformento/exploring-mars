package com.formento.exploringmars.model.impl;

import static org.assertj.core.api.Assertions.assertThat;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

// implement mutant test -> http://pitest.org
public class MarsPositionTest {

    private MarsPosition marsPosition;

    @Before
    public void init() {
        this.marsPosition = new MarsPosition(1, 2);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(MarsPosition.class).verify();
    }

    @Test
    public void shouldBeEqualWithXAndY() {
        final MarsPosition position = new MarsPosition(1, 2);
        assertThat(marsPosition.isGreaterThanOrEqualTo(position)).isTrue();
        assertThat(marsPosition.isLessThanOrEqualTo(position)).isTrue();
    }

    @Test
    public void shouldBeGreaterWithXAndY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new MarsPosition(0, 0))).isTrue();
    }

    @Test
    public void shouldBeGreaterWithX() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new MarsPosition(1, 0))).isTrue();
    }

    @Test
    public void shouldBeGreaterWithY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new MarsPosition(0, 1))).isTrue();
    }

    @Test
    public void shouldNotBeGreaterWithXAndY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new MarsPosition(2, 3))).isFalse();
    }

    @Test
    public void shouldNotBeGreaterWithX() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new MarsPosition(2, 0))).isFalse();
    }

    @Test
    public void shouldNotBeGreaterWithY() {
        assertThat(marsPosition.isGreaterThanOrEqualTo(new MarsPosition(0, 3))).isFalse();
    }

    @Test
    public void shouldBeLessWithXAndY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new MarsPosition(2, 3))).isTrue();
    }

    @Test
    public void shouldBeLessWithX() {
        assertThat(marsPosition.isLessThanOrEqualTo(new MarsPosition(2, 2))).isTrue();
    }

    @Test
    public void shouldBeLessWithY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new MarsPosition(1, 3))).isTrue();
    }

    @Test
    public void shouldNotBeLessWithXAndY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new MarsPosition(0, 0))).isFalse();
    }

    @Test
    public void shouldNotBeLessWithX() {
        assertThat(marsPosition.isLessThanOrEqualTo(new MarsPosition(1, 0))).isFalse();
    }

    @Test
    public void shouldNotBeLessWithY() {
        assertThat(marsPosition.isLessThanOrEqualTo(new MarsPosition(0, 1))).isFalse();
    }

    @Test
    public void shouldComparePosition() {
        assertThat(marsPosition.compareTo(new MarsPosition(1, 2))).isEqualTo(0);

        assertThat(marsPosition.compareTo(new MarsPosition(4, 2))).isEqualTo(-1);
        assertThat(marsPosition.compareTo(new MarsPosition(1, 3))).isEqualTo(-1);

        assertThat(marsPosition.compareTo(new MarsPosition(1, 0))).isEqualTo(1);
        assertThat(marsPosition.compareTo(new MarsPosition(0, 2))).isEqualTo(1);
    }
}
