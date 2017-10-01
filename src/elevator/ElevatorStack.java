package elevator;

/**
 * This class represents the people in the Elevator.
 * 
 * @author Wesley Chan
 *
 * @param <T>
 *            Generic used to represent an Object in the Elevator
 */
public class ElevatorStack extends MyStack {

	public static int numEmpty;
	public static int numFull;

	private static int totalRode = 0;

	ElevatorStack() {
		super();
		numEmpty = -2; // Elevator will always be empty in the beginning and at the end of the simulation
		numFull = 0;
	}

	public int getNumEmpty() {
		return numEmpty;
	}

	public int getNumFull() {
		return numFull;
	}

	public final int GETNUMEMPTY() {
		return numEmpty;
	}

	public final void INCNUMEMPTY() {
		numEmpty++;
	}

	public final int GETNUMFULL() {
		return numFull;
	}

	public final void INCNUMFULL() {
		numFull++;
	}

	public final int GETTOTALRODE() {
		return totalRode;
	}

	public final void DECTOTALRODE() {
		totalRode--;
	}

	@Override
	public void push(ElevatorPerson ep) {
		totalRode++;
		super.push(ep);
	}
}
