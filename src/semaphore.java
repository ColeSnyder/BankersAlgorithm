
import java.util.*;

public class semaphore {

	protected  int value = 0 ;
	
	protected semaphore(int initial) { value = initial ; }
	
	public synchronized void Wait() {
		value-- ;
		if (value < 0)
			try { wait() ; } catch(  InterruptedException e ) { }
	}
	
	public synchronized void Signal() {
		value++ ; if (value <= 0) notify() ;
	}

}
