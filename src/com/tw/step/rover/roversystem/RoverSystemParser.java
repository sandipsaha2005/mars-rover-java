package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.Boundary;
import com.tw.step.rover.commands.CommandCreator;
import com.tw.step.rover.commands.RoverCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;

import java.util.HashMap;
import java.util.Map;

public class RoverSystemParser {
    private final RoverSystemScanner scanner;
    private final Navigator navigator;
    private final Boundary boundary;
    private final CommandCreator commandCreator;

    public RoverSystemParser(RoverSystemScanner scanner, Navigator navigator, Boundary boundary, CommandCreator commandCreator) {
        this.scanner = scanner;
        this.navigator = navigator;
        this.boundary = boundary;
        this.commandCreator = commandCreator;
    }

    private Rover extractRover(String roverId) {
        Coordinate coordinate = scanner.scanCoordinate();
        Direction heading = scanner.scanDirection();
        return  new Rover(roverId, coordinate, heading);
    }

    private Map<String, Rover> parseRover() {
        Map<String, Rover> rovers = new HashMap<String, Rover>();

        while (!scanner.peek().endsWith(":")) {
            String roverId = scanner.scanRoverId();
            rovers.put(roverId, extractRover(roverId));
        }

        return  rovers;
    }

    public RoverSystem parse() {
        RoverSystem roverSystem = new RoverSystem();
        Map<String, Rover> rovers = parseRover();
        roverSystem.addRover(rovers);
        Map<String, RoverCommands> roversCommands = parseRoverCommands();
        roverSystem.addCommands(roversCommands);
        return roverSystem;
    }

    private RoverCommands extractCommand() {
        RoverCommands roverCommands = new RoverCommands();
        String instructions = scanner.consume();

        for (int i = 0; i < instructions.length(); i++) {
            RoverCommand roverCommand = commandCreator.create(instructions.charAt(i), navigator, boundary);
            roverCommands.add(roverCommand);
        }
        return roverCommands;
    }

    private Map<String, RoverCommands> parseRoverCommands() {
        Map<String, RoverCommands> roversCommands= new HashMap<>();

        while (!scanner.isDone() && scanner.peek().endsWith(":")) {
            roversCommands.put(scanner.scanRoverId(), extractCommand());
        }

        return roversCommands;
    }
}
