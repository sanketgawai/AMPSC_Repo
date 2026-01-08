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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataDrivenFromJson {

	 public Object[][] getDataFromJson() throws IOException {

	        ObjectMapper mapper = new ObjectMapper();

	        File file = new File(System.getProperty("user.dir")
	                + "\\src\\test\\java\\rhl\\data\\TestData.json");

	        // Read JSON array into List of HashMap
	        List<HashMap<String, String>> dataList =
	                mapper.readValue(file,
	                        new TypeReference<List<HashMap<String, String>>>() {});

	        Object[][] data = new Object[dataList.size()][1];

	        for (int i = 0; i < dataList.size(); i++) {
	            data[i][0] = dataList.get(i);
	        }

	        return data;
	    }
	}



