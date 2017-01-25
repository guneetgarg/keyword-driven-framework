package com.test;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;

import com.utilities.KeywordWrapper;

public class DriverScript {

	public static Method method[];
	public static Method capturescreenShot_method;
	public static KeywordWrapper keywords;

	@BeforeClass
	public void driverScript() throws Exception {
		keywords = new KeywordWrapper();
		method = keywords.getClass().getMethods();
		capturescreenShot_method = keywords.getClass().getMethod("captureScreenshot", String.class, String.class);

	}
}
