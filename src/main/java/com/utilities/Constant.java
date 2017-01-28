package com.utilities;

public class Constant {

	static private String excelFilePath;
	static private String reportDir;
	static private String screenShortDir;
	String baseDir = System.getProperty("user.dir");

	public String getScreenShortDir() {
		return screenShortDir;
	}

	public void setScreenShortDir(String screenShortDir) {
		Constant.screenShortDir = baseDir + "\\Report\\ScreenShot_" + screenShortDir;
	}

	//////////////////////////////////////////////////////
	public String getReportDir() {
		return reportDir;
	}

	public void setReportDir(String reportDir) {
		Constant.reportDir = baseDir + "\\Report\\Report_" + reportDir;
	}

	///////////////////////////////////////////
	public void setExcelUtil(String excelFilePath) {
		Constant.excelFilePath = baseDir + "\\" + excelFilePath;
	}

	public String getExcelUtil() {
		return excelFilePath;
	}

}
