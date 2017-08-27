package com.formento.exploringmars.api.v1.controller;

import com.formento.exploringmars.api.v1.mapper.Journey;
import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.service.impl.MarsSpacialStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spacial-station")
public class MarsSpacialStationController {

    private final MarsSpacialStationService marsSpacialStationService;

    @Autowired
    public MarsSpacialStationController(MarsSpacialStationService marsSpacialStationService) {
        this.marsSpacialStationService = marsSpacialStationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Direction explorePlanet(@RequestBody final Journey journey) {
        return marsSpacialStationService.explorePlanet(journey.getDirection(), journey.getDriveCommands());
    }

}
