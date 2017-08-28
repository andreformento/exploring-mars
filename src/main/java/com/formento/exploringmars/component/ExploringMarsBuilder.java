package com.formento.exploringmars.component;

import com.formento.exploringmars.model.Planet;
import com.formento.exploringmars.model.PlanetArea;
import com.formento.exploringmars.model.Position;
import com.formento.exploringmars.model.SpacialStation;
import org.springframework.stereotype.Component;

@Component
public class ExploringMarsBuilder {

    public PlanetAreaBuilderImpl withPlanetAreaLimits(Integer rightTopX, Integer rightTopY) {
        return new PlanetAreaBuilderImpl(rightTopX, rightTopY);
    }

    public static class PlanetAreaBuilderImpl {

        private final Position leftBottom;
        private final Position rightTop;

        private PlanetAreaBuilderImpl(Integer rightTopX, Integer rightTopY) {
            this.leftBottom = new Position(0, 0);
            this.rightTop = new Position(rightTopX, rightTopY);
        }

        public PlanetBuilderImpl withPlanet() {
            return new PlanetBuilderImpl(new PlanetArea(leftBottom, rightTop));
        }
    }

    public static class PlanetBuilderImpl {

        private final PlanetArea planetArea;

        private PlanetBuilderImpl(PlanetArea planetArea) {
            this.planetArea = planetArea;
        }

        public SpacialStationBuilderImpl withSpacialStation() {
            return new SpacialStationBuilderImpl(new Planet(planetArea));
        }
    }

    public static class SpacialStationBuilderImpl {

        private final Planet planet;

        private SpacialStationBuilderImpl(Planet planet) {
            this.planet = planet;
        }

        public SpacialStation build() {
            return new SpacialStation(planet);
        }
    }

}
