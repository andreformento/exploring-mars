package com.formento.exploringmars.config;

import com.formento.exploringmars.component.ExploringMarsBuilder;
import com.formento.exploringmars.config.MarsPlanetAreaConfig.Limit;
import com.formento.exploringmars.model.SpacialStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExploringMarsConfig {

    @Bean
    @Autowired
    public SpacialStation getSpacialStation(MarsPlanetAreaConfig marsPlanetAreaConfig, ExploringMarsBuilder exploringMarsBuilder) {
        final Limit limit = marsPlanetAreaConfig.getLimit();
        return exploringMarsBuilder.
            withPlanetAreaLimits(limit.getX(), limit.getY()).
            withPlanet().
            withSpacialStation().
            build();
    }

}
