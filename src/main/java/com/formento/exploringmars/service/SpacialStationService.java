package com.formento.exploringmars.service;

import com.formento.exploringmars.model.Direction;
import com.formento.exploringmars.model.DriveCommand;
import java.util.List;

public interface SpacialStationService {

    Direction explorePlanet(Direction initialDirection, List<? extends DriveCommand> driveCommands);

}
