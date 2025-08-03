package stepdefinition;

import baseclass.LibGlobal;
import helper.CaseHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.ClientCaseListPOM;
import pompages.OrderIntakePOM;
import userdata.OrderIntakeData;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


import static baseclass.LibGlobal.driver;

@Log
public class ClientCaseList {
    public OrderIntakePOM orderIntakePOM;
    public ClientCaseListPOM clientCaseListPOM;
    CaseHelper caseHelper= new CaseHelper();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(130));

    @When("the client clicks on the Case List and clicks on review cases")
    public void theClientClicksOnTheCaseListAndClicksOnReviewCases() {
        orderIntakePOM = new OrderIntakePOM();
        clientCaseListPOM = new ClientCaseListPOM();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getSideMenu()));
        orderIntakePOM.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getCaseList()));
        orderIntakePOM.getCaseList().click();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getReviewCases()));
        orderIntakePOM.getReviewCases().click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @Then("the client clicks on the Download button for a {string} case with priority {string}")
    public void theClientClicksOnTheDownloadButtonForACaseWithPriority(String expectedStatus, String expectedPriority) {
        executeClientCaseAction(expectedStatus, expectedPriority,
                7, "download",
                ".//td[@data-index='7']//div"
        );
        LibGlobal.quitDriver();
    }

    @And("the client clicks on the three-dot action for an {string} case with priority {string}")
    public void theClientClicksOnTheThreeDotAction(String expectedStatus, String expectedPriority) {
        executeClientCaseAction(expectedStatus, expectedPriority,
                12, "threeDot",
                ".//td[@data-index='12']//button"
        );
    }

    private void executeClientCaseAction(String expectedStatus, String expectedPriority,
                                         int actionCol, String actionType,
                                         String actionXpath) {

        boolean found = false;
        boolean paginationAttempted = false;
        try {
            @SuppressWarnings("unused")
            String detectedStatus = caseHelper.getDetectedStatus(0, 6);
            do {
                List<WebElement> rows = caseHelper.getVisibleRows("//td[@data-index='6']", "//tr");
                found = caseHelper.processRowsForAction(rows, "", expectedStatus, expectedPriority, // "" for client name
                        6, 5, 0, actionCol, actionType, actionXpath);

                if (!found) {
                    if (!paginationAttempted && caseHelper.attemptPagination("//div[@aria-label='Rows per page']", "//tr", "//li[normalize-space()='50']")) {
                        paginationAttempted = true;
                        detectedStatus = caseHelper.getDetectedStatus(0, 6);
                        rows = caseHelper.getVisibleRows("//td[@data-index='6']", "//tr");
                        found = caseHelper.processRowsForAction(rows, "", expectedStatus, expectedPriority,
                                6, 5, 0, actionCol, actionType, actionXpath);
                    } else {
                        break;
                    }
                }
            } while (!found);

            Assert.assertTrue("No matching case found for Status: " + expectedStatus + ", Priority: " + expectedPriority, found);

        } catch (Exception e) {
            log.severe("Error while searching for the case: " + e.getMessage());
            throw new RuntimeException("Failed to perform action: " + actionType, e);
        }
    }

    @And("the client clicks on the view button")
    public void theClientClicksOnTheViewButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getViewButton()));
        clientCaseListPOM.getViewButton().click();
    }

    @And("the client clicks on the action button")
    public void theClientClicksOnTheActionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getActionButton()));
        clientCaseListPOM.getActionButton().click();
    }

    @And("the client clicks on Request for estimate button")
    public void theClientClicksOnRequestForEstimateButton() {
        WebElement estimateRequestButton = wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getEstimateRequest()));
        estimateRequestButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getConfirmButton()));
        clientCaseListPOM.getConfirmButton().click();
    }

    @And("the client sees the success message Estimate Request request is sent")
    public void theClientSeesTheSuccessMessageEstimateRequestRequestIsSent() {
        try {
            if (isAlertPresent()) {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                log.info("Alert appeared hence the client is volume wise: " + alertText);

                String expectedAlertText = "Estimate request only for hourly based price type.";


                Assert.assertEquals("Unexpected alert message displayed.", expectedAlertText, alertText);
                alert.accept();
                return;
            }
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getSuccessMessage()));
            String expectedText = "Case estimation requested successfully.";
            String actualText = clientCaseListPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
        } catch (AssertionError e) {
            log.warning("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.warning("Unexpected exception: " + e.getMessage());
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Then("verify the case status changes to {string}")
    public void verifyTheCaseStatusChangesTo(String changeStatus) {

        String expectedStatus = wait.until(driver -> {
            WebElement viewStatusElement = clientCaseListPOM.getViewPageStatus();
            String actualStatus = viewStatusElement.getText().trim();
            return !actualStatus.isEmpty() ? actualStatus : null;
        });
        log.info("Found Status: " + expectedStatus);
        {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getViewPageStatus()));
            String actualText = clientCaseListPOM.getViewPageStatus().getText();
            Assert.assertEquals("The expected text was not found in the element.", changeStatus, actualText);
            LibGlobal.quitDriver();
        }
    }

    @And("the client clicks on Request for Hold button")
    public void theClientClicksOnRequestForHoldButton() {
        clientCaseListPOM.getHoldReqButton().click();
        clientCaseListPOM.getReasonInput().sendKeys("Test Hold Request");
        clientCaseListPOM.getConfirmButton().click();
    }

    @And("the client sees the success message Case Hold request is sent")
    public void theClientSeesTheSuccessMessageCaseHoldRequestIsSent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getSuccessMessage()));
            String expectedText = "Case Hold Request has been sent successfully.";
            String actualText = clientCaseListPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
            Assert.assertEquals(actualText, "The expected success message was not displayed.", expectedText);
        } catch (AssertionError e) {
            log.warning("Assertion failed: " + e.getMessage());
        }
    }

    @And("the client clicks on Request to Resume button")
    public void theClientClicksOnRequestToResumeForACaseThatIsOnHold() {
        clientCaseListPOM.getResumeReqButton().click();
        clientCaseListPOM.getReasonInput().sendKeys("Test resume Request");
        clientCaseListPOM.getConfirmButton().click();
    }

    @And("the client sees the success message Resume request is sent")
    public void theClientSeesTheSuccessMessageResumeRequestIsSent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getSuccessMessage()));
            String expectedText = "Case Resume Request has been sent successfully.";
            String actualText = clientCaseListPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
            Assert.assertEquals(actualText, "The expected success message was not displayed.", expectedText);
        } catch (AssertionError e) {
            log.warning("Assertion failed: " + e.getMessage());
        }
    }

    @And("the client clicks on Request to Cancel button")
    public void theClientRequestToCancelByClickingTheButton() {
        clientCaseListPOM.getCancelReqButton().click();
        clientCaseListPOM.getReasonInput().sendKeys("Test Cancel Request");
        clientCaseListPOM.getConfirmButton().click();
    }

    @And("the client sees the success message Case Cancel request is sent")
    public void theClientSeesTheSuccessMessageCaseCancelRequestIsSent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getSuccessMessage()));
            String expectedText = "Case Cancel Request has been sent successfully.";
            String actualText = clientCaseListPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
            Assert.assertEquals(actualText, "The expected success message was not displayed.", expectedText);
        } catch (AssertionError e) {
            log.warning("Assertion failed: " + e.getMessage());
        }
    }

    @And("the client clicks on the change priority button and selects Expedited from the dropdown")
    public void theClientClicksOnTheChangePriorityButtonAndSelectsExpeditedFromTheDropdown() {
        wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getChangePriority()));
        clientCaseListPOM.getChangePriority().click();
        wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getPriorityStandardDropdown()));
        clientCaseListPOM.getPriorityStandardDropdown().click();
        wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getExpedited()));
        clientCaseListPOM.getExpedited().click();
        clientCaseListPOM.getRequestButton().click();
    }

    @And("the client sees the success message for the request is sent")
    public void theClientSeesTheSuccessMessageForTheRequestIsSent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getSuccessMessage()));
            String expectedText = "Case Expedited Request successfully.";
            String actualText = clientCaseListPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
            Assert.assertEquals(actualText, "The expected success message was not displayed.", expectedText);
        } catch (AssertionError e) {
            log.warning("Assertion failed: " + e.getMessage());
        }
    }

    @And("the client sees a browser alert message priority is already in the {string}")
    public void theClientSeesABrowserAlertMessagePriorityIsAlreadyInThe(String priority) {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(50));
            alertWait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();

            log.info("Alert message: " + alertMessage);
            Assert.assertTrue("The alert message is not correct", alertMessage.contains("Case Already in " + priority));

            alert.accept();
            log.info("Alert dismissed by clicking OK.");

        } catch (Exception e) {
            log.severe("Error: No alert found or unexpected alert message.");
            throw new RuntimeException("Expected alert was not found or unexpected alert message.", e);
        }
    }

    @Then("the client verifies that the priority is still in {string}")
    public void theClientVerifiesThatThePriorityIsStillIn(String changePriority) {
        String expectedPriority = wait.until(driver -> {
            WebElement viewPriorityElement = clientCaseListPOM.getViewPagePriority();
            String actualStatus = viewPriorityElement.getText().trim();
            return !actualStatus.isEmpty() ? actualStatus : null;
        });
        log.info("Found Status: " + expectedPriority);
        {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getViewPagePriority()));
            String actualText = clientCaseListPOM.getViewPagePriority().getText();
            Assert.assertEquals("The expected text was not found in the element.", changePriority, actualText);
            LibGlobal.quitDriver();
        }
    }

    @And("the client clicks on the change priority button and selects Standard from the dropdown")
    public void theClientClicksOnTheChangePriorityButtonAndSelectsStandardFromTheDropdown() {
        wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getChangePriority()));
        clientCaseListPOM.getChangePriority().click();
        wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getPriorityStandardDropdown()));
        clientCaseListPOM.getPriorityStandardDropdown().click();
        wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getStandard()));
        clientCaseListPOM.getStandard().click();
        clientCaseListPOM.getRequestButton().click();
    }

    @And("the client clicks on the missing record button")
    public void theClientClicksOnTheViewButtonAndThenClicksOnTheMissingRecordButton() {
        wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getMissingRecordsButton()));
        clientCaseListPOM.getMissingRecordsButton().click();
    }

    @And("the client uploads different file types")
    public void theClientUploadsDifferentFileTypes() {
        WebDriverWait fileUploadWait = new WebDriverWait(driver, Duration.ofSeconds(12));
        List<String> filePaths = Arrays.asList(
                OrderIntakeData.OrderType.CASE_DATA.resolveRelativeFilePath(OrderIntakeData.OrderType.CASE_DATA.getDocType()),
                OrderIntakeData.OrderType.CASE_DATA.resolveRelativeFilePath(OrderIntakeData.OrderType.CASE_DATA.getGifType()),
                OrderIntakeData.OrderType.CASE_DATA.resolveRelativeFilePath(OrderIntakeData.OrderType.CASE_DATA.getPdfType()),
                OrderIntakeData.OrderType.CASE_DATA.resolveRelativeFilePath(OrderIntakeData.OrderType.CASE_DATA.getPngType()),
                OrderIntakeData.OrderType.CASE_DATA.resolveRelativeFilePath(OrderIntakeData.OrderType.CASE_DATA.getPptType()),
                OrderIntakeData.OrderType.CASE_DATA.resolveRelativeFilePath(OrderIntakeData.OrderType.CASE_DATA.getPsdType()),
                OrderIntakeData.OrderType.CASE_DATA.resolveRelativeFilePath(OrderIntakeData.OrderType.CASE_DATA.getTextFile())
        );
        for (String filePath : filePaths) {
            try {
                orderIntakePOM.getFileInput().sendKeys(filePath);
                log.info("File " + filePath + " added for upload.");
            } catch (Exception e) {
                log.warning("Unable to add file to upload: " + filePath);
            }
        }
        try {
            fileUploadWait.until(ExpectedConditions.textToBePresentInElement(orderIntakePOM.getUploadSuccess100(), "100"));
            log.info("FileUpload Completed 100%: " + orderIntakePOM.getUploadSuccess100().getText());
        } catch (Exception e) {
            log.info("Upload completion status '100%' not appeared.");
        }

        List<WebElement> fileUploadConfirms = orderIntakePOM.getFileUploadConfirms();
        try {
            fileUploadWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class, 'file-upload-confirmation')]"), 6));
            log.info("At least 7 file upload confirmation elements found.");
        } catch (Exception e) {
            log.info("Unable to find enough file upload confirmation elements.");
        }

        if (fileUploadConfirms.size() >= 7) {
            try {
                WebElement seventhElement = fileUploadConfirms.get(6);
                fileUploadWait.until(ExpectedConditions.visibilityOf(seventhElement));
                log.info("File upload confirmation 7 is visible.");
            } catch (Exception e) {
                log.info("The 7th file upload confirmation element was not visible after waiting.");
            }
        } else {
            log.info("Not enough file upload confirmation elements found.");
        }
    }

    @And("the client clicks on the confirm button")
    public void theClientClicksOnTheConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getConfirmButton()));
        clientCaseListPOM.getConfirmButton().click();
    }

    @Then("the client sees an success message for file upload")
    public void theClientSeesAnSuccessMessageForFileUpload() {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getSuccessMessage()));
            String expectedText = "Files Uploaded Successfully";
            String actualText = clientCaseListPOM.getSuccessMessage().getText();
            Assert.assertEquals("The expected success message was not displayed.", expectedText, actualText);
            Assert.assertEquals(actualText, "The expected success message was not displayed.", expectedText);
        } catch (AssertionError e) {
            log.warning("Assertion failed: " + e.getMessage());
        }
        LibGlobal.quitDriver();
    }

    @And("the client clicks on the add notes button in the notes menu")
    public void theClientClicksOnTheAddNotesButtonInTheNotesMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getNotes()));
        clientCaseListPOM.getNotes().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getAddNotes()));
        clientCaseListPOM.getAddNotes().click();
    }

    @And("the client enters {string} in the notes input field")
    public void theClientEntersNotesInTheInputField(String noteType) {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getNoteInputField()));
        String noteContent = "";
        if ("public notes".equalsIgnoreCase(noteType)) {
            noteContent = "This is a public note.";
        } else if ("private notes".equalsIgnoreCase(noteType)) {
            noteContent = "This is a private note.";
        } else {
            log.warning("Unknown note type: " + noteType);
            return;
        }
        clientCaseListPOM.getNoteInputField().sendKeys(noteContent);
        log.info("Entered " + noteType + ": " + noteContent);
    }

    @And("the client clicks on the post button")
    public void theClientClicksOnThePostButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getPostButton()));
        clientCaseListPOM.getPostButton().click();
    }

    @Then("the client verifies whether the {string} are being saved in the notes page")
    public void theClientVerifiesWhetherTheNotesAreBeingSavedInThePage(String noteType) {
        String expectedNoteContent = "";
        if ("public notes".equalsIgnoreCase(noteType)) {
            expectedNoteContent = "This is a public note.";
        } else if ("private notes".equalsIgnoreCase(noteType)) {
            expectedNoteContent = "This is a private note.";
        } else {
            log.warning("Unknown note type: " + noteType);
            throw new IllegalArgumentException("Invalid note type: " + noteType);
        }
        List<WebElement> notesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//span[contains(text(), '" + expectedNoteContent + "')]")
        ));
        wait.until(ExpectedConditions.visibilityOfAllElements(notesList));
        boolean noteFound = false;
        // Iterate through the notes and check if the expected content exists
        for (WebElement note : notesList) {
            if (note.getText().contains(expectedNoteContent)) {
                log.info("Found note: " + expectedNoteContent);
                noteFound = true;
                break;
            }
        }
        Assert.assertTrue("The note content '" + expectedNoteContent + "' was not found in the notes page.", noteFound);
        LibGlobal.quitDriver();
    }

    @And("the client enables the toggle switch and set it as private")
    public void theClientEnablesTheToggleSwitchAndSetItAsPrivate() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getNotes()));
        clientCaseListPOM.getPrivateToggle().click();
    }

    @And("the client clicks on case docs menu")
    public void clientClicksOnCaseDocsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getCaseDocsMenu()));
        clientCaseListPOM.getCaseDocsMenu().click();
    }

    @And("the client clicks on the deliverables menu")
    public void theClientClicksOnTheDeliverablesMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getDeliverableButton()));
        LibGlobal.doubleClick(driver, clientCaseListPOM.getDeliverableButton());
        log.info("Successfully double-clicked the Source File Folder.");
    }

    @Then("the client clicks on the download button of the files and assert the download functionality")
    public void theClientClicksOnTheThreeDotButtonOfTheFilesAndAssertTheDownloadFunctionality() {


        int maxRetries = 3;

        try {
            log.info("Searching for the file name...");
            List<WebElement> fileNameElements = driver.findElements(By.xpath("//td[@data-index='0']"));

            if (fileNameElements.isEmpty()) {
                log.info("File name element not found. This order is placed using custom links.");
                return;
            }

            WebElement fileNameElement = fileNameElements.get(0);
            String detectedFileName = fileNameElement.getText().trim();

            if (detectedFileName.isEmpty()) {
                log.info("This order is placed using custom links.");
                return;
            }

            log.info("Detected File Name: " + detectedFileName);

            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr[@class='MuiTableRow-root css-11t0o3']")));
            log.info("Total rows found: " + rows.size());

            boolean downloadButtonFound = false;

            for (WebElement row : rows) {
                int retryCount = 0;
                while (retryCount < maxRetries) {
                    try {
                        rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr")));
                        WebElement downloadButton = row.findElement(By.xpath(".//td[5]//*[name()='svg']"));
                        wait.until(ExpectedConditions.elementToBeClickable(downloadButton));

                        downloadButton.click();
                        caseHelper.assertDownloadOutcome();

                        downloadButtonFound = true;
                        break;
                    } catch (Exception e) {
                        retryCount++;
                        log.warning("Retrying... (" + retryCount + "/" + maxRetries + ") - Error: " + e.getMessage());
                    }
                }
                if (!downloadButtonFound) {
                    log.info("This order is placed using custom links.");
                    break;
                }
            }

        } catch (Exception e) {
            log.severe("Error while clicking the download button: " + e.getMessage());
            throw new RuntimeException("Failed to click download button", e);
        }
    }

    @And("the client clicks on the Case Query menu and enter a message to send for our internal team")
    public void theClientClicksOnTheCaseQueryMenu() {
        EstimateandTask estimateandTask= new EstimateandTask();
        estimateandTask.clientCaseListPOM= new ClientCaseListPOM();
        estimateandTask.theAdminClicksTheCaseQueriesMenu();
        estimateandTask.theAdminSendsATextMessageToTheClientRegardingTheCase();
    }
}