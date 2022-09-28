package ProgramFiles.TestClasses;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.verifier.exc.StaticCodeConstraintException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import ProgramFiles.OpenSelectedScreen;
import ProgramFiles.RunBrowser;
import testCases.ScreenShotsMaker;
import testCases.MachinesTestCase;

public class TestClass_1 extends OpenSelectedScreen {
	
//	static String operatorProd = "rubiserg_BuDrR1eluoT";
//	static String operatorQA2 = "rubiserg_D9HEatjed57";
	static String [] operators_ = {"rubiserg_D9HEatjed57", "rubiserg_BuDrR1eluoT"};
	static String selectedOperator = "";
	static String screenPath = RunBrowser.webDriver.getCurrentUrl().toString();	
	static Actions actions = new Actions(RunBrowser.webDriver);

	public static void TestMethod_1(String menuItemsText) {
		System.out.println("<<<<<<<<<<<< Test class 1 >>>>>>>>>");

			try {
				// Checking an environment
				TimeUnit.MILLISECONDS.sleep(15);
					if (screenPath.contains("qa2")) {
						selectedOperator = operators_[0];
					} else {
						selectedOperator = operators_[1];
					}// if
				// Filling the Operator input to open a tree
				WebElement actor_id_input = (WebElement) new FluentWait(RunBrowser.webDriver)
				        .withTimeout(60,TimeUnit.SECONDS)
				        .pollingEvery(1,TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.presenceOfElementLocated(By.id("actor_id_filter_input")));

				actions.moveToElement(actor_id_input)
					.click()
					.sendKeys(selectedOperator)
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
				
				// Select Unassigned Area folder; if it's missing than pick a machine out of tree
				try {
					SelectMachine(menuItemsText);						
				} catch (Exception e) {
//					SelectMachine();
					System.out.println("||||| FAILED to invoke ||||| ");
					e.printStackTrace();
				}
								
				System.out.println("||||| PASSED ||||| " + menuItemsText.toString());
				
			} catch (NoSuchElementException | ElementNotFoundException e1) {
				System.out.println("||||| FAILED ||||| " + menuItemsText.toString());
				e1.printStackTrace();
			} catch (TimeoutException e2) {
				System.out.println("(((( Timeout expired ))))");
				e2.printStackTrace();
						}//try
			 catch (InterruptedException e1) {
				 System.out.println("|||||  TIMED OUT  ||||| " + screenPath);
				e1.printStackTrace();
			}
			
		try {					
			TimeUnit.MILLISECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//TestMethod_1
	

	private static void SelectMachine(String menuItemName) {
		
		// Select a machine from the tree list
		new FluentWait(RunBrowser.webDriver)
		        .withTimeout(60,TimeUnit.SECONDS)
		        .pollingEvery(1,TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.presenceOfElementLocated(By.className("tree_link")));
		
		List<WebElement> tree_link = RunBrowser.webDriver.findElements(By.className("tree_link"));
//		List<WebElement> tree_link = RunBrowser.webDriver.findElements(By.className("//*[contains(., 'tree_link')]"));
//		List<WebElement> tree_link = RunBrowser.webDriver.findElements(By.className("tree_link"));
		
		for (WebElement tree_element : tree_link ) {
			System.out.println("<<< tree_link contents :: " + tree_element.getText());
		} //for
		
		int loopStep = 1;
			// In QA2 Unassigned folder is already open, this if is to skip the folder's opening
			if (screenPath.contains("qa2")) {
				loopStep = 2;
			} //if	
			
		try {
			for (int i = loopStep; i < 3; i++) {
				System.out.println("||||| tree_link ||||| " + tree_link.get(i).getText().substring(0,15));
	
				if (RunBrowser.webDriver.findElement(By.xpath("//*[contains(., '" + tree_link.get(i).getText().substring(0,15) + "')]")) != null) {
					//if (RunBrowser.webDriver.findElement(By.xpath("//*[text()='"+ tree_link.get(i).getText() +"']")) != null) {
					actions.moveToElement(tree_link.get(i))
					.click()
					.perform();
					TimeUnit.MILLISECONDS.sleep(100);
				} //if
			}//for	

//			TimeUnit.MILLISECONDS.sleep(100);
			MachinesTestCase.TestSets();
			
			synchronized (objectForSynch) {
				while (awaitTestFinish) {
		            try {
		            	objectForSynch.wait();
		            } catch (InterruptedException e) {
		                Thread.currentThread().interrupt(); 
		                System.out.println("Thread Interrupted");
		            }//try
		        }//while
		        awaitTestFinish = true;			        
			};//synchronized
			
			StartScreenShot(menuItemName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//try

	}//select machine
	
	private static void StartScreenShot(String screenTitle) {

		try {
			ScreenShotsMaker.ScreenShots(screenTitle);
			TimeUnit.MILLISECONDS.sleep(25);
			NotifyAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//StartScreenShot
	
	
	private static void NotifyAll() {
		synchronized (objectForSynch) {
			OpenSelectedScreen.awaitTestFinish = false;			        
			objectForSynch.notifyAll();
		};//synchronized
	}// NotifyAll
}
