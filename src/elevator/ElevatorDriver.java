package elevator;

import java.io.IOException;

/**
 * This class is the Driver class for the Elevator simulation. It interactively
 * asks for the input source filename and ask for a filename to save the output
 * data.
 * 
 * @author Wesley Chan
 *
 */
public class ElevatorDriver {
	public static void main(String[] args) throws IOException, InterruptedException {

		// First Command line argument is the input source txt file.
		ReadFile elevatorData = new ReadFile(args[0]);

		// Second Command line argument is the output source filename.
		WriteFile outputFile = new WriteFile(args[1]);

		// Prompt User for data source file

		Elevator elevOb = new Elevator(outputFile);

		// Elevator Simulator Logic
		// Determines if someone is getting on or off at current floor.
		// Determines when the elevator moves
		// Will continue until there is no more input and the elevator is empty

		// Initialize destination floor request and move elevator to that floor
		elevOb.setDestinationFloor(Integer.parseInt(elevatorData.getFileLines()[0][1]));
		for (String[] s : elevatorData.getFileLines()) {

			ElevatorPerson ep = new ElevatorPerson(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			elevOb.setRequestedFloor(ep.getFloorEntered()); // Make a floor
															// request
			elevOb.calcNextFloor();

			// Keep moving elevator and having people exit if current floor is
			// not the
			// requested floor
			while (ep.getFloorEntered() != elevOb.getCurrentFloor()) {
				elevOb.moveElev();
				elevOb.exit();
			}

			// Current floor is now the requested floor
			// If people need to exit, they will
			elevOb.exit();

			if (elevOb.isFull()) { // If elevator is full, person will walk
				outputFile.writeToFile("Elevator is full. Taking Stairs: " + ep + "\r\n");
				elevOb.getElevStack().INCNUMFULL();
			} else { // Otherwise they will get on the elevator
				outputFile.writeToFile(
						"Entering Elevator: " + ep + "\t\t|\tDesitnation Floor: " + ep.getFloorExit() + "\r\n");
				elevOb.enter(ep);
			}

		}

		// Clear out remaining people in elevator
		while (!elevOb.isEmpty()) {
			elevOb.calcNextFloor();
			elevOb.moveElev();
			elevOb.exit();
			if (elevOb.isEmpty()) {
				outputFile.writeToFile("Elevator is Empty and Not Operational.\r\n");
			}
		}

		// Add Statistical Data
		outputFile.writeToFile("\r\n----------------\r\n");
		outputFile.writeToFile("Statstical Data:\r\n");
		outputFile.writeToFile("----------------\r\n");
		outputFile.writeToFile(
				"No. of instances elevator was empty while running: " + elevOb.getElevStack().GETNUMEMPTY() + "\r\n");
		outputFile.writeToFile("No. of people who took stairs: " + elevOb.getElevStack().GETNUMFULL() + "\r\n");
		outputFile.writeToFile("No. of people who rode elevator: " + elevOb.getElevStack().GETTOTALRODE() + "\r\n");

		// Close file
		outputFile.closeFile();

		System.out.println("Simulation Complete.");
	}
}
