package com.bce.api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle cellStyle;
	public String excelPath;

	public ExcelUtility(String excelPath) {
		this.excelPath = excelPath;
	}

	public int getRowCount(String sheetName) {
		try {
			fi = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			workbook.close();
			fi.close();
			return rowCount;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int getCellCount(String sheetName, int rowNum) {
		try {
			fi = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			int cellCount = row.getLastCellNum();
			workbook.close();
			fi.close();
			return cellCount;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {
		try {
			fi = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			String data;
			try {
				data = cell.getStringCellValue();
			} catch (Exception e) {
				data = String.valueOf((int) cell.getNumericCellValue());
			}
			workbook.close();
			fi.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void setCellData(String sheetName, int rowNum, int colNum, String data) {
		try {
			fi = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
			}
			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
			}
			cell.setCellValue(data);
			fo = new FileOutputStream(excelPath);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
