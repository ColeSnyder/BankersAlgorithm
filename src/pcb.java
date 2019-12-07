import java.util.*;



public class pcb {


	public pcb(String name, int[] cpu_pages, clock gclock, semaphore csem, semaphore rsem)

	{

		myName = name;

		myclock = gclock;

		mycpusemaphore = csem;

		myresourcesemaphore = rsem;

		myNeeds = cpu_pages;

	}



	String myName;

	// int cstime, outsidetime;

	clock myclock;

	semaphore mycpusemaphore, myresourcesemaphore;

	int[] myNeeds;


	public String toString() {

		return myName;

	}



}