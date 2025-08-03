package pompages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import lombok.Getter;
import userdata.TestUserData.UserType;


@Getter
public class SignupPOM extends LibGlobal {
	public SignupPOM() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Create']")
	private WebElement createmenu;

	@FindBy(name = "email")
	private WebElement clientemail;

	@FindBy(name = "agree")
	private WebElement agreeCheckbox;

	@FindBy(xpath = "//iframe[@title='reCAPTCHA']")
	private WebElement reCaptchaFrame;

	@FindBy(xpath = "(//span[@id='recaptcha-anchor']//div)[1]")
	private WebElement captchacheckBox;

	@FindBy(xpath = "//span[text()='Next']")
	private WebElement nextbutton;

	@FindBy(xpath = "//button[text()='Resend']")
	private WebElement resendButton;

	@FindBy(xpath = "//input[@type='tel']") // XPath for all OTP fields
	private static List<WebElement> otpFields; // List of OTP input fields

	// Getter for otpFields
	public List<WebElement> getOtpFields() {
		return otpFields;
	}

	public static void enterOtp(String otp) {
		// Wait for OTP fields to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		List<WebElement> visibleOtpFields = wait.until(ExpectedConditions.visibilityOfAllElements(otpFields));

		char[] otpChars = otp.toCharArray();

		System.out.println("Number of OTP fields: " + visibleOtpFields.size());
		System.out.println("Length of OTP: " + otpChars.length);

		// Check if number of fields matches the OTP length
		if (visibleOtpFields.size() != otpChars.length) {
			throw new IllegalArgumentException("Mismatch between OTP length and number of fields.");
		}

		// Enter OTP into each field
		for (int i = 0; i < visibleOtpFields.size(); i++) {
			WebElement otpField = visibleOtpFields.get(i);

			// Clear any pre-filled value
			otpField.clear();

			// Click on the field to focus it
			otpField.click();

			// Enter the OTP character
			otpField.sendKeys(String.valueOf(otpChars[i]));
		}
	}

	@FindBy(xpath = "//a[text()='Help']")
	private WebElement scroll;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement nextbtn1;

	@FindBy(xpath = "(//input[@name='password'])[1]")
	private WebElement password;

	@FindBy(xpath = "(//input[@name='password'])[2]")
	private WebElement repassword;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement nextbtn2;

	@FindBy(xpath = "//input[@id='inlineRadio1']")
	private WebElement positionbutton;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement nextbtn3;

	@FindBy(name = "name")
	private WebElement firstname;

	@FindBy(name = "last_name")
	private WebElement lastname;

	@FindBy(name = "practice_area")
	private WebElement practicearea;

	@FindBy(id = ":r0:")
	private WebElement mobilenumber;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement nextbtn4;

	@FindBy(name = "company_name")
	private WebElement companyname;

	@FindBy(xpath = "(//select[@class='did-floating-select'])[1]")
	private WebElement companysize;

	@FindBy(name = "ext")
	private WebElement extension;

	@FindBy(id = ":r2:")
	private WebElement phonenumber;

	@FindBy(name = "address")
	private WebElement address;

	@FindBy(xpath = "(//select[@class='did-floating-select'])[2]")
	private WebElement country;

	@FindBy(xpath = "(//select[@class='did-floating-select'])[3]")
	private WebElement state;

	@FindBy(name = "city")
	private WebElement city;

	@FindBy(name = "zip_code")
	private WebElement zipcode;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement billingaddresstogglebutton;

	@FindBy(xpath = "(//input[@name='address'])[2]")
	private WebElement billingaddress;

	@FindBy(xpath = "(//select[@class='did-floating-select'])[4]")
	private WebElement billingcountry;

	@FindBy(xpath = "(//select[@class='did-floating-select'])[5]")
	private WebElement billingstate;

	@FindBy(xpath = "(//input[@name='city'])[2]")
	private WebElement billingcity;

	@FindBy(xpath = "(//input[@name='zip_code'])[2]")
	private WebElement billingzipcode;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement nextbtn5;

	@FindBy(xpath = "//div[@class='service-form-check']")
	private List<WebElement> services;

	public void selectServices(UserType signupUser) {
	    List<String> serviceNames = signupUser.getServices();
	    for (String serviceName : serviceNames) {
	        boolean isClicked = false;
	        for (WebElement service : services) {
	            if (service.getText().equalsIgnoreCase(serviceName.trim())) {
	                service.click();
	                isClicked = true;
	                break;
	            }
	        }
	        if (!isClicked) {
	            System.out.println("Service not found: " + serviceName);
	        }
	    }
	}
	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement nextbtn8;
	
	@FindBy(xpath = "//label[text()='Choose File']")
	private WebElement chooseFileButton;
	
	@FindBy(xpath = "(//input[@name='Medical Chronology'])[1]")
	private WebElement template1;
	
	@FindBy(xpath = "(//input[@name='Narrative Summary'])[1]")
	private WebElement template2;
	
	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement nextbtn9;
	
	@FindBy(xpath = "//h1[text()='Thank you!']")
	private WebElement confirmationPage;
	
	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement doneButton;
	
	@FindBy(xpath = "//div[text()='Please enter a valid email address']")
	private WebElement errorMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Email Address Already Exists.')]")
	private WebElement nonConvertedLeadPopupMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Welcome Back!')]")
	private WebElement onboardingContinuePopup;
	
	public static void uploadFile(String filePath) throws AWTException {
        // Delay to handle any intermediate operations
        Robot robot = new Robot();
        robot.delay(1000);

        // Copy the file path to the clipboard
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // Simulate CTRL+V to paste the file path
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Simulate ENTER to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        System.out.println("File uploaded successfully: " + filePath);
    }
	
	@FindBy(xpath = "//span[text()='Next']")
	private WebElement fileUploadNextButton;
}