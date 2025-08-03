package stepdefinition;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import mailtrap.MailTrapAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseclass.LibGlobal;
import exceldata.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jxl.read.biff.BiffException;
import pompages.SignupPOM;
import userdata.TestUserData;
import userdata.TestUserData.UserType;
import utils.RandomEmail;

@Slf4j
public class Signup extends LibGlobal {
    private SignupPOM signupPOM;
    private SignupExcelReader signupexcelreader;
    private List<SignupExcelTestData> signuplist;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Given("the lead navigate to the casedrive URL")
    public void the_lead_navigate_to_the_casedrive_url() throws AWTException, InterruptedException {
        log.info("Navigating to CaseDrive URL...");
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        signupPOM = new SignupPOM();
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getCreatemenu()));
    }

    @When("the lead click the create button to signup")
    public void the_lead_click_the_create_button_to_signup() {
        signupPOM.getCreatemenu().click();
    }

    @When("the lead enter the email address and click next button")
    public void the_lead_enter_the_email_address_and_click_next_button() throws InterruptedException {
        signupPOM.getClientemail().sendKeys(RandomEmail.generateSignupEmail());
        signupPOM.getAgreeCheckbox().click();
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getCaptchacheckBox())).click();
        driver.switchTo().defaultContent();
        Thread.sleep(12000);
        signupPOM.getNextbutton().click();
    }

    @When("the lead enter the OTP and click next button")
    public void the_lead_enter_the_otp_and_click_next_button() {
        // Retrieve OTP from the enum
        String otp = TestUserData.UserType.SIGNUP_USER.getOTP();

        // Check if OTP is valid
        if (otp != null && !otp.isEmpty()) {
            System.out.println("Retrieved OTP: " + otp);

            // Enter OTP into the fields
            signupPOM.enterOtp(otp);
        } else {
            throw new IllegalArgumentException("OTP is null or empty for SIGNUP_USER.");
        }
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        signupPOM.getNextbtn1().click();
    }

    @When("the lead enter the password and click next button")
    public void the_lead_enter_the_password_and_click_next_button() {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getPassword()));
        signupPOM.getPassword().sendKeys(UserType.SIGNUP_USER.getPassword());
        signupPOM.getRepassword().sendKeys(UserType.SIGNUP_USER.getConfirmPassword());
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        signupPOM.getNextbtn2().click();
    }

    @When("the lead enter the contact information about him and click next button")
    public void the_lead_enter_the_contact_information_about_him_and_click_next_button() {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getFirstname()));
        signupPOM.getFirstname().sendKeys(RandomEmail.generateFirstName());
        signupPOM.getLastname().sendKeys(RandomEmail.generateLastName());
        signupPOM.getPracticearea().sendKeys(UserType.SIGNUP_USER.getPracticeArea());
        signupPOM.getMobilenumber().sendKeys(UserType.SIGNUP_USER.getMobileNumber());
        signupPOM.getNextbtn4().click();
    }

    @When("the lead enter the company information and click next button")
    public void the_lead_enter_the_company_information_and_click_next_button() {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getCompanyname()));
        signupPOM.getCompanyname().sendKeys(UserType.SIGNUP_USER.getCompanyName());
        signupPOM.getCompanysize().sendKeys(UserType.SIGNUP_USER.getCompanySize());
        signupPOM.getPhonenumber().sendKeys(UserType.SIGNUP_USER.getPhoneNumber());
        signupPOM.getExtension().sendKeys(UserType.SIGNUP_USER.getExtension());
        signupPOM.getAddress().sendKeys(UserType.SIGNUP_USER.getAddress());
        signupPOM.getCountry().sendKeys(UserType.SIGNUP_USER.getCountry());
        signupPOM.getState().sendKeys(UserType.SIGNUP_USER.getState());
        signupPOM.getCity().sendKeys(UserType.SIGNUP_USER.getCity());
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        signupPOM.getZipcode().sendKeys(UserType.SIGNUP_USER.getZipcode());
        signupPOM.getBillingaddresstogglebutton().click();
        signupPOM.getBillingaddress().sendKeys(UserType.SIGNUP_USER.getCompanyAddress());
        signupPOM.getBillingcountry().sendKeys(UserType.SIGNUP_USER.getCompanyCountry());
        signupPOM.getBillingstate().sendKeys(UserType.SIGNUP_USER.getCompanyState());
        signupPOM.getBillingcity().sendKeys(UserType.SIGNUP_USER.getCompanyCity());
        signupPOM.getBillingzipcode().sendKeys(UserType.SIGNUP_USER.getCompanyZipcode());
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        signupPOM.getNextbtn5().click();
    }

    @When("the lead select the services and click next button")
    public void the_lead_select_the_services_and_click_next_button() {
        wait.until(ExpectedConditions.visibilityOfAllElements(signupPOM.getServices()));
        signupPOM.selectServices(UserType.SIGNUP_USER);
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        signupPOM.getNextbtn8().click();
    }

    @When("the lead select the template for the services and click next button")
    public void the_lead_select_the_template_for_the_services_and_click_next_button() {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getChooseFileButton()));
        signupPOM.getTemplate1().click();
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        signupPOM.getTemplate2().click();
        signupPOM.getNextbtn9().click();
    }

    @Then("the lead navigate to the Thankyou page for the successfull signup message")
    public void the_lead_navigate_to_the_thankyou_page_for_the_successfull_signup_message() {
        wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getDoneButton()));
        boolean text = signupPOM.getConfirmationPage().getText().contains("Thank you");
        System.out.println(text);
        signupPOM.getDoneButton().click();
        LibGlobal.quitDriver();
    }

    // Negative
    @When("the lead enter the invalid email address and click next button")
    public void the_lead_enter_the_invalid_email_address_and_click_next_button() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getClientemail()));
        signupPOM.getClientemail().sendKeys(UserType.INVALID_USER.getUsername());
        signupPOM.getAgreeCheckbox().click();
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getCaptchacheckBox())).click();
        driver.switchTo().defaultContent();
        Thread.sleep(12000);
        signupPOM.getNextbutton().click();
    }

    @Then("the lead see the error Please enter a valid email address")
    public void the_lead_see_the_error_please_enter_a_valid_email_address() {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getErrorMessage()));
        boolean errormessage = signupPOM.getErrorMessage().getText().contains("enter a valid email");
        System.out.println(errormessage);
        LibGlobal.quitDriver();
    }

    // Negative
    @When("the lead enter the non-converted email address and click next button")
    public void the_lead_enter_the_non_converted_email_address_and_click_next_button() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getClientemail()));
        signupPOM.getClientemail().sendKeys(UserType.NON_CONVERTED_LEAD.getUsername());
        signupPOM.getAgreeCheckbox().click();
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getCaptchacheckBox())).click();
        driver.switchTo().defaultContent();
        Thread.sleep(12000);
        signupPOM.getNextbutton().click();
    }

    @Then("a popup with the message Email Address Already Exist is displayed.")
    public void a_popup_with_the_message_email_address_already_exist_is_displayed() {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getNonConvertedLeadPopupMessage()));
        boolean errormessage = signupPOM.getNonConvertedLeadPopupMessage().getText()
                .contains("Email Address Already Exists.");
        System.out.println(errormessage);
        LibGlobal.quitDriver();
    }

    // Negative
    @Given("the user navigate to the casedrive URL")
    public void the_user_navigate_to_the_casedrive_url() {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        signupPOM = new SignupPOM();
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getCreatemenu()));
    }

    @When("the user click the create button to signup")
    public void the_user_click_the_create_button_to_signup() {
        signupPOM.getCreatemenu().click();
    }

    @When("the user enter the inactive employee email address and click next button")
    public void the_user_enter_the_inactive_employee_email_address_and_click_next_button() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getClientemail()));
        signupPOM.getClientemail().sendKeys(UserType.INVALID_CLIENT.getUsername());
        signupPOM.getAgreeCheckbox().click();
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getCaptchacheckBox())).click();
        driver.switchTo().defaultContent();
        Thread.sleep(12000);
        signupPOM.getNextbutton().click();
    }

    // Negative
    @Given("the inactive client navigate to the casedrive URL")
    public void the_inactive_client_navigate_to_the_casedrive_url() {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        signupPOM = new SignupPOM();
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getCreatemenu()));
    }

    @When("the client click the create button to signup")
    public void the_client_click_the_create_button_to_signup() {
        signupPOM.getCreatemenu().click();
    }

    @When("the client enter the inactive client email address and click next button")
    public void the_client_enter_the_inactive_client_email_address_and_click_next_button() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getClientemail()));
        signupPOM.getClientemail().sendKeys(UserType.INVALID_INTERNAL_USER.getUsername());
        signupPOM.getAgreeCheckbox().click();
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getCaptchacheckBox())).click();
        driver.switchTo().defaultContent();
        Thread.sleep(12000);
        signupPOM.getNextbutton().click();
    }

    // Onboarding Continue
    @When("the lead enter the onboarding email address and click next button")
    public void the_lead_enter_the_onboarding_email_address_and_click_next_button() throws BiffException, IOException, InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getClientemail()));
        signupexcelreader = new SignupExcelReader("src\\test\\resources\\Excel\\TestDataForCaseDrive.xls");
        signuplist = signupexcelreader.getsignupData("Signup");
        for (SignupExcelTestData data : signuplist) {
            // Setup a new browser instance for each user
            if (data.getOnboardEmail() == null || data.getOnboardEmail().isEmpty()) {
                continue; // Skip to the next iteration
            }
            signupPOM.getClientemail().sendKeys(data.getOnboardEmail());
            signupPOM.getAgreeCheckbox().click();
            signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
            wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getCaptchacheckBox())).click();
            driver.switchTo().defaultContent();
            Thread.sleep(12000);
            signupPOM.getNextbutton().click();
        }
    }

    @When("the lead select the template from the file explorer and click next button")
    public void the_lead_select_the_template_from_the_file_explorer_and_click_next_button() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(signupPOM.getChooseFileButton()));
        signupPOM.getChooseFileButton().click();
        ClassLoader classLoader = getClass().getClassLoader();
        String encodedFilePath = classLoader.getResource("Test File Upload/SamplePPTFile_500kb.ppt").getFile();
        val decodedPath = URLDecoder.decode(encodedFilePath, StandardCharsets.UTF_8);
        String filePath = decodedPath.startsWith("/") && decodedPath.contains(":")
                ? decodedPath.substring(1).replace("/", "\\")
                : decodedPath.replace("/", "\\");
        System.out.println("Decoded File Path: " + filePath);
        signupPOM.uploadFile(filePath);
        wait.until(ExpectedConditions.elementToBeClickable(signupPOM.getFileUploadNextButton()));
        signupPOM.getFileUploadNextButton().click();
    }

    //ResendOTP
    @When("the lead click the Resend OTP button")
    public void the_lead_click_the_resend_otp_button() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(35));
        wait1.until(ExpectedConditions.elementToBeClickable(signupPOM.getResendButton()));
        signupPOM.getResendButton().click();
    }
    @When("the lead enter the Resend OTP and click next button")
    public void the_lead_enter_the_resend_otp_and_click_next_button() throws Exception {
        Thread.sleep(4000);
        String otp = MailTrapAPI.fetchResendOTP("CaseDrive Registration OTP");
        System.out.println("Fetched OTP: " + otp);
        WebDriverWait waitEmail = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> otpFields = waitEmail
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@type='tel']")));
        if (otpFields.isEmpty()) {
            throw new Exception("OTP input fields are not found on the page.");
        }
        if (otpFields.size() > 1) {
            for (int i = 0; i < otp.length(); i++) {
                otpFields.get(i).sendKeys(Character.toString(otp.charAt(i)));
            }
        } else {
            WebElement otpInput = otpFields.get(0);
            otpInput.sendKeys(otp);
        }
        signupPOM.scrollToHelpLink(driver, signupPOM.getScroll());
        signupPOM.getNextbtn1().click();
    }
}