package com.karthik.jpm.marsrover;

import java.util.function.Predicate;

public class Position {

    private int x;
    private int y;
    private Direction direction;

    public static Position builder() {
        return new Position();
    }

    public Position withCoordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Position withDirection(final Direction direction) {
        this.direction = direction;
        return this;
    }

    /**
     * Increments x coordinate by x only if the predicate condition matches
     * 
     * @param predicate - Predicate to test against before incrementing x coordinate
     * @param x         - x coordinate to increment by
     * @return calculated position
     */
    public Position incrementXCoordinate(Predicate<Integer> predicate, final int x) {
        if (predicate.test(this.x + x)) {
            this.x += x;
        }
        return this;
    }

    /**
     * Increments x coordinate by y only if the predicate condition matches
     * 
     * @param predicate - Predicate to test against before incrementing y coordinate
     * @param y         - y coordinate to increment by
     * @return calculated position
     */
    public Position incrementYCoordinate(Predicate<Integer> predicate, final int y) {
        if (predicate.test(this.y + y)) {
            this.y += y;
        }
        return this;
    }

    /**
     * Decrements x coordinate by x only if the predicate condition matches
     * 
     * @param predicate - Predicate to test against before decrementing x coordinate
     * @param x         - x coordinate to decrement by
     * @return calculated position
     */
    public Position decrementXCoordinate(Predicate<Integer> predicate, final int x) {
        if (predicate.test(this.x - x)) {
            this.x -= x;
        }
        return this;
    }

    /**
     * Decrements y coordinate by y only if the predicate condition matches
     * 
     * @param predicate - Predicate to test against before decrementing y coordinate
     * @param y         - y coordinate to decrement by
     * @return calculated position
     */
    public Position decrementYCoordinate(Predicate<Integer> predicate, final int y) {
        if (predicate.test(this.y - y)) {
            this.y -= y;
        }
        return this;
    }

    public Position build() {
        if (x == 0 || y == 0 || direction == null) {
            throw new IllegalStateException("Co-ordinates/Direction not initialized");
        }
        return this;
    }

    public Position copy() {
        return Position.builder().withCoordinates(getX(), getY())
                .withDirection(getDirection()).build();
    }

    public enum Direction {
        EAST("E"), WEST("W"), NORTH("N"), SOUTH("S");

        private String code;

        Direction(String code) {
            this.code = code;
        }

        public static Direction fromCode(final String code) {
            for (Direction direction : Direction.values()) {
                if (direction.code.equals(code)) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("Invalid direction code: " + code);
        }

        public Direction getLeft() {
            switch (this) {
                case EAST:
                    return NORTH;
                case WEST:
                    return SOUTH;
                case NORTH:
                    return WEST;
                case SOUTH:
                    return EAST;
                default:
                    throw new IllegalArgumentException("Invalid direction");
            }
        }

        public Direction getRight() {
            switch (this) {
                case EAST:
                    return SOUTH;
                case WEST:
                    return NORTH;
                case NORTH:
                    return EAST;
                case SOUTH:
                    return WEST;
                default:
                    throw new IllegalArgumentException("Invalid direction");
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(x).append(",").append(y).append(",").append(direction.code).toString();
    }

}
