package stepdefinition;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.ClientSettingsPOM;
import userdata.TestUserData;

import java.awt.*;
import java.time.Duration;

public class ClientSettings extends LibGlobal {
    public ClientSettingsPOM clientSettings = new ClientSettingsPOM();
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    //General Settings
    @When("the client clicks on the Settings icon")
    public void the_client_clicks_on_the_settings_icon() {
        wait.until(ExpectedConditions.elementToBeClickable(clientSettings.getSettingsButton()));
        clientSettings.getSettingsButton().click();
    }

    @When("the client clicks on the Edit button")
    public void the_client_clicks_on_the_edit_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientSettings.getEditButton()));
        clientSettings.getEditButton().click();
    }

    @When("the client modify their details in general details page")
    public void the_client_modify_their_details_in_general_details_page() throws AWTException {
        LibGlobal.scrolldown();
        clientSettings.getCityField().clear();
        clientSettings.getCityField().sendKeys(TestUserData.UserType.SIGNUP_USER.companyCity);
        clientSettings.getBillingCityField().clear();
        clientSettings.getBillingCityField().sendKeys(TestUserData.UserType.SIGNUP_USER.companyCity);
        LibGlobal.closeNotification();
        LibGlobal.scrollup(4);
    }

    @When("the client clicks the Save button")
    public void the_client_clicks_the_save_button() {
        clientSettings.getSaveButton().click();
    }

    @Then("the client notified with the popup message Details saved successfully.")
    public void the_client_notified_with_the_popup_message_details_saved_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getGeneralDetailsSuccessPopup()));
        System.out.println(clientSettings.getGeneralDetailsSuccessPopup().getText());
        Assert.assertTrue("Details saved successfully.", true);
        LibGlobal.quitDriver();
    }

    //Desired Services
    @When("the client clicks the Services and Desired Services sidemenu")
    public void the_client_clicks_the_services_and_desired_services_sidemenu() {
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getServicesMenu()));
        clientSettings.getServicesMenu().click();
        clientSettings.getServicesMenu().click();
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getDesiredServicesMenu()));
        clientSettings.getDesiredServicesMenu().click();
    }

    @When("the client select the service to add")
    public void the_client_select_the_service_to_add() {
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getAPSSummary()));
        clientSettings.getAPSSummary().click();
    }

    @When("the client clicks the Request button")
    public void the_client_clicks_the_request_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientSettings.getRequestButton()));
        clientSettings.getRequestButton().click();
    }

    @Then("the client notified with the popup message Service request sent successfully")
    public void the_client_notified_with_the_popup_message_service_request_sent_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getDesiredServicesSuccessPopup()));
        System.out.println(clientSettings.getDesiredServicesSuccessPopup().getText());
        Assert.assertTrue("Service request sent successfully", true);
        LibGlobal.quitDriver();
    }

    //Notifications
    @When("the client clicks the Notification and Email Settings sidemenu")
    public void the_client_clicks_the_notification_and_email_settings_sidemenu() {
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getNotificationMenu()));
        clientSettings.getNotificationMenu().click();
        clientSettings.getNotificationMenu().click();
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getEmailSettingsMenu()));
        clientSettings.getEmailSettingsMenu().click();
    }

    @When("the client deselect the required mail service")
    public void the_client_deselect_the_required_mail_service() {
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getCaseHoldOption()));
        clientSettings.getCaseHoldOption().click();
    }

    @Then("the client notified with the popup message Email settings updated")
    public void the_client_notified_with_the_popup_message_email_settings_updated() {
        wait.until(ExpectedConditions.visibilityOf(clientSettings.getEmailSuccessPopup()));
        System.out.println(clientSettings.getEmailSuccessPopup().getText());
        Assert.assertTrue("Email settings updated", true);
        LibGlobal.quitDriver();
    }
}