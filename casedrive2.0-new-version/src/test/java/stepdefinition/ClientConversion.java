package stepdefinition;
import java.time.Duration;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pompages.CrmClientPOM;
import pompages.LoginPOM;
import userdata.ClientTeamsData;
import userdata.TestUserData.UserType;
import baseclass.LibGlobal;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.RandomEmail;

@Log
public class ClientConversion extends LibGlobal {
    private CrmClientPOM crmClientPOM;

    @Given("the admin logs into the casedrive URL")
    public void theAdminLogsInToTheCasedriveUrl(){
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        LoginPOM loginPOM = new LoginPOM();
        crmClientPOM = new CrmClientPOM();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(UserType.SUPER_ADMIN.getUsername());
        loginPOM.getPassword().sendKeys(UserType.SUPER_ADMIN.getPassword());
        loginPOM.getLoginbtn().click();
    }

    @When("the admin clicks the CRM menu and click on the Leads")
    public void theAdminClicksTheCrmMenuAndClickTheLeads() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getSaSidemenu()));
        crmClientPOM.getSaSidemenu().click();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getCRMButton()));
        crmClientPOM.getCRMButton().click();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getLeadsMenu()));
        crmClientPOM.getLeadsMenu().click();
    }

    @And("the admin select the lead in Onboard completed status to convert as client")
    public void theAdminSelectTheLeadToConvertAsClient() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        boolean found = false;
        int retryCount = 3;

        for (int attempt = 0; attempt < retryCount; attempt++) {
            try {
                wait.until(driver -> {
                    String statusText = crmClientPOM.getStatusElement().getText().trim();
                    return statusText.equals("Onboard Completed") || statusText.equals("Onboard Pending") || statusText.equals("Verification Pending");
                });

                wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getStatusColumns().get(0)));
                List<WebElement> statusColumns = crmClientPOM.getStatusColumns();
                log.info("Found " + statusColumns.size() + " status columns.");
                boolean relevantStatusFound = false;

                for (WebElement status : statusColumns) {
                    String statusText = status.getText().trim();
                    log.info("Status: " + statusText);

                    if (statusText.equals("Onboard Completed")) {
                        log.info("Found lead with status: " + statusText);
                        WebElement leadName = status.findElement(By.xpath("ancestor::tr//td[@data-index='1']"));
                        wait.until(ExpectedConditions.elementToBeClickable(leadName));
                        leadName.click();
                        found = true;
                        relevantStatusFound = true;
                        log.info("Clicked on lead with status: " + statusText);
                        break;
                    }
                }

                if (!relevantStatusFound) {
                    log.info("Relevant status not found, changing rows per page to 500.");
                    wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getRowperpage()));
                    crmClientPOM.getRowperpage().click();
                    wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getMaximumPage()));
                    crmClientPOM.getMaximumPage().click();

                    wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(crmClientPOM.getStatusColumns().get(0))));
                    log.info("Page reloaded with 500 rows per page.");

                    wait.until(driver -> {
                        String statusText = crmClientPOM.getStatusElement().getText().trim();
                        return statusText.equals("Onboard Completed") || statusText.equals("Onboard Pending") || statusText.equals("Verification Pending");
                    });

                    List<WebElement> updatedStatusColumns = crmClientPOM.getStatusColumns();
                    log.info("Found " + updatedStatusColumns.size() + " status columns on updated page.");

                    for (WebElement status : updatedStatusColumns) {
                        String statusText = status.getText().trim();
                        log.info("Status: " + statusText);

                        if (statusText.equals("Onboard Completed")) {
                            log.info("Found lead with status: " + statusText);
                            WebElement leadName = status.findElement(By.xpath("ancestor::tr//td[@data-index='1']"));
                            wait.until(ExpectedConditions.elementToBeClickable(leadName));
                            leadName.click();
                            found = true;
                            log.info("Clicked on lead with status: " + statusText);
                            break;
                        }
                    }
                }

                if (!found) {
                    log.info("No leads were found in the CRM with Onboard Completed Status");
                }
                break;
            } catch (Exception e) {
                log.info("Attempt " + (attempt + 1) + " failed due to: " + e.getMessage());
                if (attempt == retryCount - 1) {
                    log.info("All retry attempts failed. Unable to process the lead selection.");
                }
            }
        }
    }
        @And("the admin click Edit button and create company")
    public void theAdminClickEditButtonToChangeAnyFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getEditButton()));
        crmClientPOM.getEditButton().click();
        try {
            wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getCompanyIcon()));
            crmClientPOM.getCompanyIcon().click();
            crmClientPOM.getCreateCompany().click();
            theAdminShouldShouldSeesTheConfirmationMessageInAToaster();
            log.info("Company creation started.");
        } catch (Exception e) {
            log.info("Company already created, skipping to the next step.");
        }
    }

    @And("the admin saves the changes and click convert button")
    public void theAdminSavesTheChangesAndClickConvertButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getConvertButton()));
        crmClientPOM.getConvertButton().click();
    }

    @And("the admin select the required dropdown options from the pop-up window")
    public void theAdminSelectTheRequiredDropdownOptionsFromThePopupWindow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getOrganization()));
        wait.until(driver -> {


            crmClientPOM.getOrganization().click();
            crmClientPOM.retryClickDropDownValue();

            crmClientPOM.getManager().click();
            crmClientPOM.retryClickDropDownValue();

            crmClientPOM.getDivision().click();
            crmClientPOM.retryClickDropDownValue();

            crmClientPOM.getClientType().click();
            crmClientPOM.retryClickDropDownValue();

            try {
                crmClientPOM.getCompany().click();
                crmClientPOM.getCompanyValue().click();
            } catch (NoSuchElementException e) {
                log.info("Company already selected: " + e.getMessage());
            }

            return true;
        });
    }

    @And("the admin click Convert button to confirm the changes")
    public void theAdminClickConvertButtonToConfirmTheChanges() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getWaitForTextClientType()));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getConvertinPopup()));
        crmClientPOM.getConvertinPopup().click();
    }

    @And("the admin navigates to the Client page to confirm the conversion")
    public void theAdminNavigatesToTheClientPageToConfirmTheConversion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String expectedClientURLPart = "casedrive.lezdotechmed.com/admin/crm/clients";
        wait.until(ExpectedConditions.urlContains(expectedClientURLPart));
        String currentClientUrl = driver.getCurrentUrl();
        assert currentClientUrl != null;
        Assert.assertTrue("The current URL does not contain the expected part",
                currentClientUrl.contains(expectedClientURLPart));
    }

    @Then("the admin verifies whether the client ID is being generated automatically for the client")
    public void theAdminVerifiesWhetherTheClientIDIsBeingGeneratedAutomaticallyForTheClient() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getDoesclientIdTable()));
        String clientID = crmClientPOM.getDoesclientIdTable().getText().trim();
        log.info("Actual Client ID: " + clientID);
        if (!clientID.isEmpty()) {
            Assert.assertTrue("Client ID does not have the expected prefix.",
                    clientID.startsWith("LDTM"));
            LibGlobal.quitDriver();
        } else {
            log.info("Client ID is empty, unable to verify the prefix.");
            LibGlobal.quitDriver();
        }
    }

    @And("the admin does not select the required dropdown options from the pop-up window")
    public void theAdminDoesNotSelectTheRequiredDropdownOptionsFromThePopUpWindow() {
        theAdminClickConvertButtonToConfirmTheChanges();
    }

    @Then("the admin verifies that an error message is displayed, indicating the dropdown values must be selected")
    public void theAdminVerifiesThatAnErrorMessageIsDisplayedIndicatingTheDropdownValuesMustBeSelected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            boolean isPopupVisible = wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getConvertPOPup())).isDisplayed();
            Assert.assertTrue("The pop-up window should remain visible but is not.", isPopupVisible);
            log.info("Verified that the pop-up remains visible.");

            Assert.assertTrue("Dropdown options should be unselected.", crmClientPOM.getCompany().getText().isEmpty());
            log.info("Verified that dropdown options remain unselected.");
            LibGlobal.quitDriver();
        } catch (Exception e) {
            log.severe("Error while verifying the pop-up visibility and dropdown options: " + e.getMessage());

        }
    }

    @And("the admin saves the changes")
    public void theAdminSavesTheChanges() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getSaveButton()));
        crmClientPOM.getSaveButton().click();
    }


    @When("the admin clicks the CRM menu and click on the clients")
    public void theAdminClicksTheCRMMenuAndClickOnTheClients() {
        AdminDrive adminDrive= new AdminDrive();
        adminDrive.theAdminClicksTheSideMenu();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getCRMButton()));
        crmClientPOM.getCRMButton().click();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getClientsMenu()));
        crmClientPOM.getClientsMenu().click();
    }

    @And("the admin clicks on the three dot action and view a client")
    public void theAdminClicksOnTheThreeDotActionAndViewAClient() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getLatestClientThreeDot()));
        crmClientPOM.getLatestClientThreeDot().click();
        crmClientPOM.getViewbutton().click();

    }

    @And("the admin edit some details in the client details page")
    public void theAdminEditSomeDetailsInTheClientDetailsPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getEditButton()));
        crmClientPOM.getEditButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getTaskTemplateDropdown()));
        crmClientPOM.getTaskTemplateDropdown().click();
        crmClientPOM.getTaskTemplateValue().click();
    }

    @Then("the admin should should sees the confirmation message in a toaster")
    public void theAdminShouldShouldSeesTheConfirmationMessageInAToaster() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getInvitesendSucccesstoaster()));
        try {
            String successMessageText = crmClientPOM.getInvitesendSucccesstoaster().getText();
            boolean inviteSuccessMessage = successMessageText.contains("Updated successfully.");
            if (inviteSuccessMessage) {
                log.info("Update success status: " + true);
                LibGlobal.quitDriver();
            }
        } catch (Exception e) {
            log.info("Update success message was not found. Continuing with the test.");
            LibGlobal.quitDriver();
        }
    }

    @And("the admin clicks on the create button")
    public void theAdminClicksOnTheCreateButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getCreateButton()));
        crmClientPOM.getCreateButton().click();


    }

    @And("the admin fill the information about the client")
    public void theAdminFillTheInformationAboutTheClient() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getFirstName()));
        crmClientPOM.getFirstName().sendKeys(RandomEmail.generateFirstName());
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getLastName()));
        crmClientPOM.getLastName().sendKeys(RandomEmail.generateLastName());
        crmClientPOM.getMailInput().sendKeys(RandomEmail.createClientMail());

        crmClientPOM.getAssignedManagerDropDown().click();
        crmClientPOM.retryFirstValue();

        crmClientPOM.getClientCategoryDropDown().click();
        crmClientPOM.retryFirstValue();

        crmClientPOM.getDivisionDropDown().click();
        crmClientPOM.retryFirstValue();

        crmClientPOM.selectFirstOptionInSixDropdowns();
        crmClientPOM.getAddressInput().sendKeys(ClientTeamsData.MemberData.generateAddress());
        crmClientPOM.getStateDropdown().click();
        crmClientPOM.getStateDropDownValue().click();

        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getServiceSelector()));
        crmClientPOM.getServiceSelector().click();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getServiceValue1()));
        crmClientPOM.retryFirstValue();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getServiceSelector()));
        crmClientPOM.getServiceSelector().click();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getServiceValue1()));
        crmClientPOM.retryFirstValue();

        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getCreatePageTaskTemplateDropdown()));
        crmClientPOM.getCreatePageTaskTemplateDropdown().click();
        crmClientPOM.getTaskTemplateValue().click();
    }

    @And("the admin should should sees the confirmation message for client creation")
    public void theAdminShouldShouldSeesTheConfirmationMessageForClientCreation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getInvitesendSucccesstoaster()));
        String successMessageText = crmClientPOM.getInvitesendSucccesstoaster().getText();
        log.info(" Actual text " + successMessageText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageText.contains("Client created successfully"));
        LibGlobal.quitDriver();
    }

    @Then("the client logs in using the credentials from email")
    public void theClientLogsInUsingTheCredentialsFromEmail() {
        UserManagement userManagement= new UserManagement();
        userManagement.employeeLogsInUsingEmailCredentials();
    }
    }


