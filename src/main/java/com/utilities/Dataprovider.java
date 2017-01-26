package com.utilities;

import java.util.List;

import org.testng.annotations.DataProvider;

public class Dataprovider {

	ExcelUtil exU = new ExcelUtil();

	@DataProvider(name = "getTestRunnerModeData")
	public Object[][] getTestRunnerModeData() {

		List<TestCaseCl> lData = exU.ss();
		Object[][] data = null;

		data = new Object[lData.size()][3];

		System.out.println(lData.size());
		int count = 0;
		for (TestCaseCl a : lData) {
			data[count][0] = a.getTcId();
			data[count][1] = a.getDescription();
			data[count][2] = a.getRunmode();
			count++;
		}
		return data;
	}

}