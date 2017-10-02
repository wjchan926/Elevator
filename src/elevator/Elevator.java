package elevator;

import java.io.IOException;

/**
 * Represents an Elevator Object. This class controls the behavior of the
 * elevator and contains an ElevatorStack object to organize the people in the
 * elevator and another stack to represent people who temporarily exit the
 * elevator.
 * 
 * @author Wesley Chan
 *
 */
public class Elevator {

	private int currentFloor;
	private int destinationFloor;
	private int requestedFloor;
	private int direction; // -1 for down, 1 for up
	private ElevatorStack elevStack;
	private MyStack tempExitStack;
	private WriteFile outputFile;

	/**
	 * Default constructor with a Writefile as an argument.
	 * 
	 * @param wf
	 *            output filename for simulation data
	 */
	Elevator(WriteFile wf) {
		currentFloor = 1;
		direction = 1;
		destinationFloor = currentFloor;
		elevStack = new ElevatorStack();
		tempExitStack = new MyStack();
		outputFile = wf;
	}

	/**
	 * Gets the current floor the elevator is on
	 * 
	 * @return currentFloor is the floor the elevator is currently on as type
	 *         int
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Sets the Destination floor
	 * 
	 * @param floor
	 *            this is the floor that the elevator wants to go to next
	 */
	public void setDestinationFloor(int floor) {
		destinationFloor = floor;
	}

	/**
	 * Sets the requested floor
	 * 
	 * @param floor
	 *            this is a floor that a person has pressed a button on or wants
	 *            to go to
	 */
	public void setRequestedFloor(int floor) {
		requestedFloor = floor;
	}

	public ElevatorStack getElevStack() {
		return elevStack;
	}

	/**
	 * Switches direction elevator is moving. -1 corresponds to downward, 1 is
	 * upward
	 */
	public void switchElevDirection() {
		direction *= -1;
	}

	/**
	 * Method that signifies a person entering the elevator. Person will be
	 * pushed onto the elevator stack.
	 * 
	 * @param ep
	 *            ElevavatorPerson object that represents someone entering the
	 *            elevator
	 */
	public void enter(ElevatorPerson ep) {
		elevStack.push(ep);
	}

	/**
	 * Permanently pops people from the Elevator Stack if it is their exit
	 * floor. This method will pop people to a temporary stack if needed. This
	 * represents people getting out of the elevator so that the people who need
	 * to exit can get out. Then the people who temporarily exited will get back
	 * on.
	 * 
	 * Postcondition: tempExitStack will be empty after method call completes
	 * 
	 * @throws IOException if the output file cannot be found
	 */
	public void exit() throws IOException {
		if (!elevStack.isEmpty() && destinationFloor == currentFloor) {
			int size = elevStack.getSize();
			int numToExit = 0;

			tempExitStack.setStackSize(size);

			// Get number of people that need to exit this floor
			for (int i = 0; i < size; i++) {
				if (elevStack.getStackArr()[i].getFloorExit() == currentFloor) {
					numToExit++;
				}
			}

			// Temporarily holds people in the tempExitStack while person who
			// needs to exit gets out
			for (int i = 0; i < size && numToExit != 0; i++) {
				if (elevStack.peek().getFloorExit() != currentFloor) {
					tempExitStack.push(elevStack.pop());
				} else {
					// Write to file who exited
					numToExit--;
					outputFile.writeToFile("Exiting Elevator: " + elevStack.peek() + "\t\t|\tTemporarily exited: "
							+ elevStack.pop().getTempExits() + "\r\n");
				}
			}

			restoreTempExited(); // People return if exited
			calcNextFloor();
		}

	}

	/**
	 * Pops every element in the tempExitStack and Pushes them back into the
	 * elevStack. Represents people reentering the elevator after making a
	 * temporary exit. Increments the number of temporary exits a person has
	 * made.
	 * 
	 * Precondition: Used only in the exit() method Postcondition: tempExitStack
	 * will be empty after method call completes
	 */
	private void restoreTempExited() {
		while (!tempExitStack.isEmpty()) {
			tempExitStack.peek().incTempExits();
			elevStack.push(tempExitStack.pop());
			elevStack.DECTOTALRODE(); // Prevents double counting the temporary
										// Exits
		}
	}

	/**
	 * Determines which floor is the next closest floor based on the direction
	 * the elevator is moving. This floor can either be from someone exiting or
	 * entering. If there are no floor requests in the direction the elevator is
	 * moving, the elevator will switch it's direction.
	 * 
	 */
	public void calcNextFloor() {

		int diff = 5;
		int[] floorReqInElev = new int[elevStack.getSize() + 1];
		boolean floorFound = false;

		// Get all possible floor requests
		for (int i = 0; i < floorReqInElev.length - 1; i++) {
			floorReqInElev[i] = elevStack.getStackArr()[i].getFloorExit();
		}

		floorReqInElev[floorReqInElev.length - 1] = requestedFloor;

		// Check for closest floor request in current direction
		for (int i = 0; i < floorReqInElev.length; i++) {
			if (floorReqInElev[i] * direction > currentFloor * direction) {
				if (calcFloorDist(floorReqInElev[i]) < diff) {
					diff = calcFloorDist(floorReqInElev[i]);
					destinationFloor = floorReqInElev[i];
					floorFound = true;
				}
			}
		}

		// Check for closest floor request in opposite direction if none is
		// found in current direction
		if (floorFound == false) {
			switchElevDirection(); // Switches the elevator travel direction
			for (int i = 0; i < floorReqInElev.length; i++) {
				if (floorReqInElev[i] * direction > currentFloor * direction) {
					if (calcFloorDist(floorReqInElev[i]) < diff) {
						diff = calcFloorDist(floorReqInElev[i]);
						destinationFloor = floorReqInElev[i];
						floorFound = true;
					}
				}
			}
		}
	}

	/**
	 * Calculates the distance between the current floor and floor i
	 * @param i floor 
	 * @return positive int that represents how far a floor is away from the current floor
	 */
	private int calcFloorDist(int i) {
		return Math.abs(i - currentFloor);
	}

	/**
	 * This method moves the elevator to the destination floor and records the movement.
	 * It will record if the elevator is moving while there are no passengers.
	 * 
	 * @throws IOException if it cannot find the file to write to
	 */
	public void moveElev() throws IOException {
		if (elevStack.isEmpty() && tempExitStack.isEmpty()) {
			elevStack.INCNUMEMPTY();
			outputFile.writeToFile("Elevator is Currently Empty.\r\n");
		}

		currentFloor = destinationFloor;
		outputFile.writeToFile("\r\nElevator is moving to Floor " + currentFloor + "\r\n\r\n");

	}

	public boolean isEmpty() {
		return elevStack.isEmpty();
	}

	public boolean isFull() {
		return elevStack.isFull();
	}
}
