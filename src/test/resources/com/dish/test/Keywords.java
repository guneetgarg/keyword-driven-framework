package com.dish.test;

import static com.dish.test.DriverScript.APP_LOGS;
import static com.dish.test.DriverScript.CONFIG;
import static com.dish.test.DriverScript.OR;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;




import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Verify;

public class Keywords {
	public static SoftAssert s_assert = new SoftAssert();
	public WebDriver driver;

	public String openBrowser(String object, String data) {
		System.out.println("Drivers path" + Constants.usrDir
				+ "\\Driver\\chromedriver.exe");
		String path = "";
		APP_LOGS.debug("Opening browser");
		if (data.equals("Mozilla")) {
			driver = new FirefoxDriver();
		} else if (data.equals("IE")) {
			path = Constants.usrDir + "\\Driver\\IEDriverServer.exe";
			System.out.println("Path of Exe fiel" + path);
			System.setProperty("webdriver.ie.driver", path);
			driver = new InternetExplorerDriver();
		} else if (data.equals("Chrome")) {
			ChromeOptions options = new ChromeOptions(); 
			options.addArguments("user-data-dir=C:/Users/user_name/AppData/Local/Google/Chrome/User Data"); 
			//options.addArguments("--user-agent=ManualLogin"); 
			options.addArguments("chrome.switches","--disable-extensions");           
			options.addArguments("--test-type"); 
			DesiredCapabilities capabilities = new DesiredCapabilities(); 
			capabilities.setCapability(ChromeOptions.CAPABILITY, options); 
			path = Constants.usrDir + "\\Driver\\chromedriver.exe";
			System.out.println("Path of Exe fiel" + path);
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver(capabilities);
			driver.manage().window().maximize();
		}else if (data.equals("ChromeAgent")) {
			ChromeOptions options = new ChromeOptions(); 
			options.addArguments("user-data-dir=C:/Users/user_name/AppData/Local/Google/Chrome/User Data"); 
			options.addArguments("--user-agent=ManualLogin"); 
			options.addArguments("chrome.switches","--disable-extensions");           
			options.addArguments("--test-type"); 
			DesiredCapabilities capabilities = new DesiredCapabilities(); 
			capabilities.setCapability(ChromeOptions.CAPABILITY, options); 
			path = Constants.usrDir + "\\Driver\\chromedriver.exe";
			System.out.println("Path of Exe fiel" + path);
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver(capabilities);
			driver.manage().window().maximize();
		}
		long implicitWaitTime = Long.parseLong(CONFIG
				.getProperty("implicitwait"));
		driver.manage().timeouts()
				.implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		return Constants.KEYWORD_PASS;

	}

	public String navigate(String object, String data) {
		APP_LOGS.debug("Navigating to URL");
		try {
			driver.navigate().to(data);
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " -- Not able to navigate";
		}
		return Constants.KEYWORD_PASS;
	}

	public String clickLink(String object, String data) {
		APP_LOGS.debug("Clicking on link ");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " -- Not able to click on link"
					+ e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}

