package com.karthik.jpm.marsrover.command;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import com.karthik.jpm.marsrover.exception.CommandException;
import com.karthik.jpm.marsrover.rover.Rover;

public interface Command {

    BiConsumer<String, List<String>> validation = (command, patterns) -> {
        if (Objects.isNull(command) || patterns.stream().noneMatch(pattern -> command.matches(pattern))) {
            throw new CommandException("Not a valid command!");
        }
    };

    Rover execute() throws CommandException;
}
