package com.karthik.jpm.marsrover.command;

import com.karthik.jpm.marsrover.exception.CommandException;
import com.karthik.jpm.marsrover.rover.Rover;

public class MovementCommand implements Command {

    private String movementCommands;
    private Rover rover;

    public MovementCommand(final Rover rover, final String movementCommands) {
        this.rover = rover;
        this.movementCommands = movementCommands;
    }

    @Override
    public Rover execute() throws CommandException {
        String[] movementCommand = movementCommands.split(",");
        for (String command : movementCommand) {
            switch (command) {
                case "f":
                    rover.moveForward();
                    break;
                case "l":
                    rover.turnLeft();
                    break;
                case "r":
                    rover.turnRight();
                    break;
                case "b":
                    rover.moveBackward();
                    break;
                default:
                    throw new CommandException("Not a valid command!");
            }
        }
        return rover;
    }

}
