package com.test;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utilities.Dataprovider;
import com.utilities.ExcelUtil;
import com.utilities.KeywordWrapper;

public class DriverScript {

	public static Method method[];
	public KeywordWrapper keywords;

	ExcelUtil EU = ExcelUtil.getEUInstance();

	@BeforeClass
	public void driverScript() throws Exception {
		keywords = new KeywordWrapper();
		method = keywords.getClass().getMethods();
	}

	@Test(dataProvider = "getTestRunnerModeData", dataProviderClass = Dataprovider.class)
	public void execute(String tcid, String desc, String runmode) {

		if (EU.isSheetExist(tcid)) {
			System.out.println("yupiii");
		} else {
			System.out.println("Runmode is not yes");
		}

	}

}