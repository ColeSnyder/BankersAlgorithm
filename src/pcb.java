import java.util.*;


public class pcb {

	public pcb(int count, int cs, int out, clock gclock, semaphore gsem)
	{
		cstime = cs;
		outsidetime = out;
		pid = count;
		myclock = gclock;
		mysemaphore = gsem;
	}
	
	
	int pid;
	int cstime, outsidetime;
	clock myclock;
	semaphore mysemaphore;

}
