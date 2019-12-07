

import java.io.File;

import java.io.FileNotFoundException;

import java.util.*;


public class system extends Thread

{


	ArrayList<pcb> jobArray = new ArrayList<pcb>();


	systhread[] threadArray;

	int processcount;

	semaphore cpuSem, resourceSem;

	clock sysclock;


	int resource = 0;

	int degree = 0;


	int counter = 0;


	public void run()

	{


		// cpu = new semaphore(1);

		// processcount = 1;

		// sysclock = new clock();

		//

		// System.out.println("Hello system is starting " + sysclock.getTime());



		// p1 = new pcb(processcount, 50, 100, sysclock, cpu); 

		// m1 = new systhread(p1);

		// processcount++;

		//

		// p2 = new pcb(processcount, 20, 500,sysclock, cpu); 

		// m2 = new systhread(p2); 

		// processcount++;

		//

		// p3 = new pcb(processcount, 100, 500,sysclock, cpu); 

		// m3 = new systhread(p3);

		// processcount++;

		//

		//

		// p4 = new pcb(processcount, 30, 30,sysclock, cpu); 

		// m4 = new systhread(p4);

		// processcount++;


		// System.out.println("Time Requesting In Out");

		// System.out.println("");

		//

		// m1.start();

		// m2.start();

		// m3.start();

		// m4.start();




	}



	public void readFile() throws FileNotFoundException {


		cpuSem = new semaphore(1);

		resourceSem = new semaphore(1);


		// processcount = 1;

		sysclock = new clock();


		System.out.println("Hello system is starting " + sysclock.getTime());


		Scanner scan = new Scanner(new File("jobs.dat"));


		String jobName;

		String info;


		resource = scan.nextInt();

		degree = scan.nextInt();


		while (scan.hasNextLine()) {


			jobName = scan.next();

			info = scan.nextLine();


			System.out.println(info);


			int[] jobInfo = Arrays.stream(info.trim().split(" ")).mapToInt(Integer::parseInt).toArray();


			jobArray.add(new pcb(jobName, jobInfo, sysclock, cpuSem, resourceSem));


			// pcb p1 = new pcb(jobName, jobInfo, sysclock, cpuSem, resourceSem);

			// m1 = new systhread(p1);



		}


		System.out.println("Time Requesting In Out");

		System.out.println("");


		threadArray = new systhread[jobArray.size()];

		jobArray.forEach((n) -> {

			threadArray[counter++] = new systhread(n);

			threadArray[counter - 1].start();

		});


		// m1 = new systhread(p1);     after arraylist is all done, run through each one and do this



		// m1.start();

		// m2.start();

		// m3.start();

		// m4.start();



	}


}