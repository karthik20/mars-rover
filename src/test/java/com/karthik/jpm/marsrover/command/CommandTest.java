package com.karthik.jpm.marsrover.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.karthik.jpm.marsrover.exception.CommandException;

public class CommandTest {
    @Test
    public void should_throwException_if_CommandPattern_doesnt_match() {
        Throwable throwable = assertThrows(CommandException.class,
                () -> Command.validation.accept(null, List.of("Q", "W")));
        assertEquals(throwable.getMessage(), "Not a valid command!");
        throwable = assertThrows(CommandException.class,
                () -> Command.validation.accept("1,2", List.of("Q", "W")));
        assertEquals(throwable.getMessage(), "Not a valid command!");
    }
}
