package exceldata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SignupExcelReader {

	private Workbook workbook;
	
	public SignupExcelReader(String filepath) throws BiffException, IOException {
		FileInputStream file = new FileInputStream(filepath);
		this.workbook=Workbook.getWorkbook(file);
	}
	public List<SignupExcelTestData> getsignupData(String sheetName) {
		Sheet sheet = workbook.getSheet(sheetName);
		List<SignupExcelTestData> signupData = new ArrayList<>();
		int rows = sheet.getRows();
	for (int i = 2; i < rows; i++) {
		String emailID = sheet.getCell(0, i).getContents();
		String firstname = sheet.getCell(1, i).getContents();
		String lastname = sheet.getCell(2, i).getContents();
		String inchargeName = sheet.getCell(3, i).getContents();
		String inchargeEmail = sheet.getCell(4, i).getContents();
		String paymentName = sheet.getCell(5, i).getContents();
		String paymentEmail = sheet.getCell(6, i).getContents();
		String onboardEmail = sheet.getCell(7, i).getContents();
		signupData.add(new SignupExcelTestData(emailID, firstname, lastname));
		signupData.add(new SignupExcelTestData(inchargeName, inchargeEmail,
				paymentName, paymentEmail, onboardEmail));
	}
	return signupData;
}
	public void closeWorkbook() {
		workbook.close();
	}
}