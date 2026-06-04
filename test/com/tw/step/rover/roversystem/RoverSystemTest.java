package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.commands.MoveCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverSystemTest {
    @Test
    void shouldExecuteCommandsForAddedRover() {
        RoverSystem roverSystem = new RoverSystem();
        Map<String, Rover> rovers = new HashMap<>();
        Rover rover = new Rover(new Coordinate(0, 0), Direction.N);
        rovers.put("x", rover);

        Map<String, RoverCommands> roversComands = new HashMap<>();
        RoverCommands commands = new RoverCommands();
        commands.add(new MoveCommand(Navigator.create(), new InfinitePlateau()));
        roversComands.put("x", commands);

        roverSystem.addRover(rovers);
        roverSystem.addCommands(roversComands);
        roverSystem.execute();


        Rover expectedRover = new Rover(new Coordinate(0, 0), Direction.N);
        assertEquals(expectedRover, rover);
    }
}
