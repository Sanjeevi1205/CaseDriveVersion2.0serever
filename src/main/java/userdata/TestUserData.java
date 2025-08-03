package userdata;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TestUserData {
	@Getter
	@RequiredArgsConstructor
	@AllArgsConstructor
	public enum UserType {
		TEMPORAY_USER("Automation_james.smith@domain.com", "pqRsVuI@1234"),
		VALID_USER("Tom1122@lezdotechmed.com", "pqRsVuI@1234"),
		INVALID_USER("Automation_james.smithdomaincom", "pqRsVuI@1234"),
		INVALID_EMAIL("Automation_james.smith1@domain.com", "pqRsVuI@1234"),
		INVALID_PASSWORD("Automation_james.smith@domain.com", "Test@!@#"),
		NON_CONVERTED_LEAD("Automation_Tester@domain.com", "pqRsVuI@1234"),
		INVALID_CLIENT("Automation_julia.brown@domain.com", "pqRsVuI@1234"),
		NON_EXISTING_USER("AutomationTester@gmail.com", "pqRsVuI@1234"),
		INVALID_INTERNAL_USER("testemployeea@gmail.com", "pqRsVuI@1234"),
		SIGNUP_USER("000000", "pqRsVuI@1234", "pqRsVuI@1234", "Tax Law", "1234567890", "ABC Corp", "1-10", "8542675654", "101",
				"123 Elm St, New York, NY", "USA", "New York", "New York", "10001", "123 Elm St, New York, NY", "USA",
				"New York", "New York", "10001", "3216549876", "201", "Advocate", "Criminal Law", "2073281234", "301",
				"Consultant", "Corporate", Arrays.asList("Medical Chronology", "Narrative Summary", "Sorting & Indexing")),
		HOURLY_CASE_CLIENT("Automation_smith@mail.com", "pqRsVuI@1234"),
		MONTHLY_CASE_CLIENT("Automation_robert.brown@usmail.com", "pqRsVuI@1234"),
		VOLUME_CASE_CLIENT("Automation_sophia.hall@ukmail.com", "pqRsVuI@1234"),
		SUPER_ADMIN("mail@lezdotechmed.com", "Superadmin@123"), 
		CARD_DETAILS(4242424242424242L, 123, "12/28"),
		CARD_EMAIL("juliabrown@domain.com", "julia.brown","0987654321");


		public final String username;
		public final String password;
		public String OTP;
		public String confirmPassword;
		public String practiceArea;
		public String mobileNumber;
		public String companyName;
		public String companySize;
		public String phoneNumber;
		public String extension;
		public String address;
		public String country;
		public String state;
		public String city;
		public String zipcode;
		public String companyAddress;
		public String companyCountry;
		public String companyState;
		public String companyCity;
		public String companyZipcode;
		public String inchargePhoneNumber;
		public String inchargeExtension;
		public String jobRole;
		public String department;
		public String paymentPhonenumber;
		public String paymentExtension;
		public String paymentJobRole;
		public String paymentDepartment;
		public List<String> services;
		public long carddetails;
		public int securityCode;
		public String expiationData;
		public String email;
		public String phone;
		public String namemail;


		UserType(String OTP, String password, String confirmPassword, String practiceArea, String mobileNumber,
				String companyName, String companySize, String phoneNumber, String extension, String address,
				String country, String state, String city, String zipcode, String companyAddress, String companyCountry,
				String companyState, String companyCity, String companyZipcode, String inchargePhoneNumber,
				String inchargeExtension, String jobRole, String department, String paymentPhonenumber,
				String paymentExtension, String paymentJobRole, String paymentDepartment, List<String> services) {
			this.OTP = OTP;
			this.username = "";
			this.password = password;
			this.confirmPassword = confirmPassword;
			this.practiceArea = practiceArea;
			this.mobileNumber = mobileNumber;
			this.companyName = companyName;
			this.companySize = companySize;
			this.phoneNumber = phoneNumber;
			this.extension = extension;
			this.address = address;
			this.country = country;
			this.state = state;
			this.city = city;
			this.zipcode = zipcode;
			this.companyAddress = companyAddress;
			this.companyCountry = companyCountry;
			this.companyState = companyState;
			this.companyCity = companyCity;
			this.companyZipcode = companyZipcode;
			this.inchargePhoneNumber = inchargePhoneNumber;
			this.inchargeExtension = inchargeExtension;
			this.jobRole = jobRole;
			this.department = department;
			this.paymentPhonenumber = paymentPhonenumber;
			this.paymentExtension = paymentExtension;
			this.paymentJobRole = paymentJobRole;
			this.paymentDepartment = paymentDepartment;
			this.services = services;
		}

		UserType(long carddetails, int securityCode, String expiationData) {
			this.carddetails = carddetails;
			this.securityCode = securityCode;
			this.expiationData = expiationData;
			this.username = "";
			this.password = "";
		}

		UserType(String email, String namemail, String phone) {
			this.email = email;
			this.phone = phone;
			this.namemail = namemail;
			this.username = "";
			this.password = "";
		}
	}
}