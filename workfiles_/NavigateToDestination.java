package ProgramFiles;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NavigateToDestination {
	
	public static String [] screenFeatures = {"user_menu","Account Settings"};

	public static void main(String[] args) {
		
		WebDriver webDriver = RunBrowser.webDriver;
		try {
			for (int c0 = 0; c0 < screenFeatures.length; c0++) {
	
				WebElement selectedFeature = new WebDriverWait(webDriver, 600)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id(screenFeatures[c0])));
				selectedFeature.click();
				TimeUnit.SECONDS.sleep(1);
				
	//			newAction.moveToElement(webDriver.findElement(By.id("user_menu"))).click().perform();
				
//				new WebDriverWait(webDriver, 60)
//				.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Account Settings")));
//				newAction.moveToElement(webDriver.findElement(By.partialLinkText("Account Settings"))).click().perform();
//				
			}//for
		} catch (/* IOException | */ NullPointerException ioe_nulle) {
			System.out.println("Default_StartBrowser:: Failed to recreate \\com.new_Version\\screen_shots\\ directory.");
			ioe_nulle.printStackTrace();
			webDriver.quit();
			System.exit(-1);
		} catch (TimeoutException | NoSuchElementException e) {
			System.out.println("<<<<< Selected webdriver failed to open the site in " 
					+ Combo_Box.selectedBrowser.toString()
					+ ". The system was shut down. >>>>>\n");
			e.printStackTrace();
			webDriver.quit();
			System.exit(-1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//main

}//NavigateToDestination
