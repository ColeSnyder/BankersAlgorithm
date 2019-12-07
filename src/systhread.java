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


			// try {sleep(mypcb.cstime);}catch(InterruptedException e) {}


			System.out.println(mypcb.myclock.getTime() + "                     "+ mypcb.myName );

			mypcb.mycpusemaphore.Signal();



			// try {sleep(mypcb.outsidetime);}catch(InterruptedException e) {}


		}


	}
}