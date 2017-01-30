package com.utilities;

import java.util.List;

import org.testng.annotations.DataProvider;

public class Dataprovider {

	ExcelUtil EU = ExcelUtil.getEUInstance();

	@DataProvider(name = "getTestRunnerModeData")
	public Object[][] getTestRunnerModeData() {

		List<TestCaseAggregation> lData = EU.getTestCaseSheetData();
		Object[][] data = new Object[lData.size()][3];

		int count = 0;
		for (TestCaseAggregation a : lData) {
			data[count][0] = a.getTcId();
			data[count][1] = a.getDescription();
			data[count][2] = a.getRunmode();
			count++;
		}
		return data;
	}
}