import java.io.FileNotFoundException;

public class mytest1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		system S = new system();
		bankers b = new bankers(S);
		S.start();

	}

}
 