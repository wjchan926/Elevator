package elevator;

import java.io.IOException;

public class ElevatorDriver {
	public static void main(String[] args) throws IOException, InterruptedException {

		Reader elevatorData = new Reader("elevatorData.txt");

		// Prompt User for data source file

		Elevator elevOb = new Elevator();

		// Elevator Simulator Logic
		// Determines if someone is getting on or off at current floor.
		// Determines when the elevator moves
		// Will continue until there is no more input and the elevator is empty

		// Initialize destination floor request and move elevator to that floor
		elevOb.setDestinationFloor(Integer.parseInt(elevatorData.getFileLines()[0][1]));

		for (String[] s : elevatorData.getFileLines()) {

			ElevatorPerson ep = new ElevatorPerson(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
			elevOb.setRequestedFloor(ep.getFloorEntered()); // Make a floor request
			elevOb.calcNextFloor();

			if (elevOb.isEmpty()) {
				elevOb.getElevStack().INCNUMEMPTY();
				System.out.println("Empty Elevator");
			}

			// Keep moving elevator and having people exit if current floor is not the
			// requested floor
			while (ep.getFloorEntered() != elevOb.getCurrentFloor()) {
				elevOb.moveElev();
				elevOb.exit();
			}

			// Current floor is now the requested floor
			// If people need to exit, they will
			elevOb.exit();

			if (elevOb.isFull()) { // If elevator is full, person will walk
				System.out.println(ep + " is taking the stairs. Elevator is full.");
				elevOb.getElevStack().INCNUMFULL();
				elevOb.calcNextFloor();
				elevOb.moveElev();
			} else { // Otherwise they will get on the elevator
				System.out.println(ep + " is entering. " + ep + "'s destination is floor " + ep.getFloorExit());
				elevOb.enter(ep);
				elevOb.calcNextFloor();
			}
		}

		// Clear out remaining people in elevator
		while (!elevOb.isEmpty()) {
			elevOb.moveElev();
			elevOb.exit();
			if (elevOb.isEmpty()) {
				elevOb.getElevStack().INCNUMEMPTY();
				System.out.println("Empty Elevator");
			}
		}
		
		System.out.println(elevOb.getElevStack().GETNUMEMPTY());
		System.out.println(elevOb.getElevStack().GETNUMFULL());
		System.out.println(elevOb.getElevStack().GETTOTALRODE());
		

	}
}
