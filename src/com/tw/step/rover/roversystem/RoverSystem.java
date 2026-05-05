package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.rover.Rover;

import java.util.HashMap;
import java.util.Map;

public class RoverSystem {
    private Map<String,Rover> rovers = new HashMap<>();;
    private Map<String, RoverCommands> roversCommands = new HashMap<>();

    public void addRover(Map<String, Rover> rover) {
        this.rovers.putAll(rover);
    }

    public void addCommands(Map<String, RoverCommands> commands) {
        this.roversCommands.putAll(commands);
    }

    public void execute() {
        this.rovers.forEach((s, rover) -> {
            this.roversCommands.get(s).execute(rover);
        });
    }

    @Override
    public String toString() {
        return rovers.toString();
    }
}
