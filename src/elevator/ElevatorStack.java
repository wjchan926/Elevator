package elevator;

/**
 * Stack representation of the people in an elevator. Implemented by using an array with a
 * size of 5. The top of the stack represents the person closest to the elevator
 * doors. 5 is the max number of people that can be in the elevator at one time.
 * 
 * @author Wesley Chan
 *
 */
public class ElevatorStack {

	private int top;
	private ElevatorPerson[] elevArr;
	
	private static int totalRode = 0;
	private static int wasFull = 0;
	private static int wasEmpty = 0;

	ElevatorStack() {
		top = -1;
		elevArr = new ElevatorPerson[5];
	}

	/**
	 * Adds a person to the elevator stack, signifies a person has entered the
	 * elevator
	 * 
	 * @param ep
	 *            ElevatorPerson object representing a person that has entered
	 *            the elevator
	 */
	public void push(ElevatorPerson ep) {
		if (isFull()) { // Checks if elevator is full
			wasFull++;
			System.out.println("Elevator is full.");
		} else {
			elevArr[++top] = ep;
		}
	}

	/**
	 * Removes a person from the elevator stack. Signifies that a person has
	 * exited the elevator.
	 * 
	 * @return the person at top of elevator stack, null otherwise
	 */
	public ElevatorPerson pop() {
		if (isEmpty()) { // Check if elevator is empty
			wasEmpty++;
			System.out.println("Elevator is Empty");
			return null;
		} else {
			return elevArr[top--];
		}
	}

	/**
	 * Checks if the elevator is empty.
	 * 
	 * @return True if the elevator is empty, false otherwise
	 */
	public boolean isEmpty() {
		return top < 0;
	}

	/**
	 * Checks if elevator is full
	 * 
	 * @return True if there are 5 people in the elevator, false otherwise
	 */
	public boolean isFull() {
		return top == 4;
	}

	/**
	 * Prints out who is in the elevator
	 */
	public void printElevatorStack() {
		if (isEmpty()) {
			System.out.println("Elevator is Empty.");
		} else {
			for (int i = top; i > -1; i--) {
				System.out.println(elevArr[i]);
			}
		}
	}
}
