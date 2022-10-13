package ProgramFiles;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;
import org.openqa.selenium.JavascriptExecutor;

import ProgramFiles.CreateHtmlReportFiles;
import ProgramFiles.AppendScreenshotsToHTML;

public class ScreenShotsMaker {

	static WebDriver webDriver = RunBrowser.webDriver;
	static String screenShotsPath = System.getProperty("user.dir") + "\\screenShots\\";
	static Boolean isElemDisplayed = webDriver.findElement(By.className("k-input")).isDisplayed();
	static WebElement bodyElement = webDriver.findElement(By.tagName("body"));
	
	
	public static void ScreenShots(String fileName, String className) {
		
		System.out.println(" =========== SCREENSHOTS MAKER ========== ");
		
		System.out.println("<<<< Screening for file " + screenShotsPath +"\\"+ className + "\\" + fileName.toString() + " >>>>");
		
		try {
			Robot robot = new Robot();
			// Zooming view in
//				for (int i = 0; i<1; i++) {					
//					robot.keyPress(KeyEvent.VK_CONTROL);
//					robot.keyPress(KeyEvent.VK_MINUS);
//					TimeUnit.MILLISECONDS.sleep(5);
//					robot.keyRelease(KeyEvent.VK_CONTROL);
//					robot.keyRelease(KeyEvent.VK_MINUS);
//					TimeUnit.MILLISECONDS.sleep(5);
//				}//for
			
//			String shortcutGoToFullScreen = Keys.chord(Keys.F12);
//			webDriver.findElement(By.tagName("body")).sendKeys(shortcutGoToFullScreen);

			//Taking shots
			((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			File shotFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(shotFile, new File(screenShotsPath + "\\" + className + "\\" + fileName + ".png"));
			TimeUnit.SECONDS.sleep(2);
			
			//Zoom out to 0
//			robot.keyPress(KeyEvent.VK_CONTROL);
//			robot.keyPress(KeyEvent.VK_0);
			
			// Inserts a shot image into the related div
//			addScreenshotsToHTML.createDOMrepresentationOfHTML(createHtmlReportFiles.pathToHTMLfiles,
//					"container", "<div class=\"testReportDiv\">"
//			+"<img src=\""+ screenShotsPath + fileName + ".png\" /></div>");
			
			WaitAndNotify.NotifyAll();
			
		} catch (IOException e) {
			System.out.println("Failed to save file " + screenShotsPath + fileName.toString() + ".png");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}//ScreenShots
	
}// ScreenShotsMaker