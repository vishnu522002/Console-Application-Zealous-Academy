package ZealousAcademy;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		
		ZealousAcademyAccess acess = new ZealousAcademyAccess();
		
		Thread thr1 = new Thread(acess,"Vishnu");
		
		thr1.start();
		
//		Thread thr2 = new Thread(acess,"Thilak");
//		thr2.start();

	}
}
