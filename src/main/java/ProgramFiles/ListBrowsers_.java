package ProgramFiles;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import ProgramFiles.TaskKiller_;


public class ListBrowsers_ {
		
	public static String listBrowsersPath = "\\src\\main\\java\\ProgramFiles\\ListBrowsers\\";
	public static WebDriver webDriver;
	public static String [] batchFileName = {"browsers_list.bat", "brows_Lst_.bat"} ;
	public static Process process_;
	
	public static void renewLogFile() {
		
		String browsLogDir = System.getProperty("user.dir") + listBrowsersPath;
		File browsLogFile = new File(browsLogDir + "browsers_.txt");
		
			try {
				System.out.println("============> LIST BROWSERS <================");

				if (browsLogFile.exists()) {
					browsLogFile.delete();
//					TaskKiller_.main(null);
					System.out.println("Browsers list file is DELETED");
				}				
				browsLogFile.createNewFile();
//				TaskKiller_.main(null);
				System.out.println("Browsers list file is CREATED");
				TimeUnit.SECONDS.sleep(2);
				
			} catch (InterruptedException | IOException e) {
				System.out.println("Browsers list file FAILED");
				System.exit(-1);
			}
			
			try {
				ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.dir") + listBrowsersPath + batchFileName[0]);
			    
				processBuilder.directory(new File(System.getProperty("user.dir") + listBrowsersPath));
				File browserNamesLog = new File(System.getProperty("user.dir") + listBrowsersPath + "browsers_.txt");
				processBuilder.redirectErrorStream(true);
//				TaskKiller_.main(null);
				processBuilder.redirectOutput(Redirect.appendTo(browserNamesLog));
				process_ = processBuilder.start();
				process_.waitFor();
				System.out.println("<<<<<<<<<<<<<< Start Process Builder >>>>>>>>>>>>>\n");
			} catch (IOException | InterruptedException e) {
				System.out.println("<<<<<<<<<<<<<< Process Builder FAILED >>>>>>>>>>>>>\n");
				e.printStackTrace();
			}//try
//			process_.destroy();
			MakeBrowsersList.main(null);

	}//eo renewLogFile

	public static void main(String[] args) throws IOException, InterruptedException {
		renewLogFile();//Recreates a file for browsers log
	}// eomain

}//eoclass
