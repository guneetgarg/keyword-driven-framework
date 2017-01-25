package com.utilities;

import org.openqa.selenium.WebDriver;

public class KeywordWrapper {
	//static Logger log = LogManager.getLogger(Browsers.class);

	WebDriver driver;
	public String openBrowser(String browser) {
		String path = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("Firefox")) {


		} else if (browser.equalsIgnoreCase("Chrome")) {

		} else if (browser.equalsIgnoreCase("IE")) {

		}else{
			
		}
		return "";
	}
	
}
