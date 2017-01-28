package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilities.Constant;
import com.utilities.Dataprovider;
import com.utilities.ExcelUtil;
import com.utilities.KeywordWrapper;
import com.utilities.TestStepAggregation;

public class DriverScript extends Constant {
	Logger log = LogManager.getLogger(DriverScript.class);

	public Method method[];
	public Method screenshot;
	public KeywordWrapper keywords = new KeywordWrapper();
	String rr;
	String resultStatus;
	public ArrayList<String> resultSet;

	ExcelUtil EU = ExcelUtil.getEUInstance();

	@BeforeSuite
	public void driverSc() {
		rr = keywords.randomNumber();
		setReportDir(rr);
		setScreenShortDir(rr);

		keywords.createDirectory(getScreenShortDir());

		keywords.createDirectory(getReportDir());

	}

	@Parameters("excelFilePath")
	@BeforeClass
	public void driverScript(String excelFilePath) throws Exception {
		method = keywords.getClass().getMethods();
		screenshot = keywords.getClass().getMethod("getscreenshot", String.class);
		setExcelUtil(excelFilePath);
		System.out.println(getExcelUtil());
		System.out.println(getReportDir());
		keywords.moveFileToDirectory(getExcelUtil(), getReportDir());
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

	public ArrayList<String> run(List<TestStepAggregation> TSA) {
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
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						resultStatus = e.toString() + e.getCause();
					}
					resultSet.add(resultStatus);
					if (!(resultStatus.equalsIgnoreCase("pass"))) {
						try {
							screenshot.invoke(keywords, "abc.png");
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						System.out.println("77777777777777777777");
						break outerloop;
					}
					resultStatus = " ";
					break;
				}
			}

		}
		return resultSet;
	}
}