package elevator;

/**
 * This class represents the people in the Elevator.
 * 
 * @author Wesley Chan
 *
 * @param <T>
 *            Generic used to represent an Object in the Elevator
 */
public class ElevatorStack<T> extends MyStack<T> {

	public static int numEmpty;
	public static int numFull;

	private static int totalRode = 0;

	ElevatorStack() {
		super();
		numEmpty = 0;
		numFull = 0;
	}

	public int getNumEmpty() {
		return numEmpty;
	}

	public int getNumFull() {
		return numFull;
	}

	public final int GETTOTALRODE() {
		return totalRode;
	}
	
	public final void DECTOTALRODE() {
		totalRode--;
	}
	
	@Override
	public void push(T t) {
		totalRode++;
		super.push(t);
	}
	
	@Override
	public boolean isEmpty() {
		// Write to file that Elevator is empty

		return super.isEmpty();

	}

	@Override
	public boolean isFull() {
		// Write to file that Elevator is full

		return super.isFull();

	}
}
