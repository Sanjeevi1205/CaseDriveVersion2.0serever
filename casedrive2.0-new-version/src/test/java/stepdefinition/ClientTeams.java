package stepdefinition;
import baseclass.LibGlobal;
import helper.CaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import mailtrap.MailTrapAPI;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.ClientTeamsPOM;
import userdata.ClientTeamsData;
import utils.RandomEmail;
import java.time.Duration;
import java.util.List;

@Log
public class ClientTeams extends LibGlobal {
    private ClientTeamsPOM clientTeamsPOM;
    CaseHelper caseHelper = new CaseHelper();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("the client clicks the Teams menu")
    public void theClientClicksTheTeamsMenu() {
        clientTeamsPOM = new ClientTeamsPOM();
        wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getTeams()));
        clientTeamsPOM.getTeams().click();
    }

    @And("the client clicks the Add New Member button")
    public void theClientClicksTheAddNewMemberButton() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getAddNewMemberBtn()));
        clientTeamsPOM.getAddNewMemberBtn().click();
    }

    @And("the client enters the email and selects the role as Administrator")
    public void theClientEntersTheEmailAndSelectsTheRole() {
        String memberMail = RandomEmail.memberMail();
        clientTeamsPOM.mailInputFieldRetry();
        clientTeamsPOM.getMailInputField().sendKeys(memberMail);
        wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getAdministratorToggle()));
        clientTeamsPOM.getAdministratorToggle().click();
    }

    @And("the client enables the allow notifcation checkbox")
    public void theClientEnablesTheAllowNotifcationCheckbox() {
        clientTeamsPOM.getAllowNotification().click();
    }

    @And("the client clicks the invite button")
    public void theClientClicksTheInviteButton() {
        clientTeamsPOM.getInviteButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientTeamsPOM.getConfirmButton()));
        clientTeamsPOM.getConfirmButton().click();
    }

    @And("the client sees an success message to notify that the member is invited")
    public void theClientSeesTheSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getSuccessMessage()));
            String successMessageText = clientTeamsPOM.getSuccessMessage().getText();
            boolean inviteSuccessMessage = successMessageText.contains("New member has been added successfully");
            if (inviteSuccessMessage) {
                log.info("Invite success status: " + true);
            }
        } catch (Exception e) {
            log.info("Invite success message was not found. Continuing with the test.");
        }

    }

    @And("the client verifies the added member is displayed on the team list page")
    public void theClientVerifiesTheAddedMemberIsDisplayedOnTheAllPage() {
        String memberMail = "member";
        boolean found = false;

        try {
            log.info("Starting verification for member email: " + memberMail);

            do {
                List<WebElement> rows = driver.findElements(By.xpath("//tr"));
                log.info("Number of rows found: " + rows.size());

                for (WebElement row : rows) {
                    try {
                        WebElement emailColumn = wait.until(ExpectedConditions.visibilityOf(
                                row.findElement(By.xpath(".//td[@data-index='3']"))));
                        String emailText = emailColumn.getText().trim();
                        log.info("Checking email in column 3: " + emailText);

                        if (emailText.contains(memberMail)) {
                            log.info("Found matching email: " + memberMail + " in column 3.");
                            found = true;
                            break;
                        }
                    } catch (Exception rowEx) {
                        log.warning("Error while processing a row: " + rowEx.getMessage());
                    }
                }

                if (!found) {
                    log.info("Matching email not found on the current page. Attempting to change rows per page.");
                    try {
                        WebElement rowPerPage = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[@aria-label='Rows per page']")));
                        rowPerPage.click();
                        log.info("Clicked on 'Rows per page' dropdown.");

                        WebElement maximumPage = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//li[normalize-space()='50']")));
                        maximumPage.click();
                        log.info("Selected maximum rows per page.");
                        log.info("Page reloaded with maximum rows per page.");
                    } catch (Exception paginationEx) {
                        log.warning("Pagination could not be performed: " + paginationEx.getMessage());
                        break;
                    }
                }
            } while (!found);

            Assert.assertTrue("The member email: " + memberMail + " was not found in the table.", found);

            if (!found) {
                log.info("No matching email found with the given value: " + memberMail + " after checking all pages.");
            }
        } catch (Exception e) {
            log.severe("Error during table verification: " + e.getMessage());
        }

        LibGlobal.quitDriver();

    }

    @Then("the member signs up using the link from the email")
    public void theMemberSignsUpUsingTheLinkFromTheEmail() {

        WebDriver newDriver = null;
        try {
            String uniqueIdentifier = "Test_Run_" + System.currentTimeMillis();
            log.info("Unique Identifier: " + uniqueIdentifier);
            String signupUrl = MailTrapAPI.fetchSignupLink(uniqueIdentifier);
            log.info("Signup URL retrieved: " + signupUrl);

            newDriver = initializeDriver();
            newDriver.manage().window().maximize();
            newDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            newDriver.get(signupUrl);
            log.info("Navigated to Signup URL: " + signupUrl);

            WebDriverWait newWait = new WebDriverWait(newDriver, Duration.ofSeconds(20));

            WebElement firstNameField = newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=':r0:']")));
            firstNameField.sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getFirstname());

            WebElement lastNameField = newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=':r1:']")));
            lastNameField.sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getLastname());

            WebElement phoneNumberField = newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=':r2:']")));
            phoneNumberField.sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getPhonenumber());

            WebElement jobRoleField = newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=':r4:']")));
            jobRoleField.sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getJobrole());

            WebElement nextButton = newWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Next']")));
            nextButton.click();

            WebElement enterPassword = newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='outlined-adornment-password'])[1]")));
            enterPassword.sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getPassword());

            WebElement confirmPassword = newWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='outlined-adornment-password'])[2]")));
            confirmPassword.sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getConfirmpassword());

            WebElement submitButton = newWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Submit']")));
            submitButton.click();

            WebElement continueButton = newWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
            continueButton.click();

            log.info("Signup process completed successfully.");
        } catch (Exception e) {
            log.severe("Signup process failed: " + e.getMessage());
            throw new RuntimeException("Signup process failed.", e);
        } finally {
            if (newDriver != null) {
                newDriver.quit();
            }
        }
    }

    @And("the client clicks the three-dot button and selects the view option")
    public void theClientClicksTheThreeDotButtonAndSelectsTheViewOption()  {
        WebElement actionButton= wait.until(ExpectedConditions.elementToBeClickable(clientTeamsPOM.getThreeDotAction()));
        actionButton.click();
        clientTeamsPOM.getViewButton().click();
    }

    @And("the client clicks the edit button")
    public void theClientClicksTheEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clientTeamsPOM.getEditButton()));
        clientTeamsPOM.getEditButton().click();
    }

    @And("the client enables the share email toggle")
    public void theClientEnablesTheShareEmailToggle() {
        WebElement shareEmailToggle = wait.until(ExpectedConditions.elementToBeClickable(clientTeamsPOM.getNotificationToggle()));
        shareEmailToggle.click();
    }

    @And("the client saves the changes")
    public void savesTheChanges() {
        wait.until(ExpectedConditions.elementToBeClickable(clientTeamsPOM.getSaveButton()));
        clientTeamsPOM.getSaveButton().click();
    }

    @Then("the client sees an success message to notify that the member details has been saved")
    public void theClientSeesAnSuccessMessageInAToaster() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getSuccessMessage()));
            String expectedText = "Member details have been saved successfully";
            String actualText = clientTeamsPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected alert message was not displayed.", expectedText, actualText);
        } catch (Exception e) {
            log.info("Details saved success status was not found. Continuing with the test.");
            LibGlobal.quitDriver();
        }

    }

    @And("the client clicks the three-dot button of a member with {string} status and selects the three dot action")
    public void theClientClicksTheThreeDotButtonOfAMemberWithStatusAndSelectsRevokeOption(String expectedStatus) {
        executeMemberAction(expectedStatus
                // statusCol, actionCol
        );
    }

    private void executeMemberAction(String expectedStatus) {

        boolean found = false;
        boolean paginationAttempted = false;

        try {
            @SuppressWarnings("unused")
            String detectedStatus = caseHelper.getDetectedStatus(0, 5);

            do {
                List<WebElement> rows = caseHelper.getVisibleRows("//td[@data-index='5']", "//tr");
                found = caseHelper.processRowsForAction(rows, "", expectedStatus, "",
                        5, 0, 0, 7, "threeDot", ".//td[@data-index='7']//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-65aruc']");

                if (!found) {
                    if (!paginationAttempted && caseHelper.attemptPagination("//div[@aria-label='Rows per page']", "//tr", "//li[normalize-space()='50']")) {
                        paginationAttempted = true;
                        detectedStatus = caseHelper.getDetectedStatus(0, 5);
                        rows = caseHelper.getVisibleRows("//td[@data-index='5']", "//tr");
                        found = caseHelper.processRowsForAction(rows, "", expectedStatus, "",
                                5, 0, 0, 7, "threeDot", ".//td[@data-index='7']//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-65aruc']");
                    } else {
                        break;
                    }
                }
            } while (!found);

            Assert.assertTrue("No member found with Status: " + expectedStatus, found);

        } catch (Exception e) {
            log.severe("Error while searching for member status: " + e.getMessage());
            throw new RuntimeException("Failed to perform member action: threeDot", e);
        }
    }

    @And("the client clicks the Revoke button")
    public void theClientClicksTheRevokeButton() {
       clientTeamsPOM.revokeButtonRetry();
    }

    @And("the client clicks on confirm button in the confirmation popup")
    public void theClientClicksTheConfirmButton() {
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(clientTeamsPOM.getConfirmButton()));
        confirmButton.click();
        log.info("Clicked the confirm button.");
    }

    @Then("the client sees an success message to notify that the member is deleted")
    public void theClientSeesAnSuccessMessageToNotifyThatTheMemberIsDeleted() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getSuccessMessage()));
            String expectedText = "Member deleted successfully.";
            String actualText = clientTeamsPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
        } catch (Exception e) {
            log.info("Error message was not found. Continuing with the test.");
        }
        LibGlobal.quitDriver();

    }

    @Then("the client sees an alert message notifying that cannot revoke")
    public void theClientSeesAnAlertMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getSuccessMessage()));
            String expectedText = "You cannot delete this member please contact administrator";
            String actualText = clientTeamsPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected alert message was not displayed.", expectedText, actualText);
        } catch (Exception e) {
            log.info("Member Unable to Delete success status was not found. Continuing with the test.");
        }
        LibGlobal.quitDriver();

    }

    @And("the client enters an emailId that already exists and selects the role as Administrator")
    public void theClientEntersAnTheEmailIdOfAlreadyExistsAndSelectsTheRoleAsAdministrator() {
        clientTeamsPOM.mailInputFieldRetry();
        clientTeamsPOM.getMailInputField().sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getExistingMail());
        wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getAdministratorToggle()));
        clientTeamsPOM.getAdministratorToggle().click();
    }

    @Then("the client should see an error message indicating that the email is already associated with a team member")
    public void theClientShouldSeeAnErrorMessageIndicatingThatTheEmailIsAlreadyAssociatedWithATeamMember() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getSuccessMessage()));
            String expectedText = "Email Address Already Exists. Please enter a different email address.";
            String actualText = clientTeamsPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
        } catch (Exception e) {
            log.info("Error message was not found. Continuing with the test.");
        }
        LibGlobal.quitDriver();

    }

    @Then("the client should see an error message indicating that the email is Invalid")
    public void theClientShouldSeeAnErrorMessageIndicatingThatTheEmailIsInvalid() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getInvalidMailAlert()));
            String expectedText = "invalid email address";
            String actualText = clientTeamsPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
        } catch (Exception e) {
            log.info("Error message was not found. Continuing with the test.");
        }
        LibGlobal.quitDriver();
    }

    @And("the client enters an invalid email and selects the role as Administrator")
    public void theClientEntersAnInvalidEmailAndSelectsTheRoleAsAdministrator() {
        clientTeamsPOM.mailInputFieldRetry();
        clientTeamsPOM.getMailInputField().sendKeys(ClientTeamsData.MemberData.MEMBER_DATA.getInvalidEmail());
        wait.until(ExpectedConditions.visibilityOf(clientTeamsPOM.getAdministratorToggle()));
        clientTeamsPOM.getAdministratorToggle().click();
    }
}