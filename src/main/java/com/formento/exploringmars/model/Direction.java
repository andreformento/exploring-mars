package com.formento.exploringmars.model;

import java.io.Serializable;

public interface Direction extends Serializable {

    Position getPosition();

    NavigationSense getNavigationSense();

}
