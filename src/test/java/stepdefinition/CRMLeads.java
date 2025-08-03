package stepdefinition;

import baseclass.LibGlobal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.CrmClientPOM;
import userdata.ClientTeamsData;
import utils.RandomEmail;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static baseclass.LibGlobal.driver;

@Log

public class CRMLeads {
    public CrmClientPOM crmClientPOM = new CrmClientPOM();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


    @And("the admin selects a lead with Verification Pending or Onboard Pending and clicks on the three-dot menu")
    public void theAdminSelectsSendInviteLinkFromTheThreeDotMenuOfTheLeadWithVerificationPendingOrOnboardPending() {
        crmClientPOM = new CrmClientPOM();

        boolean found = false;
        try {
            int retryCountStatus = 0;
            while (retryCountStatus < 3) {
                try {
                    wait.until(driver -> {
                        String statusText = crmClientPOM.getStatusElement().getText().trim();
                        return statusText.equals("Onboard Completed") || statusText.equals("Onboard Pending")
                                || statusText.equals("Verification Pending");
                    });
                    break;
                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    log.info("Error finding status element (Attempt " + (retryCountStatus + 1) + "): " + e.getMessage());
                    retryCountStatus++;
                    if (retryCountStatus == 3) {
                        log.info("Failed to retrieve status element after 3 attempts.");
                        return;
                    }
                }
            }

            while (!found) {
                wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getStatusColumns().get(0))); // Wait for

                List<WebElement> statusColumns = crmClientPOM.getStatusColumns();
                log.info("Found " + statusColumns.size() + " status columns.");

                boolean verificationPendingOrOnboardPendingFound = false;
                for (WebElement status : statusColumns) {
                    String statusText = "";
                    int retryCountText = 0;
                    while (retryCountText < 3) {
                        try {
                            statusText = status.getText().trim();
                            break;
                        } catch (StaleElementReferenceException | NoSuchElementException e) {
                            log.info("Error retrieving status text (Attempt " + (retryCountText + 1) + "): " + e.getMessage());
                            retryCountText++;
                            if (retryCountText == 3) {
                                log.info("Failed to retrieve status text after 3 attempts.");
                                continue;
                            }
                        }
                    }

                    log.info("Status: " + statusText);
                    if (statusText.equals("Verification Pending") || statusText.equals("Onboard Pending")) {
                        log.info("Found lead with status '" + statusText + "'.");

                        WebElement threeDotAction = status
                                .findElement(By.xpath("ancestor::tr//td[@data-index='8']//button[@type='button']"));

                        int retryCount = 0;
                        while (retryCount < 3) {
                            try {
                                wait.until(ExpectedConditions.elementToBeClickable(threeDotAction));
                                threeDotAction.click();
                                found = true;
                                verificationPendingOrOnboardPendingFound = true;
                                log.info("Clicked on the three dot action.");
                                break;
                            } catch (Exception e) {
                                log.info("Error clicking three dot action (Attempt " + (retryCount + 1) + "): " + e.getMessage());
                                retryCount++;
                                if (retryCount == 3) {
                                    log.info("Failed to click three dot action after 3 attempts.");
                                }
                            }
                        }
                        break;
                    }
                }
                if (!verificationPendingOrOnboardPendingFound) {
                    try {
                        log.info(
                                "Lead with status 'Verification Pending' or 'Onboard Pending' not found, changing rows per page to 500.");

                        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getRowperpage()));
                        crmClientPOM.getRowperpage().click();
                        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getMaximumPage()));

                        crmClientPOM.getMaximumPage().click();
                        wait.until(ExpectedConditions.refreshed(
                                ExpectedConditions.visibilityOf(crmClientPOM.getStatusColumns().get(0))));

                        log.info("Page reloaded with 500 rows per page.");
                        log.info("No leads with 'Verification Pending' or 'Onboard Pending' were present in CRM.");
                        break;
                    } catch (Exception e) {
                        log.info("Error while changing rows per page and searching for lead: " + e.getMessage());
                        break;
                    }
                }
            }
            if (!found) {
                log.info("No lead with 'Verification Pending' or 'Onboard Pending' found after searching all pages.");
            }

        } catch (Exception e) {
            log.info("Error during the lead selection process: " + e.getMessage());
        }
    }

    @And("clicks on the send invitation action and click on the invite button")
    public void confirmsByClickingTheInviteButtonOnThePopUpMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getSendInvitationLink()));
        crmClientPOM.getSendInvitationLink().click();
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getConfirmationPopUp()));
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getInviteButton()));
        crmClientPOM.getInviteButton().click();
    }

    @Then("a confirmation popup for Invite Mail Send successfully is displayed and confirmed")
    public void aConfirmationPopupForInviteMailSendSuccessfullyIsDisplayedAndConfirmed() {
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getInvitesendSucccesstoaster()));
        try {
            String orderMessageText = crmClientPOM.getInvitesendSucccesstoaster().getText();
            boolean inviteSuccessMessage = orderMessageText.contains("Invite sent successfully.");
            if (inviteSuccessMessage) {
                log.info("Invite success status: " + true);
            }
        } catch (Exception e) {
            log.info("Invite success message was not found. Continuing with the test.");
        }
        LibGlobal.quitDriver();
    }

    @And("clicks on the send invitation action and click on the Cancel button")
    public void theAdminClicksTheCancelButtonToPreventSendingTheInvite() {
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getSendInvitationLink()));
        crmClientPOM.getSendInvitationLink().click();
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getConfirmationPopUp()));
        theAdminClicksTheCancelButtonToPreventTheLeadFromBeingDeleted();
    }

    @Then("the admin should remain on the same page")
    public void theAdminShouldRemainOnTheSamePage() {
        String expectedcrmleadUrlPart = "admin-casedrive.lezdotechmed.com/admin/crm/leads";
        wait.until(ExpectedConditions.urlContains(expectedcrmleadUrlPart));
        String currentCRMUrl = driver.getCurrentUrl();
        assert currentCRMUrl != null;
        Assert.assertTrue("The current URL does not contain the expected part",
                currentCRMUrl.contains(expectedcrmleadUrlPart));
        LibGlobal.quitDriver();
    }

    @And("the admin clicks on the view button in the three dot action")
    public void theAdminClicksThreeDotButtonAndClicksOnView() {
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getViewbutton()));
        crmClientPOM.getViewbutton().click();
    }

    @And("the admin clicks on delete button in the three dot action")
    public void theAdminClicksThreeDotButtonAndClicksOnDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getDeleteButton()));
        crmClientPOM.getDeleteButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getConfirmDeleteButton()));
        crmClientPOM.getConfirmDeleteButton().click();
    }

    @Then("a confirmation popup for lead delete is displayed and confirmed")
    public void aConfirmationPopupForLeadDeleteIsDisplayedAndConfirmed() {
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getDeletemessage()));
        try {
            String expectedText = "Lead deleted";
            String actualText = crmClientPOM.getDeletemessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
        } catch (Exception e) {
            log.info("Error message was not found. Continuing with the test.");
        }
    }

    @Then("the admin should be navigated to a view page that display the leads personal details")
    public void theAdminShouldBeNavigatedToANewWindowDisplayingThePersonalDetailsPage() {
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(crmClientPOM.getClientProfile()));
        profile.click();
        boolean text = crmClientPOM.getViewProfile().getText().contains("AUmail@lezdotechmed.com");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    @When("the admin clicks the Cancel button to prevent the lead from being deleted")
    public void theAdminClicksTheCancelButtonToPreventTheLeadFromBeingDeleted() {
        crmClientPOM.retryCancelButton();
    }

    @Then("the admin should see a success message for lead creation")
    public void theAdminShouldSeeASuccessMessageForLeadCreation() {
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getInvitesendSucccesstoaster()));
        String successMessageText = crmClientPOM.getInvitesendSucccesstoaster().getText();
        log.info(" Actual text " + successMessageText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageText.contains("Lead"));
    }

    @And("the admin enter some basic information in the mandatory fields")
    public void theAdminEnterSomeBasicInformationInTheMandatoryFields() {
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getFirstName()));
        crmClientPOM.getFirstName().sendKeys(RandomEmail.generateFirstName());
        crmClientPOM.getLastName().sendKeys(RandomEmail.generateLastName());
        crmClientPOM.getMailInput().sendKeys(RandomEmail.createLeadMail());
    }

    @When("the admin clicks the CRM menu and click on the Companies")
    public void theAdminClicksTheCRMMenuAndClickOnTheCompanies() {
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getSaSidemenu()));
        crmClientPOM.getSaSidemenu().click();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getCRMButton()));
        crmClientPOM.getCRMButton().click();
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getCompaniesMenu()));
        crmClientPOM.getCompaniesMenu().click();
    }

    @And("the admin enter some company information in the mandatory fields")
    public void theAdminEnterSomeCompanyInformationInTheMandatoryFields() {
        crmClientPOM.getCompanyName().sendKeys(ClientTeamsData.MemberData.generateCompanyNameCRM());
    }

    @And("the admin clicks on the create company button")
    public void theAdminClicksOnTheCreateCompanyButton() {
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getCreateCompanyButton()));
        crmClientPOM.getCreateCompanyButton().click();
    }

    @Then("the admin should see a success message for clients company creation")
    public void theAdminShouldSeeASuccessMessageForClientsCompanyCreation() {
        wait.until(ExpectedConditions.visibilityOf(crmClientPOM.getInvitesendSucccesstoaster()));
        String successMessageText = crmClientPOM.getInvitesendSucccesstoaster().getText();
        log.info(" Actual text " + successMessageText);
        Assert.assertTrue("Success message does not contain expected text",
                successMessageText.contains("Created successfully."));

    }
}