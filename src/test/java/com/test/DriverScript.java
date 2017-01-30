package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
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
	String resultStatus, randString;
	ExcelUtil EU = ExcelUtil.getEUInstance();
	ArrayList<String> resultSet;

	@BeforeSuite
	public void initialSetup() {
		randString = keywords.randomNumber();
		setScreenShortDir(randString);
		setReportDir(randString);
		keywords.createDirectory(getScreenShortDir());
		keywords.createDirectory(getReportDir());
	}

	@Parameters("excelFilePath")
	@BeforeClass
	public void driverScript(String excelFilePath) throws Exception {
		method = keywords.getClass().getMethods();
		screenshot = keywords.getClass().getMethod("getscreenshot", String.class);
		setExcelUtil(excelFilePath);
		keywords.moveFileToDirectory(getExcelUtil(), getReportDir());

	}

	@Test(dataProvider = "getTestRunnerModeData", dataProviderClass = Dataprovider.class)
	public void execute(String tcid, String desc, String runmode) {
		resultSet = new ArrayList<String>();
		if (EU.isSheetExist(tcid) && runmode.equalsIgnoreCase("Y")) {
			ArrayList<String> status = run(tcid, EU.getTestStep(tcid));
			EU.writeExcel(getReportDir() + getExcelName(), tcid, status);
			keywords.closeBroswer();
			Assert.assertEquals("true", checkTCStatus(status));
		} else if (!EU.isSheetExist(tcid)) {
			log.info("Sheet Do Not Exist");
		} else if (!runmode.equalsIgnoreCase("Y")) {
			log.info("RunMode is not Y");
		}
	}

	public String checkTCStatus(ArrayList<String> status) {
		for (String ss : status) {
			if (!(ss.equalsIgnoreCase("pass"))) {
				return ss;
			}
		}
		return "true";
	}

	public ArrayList<String> run(String tcid, List<TestStepAggregation> TSA) {
		outerloop: for (int i = 0; i < TSA.size(); i++) {
			resultStatus = " ";
			for (int j = 0; j < method.length; j++) {
				if (method[j].getName().equals(TSA.get(i).getKeyword())) {
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
						resultStatus += e.toString() + e.getCause();
					} catch (IllegalArgumentException e) {
						resultStatus += e.toString() + e.getCause();
					} catch (InvocationTargetException e) {
						resultStatus += e.toString() + e.getCause();
					}
					if (!(resultStatus.equalsIgnoreCase("pass"))) {
						try {
							screenshot.invoke(keywords, tcid);
						} catch (IllegalAccessException e) {
							resultStatus += e.toString() + e.getCause();
						} catch (IllegalArgumentException e) {
							resultStatus += e.toString() + e.getCause();
						} catch (InvocationTargetException e) {
							resultStatus += e.toString() + e.getCause();
						}
						resultSet.add(resultStatus);
						break outerloop;
					}
					resultSet.add(resultStatus);
					break;
				}
			}

		}
		return resultSet;
	}
}