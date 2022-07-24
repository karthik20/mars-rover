package com.karthik.jpm.marsrover.rover;

import com.karthik.jpm.marsrover.Position;

public interface Movable {

    Position moveForward();

    Position moveBackward();

    Position turnLeft();

    Position turnRight();

}
