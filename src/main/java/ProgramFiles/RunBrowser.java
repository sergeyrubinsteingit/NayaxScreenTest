package ProgramFiles;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ProgramFiles.OperationMenu;
import ProgramFiles.NetTrafficControl;

public class RunBrowser {
	
	public static String driverPath;
	public static WebDriver webDriver;
	public static String driverType;
//	public static String loginPath = "https://qa2.nayax.com/DCS/LoginPage.aspx";
	static String usProd = "us";
	static String qa2dev = "qa2";
	static String passwordProd = "rubisergDEbLXuX5mp4inPROD1";
	static String passwordQa2 = "rubiserg69QA2Q2eYpfKs86";

//	public static String loginPath = "https://us.nayax.com/dcs/public/facade.aspx?model=reports/dashboard";
	public static String loginPath = "https://qa2.nayax.com/dcs/public/facade.aspx?model=reports/dashboard";
	
	public static <E> void run_Browser() throws InterruptedException {
		
		driverPath = "\\src\\main\\java\\web_drivers_";
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("\n============> RUN BROWSER <================\n");
		System.out.println("=+=+=+=+=+=+=+=+=+=+=> " + Combo_Box.selectedBrowser + " <=+=+=+=+=+=+=+=+=+=+=\n");
		TimeUnit.SECONDS.sleep(1);
		if (Combo_Box.selectedBrowser.matches("Firefox")) { // FIREFOX driver
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + driverPath + "\\geckodriver.exe");
			DesiredCapabilities capabilities;
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			capabilities.setCapability("marionette", true);
			webDriver = new FirefoxDriver(capabilities);
		} else if (Combo_Box.selectedBrowser.matches("Chrome")) { // CHROME driver
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +  driverPath + "\\chromedriver.exe");
			// Setting your Chrome options (Desired capabilities)
			ChromeOptions options = new ChromeOptions();
//			options.add_argument('--start-maximized');
//			options.add_argument('--start-fullscreen');
			webDriver = new ChromeDriver();
		} else if(Combo_Box.selectedBrowser.matches("MS Edge")) { // EDGE driver
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +  driverPath + "\\msedgedriver.exe");
			webDriver = new EdgeDriver();
		} else if(Combo_Box.selectedBrowser.matches("Internet Explorer")) { // EXPLORER driver
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +  driverPath + "\\IEDriverServer.exe");
			InternetExplorerOptions ie_options = new InternetExplorerOptions().enableNativeEvents().ignoreZoomSettings();
			webDriver = new InternetExplorerDriver(ie_options);
		} else if(Combo_Box.selectedBrowser.matches("Opera")) { // OPERA driver
			System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") +  driverPath + "\\operadriver.exe");
			webDriver = new OperaDriver();
		} else if(Combo_Box.selectedBrowser.matches("Safari")) { // SAFARI driver. 
//////////	Needs to be enabled in Safari browser. Do the following: ////////////
//			Go to Safari -> Preferences-> Advanced
//			Tick mark the Checkbox with the label – Show Develop menu in menu bar
//////////	Once done, go to the Develop menu and click on the Allow Remote Automation option to enable it.
			webDriver = new SafariDriver();
		} else {
			System.out.println("\n---------------- Failure: Web driver was not found ------------------");
			System.exit(-1);
		}

		
		try { // Deletes a folder with former screenshots and recreates an empty folder
			
			Actions newAction = new Actions(webDriver);
			Robot robot = new Robot();
			
	        webDriver.manage().window().maximize();
	        webDriver.manage().deleteAllCookies();
	        			
			try {
				webDriver.navigate().to(loginPath);
//				webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS); // A pause to fill the list
			} catch (WebDriverException e1) {
				TimeUnit.SECONDS.sleep(2);
				try {
					webDriver.navigate().to(loginPath);
					webDriver.manage().timeouts().implicitlyWait( (int) Math.round(NetTrafficControl.rateToInterval * 1 ), TimeUnit.SECONDS); // A pause to fill the list
				} catch (WebDriverException e2) {
					System.out.println("Failed to reach the login page. Shut down.");
					webDriver.close();
					System.exit(-1);
					e2.printStackTrace();
				}//try
			}//try
						
//////////////////	 Login = User + Password + Click   ////////////////////////////
			
			 Map<String,String> loginCredentials = new LinkedHashMap<>();
				loginCredentials.put("txtUser","Sergeyr");
//				loginCredentials.put("txtPassword", "rubisergDEbLXuX5mp4inPROD1");
				loginCredentials.put("txtPassword", "rubisergLic13nnH5kvinQA2");
				
				Set<String> keySet = loginCredentials.keySet();
				String[] keyArray = keySet.toArray(new String[keySet.size()]);

			for (int ct0 = 0; ct0 < loginCredentials.size(); ct0++) {
				new WebDriverWait(webDriver, 600)
		        .until(ExpectedConditions.elementToBeClickable(By.id(keyArray[ct0].toString())));
				newAction.moveToElement(webDriver.findElement(By.id(keyArray[ct0].toString()))).click().perform();

			    for (char c : loginCredentials.get(keyArray[ct0]).toCharArray()) {
			        if (Character.isUpperCase(c) && !Character.isDigit(c)) {
			            robot.keyPress(KeyEvent.VK_SHIFT);
			        }else {
			            robot.keyRelease(KeyEvent.VK_SHIFT);
			        }
			        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			        if (KeyEvent.CHAR_UNDEFINED == keyCode) {
			            throw new RuntimeException(
			                "Key code not found for character '" + c + "'");
			        }//if
			        robot.keyPress(keyCode);
			        robot.delay(20);
			        robot.keyRelease(keyCode);
			        robot.delay(20);
			    }//for
			};//for
			
			TimeUnit.SECONDS.sleep(1);
			
			String [] loginElements = {
					"signin",
//					"setSmsForTotp",
					"second_factor_option_sms_input",
					"signin",
					"trustDeviceNever",
					};
			
			// Sign in button
			new WebDriverWait(webDriver, 60)
	        .until(ExpectedConditions.elementToBeClickable(By.id(loginElements[0])));
			newAction.moveToElement(webDriver.findElement(By.id(loginElements[0]))).click().perform();
			
			TimeUnit.SECONDS.sleep(1);
			
			// Send SMS button
//			new WebDriverWait(webDriver, 60)
//	        .until(ExpectedConditions.elementToBeClickable(By.id(loginElements[loginElements.length - 1])));
//			newAction.moveToElement(webDriver.findElement(By.id(loginElements[loginElements.length - 1]))).click().perform();
			
			for (int ct1=0;ct1<loginElements.length;ct1++) {
				
				System.out.println(">> Before " + loginElements[ct1]);
				
//				WebElement loginEl = new WebDriverWait(webDriver, 100)
//				        .until(ExpectedConditions.elementToBeClickable(By.id(loginElements[ct1])));
				
				WebElement loginEl = (WebElement) new FluentWait(webDriver)
				.withTimeout(200,TimeUnit.SECONDS)
		        .pollingEvery(5,TimeUnit.SECONDS)
		        .ignoring(Exception.class)
				.until(ExpectedConditions.elementToBeClickable(By.id(loginElements[ct1])));
//				
					if (loginElements[ct1] == "second_factor_option_sms_input") {
//						TimeUnit.MILLISECONDS.sleep(10);
						String verCodeString = JOptionPane.showInputDialog("Verification code:","Enter here");
						loginEl.sendKeys(verCodeString);
						loginEl.click();
					}else {
						loginEl.click();
					}//if
				System.out.println(">> After " + loginElements[ct1]);
				TimeUnit.SECONDS.sleep(1);
			}//for
			
			TimeUnit.SECONDS.sleep(2);
			System.out.println(webDriver.getCurrentUrl().toString());
			
			int ct0=0;
			String nmbSuffix[] = {"-st","-nd","-rd"};
			for (int ct2 = 0;ct2 < 3;ct2++) {
				try {
					  webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
					  System.out.println((ct0+1) + nmbSuffix[ct2] + " attempt to load the page.");
						TimeUnit.SECONDS.sleep(1);

				} catch (TimeoutException e) {
					System.out.println("Failure on window\'s loading.");
					e.printStackTrace();
					if (ct0 == 3) {
						System.out.println((ct0+1)+" attempts failed, shut down.");
						webDriver.close();
						System.exit(-1);
				}//try
				};//if
				ct0++;
			}//for	

//			System.out.println("After the Robot Minus stuff.");
			
//			new WebDriverWait(webDriver, 60)
//			        .until(ExpectedConditions.visibilityOfElementLocated(By.id("user_menu")));
////			TimeUnit.SECONDS.sleep(2);
//			newAction.moveToElement(webDriver.findElement(By.id("user_menu"))).click().perform();
//			
//			new WebDriverWait(webDriver, 60)
//	        .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Account Settings")));
//			newAction.moveToElement(webDriver.findElement(By.partialLinkText("Account Settings"))).click().perform();
//
		} catch (TimeoutException | AWTException | NoSuchElementException | NullPointerException e) {
			System.out.println("<<<<< Because of "+ e +" cannot open the page, attempt in " 
					+ Combo_Box.selectedBrowser.toString()
					+ ". The system was shut down. >>>>>\n");
			e.printStackTrace();
			webDriver.quit();
			System.exit(-1);
		}
//		NavigateToDestination.main(null);
//		OperationMenu.main(null);
		MenuItemsList.main(null);
//		System.gc();
	}	// EOF

	public static void main(String[] args) throws InterruptedException {
		run_Browser();
	}

}//class