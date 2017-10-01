package elevator;

import java.io.IOException;

public class ElevatorDriver {
	public static void main(String[] args) throws IOException {

		Reader elevatorData = new Reader("elevatorData.txt");
		// System.out.println(r.countLines());

		// Prompt User for data source file

		Elevator elevOb = new Elevator();

		// Elevator Simulator Logic
		// Determines if someone is getting on or off at current floor.
		// Determines when the elevator moves
		// Will continue until there is no more input and the elevator is empty

		// Initialize destination floor request and move elevator to that floor
		elevOb.setDestinationFloor(Integer.parseInt(elevatorData.getFileLines()[0][1]));

		do {
			for (String[] s : elevatorData.getFileLines()) {
				if (elevOb.isEmpty()) {
					elevOb.getElevStack().INCNUMEMPTY();
				}

				ElevatorPerson ep = new ElevatorPerson(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
				elevOb.setRequestedFloor(ep.getFloorExit());
				
				while (ep.getFloorEntered() != elevOb.getDestinationFloor()) {
					
					elevOb.moveElev();
					elevOb.exit();
					elevOb.detNextFloor();
				}

				if (ep.getFloorEntered() == elevOb.getDestinationFloor()) {
					elevOb.exit();

					if (elevOb.isFull()) {
						System.out.println(ep + " is taking the stairs. Elevator is full.");
						elevOb.getElevStack().INCNUMFULL();
						elevOb.detNextFloor();
						elevOb.moveElev();
					} else {
						System.out.println(ep + " is entering. " + ep + "'s destination is floor " + ep.getFloorExit());
						elevOb.enter(ep);
					}

				}
			}
		} while ((!elevOb.isEmpty()));

	}
}
