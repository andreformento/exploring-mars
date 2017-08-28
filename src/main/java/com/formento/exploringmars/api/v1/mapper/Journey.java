package com.formento.exploringmars.api.v1.mapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;

import java.io.Serializable;
import java.util.List;

public class Journey implements Serializable {

    private static final long serialVersionUID = 8171021333727164953L;
    private final Direction direction;
    private final List<DriveCommand> driveCommands;

    @JsonCreator
    public Journey(
            @JsonProperty(value = "direction") @JsonUnwrapped final Direction direction,
            @JsonProperty(value = "driveCommands") final List<DriveCommand> driveCommands) {
        this.direction = direction;
        this.driveCommands = driveCommands;
    }

    @JsonUnwrapped
    public Direction getDirection() {
        return direction;
    }

    public List<DriveCommand> getDriveCommands() {
        return driveCommands;
    }
}