	public String clickLink_linkText(String object, String data) {
		APP_LOGS.debug("Clicking on link ");
		driver.findElement(By.linkText(OR.getProperty(object))).click();

		return Constants.KEYWORD_PASS;
	}

	
	public String verifyLinkText(String object, String data) {
		APP_LOGS.debug("Verifying link Text");
		try {

			String actual = driver
					.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected = data;
			
			System.out.println("ACTUAL DATA " + actual);
			System.out.println("EXPECTED DATA " + expected);
			
			if (actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else{
				
				s_assert.assertEquals(actual, expected);
			
				return Constants.KEYWORD_FAIL + " -- Link text not verified";}
			
		} catch (Throwable t) {
			t.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Link text not verified"
					+ t.getMessage();

		}

	}

	public String clickButton(String object, String data) {
		APP_LOGS.debug("Clicking on Button");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Not able to click on Button"
					+ e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}

	public String verifyButtonText(String object, String data) {
		APP_LOGS.debug("Verifying the button text");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected = data;

			if (actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- Button text not verified "
						+ actual + " -- " + expected;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " Object not found "
					+ e.getMessage();
		}

	}

	public String selectList(String object, String data) {
		APP_LOGS.debug("Selecting from list");
		try {
			if (!data.equals(Constants.RANDOM_VALUE)) {
				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
						data);
			} else {
				// logic to find a random value in list
				WebElement droplist = driver.findElement(By.xpath(OR
						.getProperty(object)));
				List<WebElement> droplist_cotents = droplist.findElements(By
						.tagName("option"));
				Random num = new Random();
				int index = num.nextInt(droplist_cotents.size());
				String selectedVal = droplist_cotents.get(index).getText();

				driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
						selectedVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String verifyAllListElements(String object, String data) {
		APP_LOGS.debug("Verifying the selection of the list");
		try {
			WebElement droplist = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> droplist_cotents = droplist.findElements(By
					.tagName("option"));

			// extract the expected values from OR. properties
			String temp = data;
			String allElements[] = temp.split(",");
			// check if size of array == size if list
			if (allElements.length != droplist_cotents.size())
				return Constants.KEYWORD_FAIL + "- size of lists do not match";

			for (int i = 0; i < droplist_cotents.size(); i++) {
				if (!allElements[i].equals(droplist_cotents.get(i).getText())) {
					return Constants.KEYWORD_FAIL + "- Element not found - "
							+ allElements[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String verifyListSelection(String object, String data) {
		APP_LOGS.debug("Verifying all the list elements");
		try {
			String expectedVal = data;
			// System.out.println(driver.findElement(By.xpath(OR.getProperty(object))).getText());
			WebElement droplist = driver.findElement(By.xpath(OR
					.getProperty(object)));
			List<WebElement> droplist_cotents = droplist.findElements(By
					.tagName("option"));
			String actualVal = null;
			for (int i = 0; i < droplist_cotents.size(); i++) {
				String selected_status = droplist_cotents.get(i).getAttribute(
						"selected");
				if (selected_status != null)
					actualVal = droplist_cotents.get(i).getText();
			}

			if (!actualVal.equals(expectedVal))
				return Constants.KEYWORD_FAIL + "Value not in list - "
						+ expectedVal;

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not find list. "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String selectRadio(String object, String data) {
		APP_LOGS.debug("Selecting a radio button");
		try {
			String temp[] = object.split(Constants.DATA_SPLIT);
			driver.findElement(
					By.xpath(OR.getProperty(temp[0]) + data
							+ OR.getProperty(temp[1]))).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + "- Not able to find radio button";

		}

		return Constants.KEYWORD_PASS;

	}

	public String verifyRadioSelected(String object, String data) {
		APP_LOGS.debug("Verify Radio Selected");
		try {
			String temp[] = object.split(Constants.DATA_SPLIT);
			String checked = driver.findElement(
					By.xpath(OR.getProperty(temp[0]) + data
							+ OR.getProperty(temp[1]))).getAttribute("checked");
			if (checked == null)
				return Constants.KEYWORD_FAIL + "- Radio not selected";

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + "- Not able to find radio button";

		}

		return Constants.KEYWORD_PASS;

	}

	public String checkCheckBox(String object, String data) {
		APP_LOGS.debug("Checking checkbox");
		try {
			// true or null
			String checked = driver.findElement(
					By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked == null)// checkbox is unchecked
				driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not find checkbo";
		}
		return Constants.KEYWORD_PASS;

	}

	public String unCheckCheckBox(String object, String data) {
		APP_LOGS.debug("Unchecking checkBox");
		try {
			String checked = driver.findElement(
					By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked != null)
				driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not find checkbox";
		}
		return Constants.KEYWORD_PASS;

	}

	public String verifyCheckBoxSelected(String object, String data) {
		APP_LOGS.debug("Verifying checkbox selected");
		try {
			String checked = driver.findElement(
					By.xpath(OR.getProperty(object))).getAttribute("checked");
			if (checked != null)
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " - Not selected";

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not find checkbox";

		}

	}

//	public String waitMe(String object, String data) {
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR
//				.getProperty(object))));
//		return Constants.KEYWORD_PASS;
//	}

	public String verifyText(String object, String data) {
		APP_LOGS.debug("Verifying the text");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected = data;

			if (actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- text not verified "
						+ actual + " -- " + expected;
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Object not found "
					+ e.getMessage();
		}

	}

	public String writeInInput(String object, String data) {
		APP_LOGS.debug("Writing in text box");

		try {
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		} catch (Exception e) {
			s_assert.assertFalse(true);
			return Constants.KEYWORD_FAIL + " Unable to write "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String verifyTextinInput(String object, String data) {
		APP_LOGS.debug("Verifying the text in input box");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object)))
					.getAttribute("value");
			String expected = data;

			if (actual.equals(expected)) {
				return Constants.KEYWORD_PASS;
			} else {
				return Constants.KEYWORD_FAIL + " Not matching ";
			}

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Unable to find input box "
					+ e.getMessage();

		}
	}

	public String clickImage() {
		APP_LOGS.debug("Clicking the image");

		return Constants.KEYWORD_PASS;
	}

	public String verifyFileName() {
		APP_LOGS.debug("Verifying inage filename");

		return Constants.KEYWORD_PASS;
	}

	public String verifyTitle(String object, String data) {
		APP_LOGS.debug("Verifying title");
		try {
			String actualTitle = driver.getTitle();
			String expectedTitle = data;
			if (actualTitle.equals(expectedTitle))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL + " -- Title not verified "
						+ expectedTitle + " -- " + actualTitle;
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Error in retrieving title";
		}
	}

	public String exist(String object, String data) {
		APP_LOGS.debug("Checking existance of element");
		try {
			Thread.sleep(1000);
			driver.findElement(By.xpath(OR.getProperty(object)));
			
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Error still exist";
			
		}

		return Constants.KEYWORD_PASS;
	}

	public String isErrPresent(String object, String data) {
		String err = driver.findElement(By.xpath(OR.getProperty(object)))
				.getText();
		if (err.contains(data)) {
			return Constants.KEYWORD_PASS;
		}
		s_assert.assertTrue(false);
		return Constants.KEYWORD_FAIL;
	}

	public String click(String object, String data) {
		APP_LOGS.debug("Clicking on any element");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {
			s_assert.assertTrue(false);
			return Constants.KEYWORD_FAIL + " Not able to click";
		}
		return Constants.KEYWORD_PASS;
	}

	public String executeWithJavaScript(String object, String data) {
		try {

			WebElement element = driver.findElement(By.xpath(OR
					.getProperty(object)));

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			return Constants.KEYWORD_PASS;
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL;
		}
	}

	public String synchronize(String object, String data) {
		APP_LOGS.debug("Waiting for page to load");
		((JavascriptExecutor) driver)
				.executeScript("function pageloadingtime()" + "{"
						+ "return 'Page has completely loaded'" + "}"
						+ "return (window.onload=pageloadingtime());");

		return Constants.KEYWORD_PASS;
	}

	public String waitForElementVisibility(String object, String data) {
		APP_LOGS.debug("Waiting for an element to be visible");
		int start = 0;
		int time = (int) Double.parseDouble(data);
		try {
			while (time == start) {
				if (driver.findElements(By.xpath(OR.getProperty(object)))
						.size() == 0) {
					Thread.sleep(1000L);
					start++;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable to close browser. Check if its open"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	public String closeBrowser(String object, String data) {
		APP_LOGS.debug("Closing the browser");
		try {
			driver.quit();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable to close browser. Check if its open"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	public String pause(String object, String data)
			throws NumberFormatException, InterruptedException {
		long time = (long) Double.parseDouble(object);
		Thread.sleep(time * 1000L);
		return Constants.KEYWORD_PASS;
	}

	/************************ APPLICATION SPECIFIC KEYWORDS ********************************/
	public String Ribonverification(String object, String data) {
		APP_LOGS.debug("Verifying link Text");
		try {
			
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected = data;
			
			System.out.println("ACTUAL DATA " + actual);
			System.out.println("EXPECTED DATA " + expected);
			
			if (actual.contains(expected))
				return Constants.KEYWORD_PASS;
			else{
				
				s_assert.assertEquals(actual, expected);
			
				return Constants.KEYWORD_FAIL + " -- Link text not verified";}
			
		} catch (Throwable t) {
			t.printStackTrace();
			return Constants.KEYWORD_FAIL + " -- Link text not verified"
					+ t.getMessage();

		}

	}

	
	public String offeringCount(String object, String data)
	{try{
		
	List<WebElement> elms = driver.findElements(By.xpath(OR.getProperty(object)));
	int count =elms.size();
	System.out.println("Expecte value : "+data+"Actual value : "+count);
	if(data.equalsIgnoreCase(Integer.toString(count))){
		return Constants.KEYWORD_PASS;
	}else
	{
		s_assert.assertTrue(false);
		return Constants.KEYWORD_FAIL;
	}
	}
	catch(Exception e)
	{s_assert.assertTrue(false);
	
		return Constants.KEYWORD_FAIL
				+ "Unable to close browser. Check if its open"
				+ e.getMessage();
	}
	}

	
	//Getting attribut value
	public String AttributeValue(String object, String data)
	{try{
		Thread.sleep(3000);
	String value = driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
	System.out.println("Expecte value : "+data+"Actual value : "+value);
	if(value.equalsIgnoreCase(data)){
		return Constants.KEYWORD_PASS;
	}else
	{
		s_assert.assertTrue(false);
		return Constants.KEYWORD_FAIL;
	}
	}
	catch(Exception e)
	{s_assert.assertTrue(false);
	
		return Constants.KEYWORD_FAIL
				+ "Unable to close browser. Check if its open"
				+ e.getMessage();
	}
	}
	public String waitText(String object, String data) {
		try {
			Thread.sleep(3000);
		} catch (Exception ex) {
		}
		return Constants.KEYWORD_PASS;
	}

	// not a keyword

	public void captureScreenshot(String filename,
			String keyword_execution_result) throws IOException {
		// take screen shots
		if (CONFIG.getProperty("screenshot_everystep").equals("Y")) {
			// capturescreen

			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
					+ "//screenshots//" + filename + ".jpg"));

		} else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL)
				&& CONFIG.getProperty("screenshot_error").equals("Y")) {
			// capture screenshot
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
					+ "//screenshots//" + filename + ".jpg"));
		}
	}
	
public void ASSERALL()
{
	s_assert.assertAll();
}
	
}
