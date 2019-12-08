
public class bankers {
	
	static system mySystem;
	
	public bankers(system mySystem) {
		bankers.mySystem = mySystem;
	}
		
	public static boolean BankersCheck(int allocReq,pcb jobx) {
		pcb[] jobs = mySystem.getJobs().toArray(new pcb[mySystem.getJobs().size()]);;
		int freeMem = mySystem.getFreeMemory();
		pcb[] tempJobs = new pcb[jobs.length];
		System.arraycopy(jobs, 0, tempJobs, 0, jobs.length);
		
		
		if(freeMem < allocReq) {
			//System.out.println("Denied: Not enough available: Wait");
			return false; //Not enough free memory to request that
		}
		else {
			int testFreeMem = freeMem - allocReq;
			boolean freedMemory = false;
			
			while(true) {
				
				for (pcb job : tempJobs) { //Go through all of the jobs
					if(!job.isDone && !job.finished) {
						if(job.needsAlloc()) { //If the Job needs allocated aka not finished
							if(job.getAllocLeft() <= testFreeMem || (jobx.equals(job) && jobx.getAllocLeft()==allocReq)) { //Job was able to finish with what free memory is left
								freedMemory = true;
								testFreeMem += job.getAllocLeft()+job.totalAllocForJob;
								if (jobx.equals(job) && jobx.getAllocLeft()==allocReq) {
									jobx.done();
								}
								else {
									job.done();
								}
							}
						}
					}
					

				}
				
				if(!freedMemory) {
					//Stuck loopin here
					for(pcb job : tempJobs) {
						job.unDone();
					}
					//System.out.println("Request by Job "+jobx.myName+" Denied: UNSAFE: Wait    ");
					return false; //No job could be finished with this loop
				}
				else {
					boolean complete = true;
					for (pcb job : tempJobs) {
						if(!job.finished) {
							complete = complete && job.isDone;
						}
						
					}
					if(complete) {
						for(pcb job : tempJobs) {
							job.unDone();
						}
						return true; //All jobs were able to be finished
					}
					else {
						freedMemory = false; //Going through the foreach again because freed memory was true so more allocation possibly
					}
				}
				
			}
			
		}	
		
	}
	
	

}
