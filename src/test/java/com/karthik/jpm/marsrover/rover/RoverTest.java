package com.karthik.jpm.marsrover.rover;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.karthik.jpm.marsrover.Plateau;
import com.karthik.jpm.marsrover.Position;
import com.karthik.jpm.marsrover.Position.Direction;

public class RoverTest {

    @Test
    void checkRoverInitialization() {
        Rover rover = new Rover(new Plateau(), 1,
                Position.builder()
                        .withCoordinates(3, 4)
                        .withDirection(Direction.EAST).build());
        assertEquals(1, rover.getNumber());
        assertEquals("3,4,E", rover.getPosition().toString());

    }

    @Test
    void checkForwardBackwardMovements() {
        Rover rover = new Rover(new Plateau(), 1,
                Position.builder()
                        .withCoordinates(3, 4)
                        .withDirection(Direction.NORTH).build());
        rover.moveForward();
        assertEquals("3,5,N", rover.getPosition().toString());
        rover.moveBackward();
        assertEquals("3,4,N", rover.getPosition().toString());
        rover.moveForward();
        rover.moveForward();
        assertEquals("3,6,N", rover.getPosition().toString());
    }

    @Test
    void checkRotationalMovements() {
        Rover rover = new Rover(new Plateau(), 1,
                Position.builder()
                        .withCoordinates(3, 4)
                        .withDirection(Direction.NORTH).build());
        rover.turnLeft();
        assertEquals("3,4,W", rover.getPosition().toString());
        rover.turnRight();
        assertEquals("3,4,N", rover.getPosition().toString());
        rover.turnLeft();
        rover.turnLeft();
        rover.turnLeft();
        assertEquals("3,4,E", rover.getPosition().toString());
        rover.turnRight();
        assertEquals("3,4,S", rover.getPosition().toString());
    }

    @Test
    void checkRoverPositionAfterMultipleMovements() {
        Rover rover = new Rover(new Plateau(), 1,
                Position.builder()
                        .withCoordinates(3, 4)
                        .withDirection(Direction.NORTH).build());
        rover.moveForward();
        rover.moveForward();
        rover.turnRight();
        rover.moveForward();
        rover.moveForward();
        assertEquals("5,6,E", rover.getPosition().toString());

        rover.moveBackward();
        rover.moveBackward();
        rover.turnLeft();
        rover.moveBackward();
        rover.moveBackward();
        assertEquals("3,4,N", rover.getPosition().toString());
    }

    @Test
    void shouldNotMoveRover_ifPositionIsNotVacant() {
        Plateau plateau = new Plateau();
        plateau.addRoverToPlateau(new Rover(new Plateau(), 1, Position.builder()
                .withCoordinates(3, 4)
                .withDirection(Direction.NORTH).build()));
        Rover rover = new Rover(plateau, 2,
                Position.builder()
                        .withCoordinates(3, 3)
                        .withDirection(Direction.NORTH).build());
        assertEquals("3,3,N", rover.getPosition().toString());
    }

}
