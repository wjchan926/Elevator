package elevator;

/**
 * Generic stack class that will be used to hold Objects, typically an
 * ElevatorPerson
 * 
 * @author Wesley Chan
 * 
 */
public class MyStack {

	private int top;
	private ElevatorPerson[] stackArr;

	MyStack() {
		top = -1;
		stackArr = new ElevatorPerson[5];
	}

	public ElevatorPerson[] getStackArr() {
		return stackArr;
	}
	
	public void setStackSize(int i) {
		stackArr = new ElevatorPerson[i];
	}

	/**
	 * Adds an object to the stack
	 * 
	 * @param t
	 *            represents an object that will be placed in the stack
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
	 * Prints out who is in the stack
	 */
	public void printStack() {
		if (isEmpty()) {
			System.out.println("Elevator is Empty.");
		} else {
			for (int i = top; i > -1; i--) {
				System.out.println(stackArr[i]);
			}
		}
	}

	public int getSize() {
		return top+1;
	}
}
