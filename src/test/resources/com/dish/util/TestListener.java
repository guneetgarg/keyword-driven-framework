package com.dish.util;



import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



public class TestListener  {
	

SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
String Time  = dateFormat.format(new Date());


	//WebDriver driver;
	private static String fileSeperator = System.getProperty("file.separator");
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	
	public void onTestFailure(String Thname,WebDriver driver) {
		System.out.println("***** Error " + Thname + " test has failed *****");

		//driver = TestBase.getDriver();

		String testClassName = Thname.trim();
		 String ktime=Time.trim();
		 String ktime1=   ktime.replaceAll(":", "");
		 String ktime2=   ktime1.replaceAll("-", "");
		 String ktime3=   ktime2.replaceAll(" ", "");
	//	String testMethodName = result.getName().toString().trim();
		String screenShotName = testClassName +ktime3+ ".png";

		if (driver != null) {
			String imagePath = ".." + fileSeperator + "Screenshots"
					+ fileSeperator + "Results" + fileSeperator + testClassName
					+ fileSeperator
					+ takeScreenShot(driver, screenShotName, testClassName);
			System.out.println("Screenshot can be found : " + imagePath);
			
			
			
			
		}
	}

	public static String takeScreenShot(WebDriver driver,
			String screenShotName, String testName) {
		try {
			File file = new File("Screenshots" + fileSeperator + "Results");
			if (!file.exists()) {
				System.out.println("File created " + file);
				file.mkdir();
			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testName, screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);

			return screenShotName;
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}

	public String getTestClassName(String testName) {
		String[] reqTestClassname = testName.split("\\.");
		int i = reqTestClassname.length - 1;
		System.out.println("Required Test Name : " + reqTestClassname[i]);
		return reqTestClassname[i];
	}

}