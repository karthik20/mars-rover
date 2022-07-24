package com.karthik.jpm.marsrover;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.karthik.jpm.marsrover.command.CommandExecutor;

/**
 * This application serves as the starting point of Rover program.
 * <br>
 * The {@link CommandExecutor} reads the command and delegates operations
 * for each command issued for rover.<br>
 * The application allows:<br>
 * <li>Multiple rover initialization</li>
 * <li>Avoids collitions between rovers</li>
 * <li>Details positions of all rovers</li>
 * <br>
 *
 * @author Karthik Radhakrishnan
 * @see CommandExecutor
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class MarsRoverApplication implements CommandLineRunner {

	private Scanner scanner = new Scanner(System.in);
	private CommandExecutor commandExecutor;

	public MarsRoverApplication(final CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	@Override
	public void run(String... args) throws Exception {
		print("Welcome to Mars Rover program!");
		sendCommands();
	}

	private void sendCommands() {
		while (true) {
			print("");
			print("Enter Command for Rover in format:");
			print("1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>");
			print("2: Q to quit");
			print("3: S to get Rover positions");
			commandExecutor.execute(scanner.nextLine());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(MarsRoverApplication.class, args);
	}

	private void print(String string) {
		System.out.println(string);
	}

}
