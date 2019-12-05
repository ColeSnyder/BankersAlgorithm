
import java.util.*;
public class clock {
	
	long stime;
	
	public clock()
	{
		stime = Calendar.getInstance().getTime().getTime();
	}
	
	long getTime()
	{
		return Calendar.getInstance().getTime().getTime() - stime;
		
	}


}
