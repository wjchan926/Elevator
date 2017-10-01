package elevator;

/**
 * Generic stack class that will be used to hold Objects, typically an
 * ElevatorPerson
 * 
 * @author Wesley Chan
 *
 * @param <T>
 *            Generic used to represent an Object in the stack
 */
public class MyStack<T> {

	private int top;
	private Object[] stackArr;

	MyStack() {
		top = -1;
		stackArr = new Object[5];
	}
	
	@SuppressWarnings("unchecked")
	public T[] getStackArr() {
		return (T[]) stackArr;
	}

	/**
	 * Adds an object to the stack
	 * 
	 * @param t represents an object that will be placed in the stack
	 */
	public void push(T t) {
		if (isFull()) { // Checks if stack is full
			System.out.println("Full.");
		} else {
			stackArr[++top] = t;
		}
	}

	/**
	 * Removes an object from the stack.
	 * 
	 * @return the object at top of stack, null if empty
	 */
	@SuppressWarnings("unchecked")
	public T pop() {
		if (isEmpty()) { // Check if stack is empty
			System.out.println("Empty.");
			return null;
		} else {
			T t = (T) stackArr[top];
			stackArr[top] = null;
			top--;
			return t;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T peek() {
		if (isEmpty()) { // Check if stack is empty
			System.out.println("Empty.");
			return null;
		} else {
			return (T) stackArr[top];
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
		return top;
	}
}
