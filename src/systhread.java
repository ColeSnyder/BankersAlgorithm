
public class systhread extends Thread {

	pcb mypcb;


	public systhread(pcb p){
		mypcb = p;
		System.out.println("Job " + mypcb.myName + ": Starting at " + mypcb.myclock.getTime());
	}


	public void run() {


		while(!mypcb.isDone())

		{

			mypcb.mycpusemaphore.Wait(); //Wait for allowed CPU time
			System.out.println("Job "+mypcb.myName+" is running \t"+ mypcb.myclock.getTime());
			try {
				Thread.sleep(mypcb.popNeed());//Pop the first element of the desc array which has to be the CPU burst
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Waited for CPU burst time while holding semaphore
			
			

			//After it does its CPU burst it needs to do allocation!!
			//Check if it is done first though
			if(mypcb.isDone()) {
				
				System.out.println("Job "+mypcb.myName+" Done \t"+mypcb.myclock.getTime());
				mypcb.mycpusemaphore.Signal(); //Signal and this is done!
				system.resource += mypcb.totalAllocForJob;
				mypcb.done();
				System.out.println("System ("+system.resource+")");
				this.stop();
			}
			else {
				System.out.println("Job "+mypcb.myName+" needs resource\t"+mypcb.myclock.getTime());
				int allocated = 0;
				if(bankers.BankersCheck(mypcb.getNext(),mypcb)) {//Able to get some resources!
					System.out.println("Allocated ("+mypcb.getNext()+"): Remaining ("+(mypcb.getAllocLeft()-mypcb.getNext())+"): System ("+(system.resource-mypcb.getNext())+")");
					system.allocate(mypcb);
					
					//Handle whatever needs to happen here.
					//Like some more stuff
					mypcb.mycpusemaphore.Signal(); //Release CPU!!!! Let someone else get it and don't be greedy
				}
				else {
					mypcb.mycpusemaphore.Signal(); //Release CPU!!!! Let someone else get it and don't be greedy
					
					while(!bankers.BankersCheck(mypcb.getNext(),mypcb)) {
						mypcb.myresourcesemaphore.Wait();
						
						
						if(bankers.BankersCheck(mypcb.getNext(),mypcb)) {
							system.allocate(mypcb);
							mypcb.myresourcesemaphore.Signal();
							break;
						}
						
						mypcb.myresourcesemaphore.Signal();
					}
					
					
					system.allocate(mypcb);
					
					mypcb.myresourcesemaphore.Signal();
					
					//This is it cannot allocate and it needs to wait for resources
					

					//Handle whatever needs to happen here.
					//Like some more stuff
					
					mypcb.myresourcesemaphore.Signal();
					
				}
				
			}


		}


	}
}