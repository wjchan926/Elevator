package elevator;

public class ElevatorPerson {
	
	private String name;
	private int floorEntered;
	private int floorExtited;

	
	ElevatorPerson(String name, int floorEntered, int floorExited){
		this.setName(name);
		this.setFloorEntered(floorEntered);
		this.setFloorExtited(floorExited);		
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
		return floorEntered;
	}

	/**
	 * Sets the floor in which the person entered
	 * @param floorEntered floor in which the person entered of type int
	 */
	public void setFloorEntered(int floorEntered) {
		this.floorEntered = floorEntered;
	}
	
	/**
	 * Gets the floor where person wants to exit
	 * @return floor where person wants to exit as type int
	 */
	public int getFloorExtited() {
		return floorExtited;
	}

	/**
	 * Sets the floor where person wants to exit
	 * @param floorExtited floor where person wants to exit as type int
	 */
	public void setFloorExtited(int floorExtited) {
		this.floorExtited = floorExtited;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}
