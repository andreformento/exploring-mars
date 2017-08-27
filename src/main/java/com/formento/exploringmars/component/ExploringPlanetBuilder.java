package com.formento.exploringmars.component;

import com.formento.exploringmars.model.SpacialStation;

public interface ExploringPlanetBuilder {

    PlanetAreaBuilder withPlanetAreaLimits(Integer rightTopX, Integer rightTopY);

    interface PlanetAreaBuilder {

        PlanetBuilder withPlanet();
    }

    interface PlanetBuilder {

        SpacialStationBuilder withSpacialStation();
    }

    interface SpacialStationBuilder {

        SpacialStation build();
    }

}
