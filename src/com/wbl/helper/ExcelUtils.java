package com.wbl.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	   private static Logger logger= Logger.getLogger(ExcelUtils.class);
	
		public static Object[][] getExcelData(String sheetName, String fileName) {
		
		System.out.println("In the excel data get method..............."+fileName+"   "+sheetName);
		Object[][] data = null;
		
		logger.info("Reading excel data...");
		try {
			
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\Users\\sonal\\workspace1\\RestApi-Aug\\src\\resource\\rest-data\\"+fileName));
			
		
			//XSSFSheet sheet = wb.createSheet(sheetName);
			XSSFSheet sheet = wb.getSheet(sheetName);
			
			
			int noOfRows = sheet.getLastRowNum();
			
			System.out.println("No of rows" +noOfRows);
			
			data= new Object[noOfRows][];
			
			Row row;
			for(int i=1;i<=noOfRows;i++) {
				
				row = sheet.getRow(i);
				int noOfColumns = row.getLastCellNum();
			
				//	System.out.println("The no of columns are:: "+noOfColumns);
				
				String[] strData = new String[noOfColumns];
				
				int j;
				for(j=0;j<noOfColumns;j++) {
					strData[j] = row.getCell(j).getStringCellValue();
				//	System.out.println("The excel sheet data is::"  +strData[j]);
				}
				data[i-1] = strData;
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			logger.error("Excel file not found-Please give correct file details ");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			logger.error("Issue reading the given excel file");
		}
				
		return data;
	}
}
