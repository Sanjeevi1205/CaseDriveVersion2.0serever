package stepdefinition;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mailtrap.MailTrapAPI;
import pompages.LoginPOM;
import userdata.TestUserData.UserType;

public class ForgotPassword extends LibGlobal {
    private LoginPOM loginPOM;

    // @PositiveScenario
    @Given("the client navigates to the Forgot Password page")
    public void the_client_navigates_to_the_forgot_password_page() {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        loginPOM = new LoginPOM();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
    }

    @When("the client click the forgot password link")
    public void the_client_click_the_forgot_password_link() {
        LoginPOM.scrollToHelpLink(driver, loginPOM.getScroll());
        loginPOM.getForgotPassword().click();
    }

    @When("the client enter the valid email credential")
    public void the_client_enter_the_valid_email_credential() {
        loginPOM.getUsername().sendKeys(UserType.VALID_USER.getUsername());
        loginPOM.getSendOTP().click();
    }

    @When("the client enter the OTP get from the email")
    public void the_client_enter_the_otp_get_from_the_email() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 4000) {
                // Wait for 4 seconds
            }
            return true;
        });
        String otp = MailTrapAPI.fetchOTPWithRetry("Password Reset Request");
        System.out.println("Fetched OTP: " + 30);
        WebDriverWait waitEmail = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> otpFields = wait
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
        loginPOM.getConfirmButton().click();
    }

    @When("the client enter the New and Confirm Password to continue")
    public void the_client_enter_the_new_and_confirm_password_to_continue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getNewPasswordInput()));
        loginPOM.getNewPasswordInput().sendKeys(UserType.VALID_USER.getPassword());
        loginPOM.getConfirmPasswordInput().sendKeys(UserType.VALID_USER.getPassword());
        loginPOM.scrollToHelpLink(driver, loginPOM.getScroll());
        loginPOM.getChangePasswordButton().click();
    }

    @Then("a popup with the message Your password has been changed successfully is displayed.")
    public void a_popup_with_the_message_your_password_has_been_changed_successfully_is_displayed() {
        WebDriverWait popupwait = new WebDriverWait(driver, Duration.ofSeconds(2));
        popupwait.until(ExpectedConditions.elementToBeClickable(loginPOM.getPassword()));
        boolean popupmessage = loginPOM.getPassword().getText()
                .contains("You have changed your password Successfully!!");
        System.out.println(popupmessage);
        LibGlobal.quitDriver();
    }

    // @NegativeScenario1
    @When("enter the Invalid email credential")
    public void enter_the_invalid_email_credential() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(UserType.INVALID_USER.getUsername());
        loginPOM.getSendOTP().click();
    }

    @Then("an error popup emailexist the client of an invalid email.")
    public void an_error_popup_emailexist_the_client_of_an_invalid_email() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getEmaildoesnotexist()));
        boolean emailexist = loginPOM.getEmaildoesnotexist().getText().contains("Email does not exist.");
        LibGlobal.quitDriver();
    }

    // @NegativeScenario2
    @When("enter the non-existing email credential")
    public void enter_the_non_existing_email_credential() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(UserType.NON_EXISTING_USER.getUsername());
        loginPOM.getSendOTP().click();
    }

    @Then("an error popup notifies Your email address already exists is displayed.")
    public void an_error_popup_notifies_your_email_address_already_exists_is_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getNonexitingclient()));
        boolean popup = loginPOM.getNonexitingclient().getText()
                .contains("The email address  you entered does not exist. please enter valid one");
        LibGlobal.quitDriver();
    }

    // @NegativeScenario3
    @When("enters a non-converted email credential")
    public void enters_a_non_converted_email_credential() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(UserType.NON_CONVERTED_LEAD.getUsername());
        loginPOM.getSendOTP().click();
    }

    @When("the client attempts to log in with the non-converted email credential")
    public void the_client_attempts_to_log_in_with_the_non_converted_email_credential() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(UserType.NON_CONVERTED_LEAD.getUsername());
        loginPOM.getPassword().sendKeys(UserType.NON_CONVERTED_LEAD.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("a popup with the message Your email address  exists is displayed.")
    public void a_popup_with_the_message_your_email_address_exists_is_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getNonConvertedLead()));
        boolean popup = loginPOM.getNonConvertedLead().getText()
                .contains("Your account is being verified. You will receive an email with further details shortly.");
        LibGlobal.quitDriver();
    }

    // @NegativeScenario4
    @When("enters a invalid internal user email credential")
    public void enters_a_invalid_internal_user_email_credential() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(UserType.INVALID_INTERNAL_USER.getUsername());
        loginPOM.getSendOTP().click();
    }

    @When("the client attempts to log in with the invalid InternalUser email credential")
    public void the_client_attempts_to_log_in_with_the_invalid_InternalUser_email_credential() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(UserType.INVALID_INTERNAL_USER.getUsername());
        loginPOM.getPassword().sendKeys(UserType.INVALID_INTERNAL_USER.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("an error popup notifies Your account is currently inactive is displayed.")
    public void an_error_popup_notifies_your_account_is_currently_inactive_is_displayed() {
        WebDriverWait popupwait = new WebDriverWait(driver, Duration.ofSeconds(8));
        popupwait.until(ExpectedConditions.elementToBeClickable(loginPOM.getInvaildInternalUser()));
        boolean popupmessage = loginPOM.getInvaildInternalUser().getText().contains(
                "Your account is currently inactive or removed. Please contact our support team for assistance.");
        System.out.println(popupmessage);
        LibGlobal.quitDriver();
    }

    //ResendOTP
    @When("the client click the Resend OTP button")
    public void the_client_click_the_resend_otp_button() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(35));
        wait1.until(ExpectedConditions.elementToBeClickable(loginPOM.getResendButton()));
        loginPOM.getResendButton().click();
    }
    @When("the client enter the Resend OTP and click next button")
    public void the_client_enter_the_resend_otp_and_click_next_button() throws Exception {
        Thread.sleep(4000);
        String otp = MailTrapAPI.fetchresendOTP("Password Reset Request");
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
        loginPOM.scrollToHelpLink(driver, loginPOM.getScroll());
        loginPOM.getConfirmButton().click();
    }
}