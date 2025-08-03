package pompages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class ClientPaymentPOM extends LibGlobal {

	public ClientPaymentPOM() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "email")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement loginbtn;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1asfma']")
	private WebElement saSidemenu;

	@FindBy(xpath = "(//span[text()='Billing'])[1]")
	private WebElement billingButton;

	@FindBy(xpath = "//span[text()='Invoices']")
	private WebElement invioceButton;

	@FindBy(xpath = "//button[text()='Pay Now']")
	private WebElement payNowButton;

	@FindBy(xpath = "(//iframe[@role='presentation'])[1]")
	private WebElement switchFram;

	@FindBy(xpath = "(//input[@type='text'])[1]")
	private WebElement cardNumber;

	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement expiationData;

	@FindBy(xpath = "(//input[@type='text'])[3]")
	private WebElement securityCode;

	@FindBy(xpath = "//button[text()='Pay']")
	private WebElement confirmPay;

	@FindBy(xpath = "//span[text()='Payment Successful']")
	private WebElement paymentSucess;

	@FindBy(xpath = "(//button[@type='button'])[1]")
	private WebElement card;

	@FindBy(xpath = "//button[text()='Paid']")
	private WebElement paid;

	@FindBy(xpath = "(//button[text()='Download'])[1]")
	private WebElement download;

	@FindBy(name = "search-linkEmail")
	private WebElement userMail;

	@FindBy(xpath = "//div[@class='p-PhoneNumberCountrySelect-iconWrapper']")
	private WebElement userPhone;

	@FindBy(xpath = "//input[@class='p-Input-input Input p-FullNameField-inputPadding Input--empty']")
	private WebElement namemail;

	@FindBy(xpath = "//span[text()='Payment Successful']")
	private WebElement paymentSucessfully;

	@FindBy(xpath = "//span[text()='Receipts']")
	private WebElement receiptsMenu;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-65aruc'])[1]")
	private WebElement threeDotButton;

	@FindBy(xpath = "//div//p[text()='View']")
	private WebElement viewOption;

	@FindBy(xpath = "//div//p[text()='Download']")
	private WebElement downloadOption;
}