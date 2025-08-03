package exceldata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class OrderIntakeReader {
    private Workbook workbook;

    // Constructor that opens the workbook
    public OrderIntakeReader(String filePath) throws IOException, BiffException {
        FileInputStream file = new FileInputStream(filePath);
        this.workbook = Workbook.getWorkbook(file);
    }

    // Method to read data from the specified sheet and return a list of OrderIntakeTestData objects
    public List<OrderIntakeTestData> getOrderIntakeData(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        List<OrderIntakeTestData> orderIntakeData = new ArrayList<>();
        int rows = sheet.getRows();

        // Loop through each row starting from 1 to skip the header row
        for (int i = 1; i < rows; i++) {
            // Assuming the columns for username and password are at indices 0 and 1
            String username = sheet.getCell(0, i).getContents();
            String password = sheet.getCell(1, i).getContents();


            // Create a new OrderIntakeTestData object with the data from the sheet
            orderIntakeData.add(new OrderIntakeTestData(username, password));
        }

        // Return the list of OrderIntakeTestData objects
        return orderIntakeData;
    }

    // Method to close the workbook
    public void closeWorkbook() {
        workbook.close();
    }
}
