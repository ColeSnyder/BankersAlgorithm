

import java.io.File;

import java.io.FileNotFoundException;

import java.util.*;


public class system extends Thread

{

	ArrayList<pcb> jobArray = new ArrayList<pcb>();
	systhread[] threadArray;

	int processcount;

	semaphore cpuSem, resourceSem, multiprosem;

	clock sysclock;

	static int resource = 0;

	int degree = 0;
 
	int counter = 0;

	boolean done;

	int totalJobAlloc = 0;
	
	int multicounter = 0;
	
	public void run(){


		cpuSem = new semaphore(1);
		resourceSem = new semaphore(1);
		multiprosem = new semaphore(1);
		sysclock = new clock();
		resourceSem.value--;
		

		Scanner scan = null;
		try {
			scan = new Scanner(new File("jobs.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String jobName;
		String info;

		resource = scan.nextInt();
		degree = scan.nextInt();

		while (scan.hasNextLine()) {

			jobName = scan.next();
			info = scan.nextLine();

			int[] jobInfo = Arrays.stream(info.trim().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for(int i = 1; i < jobInfo.length; i+=2) {
				totalJobAlloc += jobInfo[i];
			}
			
			jobArray.add(new pcb(jobName, jobInfo, sysclock, cpuSem, resourceSem, multiprosem));

		}

		

		// threadArray = new systhread[jobArray.size()];
		
		System.out.println("System ("+system.resource+")\t"+ sysclock.getTime());
		System.out.println("");
		System.out.println("");
		
		if (degree > jobArray.size()) {
			
			threadArray = new systhread[jobArray.size()];

			jobArray.forEach((n) -> {

			threadArray[counter++] = new systhread(n);

			threadArray[counter - 1].start();
			
			});
			
		} else {
			
			threadArray = new systhread[jobArray.size()];
			
			jobArray.forEach((n) -> {

				if (multicounter < degree) {
				
					threadArray[counter++] = new systhread(n);

					threadArray[counter - 1].start();
					multicounter++;
								
				}
			
			});
			
		}

	}
	
	public ArrayList<pcb> getJobs(){
		return jobArray;
	}
	
	public int getFreeMemory() {
		return resource;
	}
	
	public boolean isDone() {
		done = true;
		jobArray.forEach((n) -> {
			done = done && n.isDone();
		});
		return done;
		
	}
	
	public static synchronized void allocate(pcb job) {
		int allocated = job.popNeed();
		job.totalAllocForJob += allocated;
		resource -= allocated;
	}

}