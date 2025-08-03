package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class LoginPOM extends LibGlobal {

	public LoginPOM() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "email")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement loginbtn;

	@FindBy(xpath = "(//span[contains(text(),'Add')])[1]")
	private WebElement addrecords;

	@FindBy(xpath = "(//div[@class='MuiBox-root css-0'])[12]")
	private WebElement profile;

	@FindBy(xpath = "(//span[@class='MuiTypography-root MuiTypography-bold css-1xxw3ug'])[1]")
	private WebElement usermail;

	@FindBy(xpath = "//div[contains(text(),'Invalid email. Please enter a valid one.')]")
	private WebElement invalidMailPopup;

	@FindBy(xpath = "//div[contains(text(),'Incorrect password')]")
	private WebElement invalidPasswordPopup;

	@FindBy(xpath = "//div[contains(text(),'Your account is currently inactive')]")
	private WebElement inactiveClient;

	@FindBy(xpath = "//a[text()='Help']")
	private WebElement scroll;

	@FindBy(xpath = "//a[text()='Forgot Password?']")
	private WebElement forgotPassword;

	@FindBy(xpath = " //button[normalize-space()='Send OTP']")
	private WebElement sendOTP;

	@FindBy(xpath = " //button[normalize-space()='Confirm']")
	private WebElement confirmButton;

	@FindBy(xpath = " //input[@name='pass']")
	private WebElement newPasswordInput;

	@FindBy(xpath = "//input[@name='matchPassword']")
	private WebElement confirmPasswordInput;

	@FindBy(xpath = "//button[normalize-space()='Confirm']")
	private WebElement changePasswordButton;

	@FindBy(xpath = "//div[text()='Email does not exist.']")
	private WebElement emaildoesnotexist;

	@FindBy(xpath = "//div[text()='The email address  you entered does not exist. please enter valid one']")
	private WebElement nonexitingclient;

	@FindBy(xpath = "//div[text()='Your account is being verified. You will receive an email with further details shortly.']")
	private WebElement nonConvertedLead;

	@FindBy(xpath = "//div[text()='Your account is currently inactive or removed. Please contact our support team for assistance.']")
	private WebElement invaildInternalUser;

	@FindBy(xpath = "//button[text()='Resend']")
	private WebElement resendButton;
}
