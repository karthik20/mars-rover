package com.karthik.jpm.marsrover.rover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.karthik.jpm.marsrover.Position;
import com.karthik.jpm.marsrover.Position.Direction;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionTest {

    @Test
    void shouldReturnDirectionFromCode() {
        assertEquals(Direction.EAST, Direction.fromCode("E"));
        assertEquals(Direction.WEST, Direction.fromCode("W"));
        assertEquals(Direction.NORTH, Direction.fromCode("N"));
        assertEquals(Direction.SOUTH, Direction.fromCode("S"));
    }

    @Test
    void should_movePositionAndCoordinates_whenNoCollision() {
        Position position = Position.builder()
                .withCoordinates(1, 2)
                .withDirection(Direction.EAST).build();
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(Direction.EAST, position.getDirection());
        position.incrementXCoordinate((x) -> true, 1).incrementYCoordinate((x) -> true, 2);
        assertEquals(2, position.getX());
        assertEquals(4, position.getY());
    }

    @Test
    void should_not_movePositionAndCoordinates_whenCollision() {
        Position position = Position.builder()
                .withCoordinates(1, 2)
                .withDirection(Direction.EAST).build();
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(Direction.EAST, position.getDirection());
        // expect no collision for same rover
        position.incrementXCoordinate((x) -> false, 1).incrementYCoordinate((x) -> true, 2);
        assertEquals(1, position.getX());
        assertEquals(4, position.getY());
    }

    @Test
    void throwExceptionIfPositionIsNotInitialized() {
        Throwable throwable = assertThrows(IllegalStateException.class, () -> {
            Position.builder().build();
        });
        assertEquals(throwable.getMessage(), "Co-ordinates/Direction not initialized");
    }

    @Test
    void checkDirectionRotations() {
        assertEquals(Direction.EAST.getLeft(), Direction.NORTH);
        assertEquals(Direction.EAST.getRight(), Direction.SOUTH);

        assertEquals(Direction.NORTH.getLeft(), Direction.WEST);
        assertEquals(Direction.NORTH.getRight(), Direction.EAST);

        assertEquals(Direction.SOUTH.getLeft(), Direction.EAST);
        assertEquals(Direction.SOUTH.getRight(), Direction.WEST);

        assertEquals(Direction.WEST.getLeft(), Direction.SOUTH);
        assertEquals(Direction.WEST.getRight(), Direction.NORTH);
    }

}
