package com.utilities;

public class Constant {

	static private String excelFilePath;
	static private String reportDir;
	static private String screenShortDir;
	String baseDir = System.getProperty("user.dir");
	static private String excelName;

	public static String getExcelName() {
		return excelName;
	}

	public static void setExcelName(String excelName) {
		Constant.excelName = excelName;
	}

	////////////////////////////////////////////////////////
	public String getScreenShortDir() {
		return screenShortDir;
	}

	public void setScreenShortDir(String screenShortDir) {
		Constant.screenShortDir = baseDir + "\\Report\\ScreenShot_" + screenShortDir + "\\";
	}

	//////////////////////////////////////////////////////
	public String getReportDir() {
		return reportDir;
	}

	public void setReportDir(String reportDir) {
		Constant.reportDir = baseDir + "\\Report\\Report_" + reportDir + "\\";
	}

	///////////////////////////////////////////
	public void setExcelUtil(String excelFilePath) {
		setExcelName(excelFilePath);
		Constant.excelFilePath = baseDir + "\\" + excelFilePath;
	}

	public String getExcelUtil() {
		return excelFilePath;
	}

}
