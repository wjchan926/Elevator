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
	private String direction;
	private ElevatorStack<ElevatorPerson> elevStack;
	private MyStack<ElevatorPerson> tempExitStack;

	Elevator() {
		currentFloor = 1;
		direction = "up";
		elevStack = new ElevatorStack<ElevatorPerson>();
		tempExitStack = new MyStack<ElevatorPerson>();
	}

	/**
	 * Gets the current floor the elevator is on
	 * 
	 * @return currentFloor is the floor the elevator is currently on as type int
	 */
	public int getCurretnFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int floor) {
		currentFloor = floor;
	}

	public MyStack<ElevatorPerson> getElevStack() {
		return elevStack;
	}

	public MyStack<ElevatorPerson> getTempExitStack() {
		return tempExitStack;
	}

	/**
	 * Returns the direction the elevator is going
	 * 
	 * @return True if the elevator is going upward, false if the elevator is going
	 *         downward
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Sets the direction to true, corresponds to elevator moving upwards
	 */
	public void elevatorUp() {
		direction = "Up";
	}

	/**
	 * Sets the direction to false, corresponds to elevator moving downwards
	 */
	public void elevatorDown() {
		direction = "Down";
	}

	public void enter(ElevatorPerson ep) {
		elevStack.push(ep);
		detNextFloor();
	}

	public void exit() {
		for (int i = 0; i <= elevStack.getSize(); i++) {
			if (elevStack.peek().getFloorExit() != destinationFloor) {
				tempExitStack.push(elevStack.pop());
			} else {
				// Write to file who exited
				elevStack.pop();
			}
		}
		
		restoreTempExited(); // People return if exited
		detNextFloor();
	}

	/**
	 * Pops every element in the tempExitStack and Pushes them back into the
	 * elevStack. Represents people reentering the elevator after making a temporary
	 * exit. Increments the number of temporary exits a person has made.
	 */
	private void restoreTempExited() {
		if (tempExitStack.getSize() > -1) {
			for (ElevatorPerson ep : tempExitStack.getStackArr()) {
				ep.incTempExits();
				elevStack.DECTOTALRODE(); // People who are temporarily exiting are not double counted in total.
				elevStack.push(tempExitStack.pop());
			}
		}
	}

	/**
	 * Determines which floor is the next closest floor based on the direction the
	 * elevator is moving. If there are no floor requests in the direction the
	 * elevator is moving, the elevator will switch it's direction. The method will
	 * then recursively determine the closest floor;
	 * 
	 * Precondition: method is only called when a new person enters the elevator is
	 * made or a person permanently exits the elevator.
	 */
	private void detNextFloor() {
		int tempFloor = currentFloor;
		int diff = 5;
		int distance = 0;

		if (!elevStack.isEmpty()) {
			switch (direction) {
			case "Up": // Elevator is moving up
				for (ElevatorPerson ep : elevStack.getStackArr()) {
					if (ep.getFloorExit() > currentFloor && diff > (distance = tempFloor - ep.getFloorExit())) {
						diff = distance;
						tempFloor = ep.getFloorExit();
					}

					// Memoization of destination floor;
					destinationFloor = tempFloor;

					if (tempFloor == currentFloor) {
						elevatorDown();
						detNextFloor();
					}
				}
				break;
			case "Down": // Elevator is moving down
				for (ElevatorPerson ep : elevStack.getStackArr()) {
					if (ep.getFloorExit() < currentFloor && diff > (distance = ep.getFloorExit() - tempFloor)) {
						diff = distance;
						tempFloor = ep.getFloorExit();
					}

					// Memoization of destination floor
					destinationFloor = tempFloor;

					if (tempFloor == currentFloor) {
						elevatorUp();
						detNextFloor();
					}
				}
				break;
			}

		}
	}
}
