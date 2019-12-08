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
				sleep(mypcb.cpuBurst());//Pop description too or whatever
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			//After it does its CPU burst it needs to do allocation!!
			
			//Check if it is done first though
			
			if(mypcb.isDone()) {
				//tis done not sure what to do with it now
			}
			else {
				
				if(bankers.BankersCheck(mypcb.getRequest())) {//Able to get some resources!
					mypcb.popDescTingy();
					//Handle whatever needs to happen here.
					//Like some more stuff
					
					mypcb.mycpusemaphore.Signal(); //Release CPU!!!! Let someone else get it and don't be greedy
				}
				else {
					mypcb.mycpusemaphore.Signal(); //Release CPU!!!! Let someone else get it and don't be greedy
					
					mypcb.myresourcesemaphore.Wait();
					//This is it cannot allocate and it needs to wait for resourses
					
					mypcb.popDescTingy();
					//Handle whatever needs to happen here.
					//Like some more stuff
					
					mypcb.myresourcesemaphore.Signal();
					
					
					
				}
				
				System.out.println(mypcb.myclock.getTime() + "                     "+ mypcb.myName );
			}


		}


	}
}