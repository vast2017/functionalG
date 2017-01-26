package com.functional;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.*;

public class ExcelRead {
	
	static Workbook wb;
	static Sheet sheet;
	
	public ExcelRead(){
		try {
			wb = WorkbookFactory.create(new File(System.getProperty("user.dir")+"/testcase.xlsx"));
	//		wb = WorkbookFactory.create(new FileInputStream(System.getProperty("user.dir")+"/testcase.xlsx"));
			
			sheet = wb.getSheet("tccases");
		} catch (Exception e) {

		} 
	}
	
	
	public  String getCellData(int rownum, int colnum) throws Exception{

	//	FileInputStream inp = new FileInputStream(System.getProperty("user.dir")+"/testcase.xlsx");
	//  System.out.println(sheet.getFirstRowNum());
	//  System.out.println(sheet.getLastRowNum());
		DataFormatter formatter = new DataFormatter();
	    Row r = sheet.getRow(rownum); // reading all 0th row
	    Cell c = r.getCell(colnum);
	    
	//    System.out.println(c.toString());
	    
	    return formatter.formatCellValue(c);
	    
	}
	
	public int getMaxRun(){
		
		int last_row = sheet.getLastRowNum();
	//	System.out.println("last_row -  " + last_row);
		return last_row;
		
	}

}
