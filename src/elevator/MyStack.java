package elevator;

/**
 * Custom Stack class that will be used to hold ElevatorPerson Objects. The
 * stack uses an Array implementation.
 * 
 * @author Wesley Chan
 * 
 */
public class MyStack {

	private int top;
	private ElevatorPerson[] stackArr;

	/**
	 * Default Constructor for the custom stack class MyStack
	 */
	MyStack() {
		top = -1;
		stackArr = new ElevatorPerson[5];
	}

	/**
	 * Gets the array holding all the ElevatorPerson objects
	 * 
	 * @return array holding the ElevatorPerson objects
	 */
	public ElevatorPerson[] getStackArr() {
		return stackArr;
	}

	/**
	 * Sets the array size.
	 * 
	 * Precondition: only use to change the size of the tempExitArr
	 * 
	 * @param i
	 *            new size of array
	 */
	public void setStackSize(int i) {
		stackArr = new ElevatorPerson[i];
	}

	/**
	 * Adds an ElevatorPerson object to the stack
	 * 
	 * @param ep
	 *            ElevatorPerson to add to the stack
	 */
	public void push(ElevatorPerson ep) {
		if (isFull()) { // Checks if stack is full
			System.out.println("Full.");
		} else {
			top++;
			stackArr[top] = ep;
		}
	}

	/**
	 * Removes an object from the stack.
	 * 
	 * @return the object at top of stack, null if empty
	 */
	public ElevatorPerson pop() {
		if (isEmpty()) { // Check if stack is empty
			System.out.println("Empty.");
			return null;
		} else {
			ElevatorPerson ep = (ElevatorPerson) stackArr[top];
			stackArr[top] = null;
			top--;
			return ep;
		}
	}

	/**
	 * Gets the object at the top of the stack without removing it
	 * 
	 * @return ElevatorPerson object at the top of the stack
	 */
	public ElevatorPerson peek() {
		if (isEmpty()) { // Check if stack is empty
			System.out.println("Empty.");
			return null;
		} else {
			return stackArr[top];
		}
	}

	/**
	 * Checks if the stack is empty.
	 * 
	 * @return True if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return top < 0;
	}

	/**
	 * Checks if stack is full
	 * 
	 * @return True if there are 5 people in the stack, false otherwise
	 */
	public boolean isFull() {
		return top == stackArr.length - 1;
	}

	/**
	 * Returns the number of ElevatorPeople objects in the stack
	 * 
	 * @return number of people in the elevator as an int
	 */
	public int getSize() {
		return top + 1;
	}
}
