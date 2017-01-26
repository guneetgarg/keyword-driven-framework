package com.test;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utilities.ExcelUtil;
import com.utilities.KeywordWrapper;
import com.utilities.TestCaseCl;

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
		checkTestCaseRunMode();
	}

	public void checkTestCaseRunMode() {
		ExcelUtil excelUtil = new ExcelUtil();
		List<TestCaseCl> listRun =excelUtil.ss();
	}

}
