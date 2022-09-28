package ProgramFiles;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperationMenu {
	
	public static String [] screenFeatures = {"Consumers","Campaign","actor_id_filter_input","rubiserg_BMo4Jac2oG","k-button","actor_id_filter_tree_0"};

		public static void main(String[] args) {
			
			System.out.println("\n============> CAMPAIGN SCREEN <================\n");
			
			WebDriver webDriver = RunBrowser.webDriver;
			
			for (int c0 = 0; c0 < 2; c0++) {

				try {
	//				WebElement selectedFeature = new WebDriverWait(webDriver, 30)
	//						.until(ExpectedConditions.visibilityOfElementLocated(By.id(screenFeatures[c0])));		
						WebElement selectedFeature = new WebDriverWait(webDriver, 30)
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+screenFeatures[c0].toString()+"']")));
					selectedFeature.click();
					TimeUnit.SECONDS.sleep(1);
	//					
				} catch (/* IOException | */ NullPointerException ioe_nulle) {
					System.out.println("Failed to find " + screenFeatures[c0]);
					ioe_nulle.printStackTrace();
					webDriver.quit();
					System.exit(-1);
				} catch ( TimeoutException | NoSuchElementException e) {
					System.out.println("<<<<< Selected webdriver failed to open the site in " 
							+ Combo_Box.selectedBrowser.toString()
							+ ". The system was shut down. >>>>>\n");
					e.printStackTrace();
					webDriver.quit();
					System.exit(-1);
				} catch (InterruptedException e) {
					webDriver.quit();
					System.exit(-1);
					e.printStackTrace();
				}//try
		}//for
			
			try {
				Robot robot = new Robot();
				for(int c1=0;c1<3;c1++) {
					robot.keyPress(KeyEvent.VK_CONTROL);			
					robot.keyPress(KeyEvent.VK_MINUS);
				};//for
			} catch (AWTException e1) {
				e1.printStackTrace();
			}//try
			
			// Input the operator's name and click Search button   

			WebElement operatorInput = new WebDriverWait(webDriver, 30)
											.until(ExpectedConditions.visibilityOfElementLocated(By.id(screenFeatures[2])));
			operatorInput.sendKeys(screenFeatures[3]);
			
			WebElement operatorSearch = new WebDriverWait(webDriver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.className(screenFeatures[4])));
			operatorSearch.click();
			
			// Selects an operator
			
			WebElement operatorSelect = new WebDriverWait(webDriver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(screenFeatures[5])));
			operatorSelect.click();

	}//main

}//class
