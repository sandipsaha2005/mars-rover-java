package com.tw.step.rover.roversystem;

import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.rover.Rover;

import java.util.HashMap;
import java.util.Map;

public class RoverSystem {
    private HashMap<String,Rover> rovers;
    private RoverCommands roverCommands;

    public void addRover(Map<String, Rover> rover) {
        this.rovers.putAll(rovers);
    }

    public void addCommands(RoverCommands roverCommands) {
        this.roverCommands = roverCommands;
    }

    public void execute(String id) {
        this.roverCommands.execute(this.rovers.get(id));
    }

    @Override
    public String toString() {
        return rovers.toString();
    }
}
