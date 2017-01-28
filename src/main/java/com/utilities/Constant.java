package com.utilities;

public class Constant {

	private String excelFilePath;
	private String reportDir;

	public String getReportDir() {
		return reportDir;
	}

	public void setReportDir(String reportDir) {
		this.reportDir = reportDir;
	}

	///////////////////////////////////////////
	public void setExcelUtil(String excelFilePath) {
		this.excelFilePath = System.getProperty("user.dir") + "\\" + excelFilePath;
	}

	public String getExcelUtil() {
		return excelFilePath;
	}

}
