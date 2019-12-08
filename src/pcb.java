import java.util.Arrays;

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
	
	public int getNext() {
		return myNeeds[0];
	}
	
	public int popNeed() {
		int popped = myNeeds[0];
		int[] temp = new int[myNeeds.length-1];
		
		if(myNeeds.length == 1) {
			myNeeds = new int[0];
		}
		else {
			for(int i = 0; i < myNeeds.length - 1; i++){
	            temp[i] = myNeeds[i+1];
	        }
		}
		
		myNeeds = temp;
		
		return popped;
	}

	public String toString() {
		return myName;
	}
	
	public int getAllocLeft() {//When this is called the CPU burst was just popped so the allocation would actually be on the even 
		int allocLeft = 0;
		
		if(myNeeds.length % 2 == 0) { //Even amount so next is allocation
			for (int i = 0; i < myNeeds.length; i+=2 ) {
				allocLeft += myNeeds[i];
			}
		}
		else { //Odd amount so next is CPU burst
			for (int i = 1; i < myNeeds.length; i+=2 ) {
				allocLeft += myNeeds[i];
			}
		}
		
		
		
		return allocLeft;
	}
	
	
	
	public void done() {
		isDone = true;
	}
	
	public void unDone() {
		isDone = false;
	}
	
	public boolean isDone() {
		return myNeeds.length <= 0;
	}
	
	public boolean needsAlloc() { 
		return getAllocLeft() > 0;
	}

}