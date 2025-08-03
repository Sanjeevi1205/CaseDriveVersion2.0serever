package pompages;

import baseclass.LibGlobal;
import lombok.Getter;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;


@Getter
@Log
public class AdminSettingsPOM extends LibGlobal {

    public AdminSettingsPOM() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/admin/setting/personal-details']//*[name()='svg']")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium py-0 css-t66k8']//*[name()='svg']//*[name()='path' and contains(@d,'M3 4H21V6H')]")
    private WebElement settingsSideMenu;

    @FindBy(xpath = "//span[contains(text(),'User Management')]")
    private WebElement userManagement;

    @FindBy(xpath = "//span[contains(text(),'Employees')]")
    private WebElement employeesMenu;

    @FindBy(xpath = "//button[normalize-space()='Add Employee']")
    private WebElement addEmployeeButton;


    public void addEmployeeButtonRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, addEmployeeButton, retries, timeout);
    }

    @FindBy(xpath = "(//button[normalize-space()='Save'])[1]")
    private WebElement saveButton;

    @FindBy(xpath = "//span[normalize-space()='Please enter the email address']")
    private WebElement validationText1;

    @FindBy(xpath = "//span[normalize-space()='Please Select the employee Id']")
    private WebElement validationText2;

    @FindBy(xpath = "//span[normalize-space()='Please enter the first name']")
    private WebElement validationText3;

    @FindBy(xpath = "//span[normalize-space()='Please enter the second name']")
    private WebElement validationText4;

    @FindBy(xpath = "//p[normalize-space()='Please select the Designation']")
    private WebElement validationText5;

    @FindBy(xpath = "//p[normalize-space()='Please select the Employee Role']")
    private WebElement validationText6;

    @FindBy(xpath = "//p[normalize-space()='Please enter the work location']")
    private WebElement validationText7;

    @FindBy(xpath = "//p[normalize-space()='Please select the profile']")
    private WebElement validationText8;

    public List<String> getValidationMessages() {
        return Arrays.asList(
                validationText1.getText(),
                validationText2.getText(),
                validationText3.getText(),
                validationText4.getText(),
                validationText5.getText(),
                validationText6.getText(),
                validationText7.getText(),
                validationText8.getText()
        );
    }

    @FindBy(xpath = "//span[normalize-space()='View']")
    private WebElement viewButton;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement mailInput;

    @FindBy(xpath = "//input[@name='emp_id']")
    private WebElement employeeId;

    @FindBy(xpath = "//li[@tabindex='0']")
    private WebElement dropDownValue;

    @FindBy(xpath = "//li[@data-value='13']")
    private WebElement departmentValue;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[2]")
    private WebElement departmentID;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[3]")
    private WebElement designationID;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[4]")
    private WebElement locationID;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[5]")
    private WebElement profileID;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[6]")
    private WebElement roleID;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[7]")
    private WebElement reportTo;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[8]")
    private WebElement approveTo;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[9]")
    private WebElement employeeCategoryID;

    @FindBy(xpath = "//span[contains(text(),'Teams')]")
    private WebElement teamsMenu;

    @FindBy(xpath = "//button[normalize-space()='+ Create Team']")
    private WebElement createTeamButton;

    @FindBy(xpath = "(//div[contains(@class, '-control')])[1]")
    private WebElement teamLeadDropDown;

    @FindBy(xpath = "(//input[contains(@class, 'MuiOutlinedInput-input')])[2]")
    private WebElement teamNameInput;

    @FindBy(xpath = "(//div[contains(@class, '-control')])[1]")
    private WebElement teamsTeamLeadDropdown;

    @FindBy(xpath = "(//div[@tabindex='-1'])[last()]")
    private WebElement latestTeamLeadDropDownValue;

    @FindBy(xpath = "(//div[contains(@class, '-control')])[2]")
    private WebElement employeeDropdowns;

    @FindBy(xpath = "//div[@tabindex='-1']")
    private List<WebElement> employeeLeadDropDownValues;

    @FindBy(xpath = "//textarea[@placeholder=' ']")
    private WebElement descriptionInput;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Edit']")
    private WebElement editButton;

    @FindBy(xpath = "(//button[normalize-space(text()) = 'Update'])[1]")
    private WebElement updateButton;

    @FindBy(xpath = "//span[contains(text(),'General')]")
    private WebElement generalSettings;

    @FindBy(xpath = "//span[contains(text(),'Companies')]")
    private WebElement companiesMenu;

    @FindBy(xpath = "//button[normalize-space()='+ Add company']")
    private WebElement addCompanyButton;

    @FindBy(xpath = "//span[normalize-space()='Please enter the Company Name']")
    private WebElement companyValidationErrorMessage1;

    @FindBy(xpath = "//span[normalize-space()='Please enter the email']")
    private WebElement companyValidationErrorMessage2;

    @FindBy(xpath = "//span[normalize-space()='Phone Number is required']")
    private WebElement companyValidationErrorMessage3;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement companyName;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneNumber;

    @FindBy(xpath = "(//div[contains(@class, 'MuiInputBase-root') and contains(@class, 'MuiAutocomplete-inputRoot')])[1]")
    private WebElement timeZone;

    @FindBy(xpath = "//div[@data-option-index='0']")
    private WebElement timeZoneDropDownValue;

    @FindBy(xpath = "//input[@name='bank_name']")
    private WebElement bankName;

    @FindBy(xpath = "//input[@name='acc_num']")
    private WebElement accountNumber;

    @FindBy(xpath = "//input[@name='acc_name']")
    private WebElement accountName;

    @FindBy(xpath = "(//div[contains(@class, 'MuiInputBase-root') and contains(@class, 'MuiAutocomplete-inputRoot')])[2]")
    private WebElement countryDropDown;

    @FindBy(xpath = "//span[normalize-space()='USA']")
    private WebElement usaValue;

    @FindBy(xpath = "//input[@name='zipcode']")
    private WebElement zipcodeInput;

    @FindBy(xpath = "//input[@name='street']")
    private WebElement streetInput;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityInput;

    @FindBy(xpath = "//input[@name='state']")
    private WebElement stateInput;

    @FindBy(xpath = "//input[@name='address']")
    private WebElement addressInput;

    @FindBy(xpath = "//ol[@dir='ltr']//li")
    private WebElement successMessage;

    @FindBy(xpath = "//tr[@data-index='0']//td[@data-index='7']//button")
    private WebElement employeeLatestThreeDot;

    @FindBy(xpath = "//span[normalize-space()='Send Invite Link']")
    private WebElement sendInvitationLink;

    @FindBy(xpath = "//button[normalize-space()='Invite']")
    private WebElement confirmsInviteButton;

    public void retryConfirmInviteButton() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, confirmsInviteButton, retries, timeout);
    }

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//span[normalize-space()='Delete']")
    private WebElement deleteButton;

    public List<String> getCompanyValidationMessages() {
        return Arrays.asList(
                companyValidationErrorMessage1.getText(),
                companyValidationErrorMessage2.getText(),
                companyValidationErrorMessage3.getText()
        );
    }

    public void selectUpToFiveEmployees() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);
        for (int i = 0; i < 5; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(employeeDropdowns)).click();
            actions.sendKeys(Keys.ENTER).perform();
            log.info("Selected employee " + (i + 1));
        }
    }

    @FindBy(xpath = "//span[contains(text(),'Tasks')]")
    private WebElement tasksMenu;

    @FindBy(xpath = "//span[contains(text(),'Task Template')]")
    private WebElement taskTemplateMenu;

    @FindBy(xpath = "//button[normalize-space()='+ New Task Template']")
    private WebElement newTasKTemplateButton;

    @FindBy(xpath = "(//div[contains(@class, '-control')]//div[contains(@class, 'css-19bb58m')])[1]")
    private WebElement taskDropDown;

    @FindBy(xpath = "//div[text()='Record Review']")
    private WebElement recordReviewDropDownValue;

    @FindBy(xpath = "(//div[contains(@class, '-control')]//div[contains(@class, 'css-19bb58m')])[2]")
    private WebElement serviceDropDown;

    @FindBy(xpath = "(//div[contains(text(),'Medical Chronology')])[1]")
    private WebElement medicalChronologyValue;

    @FindBy(xpath="//button[normalize-space()='Save Task Template']")
    private WebElement saveTaskTemplateButton;

    @FindBy(xpath = "(//div[contains(@class, '-control')]//div[contains(@class, 'css-19bb58m')])[3]")
    private WebElement serviceDropDownBill;

    @FindBy(xpath = "(//div[contains(@class, '-control')]//div[contains(@class, 'css-19bb58m')])[4]")
    private WebElement clientNameDropDown;

    @FindBy(xpath = "//div[@id='react-select-61-option-3']")
    private WebElement clientDropdownValue;

    @FindBy(xpath="(//input[@class='MuiTypography-root MuiTypography-body1 did-floating-input css-1u0otzy'])[1]")
    private WebElement templateName;

    @FindBy(xpath="(//input[@class='MuiTypography-root MuiTypography-body1 did-floating-input css-1u0otzy'])[2]")
    private WebElement templateDescription;








}





