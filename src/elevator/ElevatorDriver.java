package elevator;

import java.io.IOException;

public class ElevatorDriver {
	public static void main (String[] args) throws IOException{
		
		Reader r = new Reader("elevatorData.txt");
	//	System.out.println(r.countLines());
		
		// Prompt User for data source file
		
		ElevatorPerson ep1 = new ElevatorPerson("Wes1", 1, 2);
		ElevatorPerson ep2 = new ElevatorPerson("Wes2", 2, 4);
		ElevatorPerson ep3 = new ElevatorPerson("Wes3", 3, 1);
		ElevatorPerson ep4 = new ElevatorPerson("Wes4", 5, 1);
		
		ElevatorStack es = new ElevatorStack();
		es.push(ep1);
		es.push(ep2);
		es.push(ep3);
		es.push(ep4);
		
		es.printElevatorStack();
		
	}
}
