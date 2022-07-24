package com.karthik.jpm.marsrover.rover;

import java.util.function.Predicate;

import com.karthik.jpm.marsrover.Plateau;
import com.karthik.jpm.marsrover.Position;
import com.karthik.jpm.marsrover.Position.Direction;

public class Rover implements Movable {

    private int number;
    private Position position;
    private Plateau plateau;

    public Rover(Plateau plateau, int number, Position position) {
        this.plateau = plateau;
        this.number = number;
        this.position = position;
    }

    Predicate<Integer> checkNewXCoordinatePredicate = (
            newX) -> !plateau.isRoverPresentInPosition(this.getNumber(),
                    position.copy().withCoordinates(newX, position.getY()));
    Predicate<Integer> checkNewYCoordinatePredicate = (
            newY) -> !plateau.isRoverPresentInPosition(this.getNumber(),
                    position.copy().withCoordinates(position.getX(), newY));

    @Override
    public Position moveForward() {
        if (getCoOrdinateToModify().equals(CoOrdinate.X)) {
            if (position.getDirection().equals(Direction.EAST)) {
                position.incrementXCoordinate(checkNewXCoordinatePredicate, 1);
            } else if (position.getDirection().equals(Direction.WEST)) {
                position.decrementXCoordinate(checkNewXCoordinatePredicate, 1);
            }
        } else {
            if (position.getDirection().equals(Direction.NORTH)) {
                position.incrementYCoordinate(checkNewYCoordinatePredicate, 1);
            } else if (position.getDirection().equals(Direction.SOUTH)) {
                position.decrementYCoordinate(checkNewYCoordinatePredicate, 1);
            }
        }
        return this.position;
    }

    @Override
    public Position moveBackward() {
        if (getCoOrdinateToModify().equals(CoOrdinate.X)) {
            if (position.getDirection().equals(Direction.EAST)) {
                position.decrementXCoordinate(checkNewXCoordinatePredicate, 1);
            } else if (position.getDirection().equals(Direction.WEST)) {
                position.incrementXCoordinate(checkNewXCoordinatePredicate, 1);
            }
        } else {
            if (position.getDirection().equals(Direction.NORTH)) {
                position.decrementYCoordinate(checkNewYCoordinatePredicate, 1);
            } else if (position.getDirection().equals(Direction.SOUTH)) {
                position.incrementYCoordinate(checkNewYCoordinatePredicate, 1);
            }
        }
        return this.position;
    }

    @Override
    public Position turnLeft() {
        position.setDirection(position.getDirection().getLeft());
        return this.position;
    }

    @Override
    public Position turnRight() {
        position.setDirection(position.getDirection().getRight());
        return this.position;
    }

    private enum CoOrdinate {
        X, Y
    }

    public CoOrdinate getCoOrdinateToModify() {
        switch (this.position.getDirection()) {
            case EAST:
                return CoOrdinate.X;
            case WEST:
                return CoOrdinate.X;
            case NORTH:
                return CoOrdinate.Y;
            case SOUTH:
                return CoOrdinate.Y;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    public int getNumber() {
        return number;
    }

    public Position getPosition() {
        return position;
    }
}
