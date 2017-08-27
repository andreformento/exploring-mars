package com.formento.exploringmars.api.v1.mapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.formento.exploringmars.model.impl.DriveCommandDefault;
import com.formento.exploringmars.model.impl.MarsDirection;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.List;

public class Journey implements Serializable {

    private static final long serialVersionUID = 8171021333727164953L;
    private final MarsDirection direction;
    private final List<DriveCommandDefault> driveCommands;

    @JsonCreator
    public Journey(
        @JsonProperty(value = "direction") @JsonUnwrapped final MarsDirection direction,
        @JsonProperty(value = "driveCommands") final List<DriveCommandDefault> driveCommands) {
        this.direction = direction;
        this.driveCommands = driveCommands;
    }

    @JsonUnwrapped
    public MarsDirection getDirection() {
        return direction;
    }

    public List<DriveCommandDefault> getDriveCommands() {
        return driveCommands;
    }
}
