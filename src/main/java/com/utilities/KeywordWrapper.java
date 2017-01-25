package com.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class KeywordWrapper {
	Logger log = LogManager.getLogger(KeywordWrapper.class);

	static WebDriver driver;
	By by = null;
	String locatorType;
	String locatorValue;

	public String openBrowser(String browser) {
		log.info("Initializing browser" + browser);
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers_executable\\";
		if (browser.equalsIgnoreCase("Firefox")) {

		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", path + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			log.info("Wrong Browser Name" + browser);
		}
		driver.manage().window().maximize();
		log.info("Browser Open Successfully");
		return "Pass";
	}

	public String closeBroswer() {
		log.info("Closing browser");
		if (driver != null) {
			driver.close();
			driver = null;
		}
		return "Pass";
	}

	public String navigate(String url) {
		log.info("Navigating to URL ->" + url);
		driver.get(url);
		return "Pass";
	}

	public String enterText(String str, String loc) {
		log.info("Entering the text ->" + str);
		driver.findElement(getLocator(loc));
		return "Pass";
	}

	public enum LOCATOR_TYPE {
		name, id, linktext, xpath, partialLinkText, cssSelector
	};

	public By getLocator(String fieldLocator) {
		log.info("Field locator recieved::" + fieldLocator);

		String splitArray[] = fieldLocator.split("||");
		
		locatorType = splitArray[0];
		locatorValue = splitArray[1];
		
		log.info("locator Type : " + locatorType);
		log.info("locator Searching : " + locatorValue);
		LOCATOR_TYPE loc_temp = null;
		try {
			loc_temp = LOCATOR_TYPE.valueOf(locatorType);
		} catch (Exception e) {
			log.info("Wrong Locator type enter" + locatorType );
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

}
