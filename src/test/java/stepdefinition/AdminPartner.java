package stepdefinition;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.AdminPartnerPOM;
import utils.RandomEmail;
import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;

public class AdminPartner extends LibGlobal{
    public AdminPartnerPOM adminPartner = new AdminPartnerPOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    @When("the admin clicks the Partners side menu")
    public void the_admin_clicks_the_partners_side_menu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminPartner.getSideMenu()));
        adminPartner.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(adminPartner.getPartnerMenu()));
        adminPartner.getPartnerMenu().click();
        adminPartner.getSideMenu().click();
    }
    @When("the admin clicks the Create Partner button")
    public void the_admin_clicks_the_create_partner_button() {
        wait.until(ExpectedConditions.elementToBeClickable(adminPartner.getCreatePartnerButton()));
        adminPartner.getCreatePartnerButton().click();
    }
    @When("the admin enters the basic details of the partner")
    public void the_admin_enters_the_basic_details_of_the_partner() {
        wait.until(ExpectedConditions.visibilityOf(adminPartner.getFirstName()));
        adminPartner.getFirstName().sendKeys(RandomEmail.generateFirstName());
        adminPartner.getLastName().sendKeys(RandomEmail.generateLastName());
        adminPartner.getPhoneNumber().sendKeys(RandomEmail.generateNumericPhoneNumber());
        adminPartner.getEmail().sendKeys(RandomEmail.generatePartnerEmail());
        adminPartner.getCountry().sendKeys("USA");
        adminPartner.getState().sendKeys("California");
        adminPartner.getZipcode().sendKeys("10001");
        //adminPartner.getTimeZone().sendKeys("Eastern Standard Time");
    }
    @When("clicks the Create button")
    public void clicks_the_create_button() {
        adminPartner.getCreateButton().click();
    }
    @Then("the admin is notified with a popup message stating that the partner was created successfully")
    public void the_admin_is_notified_with_a_popup_message_stating_that_the_partner_was_created_successfully() {
        wait.until(ExpectedConditions.visibilityOf(adminPartner.getPopupMessage()));
        System.out.print(adminPartner.getPopupMessage().getText());
        assertTrue("Partner created successfully", true);
        LibGlobal.quitDriver();
    }

    //Company
    @When("the admin enable the toggle button for company")
    public void the_admin_enable_the_toggle_button_for_company() {
        wait.until(ExpectedConditions.elementToBeClickable(adminPartner.getToggleButton()));
        adminPartner.getToggleButton().click();
    }
    @When("the admin enters the basic business details of the partner")
    public void the_admin_enters_the_basic_business_details_of_the_partner() throws AWTException {
        adminPartner.getBusinessName().sendKeys(RandomEmail.generateFullName());
        adminPartner.getEmployeeID().sendKeys(RandomEmail.generateNumericPhoneNumber());
        adminPartner.getBusinessPhone().sendKeys(RandomEmail.generateNumericPhoneNumber());
        adminPartner.getWebsite().sendKeys("www.lezdotechmed.com");
        adminPartner.getBusinessEmail().sendKeys(RandomEmail.generatePartnerEmail());
        adminPartner.getIndustry().sendKeys("Florida, USA");
        adminPartner.getCountry().sendKeys("USA");
        adminPartner.getState().sendKeys("California");
        LibGlobal.scrolldown();
        adminPartner.getBusinessZipcode().sendKeys("10001");
        //adminPartner.getTimeZone().sendKeys("Eastern Standard Time");
    }

    //Export
    @When("the admin clicks the Export button")
    public void the_admin_clicks_the_export_button() {
        wait.until(ExpectedConditions.elementToBeClickable(adminPartner.getExportButton()));
        adminPartner.getExportButton().click();
    }
    @Then("the admin opens the report to verify the export file")
    public void the_admin_opens_the_report_to_verify_the_export_file() {
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        System.out.println(downloadPath);
        File folder = new File(downloadPath);
        File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
                .filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
                .orElse(null));
        Assert.assertNotNull("Downloaded report not found", downloadedFile);
        downloadedFile.delete();
        LibGlobal.quitDriver();
    }
}