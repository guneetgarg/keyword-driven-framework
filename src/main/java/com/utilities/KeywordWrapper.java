package com.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class KeywordWrapper {
	Logger log = LogManager.getLogger(KeywordWrapper.class);

	static WebDriver driver;

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

}
