package ProgramFiles;

import org.openqa.selenium.WebDriver;

public class WaitAndNotify {
	
	static boolean awaitTestFinish = OpenSelectedScreen.awaitTestFinish = true;
	static Object objectForSynch = OpenSelectedScreen.objectForSynch = new Object();
	static WebDriver webDriver = RunBrowser.webDriver;

	public static void StartWait() {
		System.out.println(" =========== StartWait() from MachinesTestCase ========== ");
		synchronized (objectForSynch) {
			while (awaitTestFinish) {
	            try {
	            	objectForSynch.wait();
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt(); 
	                System.out.println("Thread Interrupted");
	                webDriver.quit();
					System.exit(-1);
	            }//try
	        }//while
	        awaitTestFinish = true;
		}//synchronized
	}//StartWait
	
	public static void NotifyAll() {
		System.out.println(" =========== NotifyAll from MachinesTestCase ========== ");
		synchronized (objectForSynch) {
			try {
				awaitTestFinish = false;			        
				objectForSynch.notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}
		};//synchronized
	}// NotifyAll
	
}///////////////////////////////////////////////////////////////////////////////
