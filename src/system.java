import java.util.*;

public class system extends Thread
{
	
	public void run()
	{

		cpu = new semaphore(1);
		processcount = 1;
		sysclock = new clock();
	
			System.out.println("Hello system is starting " + sysclock.getTime());

			
			p1 = new pcb(processcount, 50, 100,sysclock, cpu);
			m1 = new systhread(p1);
			processcount++;
		
			p2 = new pcb(processcount, 20, 500,sysclock, cpu); 
			m2 = new systhread(p2);
			processcount++;
			
			p3 = new pcb(processcount, 100, 500,sysclock, cpu); 
			m3 = new systhread(p3);
			processcount++;
			
			
			p4 = new pcb(processcount, 30, 30,sysclock, cpu); 
			m4 = new systhread(p4);
			processcount++;

			System.out.println("Time	Requesting		In			Out");
			System.out.println("");
			
			m1.start();
			m2.start();
			m3.start();
			m4.start();
			
     		
	}

	systhread m1, m2, m3, m4;
	pcb p1, p2, p3, p4;
	int processcount;
	semaphore cpu;
	clock sysclock;

}
