package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static Workbook workbook;
	FileInputStream inputStream = null;
	Sheet sheet;
	List<TestCaseCl> listBooks = new ArrayList<TestCaseCl>();
	String excelFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestSuite.xlsx";

	private static ExcelUtil EU;
	private ExcelUtil() {

	}

	public static ExcelUtil getEUInstance() {
		if (EU == null) {
			EU = new ExcelUtil();
		}
		return EU;
	}

	public void excelSetup() {
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<TestCaseCl> ss() {
		excelSetup();
		sheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = sheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			TestCaseCl aBook = new TestCaseCl();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					aBook.setTcId(nextCell.getStringCellValue());
					break;
				case 1:
					aBook.setDescription(nextCell.getStringCellValue());
					break;
				case 2:
					aBook.setRunmode(nextCell.getStringCellValue());
					break;
				}

			}
			listBooks.add(aBook);
		}

		closeWorkwook();
		closeInputStream();

		return listBooks;

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
}
