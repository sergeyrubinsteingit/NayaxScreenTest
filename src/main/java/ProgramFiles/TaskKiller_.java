package ProgramFiles;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TaskKiller_ {
	
	public static String pathToKillerBatch = System.getProperty("user.dir") + "\\src\\main\\java\\ProgramFiles\\BatchStorage\\";

	public static void main(String[] args) {
		
		System.out.println("\n============> TASK KILLER <================\n");
		
	    File taskKiller = new File( pathToKillerBatch + "taskKiller.bat");
	    try {
			Desktop.getDesktop().open(taskKiller);
			System.out.println(">>>>>>>>>>>>>> Taskkiller's running <<<<<<<<<<<<<<\n");
			TimeUnit.SECONDS.sleep(1);
			Robot robot_ = new Robot();
			robot_.keyPress(KeyEvent.VK_ENTER);
			robot_.keyRelease(KeyEvent.VK_ENTER);
		} catch (IOException | InterruptedException | AWTException e1) {
			System.out.println("<<<<<<<<<<<<<< Taskkiller failed >>>>>>>>>>>>>\n");
			e1.printStackTrace();
		}//try
	}
}
