package com.utilities;

public class Constant {

	static private String excelFilePath;
	static 	private String reportDir;
	static private String screenShortDir;

	public String getScreenShortDir() {
		return screenShortDir;
	}

	public void setScreenShortDir(String screenShortDir) {
		this.screenShortDir = System.getProperty("user.dir") + "\\Report\\ScreenShot_" + screenShortDir;
	}

	//////////////////////////////////////////////////////
	public String getReportDir() {
		return reportDir;
	}

	public void setReportDir(String reportDir) {
		this.reportDir = System.getProperty("user.dir") + "\\Report\\Report_" + reportDir;
	}

	///////////////////////////////////////////
	public void setExcelUtil(String excelFilePath) {
		this.excelFilePath = System.getProperty("user.dir") + "\\" + excelFilePath;
		System.out.println("*******************"+excelFilePath);
	}

	public String getExcelUtil() {
		System.out.println("--------------------------"+excelFilePath);
		return excelFilePath;
	}

}
