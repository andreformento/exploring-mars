package com.formento.exploringmars.model.impl;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class MarsPositionTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(MarsPosition.class).verify();
    }

}
