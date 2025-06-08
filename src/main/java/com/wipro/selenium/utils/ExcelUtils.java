package com.wipro.selenium.utils;


import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	private Workbook workBook;	
	private Sheet sheet;

	public ExcelUtils(String xcelPath, String sheetName)
	{
		try
		{			
			FileInputStream fis=new FileInputStream(xcelPath);
			workBook=new XSSFWorkbook(fis);
			sheet=workBook.getSheet(sheetName);

		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public int getRowCount()
	{
		return sheet.getPhysicalNumberOfRows();
	}

	public int getColumnCount()
	{
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public String getCellData(int rowNum, int ColNum)
	{
		DataFormatter df=new DataFormatter();
		Cell cell=sheet.getRow(rowNum).getCell(ColNum);
		return df.formatCellValue(cell);
	}
	
	public Object[][] getTestData()
	{
		int rowCount=getRowCount();
		int columnCount=getColumnCount();
		
		Object[][] data=new Object[rowCount-1][columnCount];
		
		for(int i=1;i<rowCount;i++)//row excludes header and start with header
		{
			for(int j=0;j<columnCount;j++)
			{
				data[i-1][j]=getCellData(i, j);
			}
		}
		
		  // 🔍 Debug print
	    System.out.println("✅ Excel Test Data:");
	    for (Object[] row : data) {
	        System.out.println(java.util.Arrays.toString(row));
	    }
	    
		return data;
	}
	
	
	
	public void closeWorkBook()
	{
		try {
			if(workBook!=null)
			{
				workBook.close();
			}
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
