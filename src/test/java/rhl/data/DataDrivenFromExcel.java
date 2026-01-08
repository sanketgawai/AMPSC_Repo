package rhl.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenFromExcel {

//	public Object[][] getDataFromExcel(String sheetName) throws IOException
//	{
//		
//		File file = new File(System.getProperty("user.dir")
//                + "\\src\\test\\java\\rhl\\data\\MPSCTestData.xlsx");
//
//        FileInputStream fis = new FileInputStream(file);
//        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheet(sheetName);
//
//        int rowCount = sheet.getPhysicalNumberOfRows();
//        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
//
//        Object[][] data = new Object[rowCount - 1][1];
//
//        DataFormatter formatter = new DataFormatter();
//
//        for (int i = 1; i < rowCount; i++) {
//            Row row = sheet.getRow(i);
//            HashMap<String, String> map = new HashMap<>();
//
//            for (int j = 0; j < colCount; j++) {
//                String key = sheet.getRow(0).getCell(j).getStringCellValue();
//                String value = formatter.formatCellValue(row.getCell(j));
//                map.put(key, value);
//            }
//            data[i - 1][0] = map;
//        }
//
//        workbook.close();
//        fis.close();
//        return data;
//
//	}

	    public Object[][] getDataFromExcel(String sheetName) throws IOException {

	        File file = new File(System.getProperty("user.dir")
	                + "\\src\\test\\java\\rhl\\data\\MPSCTestDataTrueFalse.xlsx");

	        FileInputStream fis = new FileInputStream(file);
	        XSSFWorkbook workbook = new XSSFWorkbook(fis);
	        XSSFSheet sheet = workbook.getSheet(sheetName);

	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	        DataFormatter formatter = new DataFormatter();

	        // Step 1: Find Execution column index
	        int executionColIndex = -1;
	        Row headerRow = sheet.getRow(0);
	        for (int j = 0; j < colCount; j++) {
	            if (headerRow.getCell(j).getStringCellValue().equalsIgnoreCase("Execution")) {
	                executionColIndex = j;
	                break;
	            }
	        }

	        if (executionColIndex == -1) {
	            throw new RuntimeException("Execution column not found in Excel sheet");
	        }

	        // Step 2: Store only TRUE rows
	        List<Object[]> dataList = new ArrayList<>();

	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);

	            String executionValue = formatter.formatCellValue(row.getCell(executionColIndex));

	            // Fetch only rows where Execution = TRUE
	            if (executionValue.equalsIgnoreCase("TRUE")) {

	                HashMap<String, String> map = new HashMap<>();

	                for (int j = 0; j < colCount; j++) {
	                    String key = headerRow.getCell(j).getStringCellValue();
	                    String value = formatter.formatCellValue(row.getCell(j));
	                    map.put(key, value);
	                }

	                dataList.add(new Object[]{map});
	            }
	        }

	        workbook.close();
	        fis.close();

	        // Convert List to Object[][]
	        Object[][] data = new Object[dataList.size()][1];
	        for (int i = 0; i < dataList.size(); i++) {
	            data[i] = dataList.get(i);
	        }

	        return data;
	    }
	

}
