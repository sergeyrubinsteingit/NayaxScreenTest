package ProgramFiles;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuItemsList {
	
	public static String [] screenFeatures = {
			"Operations",
			"k-animation-container",
			"k-group",
			"k-item",
			"k-link",
			};
		
	static WebDriver webDriver = RunBrowser.webDriver;
	public static List<WebElement> menuItems = new ArrayList<>();
	public static List<String> menuItemsText = new ArrayList<>();
	
	public static class ItemsList {
		
//		public static Actions newAction = new Actions(webDriver);
		
		public static void CreateItemsList() {
			
			Actions newAction = new Actions(webDriver);
			//Opens drop menu
			OperationMenu.openOperationsMenu();
			// Gets menu list
			try {
				WebElement animationContainer = new WebDriverWait(webDriver, 90)
						.until(ExpectedConditions.visibilityOfElementLocated(By.className(screenFeatures[1])));
				
				menuItems = animationContainer.findElements(By.className(screenFeatures[2])
						.className(screenFeatures[3])
						.className(screenFeatures[4])
						.tagName("a")
						);
				for (WebElement menuItem_ : menuItems) {
					String menuItemTxt_ = menuItem_.getText();
//					String menuItemTxt_ = menuItem_.getAttribute("href");
					menuItemsText.add(menuItemTxt_);
				}//for

				TimeUnit.MILLISECONDS.sleep(20);
//				OperationMenu.openOperationsMenu();
				newAction.moveToElement(webDriver.findElement(By.xpath("//*[text()='" + screenFeatures[0] + "']"))).click().perform();
//				TimeUnit.SECONDS.sleep(5);
				OpenSelectedScreen openSelectedScreen = new OpenSelectedScreen();
				openSelectedScreen.main(menuItemsText);
			} catch (TimeoutException | InterruptedException e) {
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}//try
		}
	}//MenuItem

	public static void main(String[] args) {
		System.out.println("\n============> MENU ITEMS LIST <================\n");
		
		try {
			TimeUnit.MILLISECONDS.sleep(20);
			Robot robot = new Robot();
			for(int c1=0;c1<4;c1++) {
				robot.keyPress(KeyEvent.VK_CONTROL);			
				robot.keyPress(KeyEvent.VK_MINUS);
				TimeUnit.MILLISECONDS.sleep(1);
				robot.keyRelease(KeyEvent.VK_CONTROL);			
				robot.keyRelease(KeyEvent.VK_MINUS);
				TimeUnit.MILLISECONDS.sleep(1);
			};//for
			System.out.println("After the Robot Minus stuff.");

		} catch (AWTException | NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			webDriver.quit();
			System.exit(-1);
		}//try
			catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ItemsList.CreateItemsList();
		System.gc();// Garbage collector
	}//main

}
