package stepdefinition;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseclass.LibGlobal;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.LoginPOM;
import userdata.TestUserData.UserType;

public class LoginStepdefinition extends LibGlobal {
    private LoginPOM loginPOM;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    //Positive
    @Given("the client navigate to the casedrive URL")
    public void the_client_navigate_to_the_casedrive_url() {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        loginPOM = new LoginPOM();
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
    }

    @When("the client enters the valid credentials")
    public void the_client_enters_the_valid_credentials() {
        // Set user credentials using enum
        loginPOM.getUsername().sendKeys(UserType.VALID_USER.getUsername());
        loginPOM.getPassword().sendKeys(UserType.VALID_USER.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("the client should navigate to the homepage")
    public void the_client_should_navigate_to_the_homepage() {
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(loginPOM.getProfile()));
        profile.click();
        boolean text = loginPOM.getUsermail().getText().contains("Automation_james.smith@domain.com");
        System.out.println("User login status:" + text);
        LibGlobal.quitDriver();
    }

    //Negative
    @When("the client enters the invalid email credential")
    public void the_client_enters_the_invalid_email_address() {
        loginPOM.getUsername().sendKeys(UserType.INVALID_EMAIL.getUsername());
        loginPOM.getPassword().sendKeys(UserType.INVALID_EMAIL.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("an error popup notifies the client of an invalid email.")
    public void an_error_popup_notifies_the_client_of_an_invalid_email() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPOM.getInvalidMailPopup()));
        boolean popupmessage = loginPOM.getInvalidMailPopup().getText().contains("Invalid");
        System.out.println("User login status:" + popupmessage);
        LibGlobal.quitDriver();
    }

    //Negative
    @When("the client enters the valid email and an invalid password.")
    public void the_client_enters_the_valid_email_and_an_invalid_password() {
        loginPOM.getUsername().sendKeys(UserType.INVALID_PASSWORD.getUsername());
        loginPOM.getPassword().sendKeys(UserType.INVALID_PASSWORD.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("an error popup notifies the client of an invalid password.")
    public void an_error_popup_notifies_the_client_of_an_invalid_password() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPOM.getInvalidPasswordPopup()));
        boolean popupmessage = loginPOM.getInvalidPasswordPopup().getText().contains("password");
        System.out.println("User login status:" + popupmessage);
        LibGlobal.quitDriver();
    }

    //Negative
    @When("the client enters the non-converted valid credentials")
    public void the_client_enters_the_non_converted_valid_credentials() {
        loginPOM.getUsername().sendKeys(UserType.NON_CONVERTED_LEAD.getUsername());
        loginPOM.getPassword().sendKeys(UserType.NON_CONVERTED_LEAD.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("an error popup notifies the client of an pending account verification.")
    public void an_error_popup_notifies_the_client_of_an_pending_account_verification() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPOM.getNonConvertedLead()));
        boolean popupmessage = loginPOM.getNonConvertedLead().getText().contains("being verified");
        System.out.println("User login status:" + popupmessage);
        LibGlobal.quitDriver();
    }

    //Negative
    @When("the client enters the undefined user credential")
    public void the_client_enters_the_undefined_user_credential() {
        loginPOM.getUsername().sendKeys(UserType.NON_EXISTING_USER.getUsername());
        loginPOM.getPassword().sendKeys(UserType.NON_EXISTING_USER.getPassword());
        loginPOM.getLoginbtn().click();
    }

    //Negative
    @When("the client enters the Inactive email credential")
    public void the_client_enters_the_inactive_email_credential() {
        loginPOM.getUsername().sendKeys(UserType.INVALID_CLIENT.getUsername());
        loginPOM.getPassword().sendKeys(UserType.INVALID_CLIENT.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("an error popup notifies the client of account inactive or removed.")
    public void an_error_popup_notifies_the_client_of_account_inactive_or_removed() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPOM.getInactiveClient()));
        boolean popupmessage = loginPOM.getInactiveClient().getText().contains("currently inactive");
        System.out.println("User login status:" + popupmessage);
        LibGlobal.quitDriver();
    }

    //Negative
    @Given("the internal user navigate to the casedrive URL")
    public void the_internal_user_navigate_to_the_casedrive_url() {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        loginPOM = new LoginPOM();
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
    }

    @When("the internal user enters the Inactive email credential")
    public void the_internal_user_enters_the_inactive_email_credential() {
        loginPOM.getUsername().sendKeys(UserType.INVALID_INTERNAL_USER.getUsername());
        loginPOM.getPassword().sendKeys(UserType.INVALID_INTERNAL_USER.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @Then("an error popup notifies the internal user of account inactive or removed.")
    public void an_error_popup_notifies_the_internal_user_of_account_inactive_or_removed() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPOM.getInactiveClient()));
        boolean popupmessage = loginPOM.getInactiveClient().getText().contains("currently inactive");
        System.out.println("User login status:" + popupmessage);
        LibGlobal.quitDriver();
    }
}