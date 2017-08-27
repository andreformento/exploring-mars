package com.formento.exploringmars.api.v1.controller;

import com.formento.exploringmars.api.v1.mapper.Journey;
import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.impl.DriveCommandDefault;
import com.formento.exploringmars.model.impl.MarsDirection;
import com.formento.exploringmars.model.impl.MarsPosition;
import com.formento.exploringmars.service.SpacialStationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spacial-station")
public class MarsSpacialStationController {

    private final SpacialStationService spacialStationService;

    @Autowired
    public MarsSpacialStationController(SpacialStationService spacialStationService) {
        this.spacialStationService = spacialStationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Direction> getGroundProbes() {
        return spacialStationService.getGroundProbes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Direction deployGroundProbeOnPlanet(@RequestBody final MarsDirection direction) {
        return spacialStationService.deployGroundProbeOnPlanet(direction);
    }

    @PostMapping(value = "/explore-planet/{x}/{y}")
    @ResponseStatus(HttpStatus.OK)
    public Direction explorePlanet(
        @PathVariable("x") final Integer x,
        @PathVariable("y") final Integer y,
        @RequestBody final List<DriveCommandDefault> driveCommands
    ) {
        return spacialStationService.explorePlanet(new MarsPosition(x, y), driveCommands);
    }

    @PostMapping("/explore-planet")
    @ResponseStatus(HttpStatus.OK)
    public Direction explorePlanet(@RequestBody final Journey journey) {
        return spacialStationService.explorePlanet(journey.getDirection(), journey.getDriveCommands());
    }

}
