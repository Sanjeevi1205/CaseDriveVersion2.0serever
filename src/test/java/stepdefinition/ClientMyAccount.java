package stepdefinition;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mailtrap.MailTrapAPI;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.ClientMyAccountPOM;
import pompages.LoginPOM;
import pompages.OrderIntakePOM;
import userdata.TestUserData;
import utils.RandomEmail;
import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ClientMyAccount extends LibGlobal {
    public ClientMyAccountPOM clientMyAccount = new ClientMyAccountPOM();
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    public RandomEmail randomEmail = new RandomEmail();
    public OrderIntakePOM orderIntakePOM;
    public LoginPOM loginPOM;

    @When("the user clicks on the profile and selects the My Account option")
    public void the_user_clicks_on_the_profile_and_selects_the_my_account_option() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getProfile()));
        clientMyAccount.getProfile().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getMyaccountOption()));
        clientMyAccount.getMyaccountOption().click();
    }

    @When("the user navigates to a new tab")
    public void the_user_navigates_to_a_new_tab() {
        String mainWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        System.out.println("New Window Title: " + driver.getTitle());
    }

    @When("the user clicks the Edit button")
    public void the_user_clicks_the_edit_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getEditButton()));
        clientMyAccount.getEditButton().click();
    }

    @When("the user modifies their profile details")
    public void the_user_modifies_their_profile_details() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getFirstName()));
        clientMyAccount.getFirstName().clear();
        clientMyAccount.getFirstName().sendKeys(RandomEmail.generateFirstName());
        clientMyAccount.getLastName().clear();
        clientMyAccount.getLastName().sendKeys(RandomEmail.generateLastName());
        clientMyAccount.getGenderOptions().sendKeys("Male");
        clientMyAccount.getStateOptions().sendKeys("Colorado");
        LibGlobal.selectoption(clientMyAccount.getTimeZoneOptions());
    }

    @When("the user clicks the Save button")
    public void the_user_clicks_the_save_button() {
        clientMyAccount.getSaveButton().click();
    }

    @Then("the changes should be reflected on the account page")
    public void the_changes_should_be_reflected_on_the_account_page() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getSuccessPopup()));
        System.out.print(clientMyAccount.getSuccessPopup().getText());
        Assert.assertTrue("Personal information has been saved successfully.", true);
        LibGlobal.quitDriver();
    }

    //Secondary Email
    @When("the user clicks the Add Email button")
    public void the_user_clicks_the_add_email_button() throws AWTException {
        LibGlobal.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getAddEmailButton()));
        clientMyAccount.getAddEmailButton().click();
    }

    @When("the user enters their email details")
    public void the_user_enters_their_email_details() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getEmailField()));
        clientMyAccount.getEmailField().sendKeys(RandomEmail.generatePaymentEmail());
    }

    @When("the user clicks the Add button")
    public void the_user_clicks_the_add_button() {
        clientMyAccount.getAddButton().click();
    }

    @When("the user enters the OTP")
    public void the_user_enters_the_otp() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 4000) {
                // Wait for 4 seconds
            }
            return true;
        });
        String otp = MailTrapAPI.fetchSecondaryMailOTP("CaseDrive Secondary Email Verification");
        System.out.println("Fetched OTP: " + 30);
        WebDriverWait waitEmail = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> otpFields = wait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@class='base-Input-input css-1h3rd57']")));
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
    }

    @When("the user clicks the Verify button")
    public void the_user_clicks_the_verify_button() {
        clientMyAccount.getVerifyButton().click();
    }

    @Then("the user is notified with the popup message New email address has been added successfully.")
    public void the_user_is_notified_with_the_popup_message_new_email_address_has_been_added_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getNewEmailPopup()));
        System.out.print(clientMyAccount.getNewEmailPopup().getText());
        Assert.assertTrue("New email address has been added successfully.", true);
        LibGlobal.quitDriver();
    }

    @Then("the user is notified with an error message Please Enter the Email Address")
    public void the_user_is_notified_with_an_error_message_please_enter_the_email_address() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getEmptymailError()));
        System.out.println(clientMyAccount.getEmptymailError().getText());
        Assert.assertTrue("Please Enter the Email Address", true);
        LibGlobal.quitDriver();
    }

    //Invalid Email
    @When("the user enters their invalid email details")
    public void the_user_enters_their_invalid_email_details() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getEmailField()));
        clientMyAccount.getEmailField().sendKeys(RandomEmail.generateInvalidEmail());
    }

    @Then("the user is notified with an error message Please Enter the Correct Email")
    public void the_user_is_notified_with_an_error_message_please_enter_the_correct_email() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getInvalidmailError()));
        System.out.println(clientMyAccount.getInvalidmailError().getText());
        Assert.assertTrue("Please Enter the Correct Email", true);
        LibGlobal.quitDriver();
    }

    //Delete Email
    @When("the user clicks the delete button and confirm the action")
    public void the_user_clicks_the_delete_button_and_confirm_the_action() throws AWTException {
        LibGlobal.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getAddEmailButton()));
        LibGlobal.mouseHover(clientMyAccount.getDeleteIconButton());
        clientMyAccount.getDeleteIconButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getDeleteButton()));
        clientMyAccount.getDeleteButton().click();
    }

    @Then("the user is notified with the popup message Your email address has been deleted successfully.")
    public void the_user_is_notified_with_the_popup_message_your_email_address_has_been_deleted_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getDeletePopup()));
        System.out.println(clientMyAccount.getDeletePopup().getText());
        Assert.assertTrue("Your email address has been deleted successfully.", true);
        LibGlobal.quitDriver();
    }

    //Add Phone Number
    @When("the user clicks the Add mobile number button")
    public void the_user_clicks_the_add_mobile_number_button() throws AWTException {
        LibGlobal.scrolldown();
        LibGlobal.scrolldown();
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getAddMobileNumberButton()));
        clientMyAccount.getAddMobileNumberButton().click();
    }

    @When("the user enter the phone number and clicks save button")
    public void the_user_enter_the_phone_number_and_clicks_save_button() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getMobileNumberField()));
        clientMyAccount.getMobileNumberField().sendKeys(RandomEmail.generateNumericPhoneNumber());
        clientMyAccount.getSaveMobileButton().click();
    }

    @Then("the user is notified with the popup message Mobile Number Added Successfully.")
    public void the_user_is_notified_with_the_popup_message_mobile_number_added_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getMobileSuccessPopup()));
        System.out.println(clientMyAccount.getMobileSuccessPopup().getText());
        Assert.assertTrue("Mobile Number Added Successfully", true);
        LibGlobal.quitDriver();
    }

    //Empty Phone Number
    @When("the clicks save button")
    public void the_clicks_save_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getSaveMobileButton()));
        clientMyAccount.getSaveMobileButton().click();
    }

    @Then("the user is notified with an error message Enter the Phone Number")
    public void the_user_is_notified_with_an_error_message_enter_the_phone_number() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getEmptyNumberError()));
        System.out.println(clientMyAccount.getEmptyNumberError().getText());
        Assert.assertTrue("Enter the Phone Number", true);
    }

    @When("the user enter 10digit phone number and clicks save button")
    public void the_user_enter_10digit_phone_number_and_clicks_save_button() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getMobileNumberField()));
        clientMyAccount.getMobileNumberField().sendKeys(RandomEmail.generateInvalidPhoneNumber());
        clientMyAccount.getSaveMobileButton().click();
    }

    @Then("the user is notified with an error message Phone number cannot be more than {int} digits.")
    public void the_user_is_notified_with_an_error_message_phone_number_cannot_be_more_than_digits(Integer int1) {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getMoreNumberError()));
        System.out.println(clientMyAccount.getMoreNumberError().getText());
        Assert.assertTrue("Phone number cannot be more than 10 digits.", true);
        LibGlobal.quitDriver();
    }

    @Then("the user is notified with the popup message Your mobile number has been deleted successfully.")
    public void the_user_is_notified_with_the_popup_message_your_mobile_number_has_been_deleted_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getSuccessPopup2()));
        System.out.println(clientMyAccount.getSuccessPopup2().getText());
        Assert.assertTrue("Your mobile number has been deleted successfully.", true);
        LibGlobal.quitDriver();
    }

    //Set Primary
    @When("the user clicks the set primary button and confirm the action")
    public void the_user_clicks_the_set_primary_button_and_confirm_the_action() throws AWTException {
        LibGlobal.scrolldown();
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getSetAsPrimaryEmail()));
        LibGlobal.mouseHover(clientMyAccount.getSetAsPrimaryEmail());
        clientMyAccount.getSetAsPrimaryButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getUpdateButton()));
        clientMyAccount.getUpdateButton().click();
    }

    @Then("the user logs out of the current system")
    public void the_user_logs_out_of_the_current_system() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getLoginEmailField()));
        boolean url = driver.getCurrentUrl().contains("accounts.lezdotechmed.com");
        Assert.assertTrue(url);
        LibGlobal.quitDriver();
    }

    //Security Scenarios
    @When("the user clicks the security sidemenu")
    public void the_user_clicks_the_security_sidemenu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getSecurityButton()));
        clientMyAccount.getSecurityButton().click();
    }

    @When("the user clicks the change password button")
    public void the_user_clicks_the_change_password_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getChangePasswordButton()));
        clientMyAccount.getChangePasswordButton().click();
    }

    @When("the user enter the current and new password")
    public void the_user_enter_the_current_and_new_password() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getCurrentPasswordField()));
        clientMyAccount.getCurrentPasswordField().sendKeys(TestUserData.UserType.SIGNUP_USER.password);
        clientMyAccount.getNewPasswordField().sendKeys(TestUserData.UserType.SIGNUP_USER.password);
        clientMyAccount.getConfirmPasswordField().sendKeys(TestUserData.UserType.SIGNUP_USER.password);
    }

    @When("the user clicks the update button")
    public void the_user_clicks_the_update_button() {
        clientMyAccount.getPasswordUpdateButton().click();
    }

    //WrongPassword
    @When("the user enter the current and invalid new password")
    public void the_user_enter_the_current_and_invalid_new_password() {
        clientMyAccount.getCurrentPasswordField().sendKeys(TestUserData.UserType.SIGNUP_USER.password);
        clientMyAccount.getNewPasswordField().sendKeys(TestUserData.UserType.SIGNUP_USER.password);
        clientMyAccount.getConfirmPasswordField().sendKeys("TEST@123");
    }

    @Then("the user notified with the error message")
    public void the_user_notified_with_the_error_message() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getPasswordErrorMessage()));
        String message = clientMyAccount.getPasswordErrorMessage().getText();
        Assert.assertTrue(message, true);
        LibGlobal.quitDriver();
    }

    //Sessions
    @When("the user clicks the Sessions sidemenu")
    public void the_user_clicks_the_sessions_sidemenu() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getSessionsButton()));
        clientMyAccount.getSessionsButton().click();
    }

    @When("the user terminate the previous session and confirm the action")
    public void the_user_terminate_the_previous_session_and_confirm_the_action() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getSession()));
        LibGlobal.mouseHover(clientMyAccount.getSession());
        clientMyAccount.getTerminateButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getTerminatePopupButton()));
        clientMyAccount.getTerminatePopupButton().click();
    }

    @Then("the user notified with the popup message Active sessions has been terminated successfully")
    public void the_user_notified_with_the_popup_message_active_sessions_has_been_terminated_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getSessionSuccessPopup()));
        System.out.println(clientMyAccount.getSessionSuccessPopup().getText());
        Assert.assertTrue("Active sessions has been terminated successfully", true);
        LibGlobal.quitDriver();
    }

    @When("the user terminate all button and confirm the action")
    public void the_user_terminate_all_button_and_confirm_the_action() {
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getTerminateAllButton()));
        clientMyAccount.getTerminateAllButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientMyAccount.getTerminateAllPopupButton()));
        clientMyAccount.getTerminateAllPopupButton().click();
    }

    //MFA
    @When("the user clicks the MFA sidemenu")
    public void the_user_clicks_the_mfa_sidemenu() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getMfaButton()));
        clientMyAccount.getMfaButton().click();
    }

    @When("the user clicks the Enable toggle button and confirm the action")
    public void the_user_clicks_the_enable_toggle_button_and_confirm_the_action() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getEnableToggleButton()));
        clientMyAccount.getEnableToggleButton().click();
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getEnableButton()));
        clientMyAccount.getEnableButton().click();
    }

    @When("the user clicks the Setup button and clicks the enable button")
    public void the_user_clicks_the_setup_button_and_clicks_the_enable_button() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getEmailSetupButton()));
        clientMyAccount.getEmailSetupButton().click();
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getEnableButton()));
        clientMyAccount.getEnableButton().click();
    }

    @Then("the user notified with a popup message Updated successfully")
    public void the_user_notified_with_a_popup_message_updated_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getUpdatePopup()));
        System.out.println(clientMyAccount.getUpdatePopup().getText());
        Assert.assertTrue("Updated successfully", true);
        LibGlobal.quitDriver();
    }

    @When("the user enter the MFA OTP")
    public void the_user_enter_the_mfa_otp() throws Exception {
        wait.until(driver -> {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 4000) {
                // Wait for 4 seconds
            }
            return true;
        });
        String otp = MailTrapAPI.fetchMFAOTP("CaseDrive MFA Verification Code");
        System.out.println("Fetched OTP: " + 30);
        WebDriverWait waitEmail = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> otpFields = wait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[contains(@aria-label,'Digit')]")));
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
        clientMyAccount.getSubmitButton().click();
    }

    @When("the user clicks the Disable button and confirm the action")
    public void the_user_clicks_the_disable_button_and_confirm_the_action() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getDisableButton()));
        clientMyAccount.getDisableButton().click();
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getDisablePopupButton()));
        clientMyAccount.getDisablePopupButton().click();
    }

    @When("the user clicks the Enable toggle button and clicks the disable button")
    public void the_user_clicks_the_enable_toggle_button_and_clicks_the_disable_button() {
        //for this automatically clicking
    }

    @Then("the user notified with a popup message Authentication disabled")
    public void the_user_notified_with_a_popup_message_authentication_disabled() {
        wait.until(ExpectedConditions.visibilityOf(clientMyAccount.getMfaDisabledPopup()));
        System.out.print(clientMyAccount.getMfaDisabledPopup().getText());
        Assert.assertTrue("Authentication disabled", true);
        LibGlobal.quitDriver();
    }
}