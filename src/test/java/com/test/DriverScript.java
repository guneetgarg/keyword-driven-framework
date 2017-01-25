package com.test;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utilities.KeywordWrapper;

public class DriverScript {

	public static Method method[];
	public static Method capturescreenShot_method;
	public static KeywordWrapper keywords;
	private static DriverScript DS;

	private DriverScript() {

	}
	
	public static DriverScript getDSInstance() {
		if (DS == null) {
			DS = new DriverScript();
		}
		return DS;
	}

	@BeforeClass
	public void driverScript() throws Exception {
		keywords = new KeywordWrapper();
		method = keywords.getClass().getMethods();
	}

	@Test
	public void execute() {
		DriverScript.getDSInstance();
		keywords.openBrowser("chrome");
		keywords.closeBroswer();
	}
	
	public void checkTestCaseRunMode(){
		
	}

}
