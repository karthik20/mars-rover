package com.karthik.jpm.marsrover.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.karthik.jpm.marsrover.Plateau;
import com.karthik.jpm.marsrover.Position;
import com.karthik.jpm.marsrover.Position.Direction;
import com.karthik.jpm.marsrover.exception.CommandException;
import com.karthik.jpm.marsrover.rover.Rover;

public class MovementCommandTest {

    private Rover rover;

    @BeforeEach
    private void setupRover() {
        this.rover = new Rover(new Plateau(), 1, Position.builder()
                .withCoordinates(3, 4)
                .withDirection(Direction.EAST)
                .build());
    }

    @ParameterizedTest
    @MethodSource("positionAndExpectationProvider")
    void should_return_correction_positions_afterMovement(final String input, final String expected) {
        MovementCommand movementCommand = new MovementCommand(rover, input);
        Rover rover = movementCommand.execute();
        assertEquals(expected, rover.getPosition().toString());
    }

    @Test
    void should_throw_exception_when_invalid_command_is_provided() {
        MovementCommand movementCommand = new MovementCommand(rover, "f,x");
        assertThrows(CommandException.class, () -> movementCommand.execute());
    }

    private static Stream<Arguments> positionAndExpectationProvider() {
        return Stream.of(Arguments.of("f,r,f,f,b,l", "4,3,E"),
                Arguments.of("f,b,f,f,l", "5,4,N"),
                Arguments.of("l,f,f,r,f,f", "5,6,E"));
    }
}
