package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class KeywordWrapper {
	Logger log = LogManager.getLogger(KeywordWrapper.class);
	ExcelUtil EU = ExcelUtil.getEUInstance();

	static WebDriver driver;
	By by = null;
	String locatorType;
	String locatorValue;

	public void waitSleep() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String openBrowser(String browser) {
		log.info("Initializing browser" + browser);
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers_executable\\";
		try {
			if (browser.equalsIgnoreCase("Firefox")) {

			} else if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
			} else {
				log.info("Wrong Browser Name" + browser);
				return "Wrong Browser Name" + browser;
			}
			driver.manage().window().maximize();
		} catch (Exception e) {
			return "Fail unable to open browser -- " + e;
		}
		log.info("Browser Open Successfully");
		return "Pass";
	}

	public String closeBroswer() {
		log.info("Closing browser");
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
			}
		} catch (Exception e) {
			return "Fail -- unable to close broswer -- " + e;
		}
		return "Pass";
	}

	public String navigate(String url) {
		waitSleep();
		log.info("Navigating to URL ->" + url);
		try {
			driver.get(url);
		} catch (Exception e) {
			return "FAIL" + " -- Not able to navigate --" + e;
		}
		return "Pass";
	}

	public String enterText(String loc, String str) {
		waitSleep();
		log.info("Entering the text ->" + str);
		try {
			driver.findElement(getLocator(loc)).sendKeys(str);
		} catch (Exception e) {
			return "FAIL" + " -- Not able to enter text --" + e;
		}
		return "Pass";
	}

	public enum LOCATOR_TYPE {
		name, id, linktext, xpath, partialLinkText, cssSelector
	};

	public By getLocator(String fieldLocator) {
		log.info("Field locator recieved::" + fieldLocator);

		String splitArray[] = fieldLocator.split("~~");

		locatorType = splitArray[0];
		locatorValue = splitArray[1];

		log.info("locator Type : " + locatorType);
		log.info("locator Searching : " + locatorValue);
		LOCATOR_TYPE loc_temp = null;
		try {
			loc_temp = LOCATOR_TYPE.valueOf(locatorType);
		} catch (Exception e) {
			log.info("Wrong Locator type enter" + locatorType);
			e.printStackTrace();
		}

		switch (loc_temp) {
		case name: {
			log.info("locator type of the field =" + locatorType);
			by = By.name(locatorValue);
			break;
		}

		case linktext: {
			log.info("locator type of the field =" + locatorType);
			by = By.linkText(locatorValue);
			break;
		}

		case xpath: {
			log.info("locator type of the field =" + locatorType);
			by = By.xpath(locatorValue);
			break;
		}

		case partialLinkText: {
			log.info("locator type of the field =" + locatorType);
			by = By.partialLinkText(locatorValue);
			break;
		}

		case cssSelector: {
			log.info("locator type of the field =" + locatorType);
			by = By.cssSelector(locatorValue);
			break;
		}

		case id: {
			log.info("locator type of the field =" + locatorType);
			by = By.id(locatorValue);
			break;
		}

		default: {
			log.info("You just can not move from here");
		}
		}
		return by;
	}

	public String click(String loc) {
		waitSleep();
		log.info("clicking ->" + loc);
		try {
			driver.findElement(getLocator(loc)).click();
		} catch (Exception e) {
			return "Fail" + " -- Not able to click on link -- " + e;
		}
		return "Pass";
	}

	public void getscreenshot(String filename) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveFileToDirectory(String srcFile, String destDir) {
		try {
			FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createDirectory(String str) {
		System.out.println(str);
		File file = new File(str);
		if (!file.exists()) {
			if (file.mkdir()) {
				log.info("Directory is created!");
			} else {
				log.info("Failed to create directory!");
			}
		}
	}

	public String randomNumber() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}