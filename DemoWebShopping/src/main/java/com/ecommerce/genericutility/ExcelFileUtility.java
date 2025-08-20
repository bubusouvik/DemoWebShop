package com.ecommerce.genericutility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String getSheetFromExcel(String sheetName) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./testappdata/Electronics.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		int sheetNo = book.getNumberOfSheets();
		for (int i = 0; i < sheetNo; i++) {
			String sheetname = book.getSheetName(i);
			if (sheetName.equalsIgnoreCase(sheetname)) {
				break;
			}
		}
		return sheetName;

	}

	public String getProductFromExcel(String sheetName, String pname) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./testappdata/Electronics.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		String productname = null;
		for (int i = 0; i < rowcount; i++) {
			Row row = sheet.getRow(i);
			productname = row.getCell(0).getStringCellValue();
			if (pname.equalsIgnoreCase(productname)) {
				break;
			}
		}
		return productname;

	}

	public String getProductFromCell(String sheetName, String pname, String innerProduct)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./testappdata/Electronics.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sheet = book.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		String productname = null;
		String innerproduct = null;
		for (int i = 0; i < rowcount; i++) {
			Row row = sheet.getRow(i);
			productname = row.getCell(0).getStringCellValue();
			if (pname.equalsIgnoreCase(productname)) {
				int cellCount = row.getLastCellNum();
				for (int j = 1; j < cellCount; j++) {
					innerproduct = sheet.getRow(i).getCell(j).getStringCellValue();
					if (innerproduct.equalsIgnoreCase(innerProduct)) {
						break;
					}
				}
			}
		}
		return innerproduct;

	}

}
