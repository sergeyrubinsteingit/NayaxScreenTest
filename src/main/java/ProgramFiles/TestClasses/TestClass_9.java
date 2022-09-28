package ProgramFiles.TestClasses;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import ProgramFiles.OpenSelectedScreen;
import ProgramFiles.RunBrowser;
//import ProgramFiles.OpenSelectedScreen;

public class TestClass_9 extends OpenSelectedScreen {

	public static void TestMethod_9(String menuItemsText) {
		System.out.println("<<<<<<<<<<<< Test class 9 >>>>>>>>>");
		Actions actions = new Actions(RunBrowser.webDriver);

			try {
				// Filling the Operator input to open a tree
				WebElement actor_id_input = (WebElement) new FluentWait(RunBrowser.webDriver)
				        .withTimeout(60,TimeUnit.SECONDS)
				        .pollingEvery(1,TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.id("actor_id_filter_input")));

				actions.moveToElement(actor_id_input)
					.click()
					.sendKeys("rubiserg_BMo4Jac2oG")
					.perform();

				
				//Select an operator from the drop menu
				WebElement pickerText = (WebElement) new FluentWait(RunBrowser.webDriver)
				        .withTimeout(60,TimeUnit.SECONDS)
				        .pollingEvery(1,TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.className("pickerText")));

				actions.moveToElement(pickerText)
					.click()
					.perform();
				
				
				// Click on Select button
				WebElement search_machine_btn = (WebElement) new FluentWait(RunBrowser.webDriver)
				        .withTimeout(60,TimeUnit.SECONDS)
				        .pollingEvery(1,TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.id("search_machine_btn")));

				actions.moveToElement(search_machine_btn)
						.click()
						.perform();
				
				
				// Select a machine from the tree list
				new FluentWait(RunBrowser.webDriver)
				        .withTimeout(60,TimeUnit.SECONDS)
				        .pollingEvery(1,TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.className("tree_link")));
				
				List<WebElement> tree_link = RunBrowser.webDriver
						.findElements(By.className("tree_link"));
				
					actions.moveToElement(tree_link.get(2))
					.click()
					.perform();
				
				System.out.println("||||| PASSED ||||| " + menuItemsText.toString());
				
				synchronized (objectForSynch) {
					while (OpenSelectedScreen.awaitTestFinish) {
		            try {
		            	objectForSynch.wait();
		            } catch (InterruptedException e) {
		                Thread.currentThread().interrupt(); 
		                System.out.println("Thread Interrupted::: TestClass_1");
		            }//try
			        }//while
					OpenSelectedScreen.awaitTestFinish = false;			        
					objectForSynch.notifyAll();
				};//synchronized
				
			} catch (NoSuchElementException | ElementNotFoundException e1) {
				System.out.println("||||| FAILED ||||| " + menuItemsText.toString());
				e1.printStackTrace();
			} catch (TimeoutException e2) {
				System.out.println("(((( Timeout expired ))))");
				e2.printStackTrace();
			}//try
			
			
		try {					
			TimeUnit.MILLISECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
