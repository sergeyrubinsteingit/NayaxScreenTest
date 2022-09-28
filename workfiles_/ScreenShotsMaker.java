package testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ProgramFiles.RunBrowser;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.openqa.selenium.JavascriptExecutor;		


public class ScreenShotsMaker {

	static WebDriver webDriver = RunBrowser.webDriver;
	static String screenShotsPath = System.getProperty("user.dir") + "\\screenShots\\";
	static Boolean isElemDisplayed = webDriver.findElement(By.className("k-input")).isDisplayed();
	static WebElement bodyElement = webDriver.findElement(By.tagName("body"));

	
	@Test
	public static void ScreenShots(String fileName, WebElement bottomElement) {
		
		System.out.println(" =========== SCREENSHOTS MAKER ========== ");
		
		System.out.println("<<<< Screening for file " + screenShotsPath + fileName.toString() + " >>>>");
		
		System.out.println("<<<< bottomElement ::: " + bottomElement + " >>>>");
		
		try {
			Robot robot = new Robot();
			
			//var 2
//			JavascriptExecutor js = (JavascriptExecutor) webDriver;
//			js.executeScript("arguments[0].scrollIntoView(true);", bottomElement);
			
//			if (bottomElement.isDisplayed() != true || bottomElement.isEnabled() != true) {
//				System.out.println("<<<<**** bottomElement.isDisplayed() ==  " + bottomElement.isDisplayed() + " >>>>");
//			}//if
//			int i = 0;

//			while (bottomElement.isDisplayed() != true || bottomElement.isEnabled() != true) {
			
				for (int i = 0; i<2; i++) {
//					Thread.sleep(1000);
//
//					if ( isElemDisplayed != true ) {
//						
//						System.out.println("<<<<**** i =  " + i + " >>>>");
//						
//						System.out.println("<<<<**** isElemDisplayed :::  " + isElemDisplayed + " >>>>");
//						
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_MINUS);
						TimeUnit.MILLISECONDS.sleep(5);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_MINUS);
						TimeUnit.MILLISECONDS.sleep(5);
//					} else {
//						break;
//					} //if
//				}//for
				
//				if (bottomElement.isDisplayed() == true) {
//					break;
//				}//if
				
//				for (int i = 0; i<3; i++) {
//					webDriver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
				}//for

//			}//while

//			int yPosition = bottomElement.getLocation().getY();
//			js.executeScript("window.scroll (0, " + yPosition + ") ");       
//			Screenshot screenShot = new AShot()
//					.shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1.75f), 100))
//					.takeScreenshot(webDriver);
//			ImageIO.write(screenShot.getImage(), "png", new File(screenShotsPath + fileName.toString() + ".png"));
//			TimeUnit.MILLISECONDS.sleep(25);
			
			String shortcutGoToFullScreen = Keys.chord(Keys.F12);
			webDriver.findElement(By.tagName("body")).sendKeys(shortcutGoToFullScreen);

			//var 1
			((JavascriptExecutor)webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			System.out.println(" ===========  Body object " + bodyElement + " ===============");
			File shotFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(shotFile, new File(screenShotsPath + fileName.toString() + ".png"));
			TimeUnit.MILLISECONDS.sleep(25);
						
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_0);
			
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
	}//ScreenShots
	
}// ScreenShotsMaker