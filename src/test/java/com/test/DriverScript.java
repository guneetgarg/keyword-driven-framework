package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilities.Dataprovider;
import com.utilities.ExcelUtil;
import com.utilities.KeywordWrapper;
import com.utilities.TestStepAggregation;

public class DriverScript {
	Logger log = LogManager.getLogger(DriverScript.class);

	public Method method[];
	public Method screenshot;
	public KeywordWrapper keywords;
	String resultStatus;
	public ArrayList<String> resultSet;

	ExcelUtil EU = ExcelUtil.getEUInstance();

	@BeforeSuite
	public void driverSc() {
	}

	@Parameters("excelFilePath")
	@BeforeClass
	public void driverScript(String excelFilePath) throws Exception {
		keywords = new KeywordWrapper();
		method = keywords.getClass().getMethods();
		screenshot = keywords.getClass().getMethod("getscreenshot", String.class);
		EU.setExcelUtil(excelFilePath);
		// keywords.moveFileToDirectory(EU.getExcelUtil(),"");
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
			outerloop: for (int j = 0; j < method.length; j++) {
				if (method[j].getName().equals(TSA.get(i).getKeyword())) {
					System.out.println(method[j].getName() + "   " + TSA.get(i).getKeyword());
					try {
						if (method[j].getParameterCount() == 0)
							resultStatus = (String) method[j].invoke(keywords);
						else if (method[j].getParameterCount() == 1 && TSA.get(i).getObject().length() > 0)
							resultStatus = (String) method[j].invoke(keywords, TSA.get(i).getObject());
						else if (method[j].getParameterCount() == 1 && TSA.get(i).getData().length() > 0)
							resultStatus = (String) method[j].invoke(keywords, TSA.get(i).getData());
						else if (method[j].getParameterCount() == 2)
							resultStatus = (String) method[j].invoke(keywords, TSA.get(i).getObject(),
									TSA.get(i).getData());
					} catch (IllegalAccessException e) {
						resultStatus = e.toString() + e.getCause();
					} catch (IllegalArgumentException e) {
						resultStatus = e.toString() + e.getCause();
					} catch (InvocationTargetException e) {
						resultStatus = e.toString() + e.getCause();
					} catch (SecurityException e) {
						resultStatus = e.toString() + e.getCause();
					}
					// resultSet.add(resultStatus);
					if (!(resultStatus.equalsIgnoreCase("pass"))) {
						try {
							screenshot.invoke(keywords, "abc.png");
						} catch (IllegalAccessException e) {
							resultStatus = e.toString() + e.getCause();
						} catch (IllegalArgumentException e) {
							resultStatus = e.toString() + e.getCause();
						} catch (InvocationTargetException e) {
							resultStatus = e.toString() + e.getCause();
						} catch (SecurityException e) {
							resultStatus = e.toString() + e.getCause();
						}
						System.out.println("77777777777777777777");
						break outerloop;
					}
					resultStatus = " ";
					break;
				}
			}

		}
		// return resultSet;
	}
}