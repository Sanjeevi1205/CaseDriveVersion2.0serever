package stepdefinition;

import baseclass.LibGlobal;
import helper.CaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import mailtrap.MailTrapAPI;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.AdminSettingsPOM;
import pompages.LoginPOM;
import userdata.ClientTeamsData;
import utils.RandomEmail;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Log
public class UserManagement extends LibGlobal {
    public AdminSettingsPOM adminSettingsPOM;
    CaseHelper caseHelper = new CaseHelper();
    public final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("the admin clicks on the settings icon")
    public void theAdminClicksOnTheSettingsIcon() {
        adminSettingsPOM = new AdminSettingsPOM();
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getSettingsButton()));
        adminSettingsPOM.getSettingsButton().click();
    }

    @And("the admin was navigated to the settings page")
    public void theAdminWasNavigatedToTheSettingsPage() {
        String currentURL = driver.getCurrentUrl();
        assert currentURL != null;
        Assert.assertTrue("URL does not contain '/personal-details'", currentURL.contains("/admin/setting/personal-details"));
    }

    @And("the admin clicks on the side menu of settings page")
    public void theAdminClicksOnTheSideMenuOfSettingsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getSettingsSideMenu()));
        adminSettingsPOM.getSettingsSideMenu().click();
    }

    @And("the admin clicks on the user management menu")
    public void theAdminClicksOnTheUserManagementMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getUserManagement()));
        adminSettingsPOM.getUserManagement().click();
    }

    @And("the admin clicks on the employees menu")
    public void theAdminClicksOnTheEmployeesMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getEmployeesMenu()));
        adminSettingsPOM.getEmployeesMenu().click();
    }

    @And("the admin clicks on the add employee button")
    public void theAdminClicksOnTheAddEmployeeButton() {
        adminSettingsPOM.addEmployeeButtonRetry();
    }

    @And("the admin clicks on the save button without entering the basic information about the employee")
    public void theAdminClicksOnTheSaveButtonWithoutEnteringTheBasicInformationAboutTheEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getSaveButton()));
        adminSettingsPOM.getSaveButton().click();
    }

    @Then("the admin should see error message appearing from some mandatory fields")
    public void theAdminShouldSeeErrorMessageAppearingFromSomeMandatoryFields() {
        String[] expectedMessages = {
                "Please enter the email address",
                "Please Select the employee Id",
                "Please enter the first name",
                "Please enter the second name",
                "Please select the Designation",
                "Please select the Employee Role",
                "Please enter the work location",
                "Please select the profile"
        };

        String[] actualMessages = adminSettingsPOM.getValidationMessages().toArray(new String[0]);
        Assert.assertArrayEquals("Validation messages do not match", expectedMessages, actualMessages);
        LibGlobal.quitDriver();
    }

    @And("the admin enters the details in all mandatory fields")
    public void theAdminEntersTheDetailsInAllMandatoryFields() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getFirstNameInput()));
        adminSettingsPOM.getFirstNameInput().sendKeys(RandomEmail.generateFirstName());
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getLastNameInput()));
        adminSettingsPOM.getLastNameInput().sendKeys(RandomEmail.generateLastName());
        adminSettingsPOM.getMailInput().sendKeys(RandomEmail.employeeMail());
        adminSettingsPOM.getEmployeeId().sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getEmployeeID());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", adminSettingsPOM.getDepartmentID());
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getDepartmentID()));
        adminSettingsPOM.getDepartmentID().click();
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getDepartmentValue()));
        adminSettingsPOM.getDepartmentValue().click();
        adminSettingsPOM.getDesignationID().click();
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getDropDownValue()));
        adminSettingsPOM.getDropDownValue().click();
    }

    @And("the admin select the {string} as an work location")
    public void theAdminSelectTheAsAnWorkLocation(String location) {
        adminSettingsPOM.getLocationID().click();
        String dynamicXPath = "//li[normalize-space()='" + location + "']";
        WebElement locationElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", locationElement);
        locationElement.click();
    }

    @And("the admin adds an employee to the {string} Profile")
    public void theAdminAddsAnEmployeeToTheProfile(String profileName) {
        adminSettingsPOM.getProfileID().click();
        String dynamicXPath = "//li[normalize-space()='" + profileName + "']";
        WebElement profileElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", profileElement);
        profileElement.click();
    }

    @And("the admin set the role as {string}")
    public void theAdminSetTheRoleAs(String roleName) {
        adminSettingsPOM.getRoleID().click();
        String dynamicXPath = "//li[normalize-space()='" + roleName + "']";
        WebElement roleElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", roleElement);
        roleElement.click();
    }

    @And("the admin select the {string} as reporting to")
    public void theAdminSelectTheAsReportingTo(String reportTo) {
        adminSettingsPOM.getReportTo().click();
        String dynamicXPath = "//li[normalize-space()='" + reportTo + "']";
        WebElement reportElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", reportElement);
        reportElement.click();
    }

    @And("the admin select the {string} as approved by")
    public void theAdminSelectTheAsApprovedBy(String approvedBy) {
        adminSettingsPOM.getApproveTo().click();
        String dynamicXPath = "//li[normalize-space()='" + approvedBy + "']";
        WebElement approvedByElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", approvedByElement);
        approvedByElement.click();
    }

    @And("the admin clicks on the save button")
    public void theAdminClicksOnTheSaveButton() {
        theAdminClicksOnTheSaveButtonWithoutEnteringTheBasicInformationAboutTheEmployee();
    }

    @Then("the admin should see a success message for employee creation")
    public void theAdminShouldSeeASuccessMessageForEmployeeCreation() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getSuccessMessage()));
        String successMessageText = adminSettingsPOM.getSuccessMessage().getText();
        log.info(" actual text " + successMessageText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageText.contains("Created successfully."));
        LibGlobal.quitDriver();
    }

    @When("the admin clicks on the settings icon and clicks on user management")
    public void theAdminClicksOnTheSettingsIconAndOpensTheTeamsMenu() {
        theAdminClicksOnTheSettingsIcon();
        theAdminWasNavigatedToTheSettingsPage();
        theAdminClicksOnTheSideMenuOfSettingsPage();
        theAdminClicksOnTheUserManagementMenu();
    }

    @And("the admin clicks on the teams menu")
    public void theAdminClicksOnTheTeamsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getTeamsMenu()));
        adminSettingsPOM.getTeamsMenu().click();
    }

    @And("the admin clicks on the create team button")
    public void theAdminClicksOnTheCreateTeamButton() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getCreateTeamButton()));
        adminSettingsPOM.getCreateTeamButton().click();
    }

    @And("the admin creates a team by providing required details and clicks on the save button")
    public void theAdminCreatesATeamByProvidingRequiredDetailsAndClicksOnTheSaveButton() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getTeamNameInput()));
        adminSettingsPOM.getTeamNameInput().sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getDynamicTeamName());
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getDescriptionInput()));
        adminSettingsPOM.getDescriptionInput().sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getDescriptionValue());
        adminSettingsPOM.getTeamsTeamLeadDropdown().click();
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getLatestTeamLeadDropDownValue()));
        adminSettingsPOM.getLatestTeamLeadDropDownValue().click();
        adminSettingsPOM.selectUpToFiveEmployees();
        theAdminClicksOnTheSaveButton();
    }

    @Then("the admin should see a success message for team creation")
    public void theAdminShouldSeeASuccessMessageForTeamCreation() {
        theAdminShouldSeeASuccessMessageForEmployeeCreation();
    }

    @And("the admin click on the edit button on employee details page")
    public void theAdminClickOnTheEditButtonOnEmployeeDetailsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getEditButton()));
        adminSettingsPOM.getEditButton().click();
    }

    @And("the admin clicks an employee with {string} role and {string} profile")
    public void theAdminViewsEmployeeWithRoleAndProfile(String expectedRoleName, String expectedProfileName) {
        executeEmployeeAction(expectedRoleName, expectedProfileName
        );
    }

    public void executeEmployeeAction(String expectedProfileName, String expectedRoleName) {
        boolean found = false;
        boolean paginationAttempted = false;

        try {
            @SuppressWarnings("unused")
            String detectedStatus = caseHelper.getDetectedStatus(0, 3); // Profile column to wait on

            do {
                List<WebElement> rows = caseHelper.getVisibleRows("//td[@data-index='3']", "//tr");

                found = caseHelper.processRowsForProfileRoleAction(
                        rows,
                        expectedProfileName,
                        expectedRoleName,
                        3,
                        2,
                        7,
                        "view",
                        ".//td[@data-index='7']//button"
                );

                if (!found && !paginationAttempted) {
                    paginationAttempted = caseHelper.attemptPagination(
                            "//div[@aria-label='Rows per page']",
                            "//tr",
                            "//li[normalize-space()='500']"
                    );

                    if (paginationAttempted)
                        detectedStatus = caseHelper.getDetectedStatus(0, 3);
                    {
                        // After successful pagination, refresh the rows and try again
                        rows = caseHelper.getVisibleRows("//td[@data-index='3']", "//tr");

                        found = caseHelper.processRowsForProfileRoleAction(
                                rows,
                                expectedProfileName,
                                expectedRoleName,
                                3,
                                2,
                                7,
                                "view",
                                ".//td[@data-index='7']//button"
                        );
                    }
                } else {
                    break; // Found or pagination already attempted, exit loop
                }

            } while (!found);

            Assert.assertTrue("No matching employee found for Profile: " + expectedProfileName + " and Role: " + expectedRoleName, found);

        } catch (Exception e) {
            throw new RuntimeException("Failed to perform action: view", e);
        }
    }

    @And("the admin clicks on the update button")
    public void theAdminClicksOnTheUpdateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getUpdateButton()));
        adminSettingsPOM.getUpdateButton().click();
    }

    @Then("the admin should see a success message for details update")
    public void theAdminShouldSeeASuccessMessageForDetailsUpdate() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getSuccessMessage()));
        String successMessageText = adminSettingsPOM.getSuccessMessage().getText();
        log.info(" actual text " + successMessageText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageText.contains("Employee updated successfully."));
        LibGlobal.quitDriver();
    }

    @Then("the admin should see the mandatory fields error messages in company creation page")
    public void theAdminShouldSeeTheErrorMessagesInMandatoryFields() {
        String[] expectedMessages = {
                "Please enter the Company Name",
                "Please enter the email",
                "Phone Number is required"
        };
        String[] actualMessages = adminSettingsPOM.getCompanyValidationMessages().toArray(new String[0]);
        Assert.assertArrayEquals("Validation messages do not match", expectedMessages, actualMessages);
        LibGlobal.quitDriver();
    }

    @And("the admin clicks on the general setting menu")
    public void theAdminClicksOnTheGeneralSettingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getGeneralSettings()));
        adminSettingsPOM.getGeneralSettings().click();
    }

    @And("the admin clicks on the Companies menu")
    public void theAdminClicksOnTheCompaniesMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getCompaniesMenu()));
        adminSettingsPOM.getCompaniesMenu().click();
    }

    @And("the admin clicks on the add companies button")
    public void theAdminClicksOnTheAddCompanyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getAddCompanyButton()));
        adminSettingsPOM.getAddCompanyButton().click();
    }

    @And("the admin enter the mandatory details to create company")
    public void theAdminEnterTheMandatoryDetailsToCreateCompany() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getCompanyName()));
        adminSettingsPOM.getCompanyName().sendKeys(ClientTeamsData.MemberData.generateCompanyName());
        adminSettingsPOM.getMailInput().sendKeys(RandomEmail.organisationMail());
        adminSettingsPOM.getPhoneNumber().sendKeys(RandomEmail.generateNumericPhoneNumber());
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getTimeZone()));
        adminSettingsPOM.getTimeZone().click();
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getTimeZoneDropDownValue()));
        adminSettingsPOM.getTimeZoneDropDownValue().click();
        adminSettingsPOM.getAccountName().sendKeys((RandomEmail.generateFirstName()));
        adminSettingsPOM.getBankName().sendKeys(ClientTeamsData.MemberData.generateBankName());
        adminSettingsPOM.getAccountNumber().sendKeys(ClientTeamsData.MemberData.generateAccountNumber());
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getCountryDropDown()));
        adminSettingsPOM.getCountryDropDown().click();
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getUsaValue()));
        adminSettingsPOM.getUsaValue().click();
        adminSettingsPOM.getAddressInput().sendKeys(ClientTeamsData.MemberData.generateAddress());
        adminSettingsPOM.getStateInput().sendKeys(ClientTeamsData.MemberData.generateState());
        adminSettingsPOM.getCityInput().sendKeys(ClientTeamsData.MemberData.generateCity());
        adminSettingsPOM.getStreetInput().sendKeys(ClientTeamsData.MemberData.generateStreet());
        adminSettingsPOM.getZipcodeInput().sendKeys(ClientTeamsData.MemberData.generateZipcode());
    }

    @Then("the admin should see a success message for company creation")
    public void theAdminShouldSeeASuccessMessageForCompanyCreation() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getSuccessMessage()));
        String successMessageText = adminSettingsPOM.getSuccessMessage().getText();
        log.info(" actual text " + successMessageText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageText.contains("Company created successfully"));
        LibGlobal.quitDriver();
    }

    @And("the admin clicks on the three dot action of any employee")
    public void theAdminClicksOnTheThreeDotActionOfAnyEmployee() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getEmployeeLatestThreeDot()));
        adminSettingsPOM.getEmployeeLatestThreeDot().click();
    }

    @And("the admin clicks on the send invitation button and confirms")
    public void theAdminClicksOnTheSendInvitationButtonAndConfirms() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getSendInvitationLink()));
        adminSettingsPOM.getSendInvitationLink().click();
        adminSettingsPOM.retryConfirmInviteButton();
    }

    @And("the admin should see a success message for invite mail")
    public void theAdminShouldSeeASuccessMessageForInviteMail() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getSuccessMessage()));
        String successMessage = adminSettingsPOM.getSuccessMessage().getText();
        log.info(" Actual text " + successMessage);
        Assert.assertTrue("Success message does not contain expected text",
                successMessage.contains("Invite Mail Send successfully."));
        LibGlobal.quitDriver();
    }

    @And("the employee logs in using the credentials from email")
    public void employeeLogsInUsingEmailCredentials() {
        WebDriver newDriver = null;
        try {
            log.info("Fetching Login Credentials from Mail trap...");
            Map<String, String> credentials = MailTrapAPI.getEmployeeCredentialsFromEmail();
            String loginUrl = credentials.get("link");
            String email = credentials.get("email");
            String password = credentials.get("password");

            log.info("Login URL: " + loginUrl);
            log.info("Email: " + email);

            newDriver = initializeDriver();
            newDriver.manage().window().maximize();
            newDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            newDriver.get(loginUrl);
            log.info("Navigated to Login URL: " + loginUrl);

            Set<String> handles = newDriver.getWindowHandles();
            if (handles.size() > 1) {
                for (String handle : handles) {
                    newDriver.switchTo().window(handle);
                }
            }

            driver = newDriver;
            LoginPOM loginPOM = new LoginPOM();
            WebDriverWait newWait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement usernameField = newWait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
            usernameField.sendKeys(email);

            WebElement passwordField = newWait.until(ExpectedConditions.visibilityOf(loginPOM.getPassword()));
            passwordField.sendKeys(password);

            loginPOM.getLoginbtn().click();

            log.info("Login process completed successfully.");

            newWait.until(ExpectedConditions.urlContains("/dashboard"));

            String currentUrl = driver.getCurrentUrl();
            log.info("Final redirected URL: " + currentUrl);

            assert currentUrl != null;
            if (currentUrl.contains("/dashboard")) {
                log.info("Assertion Passed: User is on dashboard.");
            } else {
                throw new AssertionError("Assertion Failed: Expected to be on User dashboard, but was on: " + currentUrl);
            }

        } catch (Exception e) {
            log.severe("Login process failed: " + e.getMessage());
            throw new RuntimeException("Login process failed.", e);
        } finally {
            if (newDriver != null) {
                newDriver.quit();
            }
        }
    }

    @And("the admin clicks on the delete button")
    public void theAdminClicksOnTheDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getDeleteButton()));
        adminSettingsPOM.getDeleteButton().click();
    }

    @Then("the admin should see a success message for employee deletion")
    public void theAdminShouldSeeASuccessMessageForEmployeeDeletion() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getSuccessMessage()));
        String successMessageText = adminSettingsPOM.getSuccessMessage().getText();
        log.info(" Actual text " + successMessageText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageText.contains("Employee deleted successfully"));
        LibGlobal.quitDriver();
    }

    @And("the admin clicks on task menu and clicks on the task template button")
    public void theAdminClicksOnTaskMenuAndClicksOnTheTaskTemplateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getTasksMenu()));
        adminSettingsPOM.getTasksMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getTaskTemplateMenu()));
        adminSettingsPOM.getTaskTemplateMenu().click();
    }

    @And("the admin clicks on the new template button")
    public void theAdminClicksOnTheNewTemplateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getNewTasKTemplateButton()));
        adminSettingsPOM.getNewTasKTemplateButton().click();

    }

    @And("the admin creates a task template")
    public void theAdminCreatesATaskTemplate() {
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getTemplateName()));
        adminSettingsPOM.getTemplateName().sendKeys(ClientTeamsData.MemberData.generateTestTemplateName());

        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getTemplateDescription()));
        adminSettingsPOM.getTemplateDescription().sendKeys("Test Description");

        WebElement taskDropdown = wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getTaskDropDown()));
        taskDropdown.click();

        WebElement recordReview = wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getRecordReviewDropDownValue()));
        recordReview.click();

        WebElement serviceDropdown = wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getServiceDropDown()));
        serviceDropdown.click();

        WebElement medChronology = wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getMedicalChronologyValue()));
        medChronology.click();

        WebElement billingDropdown = wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getServiceDropDownBill()));
        billingDropdown.click();

        actions.sendKeys(Keys.ENTER).perform();

        WebElement clientDropdown = wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getClientNameDropDown()));
        clientDropdown.click();

        actions.sendKeys(Keys.ENTER).perform();

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getSaveTaskTemplateButton()));
        saveButton.click();
    }

    @Then("the admin should see a success message for task template creation")
    public void theAdminShouldSeeASuccessMessageForTaskTemplateCreation() {
        wait.until(ExpectedConditions.visibilityOf(adminSettingsPOM.getSuccessMessage()));
        String successMessageTemplateText = adminSettingsPOM.getSuccessMessage().getText();
        log.info(" actual text " + successMessageTemplateText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageTemplateText.contains("Created successfully."));
        LibGlobal.quitDriver();
    }

    @And("the admin clicks on the view button in the employee row")
    public void theAdminClicksOnTheViewButtonInTheEmployeeRow() {
        wait.until(ExpectedConditions.elementToBeClickable(adminSettingsPOM.getViewButton()));
        adminSettingsPOM.getViewButton().click();

    }
}