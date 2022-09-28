package ProgramFiles;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ProgramFiles.MenuItemsList;

public class OperationMenu {
	
	public static WebDriver webDriver = RunBrowser.webDriver;
	
	public static String[] screenFeatures = MenuItemsList.screenFeatures;
	
	public static void openOperationsMenu() {
		System.out.println("\n============> OPERATIONS MENU <================\n");

		try {		
			WebElement selectedFeature = (WebElement) new FluentWait(webDriver)
			        .withTimeout(60,TimeUnit.SECONDS)
			        .pollingEvery(1,TimeUnit.SECONDS)
			        .ignoring(Exception.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+screenFeatures[0]+"']")));
				selectedFeature.click();
				TimeUnit.MILLISECONDS.sleep(15);
//				MenuItemsList.main(screenFeatures);

		} catch (/* IOException | */ NullPointerException ioe_nulle) {
			System.out.println("NullPointerException: " + screenFeatures[0]);
			ioe_nulle.printStackTrace();
			webDriver.quit();
			System.exit(-1);
		} catch ( TimeoutException | NoSuchElementException e) {
			System.out.println("NoSuchElementException/TimeoutException: " + screenFeatures[0]);
			e.printStackTrace();
			webDriver.quit();
			System.exit(-1);
		} catch (InterruptedException e) {			
			System.out.println("InterruptedException: " + screenFeatures[0]);
			webDriver.quit();
			System.exit(-1);
			e.printStackTrace();
		}//try
		System.out.println("\n============> End of the Operations menu class <================\n");
		System.gc();// Garbage collector
	}//openOperationsMenu
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * 
	 * }//main
	 */
}//class
