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
import ProgramFiles.ScreenShotsMaker;
import ProgramFiles.WaitAndNotify;
import ProgramFiles.AppendScreenshotsToHTML;
 

public class MachinesTestCase {
	
	static WebDriver webDriver = RunBrowser.webDriver;
	static Actions actions_ = new Actions(webDriver);
	static boolean awaitTestFinish = OpenSelectedScreen.awaitTestFinish = true;
	static Object objectForSynch = OpenSelectedScreen.objectForSynch = new Object();
	static WebElement stockTab = null;
	static WebElement conf_ok_btn = null;
	static WebElement fullEmptyEntry = null;
	static JavascriptExecutor javascriptExecutor = (JavascriptExecutor)webDriver;
	
	@Test
	public static void TestSets(String className) {
		System.out.println(" =========== TEST SETS for MachinesTestCase BEGAN ========== ");
		try {
			
			List<WebElement> k_link = webDriver.findElements(By.className("k-link"));
			int startIndex = 0;
			// Removes element with no text
			RemoveNoTextElements (k_link);
			//Waiting for a function RemoveNoTextElements to end
			WaitAndNotify.StartWait();
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
					
			//Checking Stock tab presence
			stockTab = (WebElement) new FluentWait(webDriver)
		        .withTimeout(30,TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Stock']")));
			// Move to the Stock tab
			actions_.moveToElement(stockTab).build().perform();
			
				TimeUnit.MILLISECONDS.sleep(1);
			
			//Drop menu entries
			List<WebElement> menu_labels = webDriver.findElements(By.className("k-item")
					.className("k-link")
					.tagName("label"));
			
			// Move to the Stock tab
			actions_.moveToElement(stockTab).build().perform();
			
			// Removes element with no text
			RemoveNoTextElements (menu_labels);
			//Waiting for a function RemoveNoTextElements to end
			WaitAndNotify.StartWait();
			
			for (WebElement lbl : menu_labels) {
				System.out.println(" menu_labels :::>>> " + lbl.getText());
			};//for		
			
			try {;
				String redGreenClassNames [] = {"on_hand_container red_bg","on_hand_container green_bg","on_hand_container red_bg"};
				for (int i = 0; i < 2; i++) {
					//Checking for the visibility of the Red div = Empty Machine status
					fullEmptyEntry = (WebElement) new FluentWait(webDriver)
				        .withTimeout(15,TimeUnit.SECONDS)
				        .pollingEvery(3, TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.visibilityOfElementLocated(By
						.cssSelector("div[class=' "+ redGreenClassNames +"']")));  // That mean the status = "empty"
//					.className("on_hand_container red_bg")));  // That mean the status = "empty"
					
					// In case the status = "empty"
					javascriptExecutor.executeScript("arguments[0].click();", fullEmptyEntry);
					TimeUnit.MILLISECONDS.sleep(25);
					
					ClickOKbutton ("After " + menu_labels.get(6).getText().toUpperCase() + ", var 1");// Confirms the choice by clicking OK button
					WaitAndNotify.StartWait();
					
					//Screenshot
//					String className = "TestClass_1";
					ProgramFiles.ScreenShotsMaker.ScreenShots(menu_labels.get(6).getText().toUpperCase(), className);
					WaitAndNotify.StartWait();
					
					System.out.println(" <<<-&&& "+ menu_labels.get(6).getText().toUpperCase() +" had been clicked &&&->>> ");

				}//for

											
			} catch (Exception e) { // In case the status = "full"
				try {
//					for (int i = 6; i <= 7; i++) {
					for (int i = 7; i >= 6; i--) {
						System.out.println(" <<<-####->>> "+ i +" <<-####->>> ");
						// Using [JavascriptExecutor] as no other way worked due to permanent overlay of some other element (gray screen) over the desired element.
						fullEmptyEntry = (WebElement) new FluentWait(webDriver)
						        .withTimeout(15,TimeUnit.SECONDS)
						        .pollingEvery(3, TimeUnit.SECONDS)
						        .ignoring(Exception.class)
								.until(ExpectedConditions.elementToBeClickable(By
								.xpath(" //*[text()='" + menu_labels.get(i).getText().toString() + "'] ")));
						
						javascriptExecutor.executeScript("arguments[0].click();", fullEmptyEntry);
						TimeUnit.MILLISECONDS.sleep(25);
						
						System.out.println(" <<<-#### "+ menu_labels.get(i).getText().toUpperCase() +" click was performed ####->>> ");
						
						String currentLabel = menu_labels.get(i).getText().toUpperCase().toString();
						
						ClickOKbutton ("After " + currentLabel + ", var 2");// Confirms the choice by clicking OK button
//						WaitAndNotify.StartWait();
						
						TimeUnit.SECONDS.sleep(2);
						
						//Screenshot
//						String className = "TestClass_1";
						ProgramFiles.ScreenShotsMaker.ScreenShots(currentLabel, className);
						WaitAndNotify.StartWait();
						
						//Appends to HTML report a div containing screenshots 
						AppendScreenshotsToHTML.AppendNewDiv(className);
						WaitAndNotify.StartWait();

						
						//Probably hovers a mouse over Stock tab
						String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
								+"evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} "
								+"else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
						
						javascriptExecutor.executeScript(mouseOverScript, stockTab);
						
//						actions_.moveToElement(stockTab).build().perform();//Opening Stock menu
//						TimeUnit.MILLISECONDS.sleep(25);
						/////////////////////////////////////////////////////////////////////
						
//						ClickOKbutton ("After " + menu_labels.get(6).getText().toUpperCase() + ", var 2");// Confirms the choice by clicking OK button
//						StartWait();

						System.out.println(" <<<-&&& Machine was filled up &&&->>> ");
					}; //for

				} catch (Exception e1) {
							System.out.println(" <<<-&&& Menu's flow failed. &&&->>> ");
					e1.printStackTrace();
					webDriver.quit();
					System.exit(-1);
				}
			} //try
		
			System.out.println(" =========== TEST SETS for MachinesTestCase ENDED ========== ");
//			actions_.moveToElement(k_link.get(0)).build().perform();
			javascriptExecutor.executeScript("arguments[0].click();", k_link.get(0));
			WaitAndNotify.NotifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//try
	}//TestSets
	
	static Object RemoveNoTextElements (List<WebElement> k_link) {
		// Removes element with no text
		for (int ii = 0; ii < k_link.size(); ii++) {
			WebElement k_link_text = k_link.get(ii);
			if (k_link_text.getText().length() == 0) {
				k_link.remove(k_link_text);
				ii--;
			}//if
		};//for
		WaitAndNotify.NotifyAll();
		return k_link;
	}//RemoveNoTextElements
	
	//Confirms the choice by clicking OK button
	static void ClickOKbutton (String infoString) throws InterruptedException {
		
		// Using [JavascriptExecutor] as no other way worked due to permanent overlay of some other element (gray screen) over the desired element.
		WebElement conf_ok_btn = (WebElement) new FluentWait(webDriver)
		        .withTimeout(15,TimeUnit.SECONDS)
		        .pollingEvery(3, TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.elementToBeClickable(By
				.className("k-widget")
				.className("k-window-content")
				.id("conf_ok_btn")));
		
		if (conf_ok_btn.isDisplayed()) {
			TimeUnit.MILLISECONDS.sleep(1);
			javascriptExecutor.executeScript("arguments[0].click();", conf_ok_btn);
			System.out.println(" <<<-@@@ Clicking OK button for @@@->>>\n " + infoString);
		} else {
			System.out.println(" No dialog window to interact with. ");
			actions_.moveToElement(stockTab).build().perform();
		} //if
		
		WaitAndNotify.NotifyAll();
			
	}//ClickOKbutton
	
}//////////////////////////////////////////////////***END***/////////////////////////////////////////////////////
