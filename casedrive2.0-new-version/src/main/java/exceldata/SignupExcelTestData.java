package exceldata;

import lombok.Getter;

@Getter
public class SignupExcelTestData {
	private String emailID;
	private String firstname;
	private String lastname;
	private String inchargeName;
	private String inchargeEmail;
	private String paymentName;
	private String paymentEmail;
	private String onboardEmail;
	public SignupExcelTestData(String emailID, String firstname, String lastname) {
		this.emailID = emailID;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	public SignupExcelTestData(String inchargeName, String inchargeEmail, String paymentName, String paymentEmail, String onboardEmail) {
		this.inchargeName = inchargeName;
		this.inchargeEmail = inchargeEmail;
		this.paymentName = paymentName;
		this.paymentEmail = paymentEmail;
		this.onboardEmail = onboardEmail;
	}
}