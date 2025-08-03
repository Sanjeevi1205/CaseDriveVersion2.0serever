package pompages;

import baseclass.LibGlobal;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ClientMyAccountPOM extends LibGlobal {
    public ClientMyAccountPOM(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//button[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiIconButton-root')])[3]")
    private WebElement profile;

    @FindBy(xpath = "//div//span[text()='My Account']")
    private WebElement myaccountOption;

    @FindBy(xpath = "//button[text()='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "(//input[@id='formControlInput1'])[1]")
    private WebElement firstName;

    @FindBy(xpath = "(//input[@id='formControlInput1'])[2]")
    private WebElement lastName;

    @FindBy(xpath = "(//select[@id='formSelectDisabled1'])[1]")
    private WebElement genderOptions;

    @FindBy(xpath = "(//select[@id='formSelectDisabled1'])[2]")
    private WebElement countryOptions;

    @FindBy(xpath = "(//select[@id='formSelectDisabled1'])[3]")
    private WebElement stateOptions;

    @FindBy(xpath = "(//select[@id='formSelectDisabled1'])[4]")
    private WebElement timeZoneOptions;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[text()='Personal information has been saved successfully.']")
    private WebElement successPopup;

    @FindBy(xpath = "//button[text()='Add Email Address']")
    private WebElement addEmailButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//input[@class='base-Input-input css-1h3rd57']")
    private List<WebElement> otpfields;

    @FindBy(xpath = "//button[text()='Verify']")
    private WebElement verifyButton;

    @FindBy(xpath = "//div[text()='New email address has been added successfully.']")
    private WebElement newEmailPopup;

    @FindBy(xpath = "//div[text()='Please Enter the Email Address']")
    private WebElement emptymailError;

    @FindBy(xpath = "//div[text()='Please Enter the Correct Email']")
    private WebElement invalidmailError;

    @FindBy(xpath = "(//button[@type='button'])[9]")
    private WebElement deleteIconButton;

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[text()='Your email address has been deleted successfully.']")
    private WebElement deletePopup;

    //Phone Number
    @FindBy(xpath = "//button[text()='Add Mobile Number']")
    private WebElement addMobileNumberButton;

    @FindBy(xpath = "//input[@value='+']")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveMobileButton;

    @FindBy(xpath = "//div[text()='Mobile Number Added Successfully']")
    private WebElement mobileSuccessPopup;

    @FindBy(xpath = "//div[text()='Enter the Phone Number']")
    private WebElement emptyNumberError;

    @FindBy(xpath = "//div[text()='Phone number cannot be more than 10 digits.']")
    private WebElement moreNumberError;

    @FindBy(xpath = "//div[text()='Your mobile number has been deleted successfully.']")
    private WebElement successPopup2;

    @FindBy(xpath = "//div[@class='mt-3 d-flex justify-content-between align-items-center extra-email-sec hover-row row']")
    private WebElement setAsPrimaryEmail;

    @FindBy(xpath = "//div//button[text()='Set as primary']")
    private WebElement setAsPrimaryButton;

    @FindBy(xpath = "//button[text()='Update']")
    private WebElement updateButton;

    @FindBy(name = "email")
    private WebElement loginEmailField;

    //Security
    @FindBy(xpath = "(//button[@class='accordion-button collapsed'])[1]")
    private WebElement securityButton;

    @FindBy(xpath = "//button[text()='Change Password']")
    private WebElement changePasswordButton;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    private WebElement currentPasswordField;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    private WebElement newPasswordField;

    @FindBy(xpath = "(//input[@type='password'])[3]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[text()='Update']")
    private WebElement passwordUpdateButton;

    @FindBy(xpath = "//div[text()='Double-check and ensure that both passwords are entered correctly']")
    private WebElement passwordErrorMessage;

    //Sessions
    @FindBy(xpath = "(//button[@class='accordion-button collapsed'])[3]")
    private WebElement sessionsButton;

    @FindBy(xpath = "(//div[@class='sec-active-sessions align-items-center row'])[2]")
    private WebElement session;

    @FindBy(xpath = "(//button[text()='Terminate'])[1]")
    private WebElement terminateButton;

    @FindBy(xpath = "(//button[text()='Terminate'])[last()]")
    private WebElement terminatePopupButton;

    @FindBy(xpath = "//div[text()='Active sessions has been terminated successfully']")
    private WebElement sessionSuccessPopup;

    @FindBy(xpath = "//button[text()='Terminate All']")
    private WebElement terminateAllButton;

    @FindBy(xpath = "(//button[text()='Terminate All'])[2]")
    private WebElement terminateAllPopupButton;

    //MFA
    @FindBy(xpath = "(//button[@class='accordion-button collapsed'])[2]")
    private WebElement mfaButton;

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    private WebElement enableToggleButton;

    @FindBy(xpath = "//button[text()='Enable']")
    private WebElement enableButton;

    @FindBy(xpath = "(//button[text()='Setup'])[2]")
    private WebElement emailSetupButton;

    @FindBy(xpath = "//div[text()='Updated successfully']")
    private WebElement updatePopup;

    @FindBy(xpath = "(//button[text()='Disable'])[1]")
    private WebElement disableButton;

    @FindBy(xpath = "(//button[text()='Disable'])[2]")
    private WebElement disablePopupButton;

    @FindBy(xpath = "//div[text()='Authentication disabled']")
    private WebElement mfaDisabledPopup;

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;


}