package com.karthik.jpm.marsrover.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.karthik.jpm.marsrover.Plateau;
import com.karthik.jpm.marsrover.exception.CollisionException;
import com.karthik.jpm.marsrover.rover.Rover;

public class CommandExecutor {
    public static final String QUIT_COMMAND_PATTERN = "Q";
    public static final String SHOW_OUTPUT_COMMAND_PATTERN = "S";
    public static final String ROVER_COMMAND_PATTERN = "^(\\d+)\\s((?:\\d+,){2}[E|W|N|S])\\s((?:f|b|r|l){1}[,f|b|r|l^$]*)$";

    private Map<Integer, Rover> rovers = new HashMap<>();
    private Plateau plateau = new Plateau();
    private PositionalCommand positionalCommand;
    private MovementCommand movementCommand;

    private void getRoverPositions() {
        if (rovers.isEmpty()) {
            System.out.println("No Rovers are present!");
        } else {
            for (Entry<Integer, Rover> roverEntry : this.rovers.entrySet()) {
                System.out.println(
                        "\nRover " + roverEntry.getKey() + " position:");
                System.out.println(
                        new StringBuilder("Final Coordinate: ").append(roverEntry.getValue().getPosition().getX())
                                .append(",").append(roverEntry.getValue().getPosition().getY()).toString());
                System.out.println(new StringBuilder("Final Direction: ")
                        .append(roverEntry.getValue().getPosition().getDirection()).toString());
            }
        }
    }

    private static boolean isCommandMatchingPatten(final String command, final String pattern) {
        return command.matches(pattern);
    }

    public Rover execute(final String command) {
        validateSetupAndCommands(command);
        Pattern roverCommandPattern = Pattern.compile(ROVER_COMMAND_PATTERN);
        Matcher roverCommandMatcher = roverCommandPattern.matcher(command);
        try {
            if (roverCommandMatcher.matches()) {
                int roverNumber = Integer.parseInt(roverCommandMatcher.group(1).toString());
                validateRoverCount.apply(roverNumber);
                String positionInitCommand = roverCommandMatcher.group(2).toString();
                Rover rover = initRoverAndPositions(roverNumber, positionInitCommand);

                String movementCommand = roverCommandMatcher.group(3).toString().replaceAll(",$", "");
                rover = executeRoverMovement(rover, movementCommand);
                rovers.put(roverNumber, rover);
                plateau.addRoverToPlateau(rover);
            } else if (isCommandMatchingPatten(command, QUIT_COMMAND_PATTERN)) {
                quitExecution();
            } else if (isCommandMatchingPatten(command, SHOW_OUTPUT_COMMAND_PATTERN)) {
                getRoverPositions();
            }
        } catch (Exception e) {
            System.out.println("Error encountered: " + e.getMessage());
        }
        return null;
    }

    private void validateSetupAndCommands(final String command) {
        Command.validation.accept(command,
                List.of(ROVER_COMMAND_PATTERN, QUIT_COMMAND_PATTERN, SHOW_OUTPUT_COMMAND_PATTERN));
    }

    private Rover initRoverAndPositions(final int roverNumber, final String positionInitCommand) {
        this.positionalCommand = new PositionalCommand(plateau, roverNumber, positionInitCommand);
        if (plateau.isRoverPresentInPosition(roverNumber, positionalCommand.getPosition())) {
            throw new CollisionException("Rover already present in the position");
        }
        return this.positionalCommand.execute();
    }

    private Rover executeRoverMovement(Rover rover, String movementCommand) {
        this.movementCommand = new MovementCommand(rover, movementCommand);
        return this.movementCommand.execute();
    }

    private Function<Integer, Void> validateRoverCount = (numberOfRovers) -> {
        if (numberOfRovers < 1) {
            System.out.println("Invalid input.");
        }
        return null;
    };

    public static void quitExecution() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
