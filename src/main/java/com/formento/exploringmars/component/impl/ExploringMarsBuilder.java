package com.formento.exploringmars.component.impl;

import com.formento.exploringmars.component.ExploringPlanetBuilder;
import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.PlanetArea;
import com.formento.exploringmars.model.Position;
import com.formento.exploringmars.model.SpacialStation;
import com.formento.exploringmars.model.impl.MarsPlanet;
import com.formento.exploringmars.model.impl.MarsPosition;
import com.formento.exploringmars.model.impl.MarsSpacialStation;
import com.formento.exploringmars.model.impl.RectangularPlanetArea;
import org.springframework.stereotype.Component;

@Component
public class ExploringMarsBuilder implements ExploringPlanetBuilder {

    public PlanetAreaBuilderImpl withPlanetAreaLimits(Integer rightTopX, Integer rightTopY) {
        return new PlanetAreaBuilderImpl(rightTopX, rightTopY);
    }

    public static class PlanetAreaBuilderImpl implements PlanetAreaBuilder {

        private final Position leftBottom;
        private final Position rightTop;

        private PlanetAreaBuilderImpl(Integer rightTopX, Integer rightTopY) {
            this.leftBottom = new MarsPosition(0, 0);
            this.rightTop = new MarsPosition(rightTopX, rightTopY);
        }

        public PlanetBuilder withPlanet() {
            return new PlanetBuilderImpl(new RectangularPlanetArea(leftBottom, rightTop));
        }
    }

    public static class PlanetBuilderImpl implements PlanetBuilder {

        private final PlanetArea planetArea;

        private PlanetBuilderImpl(PlanetArea planetArea) {
            this.planetArea = planetArea;
        }

        public SpacialStationBuilder withSpacialStation() {
            return new SpacialStationBuilderImpl(new MarsPlanet(planetArea));
        }
    }

    public static class SpacialStationBuilderImpl implements SpacialStationBuilder {

        private final Planet planet;

        private SpacialStationBuilderImpl(Planet planet) {
            this.planet = planet;
        }

        public SpacialStation build() {
            return new MarsSpacialStation(planet);
        }
    }

}
