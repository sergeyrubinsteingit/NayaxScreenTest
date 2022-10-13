package ProgramFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
//import ProgramFiles.RunBrowser;

public class CreateHtmlReportFiles {
	
	static WebDriver webDriver = RunBrowser.webDriver;
	static String htmlReportsDir = (System.getProperty("user.dir") + "\\htmlReportsDir\\");
	static String [] reportFiles = {"Screenshots.html","Extentreport.html"};
	static File reportFile = null;
	
	public static String pathToHTMLfiles = null;
	
	public static void createReportFiles () {
		
		System.out.println("\n=================== CREATE HTML FILES ===================\n");

		try {
			//Listing of the report files in directory
			System.out.println("Files in htmlReportsDir:\n");
			for ( Path htmlFilesPath : Files.newDirectoryStream(Paths.get(htmlReportsDir)) ) {
				System.out.println(htmlFilesPath.normalize().getFileName());
			};//for
			
			//Creating new files, deleting the former ones if they exist.
			for (int i = 0; i < reportFiles.length; i++) {
				reportFile = new File(System.getProperty("user.dir") + "\\htmlReportsDir\\" + reportFiles[i]);//
					if (reportFile.exists()) {
						System.out.println("============= Former " + reportFile.getName().toUpperCase().toString() + " is DELETED. =============");
						reportFile.delete();
					};//if				
				reportFile.createNewFile();
				System.out.println("============= New " + reportFile.getName().toUpperCase().toString() + " is CREATED. =============");
			};//for
			
			//Fires adding basic html to file if the file exists
			for (int i = 0; i < 5; i++) {
				if (reportFile.exists()) {
					addBasicHTML();
					break;
				} else if (i == 4 && !reportFile.exists()) {
					System.out.println("------**------ Report file is not found. The test is shut down. ------**------");
					webDriver.quit();
				} else {
					TimeUnit.SECONDS.sleep(1);
				};//if
			} //for
			
		} 
		catch (IOException e)
		{
			System.out.println("------------ Report file is not found. The test is shut down. ------------");
			webDriver.quit();
			e.printStackTrace();
			System.exit(-1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};//try
	};//createReportFiles
	
	
	//Adds basic HTML code to a file
	static void addBasicHTML () {
		try {
			String pathToJSCSSfiles = System.getProperty("user.dir").toString() + "\\src\\main\\java\\ProgramFiles\\JS_CSS\\";
			pathToHTMLfiles = System.getProperty("user.dir") + "\\htmlReportsDir\\" + reportFiles[0];

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToHTMLfiles));
			
			bufferedWriter.write("<!DOCTYPE html>");
			bufferedWriter.write("<html lang=\"en\"><head>");
			bufferedWriter.write(" <meta charset=\"UTF-8\"></meta>");
			bufferedWriter.write("<script src=\"" + pathToJSCSSfiles + "screenshotReportJS.js" + "\"></script>");
			bufferedWriter.write("<link rel=\"stylesheet\" href=\"" + pathToJSCSSfiles + "screenshotsStyles.css" + "\" type=\"text/css\"></link>");
			bufferedWriter.write("<title>The Screen Shots Log</title>");
			bufferedWriter.write("</head>");
			bufferedWriter.write("<body onload=\"onLoadFunction();\">");
			bufferedWriter.write("<h2 id=\"reportHeader\">Test Screenshots</h2>");
			bufferedWriter.write("<div id=\"container\">");
			bufferedWriter.write("</div>");
			bufferedWriter.write("</body></html>");
			bufferedWriter.close();

		} catch (IOException e) {
			System.out.println("------------ Failed to add basic HTML to screen shot reports file. Shut down. ------------");
			webDriver.quit();
			e.printStackTrace();
			System.exit(-1);
		}
	};//addBasicHTML
	

}/////////////////////////////////////////////////////////////////////////////////////////////////
