package com.utilities;

import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
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
	public static void main(String[] args) throws IOException {
		String excelFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestSuite.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		List<TestCaseCl> listBooks = new ArrayList<TestCaseCl>();

		workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

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

		workbook.close();
		inputStream.close();

		for (TestCaseCl a : listBooks) {
			System.out.println("Description =" + a.getDescription());
			System.out.println("tcid =" + a.getTcId());
			System.out.println("Runmode =" + a.getRunmode());
			System.out.println(isSheetExist(a.getTcId()));
			System.out.println("***********");
		}

	}
	// find whether sheets exists
		public static boolean isSheetExist(String sheetName) {
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
