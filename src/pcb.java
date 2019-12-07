import java.util.*;

public class pcb {
	
	String myName; 

	clock myclock;

	semaphore mycpusemaphore, myresourcesemaphore;

	int[] myNeeds;
	
	boolean isDone;
	
	int totalAllocForJob = 0;
	
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
		
		//here we need to have a way to take out or remove job resources when it's completed
		
		return PageAllocatetionLeft;
	}
	
	public int totalJobAlloc() {
		int jobResourceAllocatetion = 0;
		
		for (int i = 1; i < myNeeds.length; i+=2 ) {
			totalAllocForJob += myNeeds[i];
		}
		
		return jobResourceAllocatetion;
	}
	
	public void done() {
		isDone = true; 
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public boolean needsAlloc() {
		return isDone;
	}

}