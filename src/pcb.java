import java.util.*;

public class pcb {
	
	String myName; 

	clock myclock;

	semaphore mycpusemaphore, myresourcesemaphore;

	int[] myNeeds;
	
	boolean isDone;
	
	public pcb(String name, int[] jobDescription, clock gclock, semaphore csem, semaphore rsem)

	{

		myName = name;

		myclock = gclock;

		mycpusemaphore = csem;

		myresourcesemaphore = rsem;

		myNeeds = jobDescription;

	}

	public String toString() {
		return myName;
	} 
	
	public int getAllocLeft() {
		int PageAllocatetionLeft = 0;
		
		for (int i = 1; i < myNeeds.length; i+=2 ) {
			PageAllocatetionLeft += myNeeds[i];
		}
		
		return PageAllocatetionLeft;
	}
	
	public void done() {
		isDone = true; 
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public boolean needsAlloc() {
		if (isDone == false) {
			return false;
		} else {
			return true;
		}
	}

}