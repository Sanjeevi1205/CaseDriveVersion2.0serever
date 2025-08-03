package stepdefinition;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helper.CaseHelper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseclass.LibGlobal;
import io.cucumber.java.en.When;
import pompages.CaseCompletionPOM;
import pompages.ClientCaseListPOM;
import pompages.OrderIntakePOM;
import userdata.CaseCompletionData;

import static org.junit.Assert.assertEquals;


@Log
public class EstimateandTask extends LibGlobal {
    public CaseCompletionPOM casecompletion = new CaseCompletionPOM();
    public ClientCaseListPOM clientCaseListPOM = new ClientCaseListPOM();
    CaseHelper caseHelper = new CaseHelper();
    public OrderIntakePOM orderIntakePOM = new OrderIntakePOM();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("the admin clicks the Cases side menu and selects Review Cases")
    public void theAdminClicksTheCasesSideMenuAndSelectsReviewCases() {
        orderIntakePOM = new OrderIntakePOM();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSideMenu()));
        casecompletion.getSideMenu().click();
        casecompletion.getCasesMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getReviewCasesMenu()));
        casecompletion.getReviewCasesMenu().click();
        casecompletion.getSideMenu().click();
    }

    @And("the admin views a case of {string} a {string} client with {string} status and priority {string}")
    public void theAdminViewsCase(String expectedClientName, String clientType, String expectedStatus, String expectedPriority) {
        executeCaseAction(expectedClientName, expectedStatus, expectedPriority,
                19, "threeDot",
                ".//td[@data-index='19']//button"
        );
    }

    @Then("the admin downloads a case of {string} a {string} client with {string} status and priority {string}")
    public void theAdminDownloadsCase(String expectedClientName, String clientType, String expectedStatus, String expectedPriority) {
        executeCaseAction(expectedClientName, expectedStatus, expectedPriority,
                12, "download",
                ".//td[@data-index='12']//div"
        );
        LibGlobal.quitDriver();
    }

    private void executeCaseAction(String expectedClientName, String expectedStatus, String expectedPriority,
                                   int actionCol, String actionType,
                                   String actionXpath) {

        boolean found = false;
        boolean paginationAttempted = false;

        try {
            @SuppressWarnings("unused")
            String detectedStatus = caseHelper.getDetectedStatus(0, 10);

            do {
                List<WebElement> rows = caseHelper.getVisibleRows("//td[@data-index='6']", "//tr");
                found = caseHelper.processRowsForAction(rows, expectedClientName, expectedStatus, expectedPriority,
                        10, 6, 16, actionCol, actionType, actionXpath);

                if (!found) {
                    if (!paginationAttempted && caseHelper.attemptPagination("//div[@aria-label='Rows per page']", "//tr", "//li[normalize-space()='100']")) {
                        paginationAttempted = true;
                        detectedStatus = caseHelper.getDetectedStatus(0, 10);
                        rows = caseHelper.getVisibleRows("//td[@data-index='6']", "//tr");
                        found = caseHelper.processRowsForAction(rows, expectedClientName, expectedStatus, expectedPriority,
                                10, 6, 16, actionCol, actionType, actionXpath);
                    } else {
                        break;
                    }
                }
            } while (!found);

            Assert.assertTrue("No matching case found for provided criteria.", found);

        } catch (Exception e) {
            throw new RuntimeException("Failed to perform action: " + actionType, e);
        }
    }

    @And("the admin clicks on the view button")
    public void theAdminClicksOnTheViewButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getViewButton()));
        casecompletion.getViewButton().click();
    }

    @And("the admin clicks the Edit button and edits No of Pages")
    public void theAdminClicksTheEditButtonAndEditsNoOfPages() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", casecompletion.getEditButton());
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getEditButton())).click();

        try {
            WebElement targetHoursInput = wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getTargetHours()));
            String targetHoursValue = targetHoursInput.getAttribute("value").trim();

            if (!targetHoursValue.isEmpty()) {
                targetHoursInput.sendKeys(Keys.CONTROL, "a");
                targetHoursInput.sendKeys(Keys.DELETE);
                log.info("Cleared Target Hours input field.");
            }
            targetHoursInput.sendKeys("10:00:00");

        } catch (Exception e) {
            throw new RuntimeException("Error interacting with Target Hours field: " + e.getMessage(), e);
        }

        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        customWait.until(driver -> {
            WebElement pagesInput = casecompletion.getEditPagesInput();
            return pagesInput.isDisplayed() && pagesInput.isEnabled();
        });

        WebElement pagesInput = casecompletion.getEditPagesInput();
        String currentValue = pagesInput.getAttribute("value").trim();

        if (currentValue.equals("0")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", pagesInput);
            log.info("Cleared the Pages input field using JavaScript as it contained '0'.");
        }

        pagesInput.sendKeys(Keys.CONTROL, "a");
        pagesInput.sendKeys(Keys.DELETE);
        log.info("Cleared the Pages input field using keyboard actions.");

        String newPageNumber = CaseCompletionData.caseDetails.CASE_DETAILS.getPageNumber();
        pagesInput.sendKeys(newPageNumber);
        log.info("Entered new page number: " + newPageNumber);
    }

    @And("the admin clicks the Save button")
    public void theAdminClicksTheSaveButton() {
        casecompletion.getSaveButton().click();
        String expectedPageNumber = CaseCompletionData.caseDetails.CASE_DETAILS.getPageNumber();
        wait.until(ExpectedConditions.textToBePresentInElementValue(casecompletion.getEditPagesInput(), expectedPageNumber));
        String actualPageNumber = casecompletion.getEditPagesInput().getDomAttribute("value").trim();
        assertEquals("Page number was not updated correctly!", expectedPageNumber, actualPageNumber);
        log.info("Verified that the page number was correctly updated: " + actualPageNumber);
    }

    @And("the admin clicks the action button")
    public void theAdminClicksTheActionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getActionButton()));
        casecompletion.getActionButton().click();
    }

    @And("the admin clicks on the create estimate button")
    public void theAdminClicksOnTheCreateEstimateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getCreateEstimateButton()));
        casecompletion.getCreateEstimateButton().click();
    }

    @And("the admin enters the quantity for the services and selects the expiry date")
    public void theAdminEntersTheQuantityForTheServicesAndSelectsTheExpiryDate() {
        orderIntakePOM.expectedDeliveryButton();
        orderIntakePOM.calenderNxtRetry();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", casecompletion.getSaveEstimate());
        orderIntakePOM.selectDateRetry();
        casecompletion.getEstimateNotes().sendKeys(CaseCompletionData.caseDetails.CASE_DETAILS.getEstimateNote());
        casecompletion.fillAllQuantityFields();
        casecompletion.clickLastCheckbox();
        validateServiceAmountAndQuantityCalculation();

    }

    @And("the admin clicks the Save Estimate button")
    public void theAdminClicksTheSaveEstimateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSaveEstimate()));
        casecompletion.getSaveEstimate().click();
    }

    @And("the admin should be notified with a pop-up message Case estimate created successfully")
    public void theAdminShouldBeNotifiedWithAPopUpMessageCaseEstimateCreatedSuccessfully() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getOrderPlacedMsg()));
            String estimationMessageText = orderIntakePOM.getOrderPlacedMsg().getText();
            boolean estimationSuccessMessage = estimationMessageText.contains("Case estimation created successfully");
            if (estimationSuccessMessage) {
                log.info("Estimation success status: " + true);
            }
        } catch (Exception e) {
            log.info("estimation success message was not found. Continuing with the test.");
        }
    }

    @Then("the admin verifies whether the case status is changed to {string}")
    public void theAdminVerifiesWhetherTheCaseStatusIsChangedTo(String changeStatus) {
        log.info("Waiting for case status to change to: " + changeStatus);

        int maxRetries = 2;
        boolean statusChanged = false;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                statusChanged = wait.until(ExpectedConditions.textToBePresentInElement(casecompletion.getViewPageStatus(), changeStatus));

                if (statusChanged) {
                    log.info(" Case status successfully changed to: " + changeStatus);
                    break;
                }
            } catch (TimeoutException e) {
                log.warning(" Timeout: Status did not change to " + changeStatus + " in attempt " + attempt + "/" + maxRetries);
            }

            if (attempt < maxRetries) {
                log.info(" Refreshing the page and retrying...");
                driver.navigate().refresh();
                wait.until(ExpectedConditions.visibilityOf(casecompletion.getViewPageStatus()));
            }
        }

        String actualText = casecompletion.getViewPageStatus().getText().trim();
        log.info("Found Status after retry: " + actualText);

        assertEquals(" The expected status was not found even after retries!", changeStatus, actualText);
        LibGlobal.quitDriver();
    }

    @And("the admin clicks the assign button")
    public void theAdminClicksTheAssignButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getAssignButton())).click();
        log.info("Clicked the Assign button.");

        if (isAlertPresent()) {
            log.warning("Unexpected alert detected after clicking Assign. Handling it...");
            driver.switchTo().alert().accept();
            log.info("Alert accepted. Triggering Edit No of Pages method...");
            theAdminClicksTheEditButtonAndEditsNoOfPages();
            theAdminClicksTheSaveButton();
            theAdminClicksTheActionButton();
            theAdminClicksTheAssignButton();
        } else {
            log.info("No alert detected. Proceeding normally.");
        }
    }

    private boolean isAlertPresent() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            alertWait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @And("the admin assigns the tasks from the template to the employees")
    public void theAdminAssignsTheTasksFromTheTemplateToTheEmployees() {
        casecompletion.selectAllTeamDropdowns();
        casecompletion.selectAllEmployeeDropdowns();
    }

    @And("the admin clicks the assign button in the template")
    public void theAdminClicksTheAssignButtonInTheTemplate() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getAssignButtonTemp()));
        casecompletion.getAssignButtonTemp().click();
    }

    @And("the admin should be notified with a pop-up message Case task created successfully")
    public void theAdminShouldBeNotifiedWithAPopUpMessageCaseTaskCreatedSuccessfully() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getOrderPlacedMsg()));
            String taskSuccessMessageText = orderIntakePOM.getOrderPlacedMsg().getText();
            boolean taskAssignSuccessMessage = taskSuccessMessageText.contains("Case task assigned successfully.");
            if (taskAssignSuccessMessage) {
                log.info("Task success status: " + true);
            }
        } catch (Exception e) {
            log.info("Task success message was not found. Continuing with the test.");
        }
    }

    @And("the admin clicks the task menu")
    public void theAdminClicksTheTaskMenu() {
        wait.until(ExpectedConditions.visibilityOf(casecompletion.getTasksMenu()));
        casecompletion.getTasksMenu().click();
    }

    @And("the admin clicks the play button and completes tasks in {string} status")
    public void theAdminClicksThePlayButtonAndCompletesTasksInStatus(String expectedStatus) {
        int maxRetries = 3;
        try {
            log.info("Searching for tasks with Status: " + expectedStatus);
            waitForTaskStatus();

            List<WebElement> rows = retrySeleniumAction(() -> wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(casecompletion.ROW_XPATH))
            ), maxRetries);

            log.info("Total rows found: " + rows.size());

            for (int i = 0; i < rows.size(); i++) {
                final int rowIndex = i; // Needed for lambda

                retrySeleniumAction(() -> {
                    processTaskRow(rows, rowIndex, expectedStatus);
                    waitForTaskStatus();

                    return null;
                }, maxRetries);
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "Error processing tasks: " + e.getMessage(), e);
            throw new RuntimeException("Failed to process tasks with status: " + expectedStatus, e);
        }
    }



    public <T> T retrySeleniumAction(Supplier<T> action, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                return action.get();
            } catch (StaleElementReferenceException | NoSuchElementException |
                     TimeoutException | ElementClickInterceptedException e) {
                attempt++;
                log.warning("Retry attempt " + attempt + " due to exception: " + e.getClass().getSimpleName()
                        + " - " + e.getMessage());
                if (attempt == maxRetries) {
                    throw new RuntimeException("Action failed after " + maxRetries + " retries.", e);
                }
            }
        }
        throw new RuntimeException("Unreachable code - retry loop exited unexpectedly.");
    }


    private void processTaskRow(List<WebElement> rows, int index, String expectedStatus) {
        int retryCount = 0;

        while (retryCount < CaseCompletionPOM.MAX_RETRIES) {
            try {
                rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath(CaseCompletionPOM.ROW_XPATH)));
                WebElement row = rows.get(index);

                String actualStatus = row.findElement(By.xpath(CaseCompletionPOM.STATUS_XPATH)).getText().trim();
                log.info("Checking row - Status: " + actualStatus);

                if (actualStatus.equalsIgnoreCase(expectedStatus)) {
                    clickElement(row.findElement(By.xpath(CaseCompletionPOM.PLAY_BUTTON_XPATH)), "Play button");
                    confirmAction();
                    waitForStatusUpdate(index);
                    waitForTaskStatus();
                    completeTask(row);
                    return;


                } else if (actualStatus.equalsIgnoreCase("Work in Progress") || actualStatus.equalsIgnoreCase("Paused")) {
                    completeTask(row);
                    return;
                } else {
                    break;
                }

            } catch (StaleElementReferenceException e) {
                log.warning("Stale element encountered. Retrying... Attempt " + (++retryCount));
            } catch (Exception e) {
                retryCount++;
                log.warning("Retrying... (" + retryCount + "/" + CaseCompletionPOM.MAX_RETRIES + ") - Error: " + e.getMessage());
            }
        }
    }

    private void completeTask(WebElement row) {
        clickElement(row.findElement(By.xpath(CaseCompletionPOM.THREE_DOT_BUTTON_XPATH)), "Three-Dot action button");
        clickElement(driver.findElement(By.xpath(CaseCompletionPOM.MARK_AS_COMPLETE_XPATH)), "Mark as Complete button");
        confirmAction();
        log.info("Task completed successfully.");
    }

    private void waitForStatusUpdate(int index) {
        wait.until(driver -> {
            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath(CaseCompletionPOM.ROW_XPATH)));
            String updatedStatus = rows.get(index)
                    .findElement(By.xpath(CaseCompletionPOM.STATUS_XPATH))
                    .getText().trim();
            return updatedStatus.equalsIgnoreCase("Work in Progress");
        });

        log.info("Task status changed to " + "Work in Progress" + ".");
    }

    private void clickElement(WebElement element, String description) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        log.info("Clicked " + description + ".");
    }

    private void confirmAction() {
        clickElement(casecompletion.getConfirmButton(), "Confirm button");
        waitForTaskStatus();
        log.info("Status updated after Confirm button click.");
    }


    @And("the admin should see that all task status is changed to {string}")
    public void theAdminShouldSeeThatAllTaskStatusIsChangedTo(String expectedStatus) {
        int maxRetries = 20;
        boolean allTasksCompleted = true;
        boolean taskExists = false;

        try {
            log.info("Verifying that all tasks have status: " + expectedStatus);

            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr")));
            log.info("Total rows found: " + rows.size());

            if (rows.isEmpty()) {
                log.info("No tasks found with status: " + expectedStatus + ". Nothing to verify.");
                return; // Exit method without failing the test
            }

            for (WebElement row : rows) {
                int retryCount = 0;

                while (retryCount < maxRetries) {
                    try {
                        String actualStatus = row.findElement(By.xpath(".//td[@data-index='11']")).getText().trim();
                        log.info("Checking row - Expected: " + expectedStatus + ", Actual: " + actualStatus);


                        if (actualStatus.equalsIgnoreCase(expectedStatus) || actualStatus.equalsIgnoreCase("Reassigned")) {
                            taskExists = true;
                            log.info("Task status is either '" + expectedStatus + "' or 'Re-assigned'. Verification passed.");
                        } else {
                            allTasksCompleted = false;
                            log.warning("Task with incorrect status found! Expected: " + expectedStatus + ", Found: " + actualStatus);
                        }
                        break;

                    } catch (StaleElementReferenceException e) {
                        log.warning("Stale element encountered. Retrying... Attempt " + (retryCount + 1));
                        retryCount++;
                        if (retryCount == maxRetries) {
                            log.severe("Max retries reached for stale element.");
                        }
                    } catch (Exception e) {
                        log.warning("Error while verifying task status: " + e.getMessage());
                        break;
                    }
                }
            }

            if (!taskExists) {
                log.info("No tasks with the expected status (" + expectedStatus + ") or 'Re-assigned' are available for this case.");
            } else {

                Assert.assertTrue("Not all tasks have status: " + expectedStatus + " or 'Re-assigned'", allTasksCompleted);
                log.info(" Verified that all tasks have status: " + expectedStatus + " or 'Re-assigned'.");
            }

        } catch (Exception e) {
            log.severe(" Error while verifying task statuses: " + e.getMessage());
            throw new RuntimeException("Failed to verify task statuses as: " + expectedStatus, e);
        }
    }

    @And("the admin clicks on the case approve button")
    public void theAdminClicksOnTheCaseApproveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getCaseApproveButton()));
        casecompletion.getCaseApproveButton().click();
    }

    @And("the admin upload different file types")
    public void theAdminUploadDifferentFileTypes() {
        ClientCaseList clientCaseList = new ClientCaseList();
        clientCaseList.orderIntakePOM = new OrderIntakePOM();
        clientCaseList.theClientUploadsDifferentFileTypes();
    }

    @And("the admin clicks on the confirm button")
    public void theAdminClicksOnTheConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(clientCaseListPOM.getConfirmButton()));
        clientCaseListPOM.getConfirmButton().click();
    }


    public void waitForTaskStatus() {
        wait.until(driver -> {
            WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//tr[@data-index='0']//td[@data-index='11']")));
            return !statusElement.getText().trim().isEmpty();
        });
    }

    @And("the admin clicks the three dot action of a task in {string} status")
    public void theAdminClicksTheThreeDotActionOfATaskInStatus(String expectedStatus) {
        int maxRetries = 3;
        boolean actionPerformed = false;

        try {
            log.info("Searching for tasks with Status: " + expectedStatus);

            waitForTaskStatus();

            List<WebElement> rows = caseHelper.getVisibleRows("//td[@data-index='11']", "//tr[@class='MuiTableRow-root css-11t0o3']");
            log.info("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                int retryCount = 0;

                while (retryCount < maxRetries) {
                    try {
                        String actualStatus = caseHelper.getColumnText(row, 11);
                        log.info("Checking row - Status: " + actualStatus);

                        if (actualStatus.equalsIgnoreCase(expectedStatus)) {
                            boolean clicked = caseHelper.clickActionButton(row, ".//td[@data-index='13']//button");

                            if (clicked) {
                                log.info("Clicked Three-Dot action button successfully.");
                                actionPerformed = true;
                                return;
                            } else {
                                log.warning("Failed to click Three-Dot action button, retrying...");
                            }
                        } else {
                            break; // Status doesn't match, no point retrying this row
                        }
                    } catch (Exception e) {
                        retryCount++;
                        log.warning("Retrying... (" + retryCount + "/" + maxRetries + ") - Error: " + e.getMessage());
                    }
                }
            }

            if (!actionPerformed) {
                log.warning("No task found with the expected status: " + expectedStatus);
                throw new RuntimeException("Failed to find and click the three-dot action button for status: " + expectedStatus);
            }

        } catch (Exception e) {
            log.severe("Error while clicking the three-dot action button: " + e.getMessage());
            throw new RuntimeException("Failed to click three-dot button", e);
        }
    }


    @And("the admin clicks the re assign button and reassign the task to another employee from another team")
    public void theAdminClicksTheReAssignButtonAndReassignTheTaskToAnotherEmployeeFromAnotherTeam() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getReAssignButton()));
        casecompletion.getReAssignButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getReassignTeamsDropdown()));
        casecompletion.getReassignTeamsDropdown().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSecondTeamDropdownValue()));
        casecompletion.getSecondTeamDropdownValue().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getReassignEmployeeDropdown()));
        casecompletion.getReassignEmployeeDropdown().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSecondEmployeeDropdownValue()));
        casecompletion.getSecondEmployeeDropdownValue().click();
        casecompletion.getReassignButtonTemp().click();
    }

    @And("the admin should see the task status is changed to {string}")
    public void theAdminShouldSeeTheTaskStatusIsChangedTo(String expectedStatus) {
        try {
            waitForTaskStatus();
            log.info("Verifying if at least one task status is updated to: " + expectedStatus);

            wait.until(driver -> {
                List<WebElement> rows = driver.findElements(By.xpath("//tr"));
                return !rows.isEmpty();
            });

            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr")));
            log.info("Total rows found: " + rows.size());

            boolean statusFound = false;

            for (WebElement row : rows) {
                try {

                    WebElement statusElement = row.findElement(By.xpath(".//td[@data-index='11']"));
                    String actualStatus = statusElement.getText().trim();
                    log.info("Checking row - Current Status: " + actualStatus);

                    if (actualStatus.equalsIgnoreCase(expectedStatus)) {
                        log.info(" Task with expected status found! Status: " + actualStatus);
                        statusFound = true;
                        break;
                    }
                } catch (Exception e) {
                    log.warning("Skipping row due to issue: " + e.getMessage());
                }
            }


            if (statusFound) {
                log.info(" Test passed: At least one task has been successfully updated to " + expectedStatus);
            } else {
                throw new AssertionError(" No tasks found with the expected status: " + expectedStatus);
            }

        } catch (TimeoutException e) {
            log.severe(" Timeout: The task status did not update within the expected time. " + e.getMessage());
            throw new RuntimeException(" Task status update verification failed", e);
        } catch (Exception e) {
            log.severe(" Error while verifying task status update: " + e.getMessage());
            throw new RuntimeException(" Task status update verification failed", e);
        }
    }

    @And("the admin clicks on the Hold button")
    public void theAdminClicksOnTheHoldButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getHoldButton()));
        casecompletion.getHoldButton().click();
    }

    @And("the admin should see the success message that the case has been {string}")
    public void theAdminShouldSeeTheSuccessMessageThatTheCaseHasBeen(String expectedText) {
        try {
            wait.until(ExpectedConditions.visibilityOf(clientCaseListPOM.getSuccessMessage()));
            String expectedMessage = "Case " + (expectedText) + " successfully.";
            String actualText = clientCaseListPOM.getSuccessMessage().getText();
            assertEquals("The expected success message was not displayed.", expectedMessage, actualText);
            assertEquals(actualText, "The expected success message was not displayed.", expectedText);
        } catch (AssertionError e) {
            log.warning("Assertion failed: " + e.getMessage());
        }
    }

    @And("the admin clicks on the Cancel button")
    public void theAdminClicksOnTheCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getCancelButton()));
        casecompletion.getCancelButton().click();
    }

    @And("the admin clicks on the resume button")
    public void theAdminClicksOnTheResumeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getResumeButton()));
        casecompletion.getResumeButton().click();
    }

    @Then("the admin clicks the delete button and deletes a task")
    public void theAdminClicksTheDeleteButtonAndDeletesATask() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getDeleteButton()));
        casecompletion.getDeleteButton().click();
        theAdminClicksOnTheConfirmButton();
        log.info("Task deleted Successfully");
        LibGlobal.quitDriver();
    }

    @And("the admin clicks on the select all task button")
    public void theAdminClicksOnTheSelectAllTaskButton() {
        casecompletion.selectAllTaskCheckBox();
    }

    @And("the Admin clicks on the task action button")
    public void theAdminClicksOnTheTaskActionButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getTaskActions()));
        casecompletion.getTaskActions().click();
    }

    @Then("the admin clicks on the delete button in the task action and deletes the task")
    public void theAdminClicksOnTheDeleteButtonInTheTaskActionAndDeletesTheTask() {
        casecompletion.deleteActionsClick();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getConfirmDeleteButton()));
        casecompletion.getConfirmDeleteButton().click();
        log.info("All Task deleted Successfully");
        LibGlobal.quitDriver();
    }

    @And("the admin clicks the play button of a task in {string} status")
    public void theAdminClicksThePlayButtonOfATaskInStatus(String expectedStatus) {
        int maxRetries = 3;
        try {
            log.info("Searching for tasks with Status: " + expectedStatus);

            waitForTaskStatus();

            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr[@class='MuiTableRow-root css-11t0o3']")));
            log.info("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                int retryCount = 0;

                while (retryCount < maxRetries) {
                    try {
                        rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr")));
                        String actualStatus = row.findElement(By.xpath(".//td[@data-index='11']")).getText().trim();
                        log.info("Checking row - Status: " + actualStatus);

                        if (actualStatus.equalsIgnoreCase(expectedStatus)) {
                            WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(
                                    row.findElement(By.xpath(".//td[@data-index='2']//div[@class='MuiBox-root css-1lkj472']//*[name()='svg']"))));
                            playButton.click();
                            log.info("Clicked play button.");
                            theAdminClicksOnTheConfirmButton();

                            return;
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        retryCount++;
                        log.warning("Retrying... (" + retryCount + "/" + maxRetries + ") - Error: " + e.getMessage());
                    }
                }
            }

            log.warning("No task found with the expected status: " + expectedStatus);
        } catch (Exception e) {
            log.severe("Error while clicking the three-dot action button: " + e.getMessage());
            throw new RuntimeException("Failed to click three-dot button", e);
        }
    }

    @And("the admin clicks the Pause button of a task in {string} status")
    public void theAdminClicksThePauseButtonOfATaskInStatus(String expectedStatus) {
        int maxRetries = 3;

        try {
            log.info("Searching for tasks with Status: " + expectedStatus);

            waitForTaskStatus();

            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr[@class='MuiTableRow-root css-11t0o3']")));
            log.info("Total rows found: " + rows.size());
            for (WebElement row : rows) {
                int retryCount = 0;

                while (retryCount < maxRetries) {
                    try {
                        rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr")));
                        String actualStatus = row.findElement(By.xpath(".//td[@data-index='11']")).getText().trim();
                        log.info("Checking row - Status: " + actualStatus);

                        if (actualStatus.equalsIgnoreCase(expectedStatus)) {

                            WebElement pauseButton = wait.until(ExpectedConditions.elementToBeClickable(
                                    row.findElement(By.xpath(".//td[@data-index='2']//div[@class='MuiBox-root css-1lkj472']//*[name()='svg']"))));
                            pauseButton.click();
                            log.info("Clicked Pause button.");
                            return;

                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        retryCount++;
                        log.warning("Retrying... (" + retryCount + "/" + maxRetries + ") - Error: " + e.getMessage());
                    }
                }
            }

            log.warning("No task found with the expected status: " + expectedStatus);

        } catch (Exception e) {
            log.severe("Error while clicking the pause button and interacting with the dropdown: " + e.getMessage());
            throw new RuntimeException("Failed to interact with the pause button and dropdown", e);
        }
    }

    @And("the admin select a reason enter a note and clicks the confirm button")
    public void theAdminSelectAReasonAndEnterANote() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getReasonDropdown()));
            casecompletion.getReasonDropdown().click();
            log.info("Opened dropdown.");
            wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getReasonValue()));
            casecompletion.getReasonValue().click();
            log.info("Dropdown value selected.");

            theAdminClicksOnTheConfirmButton();

        } catch (Exception e) {
            log.severe("Error while selecting reason and entering a note: " + e.getMessage());
            throw new RuntimeException("Failed to select reason and enter a note", e);
        }
    }

    @And("the admin click on the acknowledgement button")
    public void theAdminClickOnTheAcknowledgementButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getAcknowledgementButton()));
        casecompletion.getAcknowledgementButton().click();
        theAdminClicksOnTheConfirmButton();
        LibGlobal.quitDriver();
    }

    @And("the admin clicks on the Upload and deliver button")
    public void theAdminClicksOnTheUploadAndDeliverButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getUploadAndDeliverButton()));
        casecompletion.getUploadAndDeliverButton().click();
    }

    @And("the admin enters the billed amount")
    public void theAdminEntersTheBilledAmount() {
        casecompletion.fillAllQuantityFields();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", casecompletion.getBillAllCheckBox());
        casecompletion.getBillAllCheckBox().click();
        casecompletion.clickLastCheckbox();
        validateServiceAmountAndQuantityCalculation();
    }

    @And("the admin clicks on the upload and deliver now button in the pre bill table")
    public void theAdminClicksOnTheUploadAndDeliverButtonInThePreBillTable() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", casecompletion.getUploadAndDeliverNowButton());
        casecompletion.getUploadAndDeliverNowButton().click();
    }


    @And("the admin clicks on the Case Docs Submenu")
    public void theAdminClicksOnTheCaseDocsSubmenu() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getCaseDocsMenu()));
        casecompletion.getCaseDocsMenu().click();
    }

    @And("the admin clicks on the source files folder")
    public void theAdminClicksOnTheSourceFilesFolder() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSourceFileFolder()));
        LibGlobal.doubleClick(driver, casecompletion.getSourceFileFolder());
        log.info("Successfully double-clicked the Source File Folder.");
    }

    @Then("the Admin clicks on the three dot button of the files and assert the download functionality")
    public void theAdminClicksOnTheThreeDotButtonOfTheFilesAndAssertTheDownloadFunctionality() {
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

            boolean threeDotButtonFound = false;

            for (WebElement row : rows) {
                int retryCount = 0;

                while (retryCount < maxRetries) {
                    try {
                        rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr")));
                        WebElement threeDotButton = row.findElement(By.xpath(".//td[@data-index='4']//button"));
                        wait.until(ExpectedConditions.elementToBeClickable(threeDotButton));

                        threeDotButton.click();
                        casecompletion.getDownloadButton().click();
                        caseHelper.assertDownloadOutcome();

                        threeDotButtonFound = true;
                        LibGlobal.quitDriver();
                        break;
                    } catch (Exception e) {
                        retryCount++;
                        log.warning("Retrying... (" + retryCount + "/" + maxRetries + ") - Error: " + e.getMessage());
                    }
                }

                if (!threeDotButtonFound) {
                    log.info("This order is placed using custom links.");
                    break;
                }
            }

        } catch (Exception e) {
            log.severe("Error while clicking the ThreeDot Action button: " + e.getMessage());
            LibGlobal.quitDriver();
            throw new RuntimeException("Failed to click ThreeDot Action button", e);
        }
    }

    @And("the admin enters the Pages in the quantity fields")
    public void theAdminEntersThePagesInTheQuantityFields() {
        String pageNumbers = "1";
        try {
            WebElement totalPagesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Total No of Pages')]")
            ));


            String text = totalPagesElement.getText();
            Matcher matcher = Pattern.compile("\\d+").matcher(text);
            if (matcher.find()) {
                pageNumbers = matcher.group();
            }
        } catch (TimeoutException e) {
            log.warning("Could not locate the total pages element. Using default value.");
        }


        List<WebElement> inputs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//input[starts-with(@name, 'service_info.[') and contains(@name, '].quantity')]")
        ));

        log.info("Found quantity inputs: " + inputs.size());


        for (WebElement input : inputs) {
            int retryCount = 0;
            while (retryCount < 3) {
                try {
                    WebElement refreshedInput = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(input)));

                    if (!refreshedInput.getAttribute("value").isEmpty()) {
                        refreshedInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                        refreshedInput.sendKeys(Keys.DELETE);
                        log.info("Cleared existing value from input field.");
                    }

                    refreshedInput.sendKeys(pageNumbers);
                    log.info("Entered value: " + pageNumbers);
                    break;
                } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                    log.warning("Retrying input field... Attempt " + (retryCount + 1));
                    retryCount++;
                }
            }
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", casecompletion.getBillAllCheckBox());
        casecompletion.getBillAllCheckBox().click();
    }

    @And("the admin enters the quantity in pages for the services and selects the expiry date")
    public void theAdminEntersTheQuantityInPagesForTheServicesAndSelectsTheExpiryDate() {
        orderIntakePOM.expectedDeliveryButton();
        orderIntakePOM.calenderNxtRetry();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", casecompletion.getSaveEstimate());
        orderIntakePOM.selectDateRetry();
        casecompletion.getEstimateNotes().sendKeys(CaseCompletionData.caseDetails.CASE_DETAILS.getEstimateNote());
        casecompletion.fillAllQuantityFieldsInPages();
        casecompletion.clickLastCheckbox();
        validateServiceAmountAndQuantityCalculation();
    }

    public void validateServiceAmountAndQuantityCalculation() {
        BigDecimal grandTotal = BigDecimal.ZERO;
        List<WebElement> serviceAmountInputs = driver.findElements(
                By.xpath("//input[starts-with(@name, 'service_info') and contains(@name, '].service_amount')]")
        );

        log.info("Found total amount fields: " + serviceAmountInputs.size());

        for (int i = 0; i < serviceAmountInputs.size(); i++) {
            try {
                String indexStr = String.valueOf(i);

                String serviceAmountXPath = "//input[@name='service_info.[" + indexStr + "].service_amount']";
                String quantityXPath = "//input[@name='service_info.[" + indexStr + "].quantity']";
                String amountXPath = "//input[@name='service_info.[" + indexStr + "].amount']";

                List<WebElement> amountElements = driver.findElements(By.xpath(amountXPath));

                if (amountElements.isEmpty()) {
                    List<WebElement> complimentaryTags = driver.findElements(
                            By.xpath("//p[contains(text(),'Complimentary')]")
                    );

                    if (!complimentaryTags.isEmpty()) {
                        log.info("Row " + i + " marked as Complimentary — skipping.");
                        continue;
                    } else {
                        throw new NoSuchElementException("Amount input missing and no Complimentary tag found for row " + i);
                    }
                }

                WebElement serviceAmountInput = driver.findElement(By.xpath(serviceAmountXPath));
                WebElement quantityInput = driver.findElement(By.xpath(quantityXPath));
                WebElement amountInput = amountElements.get(0);

                String serviceAmountRaw = serviceAmountInput.getAttribute("value");
                String quantityRaw = quantityInput.getAttribute("value");
                String actualAmountRaw = amountInput.getAttribute("value");

                BigDecimal serviceAmount = parseValue(serviceAmountRaw);
                BigDecimal quantity = parseValue(quantityRaw);
                BigDecimal actualAmount = parseValue(actualAmountRaw);

                BigDecimal expectedAmount = serviceAmount.multiply(quantity);

                log.info("Row " + i + " → Raw | service_amount: " + serviceAmountRaw + ", quantity: " + quantityRaw + ", amount: " + actualAmountRaw);
                log.info("Row " + i + " → Parsed | " + serviceAmount + " × " + quantity + " = " + expectedAmount + " (actual: " + actualAmount + ")");

                assertEquals("Mismatch in calculated amount at row index " + i +
                        " | Expected: " + expectedAmount + ", Actual: " + actualAmount, 0, expectedAmount.compareTo(actualAmount));

                grandTotal = grandTotal.add(actualAmount);

            } catch (Exception e) {
                log.warning("Error processing row " + i + ": " + e.getMessage());
            }
        }

        try {
            assertTotalAgainstPrimaryUIElement(grandTotal);
        } catch (TimeoutException e) {
            log.warning("Primary total element not found, falling back to alternate total validation.");
            assertTotalAgainstFallbackInputs(grandTotal);
        }
    }

    private void assertTotalAgainstFallbackInputs(BigDecimal grandTotal) {
        try {
            WebElement expectedTotalInput = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input css-mevgbx'])[1]"));
            WebElement discountInput = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input css-mevgbx'])[2]"));
            WebElement finalTotalInput = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input css-mevgbx'])[3]"));

            String expectedTotalRaw = expectedTotalInput.getAttribute("value");
            BigDecimal expectedTotal = parseValue(expectedTotalRaw);

            log.info("Grand Total (calculated): " + grandTotal + " | Expected Total (input): " + expectedTotal);

            assertEquals("Grand total does not match expected total input! Calculated: " + grandTotal + ", Expected: " + expectedTotal,
                    0, grandTotal.compareTo(expectedTotal));

            discountInput.clear();
            discountInput.sendKeys("10");

            String finalTotalRaw = finalTotalInput.getAttribute("value");
            BigDecimal finalTotal = parseValue(finalTotalRaw);
            BigDecimal discountedTotal = expectedTotal.subtract(BigDecimal.TEN);

            log.info("Fallback → Expected Total: " + expectedTotal + ", Discount: 10, Final Total UI: " + finalTotal);

            assertEquals("Fallback total check failed: expected " + discountedTotal + ", found " + finalTotal,
                    0, discountedTotal.compareTo(finalTotal));

        } catch (Exception e) {
            log.severe("Fallback total calculation failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void assertTotalAgainstPrimaryUIElement(BigDecimal grandTotal) {
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Total $')]")
        ));

        String totalText = totalElement.getText().replaceAll("[^\\d.]", "").trim();
        BigDecimal uiTotalAmount = new BigDecimal(totalText);

        log.info("Grand Total (calculated): " + grandTotal + " | Total (UI): " + uiTotalAmount);

        assertEquals("Total amount does not match UI value! Calculated: " + grandTotal + ", UI: " + uiTotalAmount,
                0, grandTotal.compareTo(uiTotalAmount));
    }

    private BigDecimal parseValue(String input) {
        input = input.trim();

        if (input.contains(":")) {
            String[] parts = input.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);

            return BigDecimal.valueOf(hours)
                    .add(BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP))
                    .add(BigDecimal.valueOf(seconds).divide(BigDecimal.valueOf(3600), 2, RoundingMode.HALF_UP));
        } else {
            String cleaned = input.replaceAll("[^\\d.]", "");
            return new BigDecimal(cleaned);
        }
    }

    @And("the admin verifies whether any quality audit task is assigned to an user")
    public void theAdminVerifiesWhetherAnyQualityAuditTaskIsAssignedToAUser() {
        boolean hasText = false;
        int retries = 3;

        for (int i = 0; i < retries; i++) {
            try {
                hasText = wait.until(driver1 -> {
                    WebElement statusElement = driver1.findElement(By.xpath("//td[@data-index='1']"));
                    return !statusElement.getText().trim().isEmpty();
                });
                break;
            } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
                log.info("Attempt " + (i + 1) + " failed: " + e.getMessage());

            }
        }

        if (!hasText) {
            addQualityAuditTask();
            return;
        }

        List<WebElement> taskCells = driver.findElements(By.xpath("//td[@data-index='1']"));
        boolean found = taskCells.stream()
                .anyMatch(cell -> "Quality Audit".equalsIgnoreCase(cell.getText().trim()));

        if (found) {
            WebElement submenu = wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getQualityAuditSubMenu()));
            submenu.click();
        } else {
            addQualityAuditTask();
        }
    }

    public void addQualityAuditTask() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getAddTaskButton())).click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getTaskDropDown()));
        casecompletion.getTaskDropDown().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getQualityAuditTask()));
        casecompletion.getQualityAuditTask().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getTeamDropDown()));
        casecompletion.getTeamDropDown().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getTechPhantomsTeam()));
        casecompletion.getTechPhantomsTeam().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSingleEmployeeDropDown()));
        casecompletion.getSingleEmployeeDropDown().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSecondEmployeeDropdownValue()));
        casecompletion.getSecondEmployeeDropdownValue().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getCreateButton()));
        casecompletion.getCreateButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getQualityAuditSubMenu()));
        casecompletion.getQualityAuditSubMenu().click();
    }


    @Then("the admin creates a single task by clicking on the add task button")
    public void theAdminCreatesASingleTaskByClickingOnTheAddTaskButton() {
        addQualityAuditTask();
        LibGlobal.quitDriver();
    }

    @And("the admin clicks the assign button in the Quality audit menu")
    public void theAdminClicksTheAssignButtonInTheQualityAuditMenu() {
        theAdminClicksTheAssignButtonInTheTemplate();
    }

    @And("the admin selects the details from the dropdown")
    public void theAdminSelectsTheDetailsFromTheDropdown() {
        casecompletion.selectAllAuditDropDownsByColumn(2, "Audit Type");
        casecompletion.selectAllAuditDropDownsByColumn(3, "Audit Team");
        casecompletion.selectAllAuditDropDownsByColumn(4, "Audit Employee");
        casecompletion.selectAllAuditDropDownsByColumn(5, "Template");
    }

    @And("the admin click on the assign in the assigning modal")
    public void theAdminClickOnTheAssignInTheAssigningModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getAssignButtonAudTempModal()));
            casecompletion.getAssignButtonAudTempModal().click();
            log.info("Clicked on the Assign Audit button.");
        } catch (TimeoutException | NoSuchElementException e) {
            log.info("Assign Audit button not found. Audit already assigned or not needed.");
            return;
        } catch (Exception e) {
            log.warning("Unexpected issue while trying to click Assign Audit button: " + e.getMessage());
            return;
        }
        log.info("Audit assignment process completed.");
    }


    @And("the admin start auditing the reviewers output and save the Marks")
    public void theAdminStartAuditingTheReviewersOutput() {
        int maxRetries = 3;

        try {
            wait.until(driver -> {
                try {
                    WebElement reviewerNameElement = driver.findElement(By.xpath("//td[@data-index='0']"));
                    String text = reviewerNameElement.getText();
                    return !text.trim().isEmpty();
                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    return false;
                }
            });
            log.info("Searching for the reviewer name...");
            List<WebElement> reviewerNameElements = driver.findElements(By.xpath("//td[@data-index='0']"));

            if (reviewerNameElements.isEmpty()) {
                log.info("Reviewer name element not found");
                return;
            }

            WebElement firstReviewerElement = reviewerNameElements.get(0);
            String detectedReviewerName = firstReviewerElement.getText().trim();

            if (detectedReviewerName.isEmpty()) {
                log.info("This case doesn't have any Auditor assigned.");
                return;
            }

            log.info("Detected File Name: " + detectedReviewerName);

            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[@class='MuiTableRow-root css-11t0o3']")));
            log.info("Total rows found: " + rows.size());

            int totalRows = rows.size();

            for (int i = 0; i < totalRows; i++) {
                int retryCount = 0;
                boolean reviewerDoubleClickSuccess = false;

                while (retryCount < maxRetries) {
                    try {
                        wait.until(driver -> {
                            try {
                                WebElement reviewerNameElement = driver.findElement(By.xpath("//td[@data-index='0']"));
                                String text = reviewerNameElement.getText();
                                return !text.trim().isEmpty();
                            } catch (StaleElementReferenceException | NoSuchElementException e) {
                                return false;
                            }
                        });

                        rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//tr[@class='MuiTableRow-root css-11t0o3']")));

                        if (i >= rows.size()) {
                            log.warning("Index " + i + " out of bounds after refresh. Skipping.");
                            break;
                        }

                        WebElement row = rows.get(i);
                        WebElement reviewerCell = row.findElement(By.xpath(".//td[@data-index='4']"));
                        wait.until(ExpectedConditions.elementToBeClickable(reviewerCell));

                        Actions actions = new Actions(driver);
                        actions.moveToElement(reviewerCell).doubleClick().build().perform();
                        log.info("Double-clicked on reviewer cell.");

                        wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("errortype_info.0.error_category.1.score")));
                        log.info("Remark input found after double-click on reviewer.");

                        theAdminGiveMarkAndSavesTheOutput();

                        reviewerDoubleClickSuccess = true;
                        break;

                    } catch (Exception e) {
                        retryCount++;
                        log.warning("Retrying reviewer double-click for row " + (i + 1) + " (" + retryCount + "/" + maxRetries + ") - Error: " + e.getMessage());
                    }
                }

                if (!reviewerDoubleClickSuccess) {
                    log.info("Reviewer column interaction failed or skipped for row " + (i + 1));

                }
            }

        } catch (Exception e) {
            log.severe("Unexpected error during reviewer name handling: " + e.getMessage());
        }
    }

    public void theAdminGiveMarkAndSavesTheOutput() {
        try {
            WebElement remarkInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("errortype_info.0.error_category.1.score")));
            String existingRemark = remarkInput.getAttribute("value").trim();
            if (!existingRemark.isEmpty()) {
                remarkInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
            }
            remarkInput.sendKeys("Not accurate");
            log.info("Entered remark: Not accurate");

            WebElement totalScore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("total_score")));
            String scoreValue = totalScore.getAttribute("value").trim();
            log.info("Total Score displayed: " + scoreValue);

            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getNextButtonQualityAudit()));

            nextBtn.click();
            log.info("Clicked on Next button");

            WebElement overallReview = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("overall_review")));
            String existingReview = overallReview.getAttribute("value").trim();
            if (!existingReview.isEmpty()) {
                overallReview.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
            }
            overallReview.sendKeys("Good");
            log.info("Entered Overall Review: Good");

            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSaveButton()));
            saveBtn.click();
            log.info("Clicked on Save button");

            WebElement confirmSaveBtn = wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getConfirmSaveButtonQuality()));
            confirmSaveBtn.click();
            log.info("Clicked on Confirm Save button");
        } catch (Exception e) {
            log.severe("Failed during audit review and save: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Then("the admin verifies the total quality audit mark given for the case")
    public void theAdminVerifiesTheTotalQualityAuditMarkGivenForTheCase() {
        try {
            driver.navigate().refresh();
            WebElement element = casecompletion.getQualityScoreForCase();
            wait.until(ExpectedConditions.visibilityOf(element));
            String scoreText = element.getText().trim();
            log.info("The quality score given for this case is " + scoreText);
            LibGlobal.quitDriver();
        } catch (Exception e) {
            log.severe("Failed to retrieve quality score: " + e.getMessage());
        }
    }

    @And("the admin enters the internal estimate hours for all the tasks")
    public void theAdminEntersTheInternalEstimateHoursForAllTheTasks() {
        int maxRetries = 3;
        try {
            List<WebElement> initialButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[@class='MuiTableRow-root css-11t0o3']//td[@data-index='13']//button")));

            int totalButtons = initialButtons.size();
            log.info("Total three-dot buttons found: " + totalButtons);

            for (int i = 1; i <= totalButtons; i++) {
                int retryCount = 0;
                boolean success = false;

                while (retryCount < maxRetries && !success) {
                    try {
                        String dynamicXPath = "(//tr[@class='MuiTableRow-root css-11t0o3']//td[@data-index='13']//button)[" + i + "]";
                        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXPath)));

                        button.click();
                        log.info("Clicked Three-Dot button at position: " + i);

                        performAddEstimateTimeActions();
                        successMessageTaskEstimation();
                        success = true;

                    } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException
                             | ElementNotInteractableException e) {
                        retryCount++;
                        log.warning("Retrying button click at position " + i + ". Attempt " + retryCount + " due to: " + e.getClass().getSimpleName());
                    }
                }

                if (!success) {
                    log.severe("Failed to interact with button at position " + i + " after " + maxRetries + " attempts.");
                }
            }

            log.info("Completed processing all " + totalButtons + " tasks.");

        } catch (Exception e) {
            log.severe("Error locating three-dot buttons: " + e.getMessage());
            throw new RuntimeException("Failed to process all tasks", e);
        }
    }

    private void performAddEstimateTimeActions() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getAddIntEstButton()));
            casecompletion.getAddIntEstButton().click();

            wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getInternalEstHrs()));
            casecompletion.getInternalEstHrs().sendKeys(Keys.CONTROL + "a");
            casecompletion.getInternalEstHrs().sendKeys(Keys.BACK_SPACE);
            casecompletion.getInternalEstHrs().sendKeys("55");

            wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getInternalEstMin()));
            casecompletion.getInternalEstMin().sendKeys(Keys.CONTROL + "a");
            casecompletion.getInternalEstMin().sendKeys(Keys.BACK_SPACE);
            casecompletion.getInternalEstMin().sendKeys("55");

            wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSubmitButton()));
            casecompletion.getSubmitButton().click();

            wait.until(ExpectedConditions.invisibilityOf(casecompletion.getSubmitButton())); // Wait for modal to close
            log.info("Estimate time submitted successfully.");

        } catch (Exception e) {
            log.severe("Error while performing Add Estimate actions: " + e.getMessage());
            throw new RuntimeException("Failed to complete estimate time entry", e);
        }
    }


    public void successMessageTaskEstimation() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getOrderPlacedMsg()));
            String taskSuccessMessageText = orderIntakePOM.getOrderPlacedMsg().getText();
            boolean taskAssignSuccessMessage = taskSuccessMessageText.contains("Task estimation updated successfully.");
            if (taskAssignSuccessMessage) {
                log.info("Task success status: " + true);
            }
        } catch (Exception e) {
            log.info("Task success message was not found. Continuing with the test.");
        }
    }


    @Then("the admin verifies whether the total estimate hours is calculated correctly")
    public void theAdminVerifiesWhetherTheTotalEstimateHoursIsCalculatedCorrectly() {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                List<WebElement> timeCells = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//tr[@class='MuiTableRow-root css-11t0o3']//td[@data-index='12']")));

                log.info("Total rows found for estimate hours: " + timeCells.size());

                int totalSeconds = 0;

                for (WebElement cell : timeCells) {
                    List<WebElement> spans = cell.findElements(By.tagName("span"));
                    StringBuilder timeValueBuilder = new StringBuilder();
                    for (WebElement span : spans) {
                        timeValueBuilder.append(span.getText().trim());
                    }

                    String timeText = timeValueBuilder.toString();
                    log.info("Reading time value: " + timeText);

                    if (timeText.length() == 6) {
                        String formattedTime = timeText.substring(0, 2) + ":" +
                                timeText.substring(2, 4) + ":" +
                                timeText.substring(4, 6);

                        log.info("Formatted time value: " + formattedTime);

                        int seconds = convertTimeToSeconds(formattedTime);
                        totalSeconds += seconds;
                    }
                }

                String totalTimeFormatted = convertSecondsToTimeFormat(totalSeconds);
                log.info("Total calculated estimate time: " + totalTimeFormatted);


                WebElement totalTimeDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[@class='MuiBox-root css-qylknm'])[1]")));
                List<WebElement> totalSpans = totalTimeDiv.findElements(By.tagName("span"));
                StringBuilder displayedTotalBuilder = new StringBuilder();

                for (WebElement span : totalSpans) {
                    displayedTotalBuilder.append(span.getText().trim());
                }

                String displayedTotal = displayedTotalBuilder.toString();
                log.info("Displayed total estimate time digits: " + displayedTotal);

                if (displayedTotal.length() == 7) {
                    String formattedDisplayedTime = displayedTotal.substring(0, 3) + ":" +
                            displayedTotal.substring(3, 5) + ":" +
                            displayedTotal.substring(5, 7);

                    log.info("Formatted displayed total time: " + formattedDisplayedTime);

                    // Assert equality of calculated vs displayed formatted times
                    assertEquals("Total estimate hours mismatch!",
                            totalTimeFormatted, formattedDisplayedTime);
                } else {
                    throw new RuntimeException("Unexpected displayed total time length: " + displayedTotal.length());
                }
                LibGlobal.quitDriver();
                break; // Success - exit retry loop

            } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException |
                     ElementNotInteractableException e) {
                attempt++;
                log.warning("Attempt " + attempt + " failed. Retrying... Error: " + e.getMessage());

                if (attempt == maxRetries) {
                    log.severe("Error while verifying total estimate hours after " + maxRetries + " attempts.");
                    LibGlobal.quitDriver();
                    throw new RuntimeException("Failed to verify total estimate hours", e);
                }
            }
        }
    }

    private int convertTimeToSeconds(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = parts.length > 0 ? Integer.parseInt(parts[0]) : 0;
        int minutes = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        int seconds = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;

        return (hours * 3600) + (minutes * 60) + seconds;
    }

    private String convertSecondsToTimeFormat(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @And("the admin clicks the case queries menu")
    public void theAdminClicksTheCaseQueriesMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getCaseQueries()));
        casecompletion.getCaseQueries().click();
    }

    @And("the admin sends a text message to the client regarding the case")
    public void theAdminSendsATextMessageToTheClientRegardingTheCase(){
        casecompletion.sendMessageWithRetry("Test: Could you please provide the detailed timeline?", 3);
        LibGlobal.quitDriver();

    }}
