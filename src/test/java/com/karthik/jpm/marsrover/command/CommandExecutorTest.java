package com.karthik.jpm.marsrover.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class CommandExecutorTest {

    private CommandExecutor commandExecutor;
    PrintStream oldPrintStream = System.out;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @BeforeEach
    private void init() {
        commandExecutor = new CommandExecutor();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @AfterEach
    private void reset() {
        System.setOut(oldPrintStream);
    }

    @Test
    void shouldReturn_rover_cordinates_byNumber_noCollision() {
        commandExecutor.execute("1 1,2,E f");
        commandExecutor.execute("S");
        assertEquals("Rover 1 position:\nFinal Coordinate: 2,2\nFinal Direction: EAST", baos.toString().trim());

        commandExecutor.execute("2 3,4,N l,f,b");
        commandExecutor.execute("S");
        Assertions.assertThat(
                baos.toString().trim().contains("Rover 1 position:\nFinal Coordinate: 3,2\nFinal Direction: EAST\n" +
                        "Rover 2 position:\nFinal Coordinate: 3,4\nFinal Direction: WEST"));
    }

    @Test
    void shouldNotInitializeRover_whenCOllision_onInitialization() {
        commandExecutor.execute("1 3,4,N f,b");
        commandExecutor.execute("2 3,4,E r");
        commandExecutor.execute("S");
        Assertions.assertThat(
                baos.toString().trim().contains(
                        "Error encountered: Rover already present in the position\nRover 1 position:\nFinal Coordinate: 3,4\nFinal Direction: NORTH\n"));
    }

    @Test
    void shouldNot_moveRover_whenCollision_onMovement() {
        commandExecutor.execute("1 3,4,N f,f,r,f,f");
        commandExecutor.execute("2 5,4,E l,f,f");
        commandExecutor.execute("S");
        Assertions.assertThat(
                baos.toString().trim().contains(
                        "Rover 1 position:\nFinal Coordinate: 5,6\nFinal Direction: EAST\n\nRover 2 position:\nFinal Coordinate: 5,5\nFinal Direction: NORTH"));
    }

    @Test
    @Disabled
    void should_exit_cli_on_QuitCommand() {
        commandExecutor.execute("Q");
        Runtime originalRuntime = Runtime.getRuntime();
        Runtime spyRuntime = spy(originalRuntime);
        doNothing().when(spyRuntime).exit(anyInt());
        ReflectionTestUtils.setField(Runtime.class, "currentRuntime", spyRuntime);
        verify(spyRuntime).exit(0);
    }
}
