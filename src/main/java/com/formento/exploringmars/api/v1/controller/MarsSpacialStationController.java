package com.formento.exploringmars.api.v1.controller;

import com.formento.exploringmars.api.v1.mapper.Journey;
import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.Position;
import com.formento.exploringmars.service.MarsSpacialStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spacials-stations/ground-probes")
public class MarsSpacialStationController {

    private final MarsSpacialStationService marsSpacialStationService;

    @Autowired
    public MarsSpacialStationController(MarsSpacialStationService marsSpacialStationService) {
        this.marsSpacialStationService = marsSpacialStationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Direction> getGroundProbes() {
        return marsSpacialStationService.getGroundProbes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Direction deployGroundProbeOnPlanet(@RequestBody final Direction direction) {
        return marsSpacialStationService.deployGroundProbeOnPlanet(direction);
    }

    @PutMapping(value = "/{x}/{y}/explore-planet-by-position")
    @ResponseStatus(HttpStatus.OK)
    public Direction explorePlanet(
            @PathVariable("x") final Integer x,
            @PathVariable("y") final Integer y,
            @RequestBody final List<DriveCommand> driveCommands
    ) {
        return marsSpacialStationService.explorePlanet(new Position(x, y), driveCommands);
    }

    @PostMapping("/explore-planet-by-jorney")
    @ResponseStatus(HttpStatus.OK)
    public Direction explorePlanet(@RequestBody final Journey journey) {
        return marsSpacialStationService.explorePlanet(journey.getDirection(), journey.getDriveCommands());
    }

}
