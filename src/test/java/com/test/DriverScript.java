package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utilities.Dataprovider;
import com.utilities.ExcelUtil;
import com.utilities.KeywordWrapper;
import com.utilities.TestStepAggregation;

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
		if (EU.isSheetExist(tcid) && runmode.equalsIgnoreCase("Y")) {
			run(EU.getTestStep(tcid));
		} else if (!EU.isSheetExist(tcid)) {
			// Assert.fail();
		} else if (!runmode.equalsIgnoreCase("Y")) {
			// Assert.fail();
		}
	}

	public void run(List<TestStepAggregation> TSA) {
		for (int i = 0; i < TSA.size(); i++) {

			for (int j = 0; j < method.length; j++) {
				if (method[j].getName().equals(TSA.get(i).getKeyword())) {
					System.out.println(method[j].getName() + "   " + TSA.get(i).getKeyword());
					try {
						if (method[j].getParameterCount() == 1 && TSA.get(i).getObject().length() > 0)
							method[j].invoke(keywords, TSA.get(i).getObject());
						else if (method[j].getParameterCount() == 1 && TSA.get(i).getData().length() > 0)
							method[j].invoke(keywords, TSA.get(i).getData());
						else if (method[j].getParameterCount() == 2)
							method[j].invoke(keywords, TSA.get(i).getObject(), TSA.get(i).getData());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
					break;
				}
			}

		}
	}
}