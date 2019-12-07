
public class bankers {
	
	system mySystem;
	
	public bankers(system mySystem) {
		this.mySystem = mySystem;
	}
	
	public void allocRequest(pcb job, int allocReq) {
		if(BanekrsCheck(mySystem.getJobs(), mySystem.getFreeMemory(), allocReq)) {
			job.allocate(); //After resource is allocated probably should check if anything freed up
			checkWaiting(); //This is checking if freed up and possibly should let someone waiting on semaphore back in!!
		}
		else {
			job.resourceWait();//Wait on the resource Sem
		}
	}
		
	public static boolean BankersCheck(pcb[] jobs, int freeMem, int allocReq) {
		pcb[] tempJobs;
		System.arraycopy(jobs, 0, tempJobs, 0, jobs.length);
		
		if(freeMem < allocReq) {
			return false; //Not enough free memory to request that
		}
		else {
			int testFreeMem = freeMem - allocReq;
			boolean freedMemory = false;
			
			while(true) {
				for (pcb job : tempJobs) { //Go through all of the jobs
					if(job.needsAlloc()) { //If the Job needs allocated aka not finished
						
						
						System.out.println(job.getAllocLeft()); // test this 
						
						
						if(job.getAllocLeft() <= testFreeMem) { //Job was able to finish with what free memory is left
							freedMemory = true;
							testFreeMem += job.getTotalAlloc();
							job.done();
						}
					}

				}
				
				if(!freedMemory) {
					return false; //No job could be finished with this loop
				}
				else {
					
					if(tempJobs.isDone()) {
						return true; //All jobs were able to be finished
					}
					else {
						freedMemory = false; //Going through the foreach again because freed memory was true so more allocation possibly
					}
				}
				
			}
			
		}		
		return false;
	}
	
	

}
