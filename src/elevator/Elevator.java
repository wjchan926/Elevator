package elevator;

/**
 * Represents an Elevator Object. This class controls the behavior of the
 * elevator and contains an ElevatorStack object to organize the people in the
 * elevator.
 * 
 * @author Wesley Chan
 *
 */
public class Elevator {

	private int currentFloor;
	private String direction;
	private ElevatorStack es;

	Elevator() {
		currentFloor = 1;
		direction = "up";
		es = new ElevatorStack();
	}

	/**
	 * Gets the current floor the elevator is on
	 * 
	 * @return currentFloor is the floor the elevator is currently on as type
	 *         int
	 */
	public int getCurretnFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int floor) {
		currentFloor = floor;
	}

	public ElevatorStack getEs() {
		return es;
	}

	public void elevatorUp() {
		direction = "up";
	}

	public void elevatorDown() {
		direction = "down";
	}

}
