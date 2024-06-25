package com.bce.api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Data")
	public String[][] getAllEmployeeData() {
		String path = System.getProperty("user.dir") + "/testdata/testdata.xlsx";
		ExcelUtility excelUtility = new ExcelUtility(path);
		int rowNum = excelUtility.getRowCount("Sheet1");
		int colNum = excelUtility.getCellCount("Sheet1", 1);

		String apiData[][] = new String[rowNum][colNum];
		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				apiData[i - 1][j] = excelUtility.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
	}

	@DataProvider(name = "EmpIds")
	public String[] getAllEmployeeIds() {
		String path = System.getProperty("user.dir") + "/testdata/testdata.xlsx";
		ExcelUtility excelUtility = new ExcelUtility(path);
		int rowNum = excelUtility.getRowCount("Sheet1");

		String apiData[] = new String[rowNum];
		for (int i = 1; i <= rowNum; i++) {
				apiData[i - 1] = excelUtility.getCellData("Sheet1", i, 0);
		}
		return apiData;
	}

}
