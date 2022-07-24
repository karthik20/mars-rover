package com.karthik.jpm.marsrover.command;

import com.karthik.jpm.marsrover.Plateau;
import com.karthik.jpm.marsrover.Position;
import com.karthik.jpm.marsrover.Position.Direction;
import com.karthik.jpm.marsrover.rover.Rover;

public class PositionalCommand implements Command {

    private int roverNumber;
    private Position position;
    private Plateau plateau;

    public PositionalCommand(Plateau plateau, int roverNumber, final String positionCommand) {
        this.roverNumber = roverNumber;
        String[] positionCommandArray = positionCommand.split(",");
        int x = Integer.parseInt(positionCommandArray[0]);
        int y = Integer.parseInt(positionCommandArray[1]);
        String direction = positionCommandArray[2];
        this.position = Position.builder()
                .withCoordinates(x, y)
                .withDirection(Direction.fromCode(direction))
                .build();
        this.plateau = plateau;
    }

    public Rover execute() {
        Rover rover = new Rover(plateau, roverNumber, position);
        return rover;
    }

    public Position getPosition() {
        return position;
    }
}
