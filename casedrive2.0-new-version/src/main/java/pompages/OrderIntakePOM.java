package pompages;
import baseclass.LibGlobal;
import lombok.Getter;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Log
@Getter
public class OrderIntakePOM extends LibGlobal {
    public OrderIntakePOM() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1asfma']")
    private WebElement sideMenu;

    @FindBy(xpath = "//span[contains(text(),'Add Records')]")
    private WebElement addRecordsBtn;

    @FindBy(xpath = "//li[normalize-space()='New Records']")
    private WebElement newRecords;

    @FindBy(xpath = "//li[normalize-space()='Additional Records']")
    private WebElement additionalRecords;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstnameField;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastnameField;

    @FindBy(xpath = "//span[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiSwitch-switchBase') and contains(@class, 'MuiSwitch-colorPrimary') and contains(@class, 'PrivateSwitchBase-root')]")
    private WebElement expediteDisabled;

    @FindBy(xpath = "//span[@class='MuiButtonBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary Mui-checked PrivateSwitchBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary Mui-checked Mui-checked css-fizi60']")
    private WebElement expediteEnabled;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeMedium css-142cb9c']")
    private WebElement expectedDelivery;

    public void expectedDeliveryButton() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, expectedDelivery, retries, timeout);
    }

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart MuiIconButton-sizeMedium MuiPickersArrowSwitcher-button css-4be7ig']")
    private WebElement calenderNextBtn;

    public void calenderNxtRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, calenderNextBtn, retries, timeout);
    }

    @FindBy(xpath = "//button[normalize-space()='18']")
    private WebElement selectDate;

    public void selectDateRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, selectDate, retries, timeout);
    }

    @FindBy(xpath = "//input[@id='free-solo-2-demo']")
    private WebElement serviceSelector;

    public void retryServiceSelector(){
        int retries= 4;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, serviceSelector, retries, timeout);
    }

    @FindBy(xpath = "//div[@id='free-solo-2-demo-option-0'][@tabindex='-1']")
    private WebElement firstService;

    @FindBy(xpath = "//div[@id='free-solo-2-demo-option-1'][@tabindex='-1']")
    private WebElement secondService;

    @FindBy(xpath = "//div[@id='free-solo-2-demo-option-2'][@tabindex='-1']")
    private WebElement thirdService;

    @FindBy(xpath="//span[normalize-space()='Medical Record Retrieval']")
    private WebElement medicalRecordRetrievalService;

    public void medicalRecordRetrievalServiceRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, medicalRecordRetrievalService, retries, timeout);
    }

    @FindBy(xpath="//span[normalize-space()='Upload the scanned authorization form']")
    private WebElement offlineMethod;

    public void retryOfflineMethod() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, offlineMethod, retries, timeout);
    }

    @FindBy(xpath="//span[normalize-space()='Complete the authorization form online']")
    private WebElement onlineMethod;

    public void retryOnlineMethod() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, onlineMethod, retries, timeout);
    }

    @FindBy(xpath="//h3[normalize-space()='Download Authorization Form']")
    private WebElement downloadAuthForm;

    @FindBy(xpath="//li[normalize-space()='Download']")
    private WebElement downloadAuth;

    @FindBy(xpath="//li[normalize-space()='Send to patient']")
    private WebElement sendToPatient;

    @FindBy(xpath="//input[@name='authEmail']")
    private WebElement patientInput;

    @FindBy(xpath="//button[normalize-space()='Send']")
    private WebElement sendButton;

    @FindBy(xpath="//div[@class='MuiPickersCalendarHeader-label css-1v994a0']")
    private WebElement yearSelector;

    @FindBy(xpath="//button[normalize-space()='1998']")
    private WebElement birthYear;

    @FindBy(xpath="//input[@name='social_security_no']")
    private WebElement socialSecurityNumber;

    @FindBy(xpath="//input[@name='email_address']")
    private WebElement patientMail;

    @FindBy(xpath="//input[@name='primary_phone_no']")
    private WebElement patientPhone;

    @FindBy(xpath="//input[@name='address1']")
    private WebElement patientAddress;

    @FindBy(xpath="//input[@name='city']")
    private WebElement patientCity;

    @FindBy(xpath="//input[@name='zip_code']")
    private WebElement patientZipCode;

    @FindBy(xpath="//input[@name='state']")
    private WebElement patientState;

    public void fillProviderForms(int totalProviders, Map<String, String> testData) {
        for (int i = 0; i < totalProviders; i++) {
            int displayIndex = i + 1; // for 1-based numbering

            fillField(i, "first_name", testData.get("first_name") + displayIndex);
            fillField(i, "last_name", testData.get("last_name") + displayIndex);
            fillField(i, "phone_no", testData.get("phone_no")); // usually don't number these
            fillField(i, "fax_no", testData.get("fax_no"));
            fillField(i, "facility_name", testData.get("facility_name") + displayIndex);
            fillField(i, "address1", testData.get("address1") + " #" + displayIndex);
            fillField(i, "city", testData.get("city"));
            fillField(i, "state", testData.get("state"));
            fillField(i, "zip_code", testData.get("zip_code"));

            if (i < totalProviders - 1) {
                WebElement additionalDoctorInfo = driver.findElement(
                        By.xpath("//span[@class='MuiTypography-root MuiTypography-bold pointer-hand css-ken2s7']"));
                additionalDoctorInfo.click();

                WebElement openProviderForm = driver.findElement(
                        By.xpath(String.format("(//h5[normalize-space()='Provider/Doctor Name'])[%d]", i + 2)));
                openProviderForm.click();
            }
        }
    }
    private void fillField(int index, String fieldName, String value) {
        String xpath = String.format("//input[@name='providerInfo.[%d].%s']", index, fieldName);
        WebElement field = driver.findElement(By.xpath(xpath));
        field.clear();
        field.sendKeys(value);
    }

    public void selectDropdownByIndices(List<Integer> indices) {
        final int maxRetries = 3;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int index : indices) {
            boolean success = false;
            int attempts = 0;

            while (!success && attempts < maxRetries) {
                try {
                    String dropdownXpath = String.format("(//input[@id='free-solo-2-demo'])[%d]", index);
                    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXpath)));
                    dropdown.click();

                    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-option-index='2']")));
                    option.click();

                    success = true;

                } catch (Exception e) {
                    attempts++;
                    log.info(String.format("Attempt %d failed to select dropdown at index %d: %s", attempts, index, e.getMessage()));

                    if (attempts >= maxRetries) {
                        log.info("Max retries reached. Unable to select dropdown at index: " + index);
                    } else {
                        try {
                            String dropdownXpath = String.format("(//input[@id='free-solo-2-demo'])[%d]", index);
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropdownXpath)));
                        } catch (Exception waitException) {
                            log.info("Waiting for dropdown element before retrying failed: " + waitException.getMessage());
                        }
                    }
                }
            }
        }
    }
    @FindBy(xpath = "//button[@title='Previous month']//*[name()='svg']")
    private WebElement previousMonth;

    public void retryPreviousMonth()
    {int retries = 3;
    Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, previousMonth, retries, timeout);}



    public void handleDatePickersByIndices(List<Integer> indices) {
        for (int index : indices) {
            for (int attempt = 1; attempt <= 3; attempt++) {
                try {
                    String xpath = String.format(
                            "(//button[contains(@class, 'MuiIconButton-root') and contains(@class, 'css-142cb9c')])[%d]", index);

                    WebElement datePicker = new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

                    datePicker.click();

                    if (index % 2 != 0) {
                        retryPreviousMonth();
                    } else {
                        calenderNxtRetry();
                    }

                    selectDateRetry();
                    break; // Success, exit retry loop

                } catch (StaleElementReferenceException | ElementClickInterceptedException | NoSuchElementException e) {
                    log.info("Retry " + attempt + " failed for index " + index + ": " + e.getClass().getSimpleName());
                    if (attempt == 3) {
                        log.info("Max retries reached for index " + index);
                    }
                }
            }
        }
    }


    @FindBy(xpath = "//button[contains(text(),'Next')]")
    private WebElement nextButton;

    @FindBy(xpath = "//span[normalize-space()='Please enter your first name']")
    private WebElement firstnameErrorMess;

    @FindBy(xpath = "//span[normalize-space()='Please enter your last name']")
    private WebElement lastnameErrorMess;

    @FindBy(xpath = "//div[@class='MuiStack-root css-1tbd6bz']")
    private WebElement serviceSelectErrorMess;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container css-16xxdxa']//div[1]//div[1]//div[1]//div[1]//div[1]//button[1]//*[name()='svg']")
    private WebElement caseType;

    public void retryCaseTypeClick() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, caseType, retries, timeout);
    }

    // CaseType and CaseSubType Value

    @FindBy(xpath = "(//div[@class='MuiAutocomplete-option MuiBox-root css-0'][@tabindex='-1'])[1]")
    private WebElement dropDownValue;

    public void retryClickDropDownValue() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, dropDownValue, retries, timeout);
    }

    @FindBy(xpath = "//div[@class='MuiAutocomplete-root MuiAutocomplete-fullWidth MuiAutocomplete-hasPopupIcon css-1bjqpmb']//button[@title='Open']//*[name()='svg']")
    private WebElement caseSubType;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(xpath = "//div[contains(text(),'100')] ")
    private WebElement uploadSuccess100;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 MuiGrid-grid-lg-12 css-18nbvmr']")
    private List<WebElement> fileUploadConfirms;

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "//textarea[@name='case_overview']")
    private WebElement caseOverview;

    @FindBy(xpath = "//button[@type='button']//div[@class='d-flex align-items-center MuiBox-root css-0']//*[name()='svg']")
    private WebElement editButton;

    @FindBy(xpath = "//input[@name='first_name'][@id=':r4u:']")
    private WebElement confirmFirstName;

    @FindBy(xpath = "//input[@name='case_no']")
    private WebElement confirmPageCaseNumb;

    @FindBy(xpath = "//*[name()='path' and contains(@d,'M433.941 1')]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Confirm & Submit']")
    private WebElement confirmAndSubmit;

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmOrder;

    @FindBy(xpath = "//input[@class='PrivateSwitchBase-input MuiSwitch-input css-1m9pwf3']")
    private WebElement estimateToggle;

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 text-center pointer-hand css-1d7d46l']")
    private WebElement customLink;

    @FindBy(xpath = "//input[@name='download_link']")
    private WebElement downloadLink;

    @FindBy(xpath = "//input[@name='upload_link']")
    private WebElement uploadLink;

    @FindBy(xpath = "//ol[@dir='ltr']//li")
    private WebElement orderPlacedMsg;

    @FindBy (xpath = "//input[@placeholder='Search Case Name']")
    private WebElement searchCase;

    @FindBy (xpath = "//div[@tabindex='-1'] [@data-option-index='0']")
    private WebElement selectCase;

    @FindBy (xpath="//div[@tabindex='-1'][@data-tag-index='0']")
    private WebElement doesSelectedService1;

    @FindBy(xpath = "//div[@tabindex='-1'][@data-tag-index='1']")
    private WebElement doesSelectedService2;

    @FindBy(xpath="//div[@tabindex='-1'][@data-tag-index='2']")
    private WebElement doesSelectedService3;

    @FindBy (xpath="//tr[@data-index='0'] //td[@data-index='3']")
    private WebElement doesCaseNumber;

    @FindBy (xpath="//tr[@data-index='0'] //td[@data-index='2']")
    private WebElement doesRetrievalCaseNumber;

    @FindBy(xpath = "//span[contains(text(),'Cases')]")
    private WebElement caseList;

    @FindBy(xpath = "//li[normalize-space()='Review Cases']")
    private WebElement reviewCases;

    @FindBy (xpath = "//span[@class='MuiTypography-root MuiTypography-light css-h9oekb']")
    private WebElement caseOverError;

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1fgiayz']")
    private WebElement fileUploadError;
}