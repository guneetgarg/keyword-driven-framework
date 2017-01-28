package com.utilities;

public class Constant {

	static private String excelFilePath;
	static private String reportDir;
	static private String screenShortDir;

	public String getScreenShortDir() {
		return screenShortDir;
	}

	public void setScreenShortDir(String screenShortDir) {
		Constant.screenShortDir = System.getProperty("user.dir") + "\\Report\\ScreenShot_" + screenShortDir;
	}

	//////////////////////////////////////////////////////
	public String getReportDir() {
		return reportDir;
	}

	public void setReportDir(String reportDir) {
		Constant.reportDir = System.getProperty("user.dir") + "\\Report\\Report_" + reportDir;
	}

	///////////////////////////////////////////
	public void setExcelUtil(String excelFilePath) {
		Constant.excelFilePath = System.getProperty("user.dir") + "\\" + excelFilePath;
	}

	public String getExcelUtil() {
		return excelFilePath;
	}

}
