import java.util.*;


public class systhread extends Thread

{

	pcb mypcb;


	public systhread(pcb p)

	{

		mypcb = p;


		System.out.println("Process # " + mypcb.myName + ": Starting at " + mypcb.myclock.getTime());


	}


	public void run()

	{


		while(true)

		{ 

			long t;


			System.out.println(mypcb.myclock.getTime() + " "+ mypcb.myName );


			mypcb.mycpusemaphore.Wait();

			System.out.println(mypcb.myclock.getTime() + "                 "+ mypcb.myName);

			//We can now use the CPU!!
			
			try {
				sleep(mypcb.cpuBurst());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//After it does its CPU burst it needs to do allocation!!
			
			//Check if it is done first though
			
			if(mypcb.isDone()) {
				//tis done not sure what to do with it now
			}
			else {
				bankers.BankersCheck(mypcb.getRequest());
			}
			
			

			// try {sleep(mypcb.cstime);}catch(InterruptedException e) {}


			System.out.println(mypcb.myclock.getTime() + "                     "+ mypcb.myName );

			mypcb.mycpusemaphore.Signal();



			// try {sleep(mypcb.outsidetime);}catch(InterruptedException e) {}


		}


	}
}