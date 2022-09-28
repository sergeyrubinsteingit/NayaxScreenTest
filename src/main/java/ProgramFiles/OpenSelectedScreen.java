package ProgramFiles;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ProgramFiles.OperationMenu;
import ProgramFiles.MenuItemsList;
import ProgramFiles.TestClasses.*;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ParameterDefinition;


public class OpenSelectedScreen {
	
	static WebDriver webDriver = RunBrowser.webDriver;
	public static List<WebElement> menuItems_ = new ArrayList<>();
	
	public static List<WebElement> kGroup = new ArrayList<>();
	
	public static WebElement itemToClick;
	public static boolean awaitTestFinish = true;
	
	public static Object objectForSynch = new Object();

	@SuppressWarnings("deprecation")
	public static void main(List<String> menuItemsText) {
		
		for (String itemText : menuItemsText) {
			System.out.println(">>> --- "+ itemText);
		}//for
		
		 try {
			   Actions actions = new Actions(RunBrowser.webDriver); 

				for (int c0=0;c0<menuItemsText.size();c0++) {
				
				System.out.println("\n============> OPEN SELECTED SCREEN <================\n");
				
				//Opens drop menu
		        new FluentWait<WebDriver>(webDriver)
			        .withTimeout(120,TimeUnit.SECONDS)
			        .pollingEvery(3, TimeUnit.SECONDS)
			        .ignoring(Exception.class)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.className(MenuItemsList.screenFeatures[1])));
//				TimeUnit.MILLISECONDS.sleep(45);
				OperationMenu.openOperationsMenu();
//				TimeUnit.MILLISECONDS.sleep(40);

				WebElement animationContainer = null;
		        try {
					animationContainer = (WebElement) new FluentWait<WebDriver>(webDriver)
				        .withTimeout(120,TimeUnit.SECONDS)
				        .pollingEvery(3, TimeUnit.SECONDS)
				        .ignoring(Exception.class)
						.until(ExpectedConditions.visibilityOfElementLocated(By.className(MenuItemsList.screenFeatures[1])));
				} catch (Exception e1) {
					System.out.println("The animationContainer element,\n "+ animationContainer +"\nwas not found. Timeout expired, the test is shut down");
					webDriver.quit();
					System.exit(-1);
					e1.printStackTrace();
				}
		        
		        System.out.println("After OperationMenu.openOperationsMenu();");

				WebElement itemToClick = (WebElement) new FluentWait(webDriver)
			        .withTimeout(90,TimeUnit.SECONDS)
			        .pollingEvery(5,TimeUnit.SECONDS)
			        .ignoring(Exception.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText(menuItemsText.get(c0))));
								        
//					//Opens selection in new tab
					if (itemToClick.getSize() != null) {
						actions.moveToElement(itemToClick)
						.keyDown(Keys.CONTROL)
						.keyDown(Keys.LEFT_SHIFT)
						.click(itemToClick)
						.keyUp(Keys.CONTROL)
						.keyUp(Keys.LEFT_SHIFT)
						.perform();
						TimeUnit.MILLISECONDS.sleep(15);
					}else{
				        System.out.println("------&&> Right-click drop in menu is unaccessable. The system is shut down.");
						webDriver.quit();
						System.exit(-1);
					}//if

					//Switches to new tab
					   ArrayList<String> tabsList = new ArrayList<String> (webDriver.getWindowHandles());
					   webDriver.switchTo().window(tabsList.get(c0+1));

		        try {
			        //Invokes test class
		        	String classPath = "ProgramFiles.TestClasses.";
			        String testClassID = classPath + "TestClass_" + String.valueOf(c0+1);
			        String testMethodID = "TestMethod_" + String.valueOf(c0+1);
			        System.out.println("testClassID > " + testClassID);
			        Class<?> selectedClass;
					selectedClass = Class.forName(testClassID);
			        System.out.println("------>  "+selectedClass.getPackageName());

					Method method = selectedClass.getMethod(testMethodID, String.class);
					
			        System.out.println("------%>  "+ method.getName());

					method.invoke(null, menuItemsText.get(c0).toString());
					
					TimeUnit.SECONDS.sleep(3);
					
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
					
				} catch ( ClassNotFoundException |  IllegalArgumentException | NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
					webDriver.quit();
					System.exit(-1);
				}
//		        TestClass_1.main(null);
		    }//for

		 } catch (TimeoutException | InterruptedException  | StaleElementReferenceException e) {
		        System.out.println("Open selected screen: FAILED");
				e.printStackTrace();
				webDriver.quit();
				System.exit(-1);
			}//try
	}

}