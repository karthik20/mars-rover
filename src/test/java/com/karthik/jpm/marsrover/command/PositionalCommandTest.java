package com.karthik.jpm.marsrover.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.karthik.jpm.marsrover.Plateau;
import com.karthik.jpm.marsrover.Position.Direction;
import com.karthik.jpm.marsrover.rover.Rover;

public class PositionalCommandTest {

    @Test
    public void testRoverPosition() {
        PositionalCommand positionalCommand = new PositionalCommand(new Plateau(), 2, "3,4,E");
        Rover rover = positionalCommand.execute();
        assertEquals(2, rover.getNumber());
        assertEquals(3, rover.getPosition().getX());
        assertEquals(4, rover.getPosition().getY());
        assertEquals(Direction.EAST, rover.getPosition().getDirection());
    }
}
