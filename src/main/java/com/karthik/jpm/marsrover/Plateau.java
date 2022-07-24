package com.karthik.jpm.marsrover;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.karthik.jpm.marsrover.rover.Rover;

@Component
public class Plateau {
    private List<Rover> rovers = new ArrayList<>();

    public void addRoverToPlateau(Rover rover) {
        this.rovers.add(rover);
    }

    public boolean isRoverPresentInPosition(final int roverNumber, final Position position) {
        return rovers.stream().filter(rover -> rover.getNumber() != roverNumber).map((rover) -> rover.getPosition())
                .anyMatch(roverPosition -> roverPosition.getX() == position.getX()
                        && roverPosition.getY() == position.getY());
    }

}
