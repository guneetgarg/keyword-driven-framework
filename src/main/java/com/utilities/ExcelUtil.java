package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.model.WorkbookRecordList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil extends Constant {

	static Workbook workbook;
	FileInputStream inputStream = null;
	Sheet sheet;

	/*
	 * Singleton
	 */
	private static ExcelUtil EU;

	private ExcelUtil() {

	}

	public static ExcelUtil getEUInstance() {
		if (EU == null) {
			EU = new ExcelUtil();
		}
		return EU;
	}

	public void excelSetup(String file) {
		try {
			inputStream = new FileInputStream(new File(file));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<TestCaseAggregation> getTestCaseSheetData() {
		excelSetup(getExcelUtil());
		sheet = workbook.getSheetAt(0);
		List<TestCaseAggregation> listTCA = new ArrayList<TestCaseAggregation>();

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			TestCaseAggregation TCA = new TestCaseAggregation();
			TCA.setTcId(sheet.getRow(i).getCell(0).toString());
			TCA.setDescription(sheet.getRow(i).getCell(1).toString());
			TCA.setRunmode(sheet.getRow(i).getCell(2).toString());
			listTCA.add(TCA);
		}

		closeWorkwook();
		closeInputStream();

		return listTCA;

	}

	public void closeWorkwook() {
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeInputStream() {
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// whether sheets exists
	public boolean isSheetExist(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	public List<TestStepAggregation> getTestStep(String sheetName) {
		List<TestStepAggregation> listTSA = new ArrayList<TestStepAggregation>();
		excelSetup(getExcelUtil());
		sheet = workbook.getSheet(sheetName);
		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			TestStepAggregation TSA = new TestStepAggregation();
			TSA.setDescription(sheet.getRow(i).getCell(0).toString());
			TSA.setKeyword(sheet.getRow(i).getCell(1).toString());
			TSA.setObject(sheet.getRow(i).getCell(2).toString());
			TSA.setData(sheet.getRow(i).getCell(3).toString());
			listTSA.add(TSA);
		}

		closeWorkwook();
		closeInputStream();
		return listTSA;
	}

	public void writeExcel(String file, String sheetName, ArrayList<String> arrResult) {
		excelSetup(file);
		sheet = workbook.getSheet(sheetName);

		for (int i = 0; i < arrResult.size(); i++) {
			Row row = sheet.getRow(i + 1);
			Cell cell = row.createCell(4);
			cell.setCellValue(arrResult.get(i));
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(file));
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
