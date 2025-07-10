package com.learn.utility;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelfile {

	public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
		try (FileInputStream inputStream = new FileInputStream(fileName);
				XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

			XSSFSheet sheet = workBook.getSheet(sheetName);
			if (sheet == null)
				return "";

			Row row = sheet.getRow(rowNo);
			if (row == null)
				return "";

			Cell cell = row.getCell(cellNo);
			if (cell == null)
				return "";

			switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case FORMULA:
				return cell.getCellFormula();
			default:
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static int getRowCount(String fileName, String sheetName) {
		try (FileInputStream inputStream = new FileInputStream(fileName);
				XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

			XSSFSheet sheet = workBook.getSheet(sheetName);
			return (sheet != null) ? sheet.getLastRowNum() + 1 : 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getColCount(String fileName, String sheetName) {
		try (FileInputStream inputStream = new FileInputStream(fileName);
				XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {

			XSSFSheet sheet = workBook.getSheet(sheetName);
			if (sheet == null)
				return 0;

			Row row = sheet.getRow(0);
			return (row != null) ? row.getLastCellNum() : 0;
		} catch (Exception e) {
			return 0;
		}
	}
}
