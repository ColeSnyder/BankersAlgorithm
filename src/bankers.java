
public class bankers {
	
	system mySystem;
	
	public bankers(system mySystem) {
		this.mySystem = mySystem;
	}
		
	public static boolean BankersCheck(int allocReq) {
		pcb[] jobs = (pcb[]) mySystem.getJobs().toArray();
		int freeMem = mySystem.getFreeMemory();
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
						if(job.getAllocLeft() <= testFreeMem) { //Job was able to finish with what free memory is left
							System.out.println(job);
							freedMemory = true;
							testFreeMem += job.totalJobAlloc();
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
