package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilities.Dataprovider;
import com.utilities.ExcelUtil;
import com.utilities.KeywordWrapper;
import com.utilities.TestStepAggregation;

public class DriverScript {
	Logger log = LogManager.getLogger(DriverScript.class);

	public static Method method[];
	public KeywordWrapper keywords;

	ExcelUtil EU = ExcelUtil.getEUInstance();

	@Parameters("excelFilePath")
	@BeforeClass
	public void driverScript(String excelFilePath) throws Exception {
		keywords = new KeywordWrapper();
		method = keywords.getClass().getMethods();
		EU.setExcelUtil(excelFilePath);
	}

	@Test(dataProvider = "getTestRunnerModeData", dataProviderClass = Dataprovider.class)
	public void execute(String tcid, String desc, String runmode) {
		if (EU.isSheetExist(tcid) && runmode.equalsIgnoreCase("Y")) {
			run(EU.getTestStep(tcid));
		} else if (!EU.isSheetExist(tcid)) {
			log.info("Sheet Do Not Exist");
		} else if (!runmode.equalsIgnoreCase("Y")) {
			log.info("RunMode is not Y");
		}
	}

	public void run(List<TestStepAggregation> TSA) {
		for (int i = 0; i < TSA.size(); i++) {

			for (int j = 0; j < method.length; j++) {
				if (method[j].getName().equals(TSA.get(i).getKeyword())) {
					System.out.println(method[j].getName() + "   " + TSA.get(i).getKeyword());
					try {
						if (method[j].getParameterCount() == 0)
							method[j].invoke(keywords);
						else if (method[j].getParameterCount() == 1 && TSA.get(i).getObject().length() > 0)
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