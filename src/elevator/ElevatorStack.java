package elevator;

/**
 * This class represents the space available in the Elevator. This class extends
 * MyStack.
 * 
 * @author Wesley Chan
 *
 */
public class ElevatorStack extends MyStack {

	public static int numEmpty;
	public static int numFull;

	private static int totalRode = 0;

	/**
	 * Default constructor for the ElevatorStack class
	 */
	ElevatorStack() {
		super();
		numEmpty = 0; // Do not count when elevator is empty at the end or
						// beginning
		numFull = 0;
	}

	/**
	 * Gets the number of times the elevator was empty while moving
	 * 
	 * @return number of times the elevator was empty while moving as an int
	 */
	public final int GETNUMEMPTY() {
		return numEmpty;
	}

	/**
	 * Increases the number of times the elevator was empty while moving by 1
	 */
	public final void INCNUMEMPTY() {
		numEmpty++;
	}

	/**
	 * Gets the number of times the elevator was full and someone had to take
	 * the stairs
	 * 
	 * @return number of times elevator was full as an int
	 */
	public final int GETNUMFULL() {
		return numFull;
	}

	/**
	 * Increases the number of times the elevator was full by 1
	 */
	public final void INCNUMFULL() {
		numFull++;
	}

	/**
	 * Gets the total amount of people that rode on the elevator
	 * 
	 * @return total amount of people that rode the elevator as an int
	 */
	public final int GETTOTALRODE() {
		return totalRode;
	}

	/**
	 * Decreases the total amount of people that rode the elevator by one.
	 * 
	 * Precondition: Only used when people are temporarily exiting the elevator
	 * to prevent a double count of the people riding the elevator.
	 */
	public final void DECTOTALRODE() {
		totalRode--;
	}

	/**
	 * Adds an ElevatorPerson object to the top of the stack. Represents someone
	 * walking into the elevator. Increases the amount of people that rode on
	 * the elevator by 1.
	 */
	@Override
	public void push(ElevatorPerson ep) {
		totalRode++;
		super.push(ep);
	}
}
