[![Java CI with Maven](https://github.com/karthik20/mars-rover/actions/workflows/maven.yml/badge.svg)](https://github.com/karthik20/mars-rover/actions/workflows/maven.yml)

## Run the application (JRE 1.8)
The application comes with maven wrapper.
To run,
```
./mvnw clean install
./mvnw spring-boot:run
```

The program allows to initialize multiple rovers and command them
To initialize/Command individual rover by numbners and position and move them:
- Commands are case-sensitive
## Scenario 1: No collision single rover
```
Welcome to Mars Rover program!
Enter Command for Rover in format:
1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>
2: Q to quit
3: S to get Rover positions
1 3,4,N f,f,r,f,f

Enter Command for Rover in format:
1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>
2: Q to quit
3: S to get Rover positions
S

Rover 1 position:
Final Coordinate: 5,6
Final Direction: EAST
```
You can follow above command to control the same rover by number.

## Scenario 2: Collision with rovers

In case of multiple rovers, if any movement might cause collision, it would prevent the further movements and the command will halt before collision.
```
Welcome to Mars Rover program!
Enter Command for Rover in format:
1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>
2: Q to quit
3: S to get Rover positions
1 3,4,N f,f,r,f,f

Enter Command for Rover in format:
1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>
2: Q to quit
3: S to get Rover positions
S

Rover 1 position:
Final Coordinate: 5,6
Final Direction: EAST

Enter Command for Rover in format:
1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>
2: Q to quit
3: S to get Rover positions
2 5,4,E l,f,f

Enter Command for Rover in format:
1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>
2: Q to quit
3: S to get Rover positions
S

Rover 1 position:
Final Coordinate: 5,6
Final Direction: EAST

Rover 2 position:
Final Coordinate: 5,5
Final Direction: NORTH

Enter Command for Rover in format:
1: <Rover number> <Co-ordinate X, Y, Direction (E, W, N, S)> <Movements (f, b, l, r)>
2: Q to quit
3: S to get Rover positions

Q
```

### Limitations
- Program doesn't handle just movements without initial directions yet
- Any commands must be again given with rover number and initial postitions plus further movements
