package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// It gets all the data from excelsheets.
	// 
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "/testData/Userdata.xlsx";  // Use a single slash
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        // Initialize the array to store data from Excel
        String apidata[][] = new String[rownum][colcount];

        // Loop through rows and columns to fetch data
        for (int i = 1; i <= rownum; i++) {  // If data starts from row 2 in Excel, else use i=0
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }
    
    
    // It gets only the username from the excelsheets.
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "/testData/Userdata.xlsx";  // Use a single slash
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");

        // Initialize the array to store usernames
        String apidata[] = new String[rownum];

        // Loop through rows to fetch usernames from column 1 (index 1)
        for (int i = 1; i <= rownum; i++) {  // If usernames start from row 2, else use i=0
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);  // Assuming column 1 contains usernames
        }

        return apidata;
    }
}
