package elevator;

/**
 * This class represents a person that wants to use the elevator.
 * 
 * @author Wesley Chan
 *
 */
public class ElevatorPerson {
	
	private String name;
	private int floorEnter;
	private int floorExit;
	private int tempExits;
	
	ElevatorPerson(String name, int floorEnter, int floorExit){
		this.setName(name);
		this.setFloorEntered(floorEnter);
		this.setFloorExit(floorExit);		
		tempExits = 0;
	}

	/**
	 * Gets the person's name
	 * @return name of person
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the person's name
	 * @param name of person as type String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the floor which the person entered
	 * @return the floor in which the person entered as type int
	 */
	public int getFloorEntered() {
		return floorEnter;
	}

	/**
	 * Sets the floor in which the person entered
	 * @param floorEnter floor in which the person entered of type int
	 */
	public void setFloorEntered(int floorEnter) {
		this.floorEnter = floorEnter;
	}
	
	/**
	 * Gets the floor where person wants to exit
	 * @return floor where person wants to exit as type int
	 */
	public int getFloorExit() {
		return floorExit;
	}

	/**
	 * Sets the floor where person wants to exit
	 * @param floorExit floor where person wants to exit as type int
	 */
	public void setFloorExit(int floorExit) {
		this.floorExit = floorExit;
	}
	
	/**
	 * Returns the number of times this person makes a temporary exit
	 * @return tempExits number of temporary exits person has made as type int
	 */
	public int getTempExits() {
		return tempExits;
	}
	
	/**
	 * Increases the number of temporary exits the person has made by 1
	 */
	public void incTempExits() {
		tempExits++;
	}
	
	
	
	@Override
	public String toString(){
		return name;
	}
	
}
