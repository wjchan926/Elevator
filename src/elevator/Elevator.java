package elevator;

/**
 * Represents an Elevator Object. This class controls the behavior of the
 * elevator and contains an ElevatorStack object to organize the people in the
 * elevator and another ElevatorStack to represent people who temporarily exit
 * the elevator.
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

	Elevator() {
		currentFloor = 1;
		direction = 1;
		destinationFloor = currentFloor;
		elevStack = new ElevatorStack();
		tempExitStack = new MyStack();
	}

	/**
	 * Gets the current floor the elevator is on
	 * 
	 * @return currentFloor is the floor the elevator is currently on as type int
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int floor) {
		currentFloor = floor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int floor) {
		destinationFloor = floor;
	}

	public void setRequestedFloor(int floor) {
		requestedFloor = floor;
	}

	public ElevatorStack getElevStack() {
		return elevStack;
	}

	public MyStack getTempExitStack() {
		return tempExitStack;
	}

	/**
	 * Returns the direction the elevator is going
	 * 
	 * @return 1 if the elevator is moving up and if the elevator is moving down
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * Switches direction elevator is moving. -1 correponds to downward
	 */
	public void switchElevDirection() {
		direction *= -1;
	}

	/**
	 * Method that signifies a person entering the elevator. Person will be pushed
	 * onto the elevator stack. A new destination floor will be assigned if the
	 * person entering's exit floor is closer to the current floor and is in the
	 * same direction the elevator is moving than the original destination floor.
	 * 
	 * @param ep
	 *            ElevavatorPerson object that represents someone entering the
	 *            elevator
	 */
	public void enter(ElevatorPerson ep) {
		elevStack.push(ep);
	}

	/**
	 * Permanently pops people from the Elevator Stack if it is their exit floor.
	 * This method will pop people to a temporary stack if needed. This represents
	 * people getting out of the elevator so that the people who need to exit can
	 * exit. Then the people who temporarily exited will get back on.
	 * 
	 * Postcondition: tempExitStack will be empty after method call completes
	 */
	public void exit() {
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

			for (int i = 0; i < size && numToExit != 0; i++) {
				if (elevStack.peek().getFloorExit() != currentFloor) {
					tempExitStack.push(elevStack.pop());
				} else {
					// Write to file who exited
					numToExit--;
					System.out.println(
							elevStack.peek() + " is exiting. Temporarily exited: " + elevStack.pop().getTempExits());
				}
			}
			restoreTempExited(); // People return if exited
			calcNextFloor();
		}

	}

	/**
	 * Pops every element in the tempExitStack and Pushes them back into the
	 * elevStack. Represents people reentering the elevator after making a temporary
	 * exit. Increments the number of temporary exits a person has made.
	 * 
	 * Precondition: Used only in the exit() method Postcondition: tempExitStack
	 * will be empty after method call completes
	 */
	private void restoreTempExited() {
		while (!tempExitStack.isEmpty()) {
			tempExitStack.peek().incTempExits();
			elevStack.push(tempExitStack.pop());
			elevStack.DECTOTALRODE(); // Prevents double counting the temporary Exits
		}
	}

	/**
	 * Determines which floor is the next closest floor based on the direction the
	 * elevator is moving. This floor can either be from someone exiting or
	 * entering. If there are no floor requests in the direction the elevator is
	 * moving, the elevator will switch it's direction.
	 * 
	 */
	public void calcNextFloor() {

		int diff = 5;
		int[] floorReqInElev = new int[elevStack.getSize() + 1];
		boolean floorFound = false;

		for (int i = 0; i < floorReqInElev.length - 1; i++) {
			floorReqInElev[i] = elevStack.getStackArr()[i].getFloorExit();
		}

		floorReqInElev[floorReqInElev.length - 1] = requestedFloor;

		for (int i = 0; i < floorReqInElev.length; i++) {
			if (floorReqInElev[i] * direction > currentFloor * direction) {
				if (calcFloorDist(floorReqInElev[i]) < diff) {
					diff = calcFloorDist(floorReqInElev[i]);
					destinationFloor = floorReqInElev[i];
					floorFound = true;
				}
			}
		}

		if (floorFound == false) {
			switchElevDirection();
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

		/*
		 * int diff = 5; int[] floorReqInElev = new int[elevStack.getSize()]; boolean
		 * reqInDir = true;
		 * 
		 * for (int i = 0; i < floorReqInElev.length; i++) { floorReqInElev[i] =
		 * elevStack.getStackArr()[i].getFloorExit(); }
		 * 
		 * // Linear search for closest floor since there are only max 6 elements. Also
		 * // take into account direction of elevator.
		 * 
		 * for (int i = 0; i < floorReqInElev.length; i++) { if (floorReqInElev[i] *
		 * direction > currentFloor * direction) { if (calcFloorDist(floorReqInElev[i])
		 * < diff) { diff = calcFloorDist(floorReqInElev[i]); destinationFloor =
		 * floorReqInElev[i]; } } }
		 */
	}

	private int calcFloorDist(int i) {
		return Math.abs(i - currentFloor);
	}

	/**
	 * This method moves the elevator to the destination floor
	 */
	public void moveElev() {
		currentFloor = destinationFloor;
		System.out.println("Elevator is on floor " + currentFloor);
	}

	public boolean isEmpty() {
		return elevStack.isEmpty();
	}

	public boolean isFull() {
		return elevStack.isFull();
	}
}
