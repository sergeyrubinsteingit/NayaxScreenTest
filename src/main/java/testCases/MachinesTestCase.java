package testCases;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import ProgramFiles.MenuItemsList;
import ProgramFiles.OpenSelectedScreen;
import ProgramFiles.RunBrowser;
 

public class MachinesTestCase {
	
	static WebDriver webDriver = RunBrowser.webDriver;
	static Actions actions_ = new Actions(webDriver);
	static boolean awaitTestFinish = OpenSelectedScreen.awaitTestFinish = true;
	static Object objectForSynch = OpenSelectedScreen.objectForSynch = new Object();
	static WebElement stockTab = null;
	static WebElement conf_ok_btn = null;
	static JavascriptExecutor javascriptExecutor = (JavascriptExecutor)webDriver;
	
	@Test
	public static void TestSets() {
		System.out.println(" =========== TEST SETS for MachinesTestCase BEGAN ========== ");
		try {
			
			List<WebElement> k_link = webDriver.findElements(By.className("k-link"));
			int startIndex = 0;
			// Removes element with no text
			RemoveNoTextElements (k_link);
			//Waiting for a function RemoveNoTextElements to end
			StartWait();
			for (int ii = 0; ii < k_link.size(); ii++) {
				WebElement k_link_text = k_link.get(ii);

				if (k_link_text.getText().contains("Dashboard")) {
					// Index of the first tab in Machine's options
					startIndex = k_link.indexOf(k_link_text );
				} //if
	
			} //for
	
			System.out.println(" startIndex :::>> " + startIndex);
					
			//Click Products
			for ( int i = startIndex; i < k_link.size(); i++ ) {
				System.out.println(" k_link.get(i) :::%%%%> " + k_link.get(i).getText());			
				if (k_link.get(i).getText().contains("Products Map")) {
					actions_.moveToElement(k_link.get(i)).click().perform();
				} //if
			};//for
					
			//Click Stock tab
			stockTab = (WebElement) new FluentWait(webDriver)
		        .withTimeout(30,TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Stock']")));
			
			actions_.moveToElement(stockTab).build().perform();
			
				TimeUnit.MILLISECONDS.sleep(15);
			
			//Drop menu entries
			List<WebElement> menu_labels = webDriver.findElements(By.className("k-item")
					.className("k-link")
					.tagName("label"));
			
			// Removes element with no text
			RemoveNoTextElements (menu_labels);
			//Waiting for a function RemoveNoTextElements to end
			StartWait();
			
			for (WebElement lbl : menu_labels) {
				System.out.println(" menu_labels :::>>> " + lbl.getText());
			};//for		
			
			try {
				//Checking for the visibility of the Red div = Empty Machine status
				new FluentWait(webDriver)
			        .withTimeout(30,TimeUnit.SECONDS)
			        .pollingEvery(3, TimeUnit.SECONDS)
			        .ignoring(Exception.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By
						.className("on_hand_container red_bg")));  // That mean the status = "empty"
				
				// In case the status = "empty"
				actions_.moveToElement(menu_labels.get(6)).click().perform();//filling up machine
				
				ClickOKbutton ("After " + menu_labels.get(6).getText().toUpperCase() + ", var 1");// Confirms the choice by clicking OK button
				StartWait();
				
				System.out.println(" <<<-&&& Machine was filled up &&&->>> ");
				
			} catch (Exception e) { // In case the status = "full"
				try {
//					actions_.moveToElement(menu_labels.get(7)).click().perform();//emptying machine
//							System.out.println(" <<<-#### Machine was emptied up ####->>> ");
//							
//					ClickOKbutton ("After " + menu_labels.get(7).getText().toUpperCase() + ", var 2");// Confirms the choice by clicking OK button
//					StartWait();
//					TimeUnit.SECONDS.sleep(11);
					
//					actions_.moveToElement(stockTab).build().perform();//Opening Stock menu
//					TimeUnit.SECONDS.sleep(1);
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//					actions_.moveToElement(menu_labels.get(6)).click().perform();//filling up machine
					
					for (int i = 6; i < 8; i++) {
						// Using [JavascriptExecutor] as no other way worked due to permanent overlay of some other element (gray screen) over the desired element.
						WebElement fullEmptyEntry = (WebElement) new FluentWait(webDriver)
						        .withTimeout(18,TimeUnit.SECONDS)
						        .pollingEvery(3, TimeUnit.SECONDS)
						        .ignoring(Exception.class)
								.until(ExpectedConditions.elementToBeClickable(By
								.xpath(" //*[text()='" + menu_labels.get(i).getText() + "'] ")));
						
						javascriptExecutor.executeScript("arguments[0].click();", fullEmptyEntry);
						
						System.out.println(" <<<-#### "+ menu_labels.get(i).getText().toUpperCase() +" click was performed ####->>> ");
						
						ClickOKbutton ("After " + menu_labels.get(i).getText().toUpperCase() + ", var 2");// Confirms the choice by clicking OK button
						StartWait();
					}; //for
					
					actions_.moveToElement(stockTab).build().perform();//Opening Stock menu
					TimeUnit.SECONDS.sleep(1);
					/////////////////////////////////////////////////////////////////////
					
					ClickOKbutton ("After " + menu_labels.get(6).getText().toUpperCase() + ", var 2");// Confirms the choice by clicking OK button
					StartWait();

							System.out.println(" <<<-&&& Machine was filled up &&&->>> ");
				} catch (Exception e1) {
							System.out.println(" <<<-&&& Menu's flow failed. &&&->>> ");
					e1.printStackTrace();
					webDriver.quit();
					System.exit(-1);
				}
			} //try
		
			System.out.println(" =========== TEST SETS for MachinesTestCase ENDED ========== ");
			actions_.moveToElement(stockTab).build().perform();
			NotifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//try
	}//TestSets
	
	public static void StartWait() {
		System.out.println(" =========== StartWait() from MachinesTestCase ========== ");
		synchronized (objectForSynch) {
			while (awaitTestFinish) {
	            try {
	            	objectForSynch.wait();
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt(); 
	                System.out.println("Thread Interrupted");
	                webDriver.quit();
					System.exit(-1);
	            }//try
	        }//while
	        awaitTestFinish = true;
		}//synchronized
	}//StartWait
	
	public static void NotifyAll() {
		System.out.println(" =========== NotifyAll from MachinesTestCase ========== ");
		synchronized (objectForSynch) {
			try {
				awaitTestFinish = false;			        
				objectForSynch.notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}
		};//synchronized
	}// NotifyAll
	
	
	static Object RemoveNoTextElements (List<WebElement> k_link) {
		// Removes element with no text
		for (int ii = 0; ii < k_link.size(); ii++) {
			WebElement k_link_text = k_link.get(ii);
			if (k_link_text.getText().length() == 0) {
				k_link.remove(k_link_text);
				ii--;
			}//if
		};//for
		NotifyAll();
		return k_link;
	}//RemoveNoTextElements
	
	//Confirms the choice by clicking OK button
	static void ClickOKbutton (String infoString) throws InterruptedException {
		
		// Using [JavascriptExecutor] as no other way worked due to permanent overlay of some other element (gray screen) over the desired element.
		WebElement conf_ok_btn = (WebElement) new FluentWait(webDriver)
		        .withTimeout(18,TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.elementToBeClickable(By
				.className("k-widget")
				.className("k-window-content")
				.id("conf_ok_btn")));
		
		if (conf_ok_btn.getSize().width != 0) {
			javascriptExecutor.executeScript("arguments[0].click();", conf_ok_btn);
			
			System.out.println(" <<<-@@@ Clicking OK button for @@@->>>\n " + infoString);
			TimeUnit.SECONDS.sleep(1);
		} else {
			actions_.moveToElement(stockTab).build().perform();
		} //if
		
		NotifyAll();
			
	}//ClickOKbutton
	
}//////////////////////////////////////////////////***END***/////////////////////////////////////////////////////
