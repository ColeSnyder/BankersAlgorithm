import java.util.*;

public class systhread extends Thread
{
	pcb mypcb;
	
	public systhread(pcb p)
	{
		mypcb = p;
		
		System.out.println("Process # " + mypcb.pid + ": Starting at " + mypcb.myclock.getTime());
		
	}
	
	public void run()
	{
		
		while(true)
		{
			long t;
			
			System.out.println(mypcb.myclock.getTime() + "		"+ mypcb.pid );
			
			mypcb.mysemaphore.Wait();
			System.out.println(mypcb.myclock.getTime() + "		                 "+ mypcb.pid);
			
			try {sleep(mypcb.cstime);}catch(InterruptedException e) {}
			
			System.out.println(mypcb.myclock.getTime() + "				                     "+ mypcb.pid );
			mypcb.mysemaphore.Signal();
			
			
			try {sleep(mypcb.outsidetime);}catch(InterruptedException e) {}
			
		}
		
	}

}
