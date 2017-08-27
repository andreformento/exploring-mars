package com.formento.exploringmars.service;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;
import com.formento.exploringmars.model.Position;
import java.util.List;

public interface SpacialStationService {

    Direction explorePlanet(Direction initialDirection, List<? extends DriveCommand> driveCommands);

    Direction explorePlanet(Position position, List<? extends DriveCommand> driveCommands);

    List<Direction> getGroundProbes();

    Direction deployGroundProbeOnPlanet(Direction direction);

}
