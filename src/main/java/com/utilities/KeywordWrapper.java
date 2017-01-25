package com.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class KeywordWrapper {
	Logger log = LogManager.getLogger(KeywordWrapper.class);

	static WebDriver driver;

	public String openBrowser(String browser) {
		log.info("");
		String path = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("Firefox")) {

		} else if (browser.equalsIgnoreCase("Chrome")) {

		} else if (browser.equalsIgnoreCase("IE")) {

		} else {
			log.info("Wrong Browser Name" + browser);
		}
		return "";
	}

}
