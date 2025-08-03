package pompages;

import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import lombok.Getter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Log
public class CaseCompletionPOM extends LibGlobal {

    public CaseCompletionPOM() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1bb6tpb']//*[name()='svg']")
    private WebElement sideMenu;

    @FindBy(xpath = "(//span[@class='ps-menu-label css-1w0bdvs'])[1]")
    private WebElement casesMenu;

    @FindBy(xpath = "(//span[@class='ps-menu-label css-1w0bdvs'])[1]")
    private WebElement reviewCasesMenu;

    @FindBy(xpath = "//span[normalize-space()='View/Edit']")
    private WebElement viewButton;

    @FindBy(xpath = "//span[normalize-space()='Details']//*[name()='svg']")
    private WebElement editButton;

    @FindBy(xpath = "//input[@name='page_count']")
    private WebElement editPagesInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "(//button[normalize-space()='Action'])[1]")
    private WebElement actionButton;

    @FindBy(xpath = "//button[normalize-space()='Save Estimate']")
    private WebElement saveEstimate;

    @FindBy(xpath = "//li[normalize-space()='Create Estimate']//div[@class='MuiListItemIcon-root css-xvvc1w']//*[name()='svg']")
    private WebElement createEstimateButton;

    @FindBy(xpath = "(//span[contains(@class, 'MuiTypography-root') and contains(@class, 'MuiTypography-light')])[1]")
    private WebElement viewPageStatus;

    @FindBy(xpath = "//textarea[@name='estimate_note']")
    private WebElement estimateNotes;

    @FindBy(xpath = "//input[@name='target_hours']")
    private WebElement targetHours;

    @FindBy(xpath = "//li[normalize-space()='Assign']//div[@class='MuiListItemIcon-root css-xvvc1w']//*[name()='svg']")
    private WebElement assignButton;

    @FindBy(xpath = "//button[normalize-space()='Assign']")
    private WebElement assignButtonTemp;

    @FindBy(xpath = "(//button[normalize-space()='Assign'])[2]")
    private WebElement assignButtonAudTempModal;

    @FindBy(xpath = "//li[normalize-space()='Hold']")
    private WebElement holdButton;

    @FindBy(xpath = "//li[normalize-space()='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//li[normalize-space()='Resume']")
    private WebElement resumeButton;

    @FindBy(xpath = "//li[normalize-space()='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//span[@aria-label='Toggle select all']")
    private WebElement selectAllTasks;

    @FindBy(xpath = "(//input[@id='combo-box-demo'])[1]")
    private WebElement reasonDropdown;

    @FindBy(xpath = "//li[@data-option-index='0']")
    private WebElement reasonValue;

    @FindBy(xpath = "//li[normalize-space()='Acknowledge']")
    private WebElement acknowledgementButton;

    @FindBy(xpath = "//li[normalize-space()='Upload & Deliver']")
    private WebElement uploadAndDeliverButton;

    @FindBy(xpath = "(//div[contains(@class, 'flex')]//span[contains(@class, 'MuiCheckbox-root')])[1]")
    private WebElement billAllCheckBox;

    @FindBy(xpath = "//button[normalize-space()='Upload & Deliver Now']")
    private WebElement uploadAndDeliverNowButton;

    @FindBy(xpath = "//button[normalize-space()='Case Docs']")
    private WebElement caseDocsMenu;

    @FindBy(xpath = "//span[normalize-space()='Source Files']")
    private WebElement sourceFileFolder;

    @FindBy(xpath = "//li[normalize-space()='Download']")
    private WebElement downloadButton;

    @FindBy(xpath = "//span[normalize-space()='Deliverables']")
    private WebElement deliverableButton;

    @FindBy(xpath = "//button[normalize-space()='Quality Audit']")
    private WebElement qualityAuditSubMenu;

    @FindBy(xpath = "//button[normalize-space()='+ Add Task']")
    private WebElement addTaskButton;

    @FindBy(xpath = "//li[normalize-space()='Quality Audit']")
    private WebElement qualityAuditTask;

    @FindBy(xpath = "(//div[@id='demo-simple-select'])[1]")
    private WebElement taskDropDown;

    @FindBy(xpath = "(//div[@id='demo-simple-select'])[2]")
    private WebElement teamDropDown;

    @FindBy(xpath = "(//div[contains(@class, 'MuiAutocomplete-inputRoot')])[1]")
    private WebElement singleEmployeeDropDown;

    @FindBy(xpath = "//li[@data-value='1']")
    private WebElement techPhantomsTeam;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    private WebElement createButton;

    @FindBy(xpath = "//div[@class='MuiBox-root css-7krxd']")
    private WebElement qualityScoreForCase;

    @FindBy(xpath = "//button[normalize-space()='Next']")
    private WebElement nextButtonQualityAudit;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[2]")
    private WebElement confirmSaveButtonQuality;

    @FindBy(xpath="//li[normalize-space()='Add Est Time']")
    private WebElement addIntEstButton;

    @FindBy(xpath="(//input[@type='number'])[1]")
    private WebElement internalEstHrs;

    @FindBy(xpath="(//input[@type='number'])[2]")
    private WebElement internalEstMin;

    @FindBy(xpath = "//button[normalize-space()='Case Queries']")
    private WebElement caseQueries;

    @FindBy(xpath="//div[contains(@class, 'MuiInputBase-root')]//textarea[@placeholder='Type a message...']")
    private WebElement typeAMessage;

    public void sendMessageWithRetry(String message, int maxRetries) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                WebElement messageInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//textarea[contains(@id, ':r')]")
                ));

                messageInput.clear(); // Clear existing text if any
                messageInput.sendKeys(message);
                messageInput.sendKeys(Keys.ENTER); // If Enter submits the message

                log.info("Message sent successfully: " + message);
                return; // Exit after success

            } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException e) {
                attempt++;
                log.warning("Retry attempt " + attempt + " failed due to: " + e.getClass().getSimpleName()
                        + " - " + e.getMessage());
                if (attempt == maxRetries) {
                    throw new RuntimeException("Failed to send message after " + maxRetries + " retries.", e);
                }
            }
        }
    }


    @FindBy(xpath="//*[name()='path' and contains(@d,'M476.59 22')]")
    private WebElement sendAMessageButton;

    @FindBy(xpath="//button[normalize-space()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//td[contains(@class, 'MuiTableCell-root')]//input[contains(@class, 'PrivateSwitchBase-input')]")
    private List<WebElement> freeCheckBoxes;

    public void clickLastCheckbox() {
        if (!freeCheckBoxes.isEmpty()) {
            WebElement lastCheckbox = freeCheckBoxes.get(freeCheckBoxes.size() - 1);

            // Check if checkbox is already selected using isSelected()
            if (!lastCheckbox.isSelected()) {
                lastCheckbox.click();
                log.info("Last checkbox was unchecked and is now clicked.");

                // Optional verification after clicking
                if (lastCheckbox.isSelected()) {
                    log.info("Checkbox is now checked successfully.");
                } else {
                    log.info("Checkbox click failed. It is still unchecked.");
                }
            } else {
                log.info("Last checkbox is already checked. No action taken.");
            }
        } else {
            log.info("No checkboxes found.");
        }
    }

    public void selectAllTaskCheckBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = null;
        int retries = 3;
        while (retries > 0) {
            try {
                element = wait.until(ExpectedConditions.visibilityOf(selectAllTasks));
                element.click();
                break;
            } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                retries--;
                log.info("StaleElementReferenceException encountered. Retrying... Attempts left: " + retries);
                if (retries == 0) {
                    log.info("Failed to click the Select All button after retries.");
                }
            }
        }
    }

    @FindBy(xpath = "(//button[normalize-space()='Action'])[2]")
    private WebElement taskActions;

    @FindBy(xpath = "(//li[@role='menuitem'][normalize-space()='Delete'])[2]")
    private WebElement deleteActions;

    public void deleteActionsClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = null;
        int retries = 3;
        while (retries > 0) {
            try {
                element = wait.until(ExpectedConditions.visibilityOf(deleteActions));
                element.click();
                break;
            } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                retries--;
                log.info("StaleElementReferenceException encountered. Retrying... Attempts left: " + retries);
                if (retries == 0) {
                    log.info("Failed to click the delete button after retries.");
                }
            }
        }
    }


    public void fillAllQuantityFieldsWithValue(String valueToEnter, String label) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> inputs = new ArrayList<>();
        int maxRetries = 3;
        boolean success = false;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                inputs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//input[starts-with(@name, 'service_info.[') and contains(@name, '].quantity')]")
                ));
                log.info("[" + label + "] Found quantity inputs on attempt " + attempt + ": " + inputs.size());
                success = true;
                break;

            } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                log.warning("[" + label + "] Retry " + attempt + ": Issue locating input fields - " + e.getClass().getSimpleName());
            }
        }

        if (!success || inputs.isEmpty()) {
            throw new RuntimeException("[" + label + "] Failed to locate quantity input fields after multiple retries.");
        }

        for (WebElement input : inputs) {
            int retryCount = 0;
            while (retryCount < maxRetries) {
                try {
                    WebElement refreshedInput = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(input)));

                    if (!Objects.requireNonNull(refreshedInput.getAttribute("value")).trim().isEmpty()) {
                        refreshedInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                        refreshedInput.sendKeys(Keys.DELETE);
                        log.info("[" + label + "] Cleared existing value from input field.");
                    }

                    refreshedInput.sendKeys(valueToEnter);
                    log.info("[" + label + "] Entered value: " + valueToEnter);
                    break;

                } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                    log.warning("[" + label + "] Retry " + (retryCount + 1) + ": Issue while entering value - " + e.getClass().getSimpleName());
                    retryCount++;
                }
            }
        }
    }
    public void fillAllQuantityFields() {
        fillAllQuantityFieldsWithValue("20:00:00", "Hours");
    }
    public void fillAllQuantityFieldsInPages() {
        fillAllQuantityFieldsWithValue("100", "Pages");
    }

    @FindBy(xpath = "//button[normalize-space()='Tasks']")
    private WebElement tasksMenu;

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//li[normalize-space()='Case Approve']")
    private WebElement caseApproveButton;

    @FindBy(xpath = "//li[normalize-space()='Re-Assign']")
    private WebElement reAssignButton;

    @FindBy(xpath = "(//input[@id='combo-box-demo'])[2]")
    private WebElement reassignTeamsDropdown;

    @FindBy(xpath = "//div[@class='MuiFormControl-root MuiFormControl-fullWidth MuiTextField-root css-csjari']//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall MuiInputBase-adornedEnd MuiAutocomplete-inputRoot css-1xd3l1f']")
    private WebElement reassignEmployeeDropdown;

    @FindBy(xpath = "//button[normalize-space()='Re-Assign']")
    private WebElement reassignButtonTemp;


    @FindBy(xpath = "//div[contains(@name, 'assigntask.') and contains(@name, '.team_id')]//input[contains(@class, 'MuiAutocomplete-input')]")
    private List<WebElement> teamDropdowns;

    @FindBy(xpath = "//li[@id='combo-box-demo-option-1']")
    private WebElement secondTeamDropdownValue;

    public void selectAllTeamDropdowns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        int maxRetries = 3;

        wait.until(ExpectedConditions.visibilityOfAllElements(teamDropdowns));
        log.info("All team dropdowns are visible. Total found: " + teamDropdowns.size());

        for (int i = 0; i < teamDropdowns.size(); i++) {
            int retryCount = 0;
            while (retryCount < maxRetries) {
                try {
                    WebElement dropdown = teamDropdowns.get(i);

                    WebElement refreshedDropdown = wait.until(ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable(dropdown)));
                    refreshedDropdown.click();
                    log.info("Clicked on team dropdown #" + (i + 1));

                    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//li[starts-with(@id,'combo-box-demo-option-')]")));

                    if (options.isEmpty()) {
                        log.warning("No dropdown options found for dropdown #" + (i + 1));
                        break;
                    }

                    int optionIndex = i % options.size();
                    WebElement optionToSelect = options.get(optionIndex);

                    String optionText = optionToSelect.getText().trim();

                    wait.until(ExpectedConditions.elementToBeClickable(optionToSelect)).click();
                    log.info("Selected team option #" + optionIndex + ": " + optionText);

                    wait.until(driver -> dropdown.getAttribute("value").equals(optionText));

                    break;
                } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                    log.warning("Retrying dropdown #" + (i + 1) + " due to exception: " + e.getMessage());
                    retryCount++;
                } catch (Exception e) {
                    log.severe("Failed to interact with team dropdown #" + (i + 1) + ": " + e.getMessage());
                    break;
                }
            }
        }

        log.info("Completed selection for all team dropdowns.");
    }


    @FindBy(xpath = "//input[contains(@class, 'MuiAutocomplete-input') and not(@id='combo-box-demo')]")
    private List<WebElement> employeeDropdowns;

    @FindBy(xpath = "//li[@data-option-index='0']")
    private WebElement employeeDropdownValue;

    @FindBy(xpath = "//li[@data-option-index='1']")
    private WebElement secondEmployeeDropdownValue;


    public void selectAllEmployeeDropdowns() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        int maxRetries = 3;
        wait.until(ExpectedConditions.visibilityOfAllElements(employeeDropdowns));
        log.info("All employee dropdowns are visible. Total found: " + employeeDropdowns.size());

        for (WebElement dropdown : employeeDropdowns) {
            int retryCount = 0;
            while (retryCount < maxRetries) {
                try {
                    WebElement refreshedDropdown = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(dropdown)));
                    refreshedDropdown.click();
                    log.info("Clicked on an employee dropdown.");


                    WebElement refreshedEmployeeDropdownValue = wait.until(ExpectedConditions.visibilityOf(employeeDropdownValue));
                    refreshedEmployeeDropdownValue.click();
                    log.info("Selected the first employee dropdown value.");

                    break;
                } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                    log.warning("StaleElementReferenceException encountered in employee dropdown. Retrying... Attempt " + (retryCount + 1));
                    retryCount++;
                } catch (Exception e) {
                    log.severe("Failed to interact with an employee dropdown: " + e.getMessage());
                    break;
                }
            }
        }
        log.info("Completed selection for all employee dropdowns.");
    }


    @FindBy(xpath = "(//div[@class='MuiBox-root css-16x4pvs'])[1] ")
    private WebElement auditFirstDropDownValue;


    public void selectAllAuditDropDownsByColumn(int columnIndex, String dropdownTypeDescription) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        int maxRetries = 3;
        String xpath = String.format("//td[%d]//input[@id='free-solo-2-demo']", columnIndex);

        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        } catch (TimeoutException e) {
            log.info("Audit task is already assigned to employees. Skipping dropdown selection for " + dropdownTypeDescription);

            // Attempt to click the cancel button
            try {
                WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Cancel']")));
                cancelBtn.click();
                log.info("Clicked the Cancel button.");
            } catch (Exception cancelEx) {
                log.warning("Could not click the Cancel button: " + cancelEx.getMessage());
            }

            return;
        }

        List<WebElement> dropdowns = driver.findElements(By.xpath(xpath));
        log.info("All " + dropdownTypeDescription + " dropdowns are present. Total found: " + dropdowns.size());

        for (WebElement dropdown : dropdowns) {
            int retryCount = 0;
            while (retryCount < maxRetries) {
                try {
                    WebElement refreshedDropdown = wait.until(ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable(dropdown)));
                    refreshedDropdown.click();
                    log.info("Clicked on a " + dropdownTypeDescription + " dropdown.");

                    WebElement refreshedValue = wait.until(ExpectedConditions.visibilityOf(auditFirstDropDownValue));
                    refreshedValue.click();
                    log.info("Selected the first value in " + dropdownTypeDescription + " dropdown.");

                    break;
                } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                    log.warning("Retrying " + dropdownTypeDescription + " dropdown due to: " + e.getClass().getSimpleName()
                            + ". Attempt " + (retryCount + 1));
                    retryCount++;
                } catch (Exception e) {
                    log.severe("Failed to interact with a " + dropdownTypeDescription + " dropdown: " + e.getMessage());
                    break;
                }
            }
        }

        log.info("Completed selection for all " + dropdownTypeDescription + " dropdowns.");
    }



    public static final int MAX_RETRIES = 3;
    public static final String ROW_XPATH = "//tr[@class='MuiTableRow-root css-11t0o3']";
    public static final String STATUS_XPATH = ".//td[@data-index='11']";
    public static final String THREE_DOT_BUTTON_XPATH = ".//td[@data-index='13']//button";
    public static final String PLAY_BUTTON_XPATH = ".//td[@data-index='2']//div[@class='MuiBox-root css-1lkj472']//*[name()='svg']";
    public static final String MARK_AS_COMPLETE_XPATH = "//li[normalize-space()='Mark as complete']";


    //GenerateInvoice and Invoice POM
    @FindBy(xpath = "(//span[@class='ps-menu-label css-1w0bdvs'])[8]")
    private WebElement billingMenu;

    @FindBy(xpath = "(//span[@class='ps-menu-label css-1w0bdvs'])[10]")
    private WebElement generateInvoiceMenu;

    @FindBy(xpath = "(//tr[@class='MuiTableRow-root css-11t0o3']//td[6])[1]")
    private WebElement pendingVerificationStatus;

    @FindBy(xpath = "//button[text()='Edit Invoice']")
    private WebElement editInvoiceButton;

    @FindBy(xpath = "//tr[@class='MuiTableRow-root css-1p8w471']//td[1]")
    private WebElement dragAndDropFrom;

    @FindBy(xpath = "(//div[contains(@class, 'justify-content-end')])[last()]")
    private WebElement dragAndDropTo;

    @FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input css-mevgbx'])[2]")
    private WebElement discountField;

    @FindBy(xpath = "//button[text()='Update']")
    private WebElement updateButton;

    @FindBy(xpath = "//textarea[@placeholder='Add Note']")
    private WebElement internalNotesText;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//button[text()='Generate Invoice']")
    private WebElement generateInvoiceButton;

    @FindBy(xpath = "//button[text()='Monthly Invoices']")
    private WebElement montlyInvoiceButton;

    @FindBy(xpath = "(//tr[@class='MuiTableRow-root css-11t0o3']//button[@type='button'])[1]")
    private WebElement companyNameThreeDotButton;

    @FindBy(xpath = "//p[text()='View']")
    private WebElement companyviewButton;

    @FindBy(xpath = "//button[text()='Verify Invoice']")
    private WebElement verifyInvoiceButton;

    @FindBy(xpath = "//p[text()='Generate Invoice']")
    private WebElement monthlyGenerateInvoiceButton;

    @FindBy(xpath = "//button[text()='Confirm']")
    private WebElement confirmButton2;

    //Invoice
    @FindBy(xpath = "(//span[@class='ps-menu-label css-1w0bdvs'])[8]")
    private WebElement invoiceMenu;

    @FindBy(xpath = "//tr[@class='MuiTableRow-root css-11t0o3']//td[8]")
    private List<WebElement> statusMenu;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement invoicesaveButton;

    @FindBy(xpath = "//button[text()='Generate']")
    private WebElement generateButton;

    @FindBy(xpath = "//button[text()='Send Invoice']")
    private WebElement sendInvoiceButton;

    @FindBy(xpath = "//div[text()='Invoice sent successfully']")
    private WebElement popupMessage;


    //Estimates
    @FindBy(xpath = "//span[contains(text(),'Estimates')]")
    private WebElement estimatesMenu;

    @FindBy (xpath = "//tr[@data-index='0']//td[@data-index='3']")
    private WebElement statusElement;

    @FindBy(xpath="//button[normalize-space()='Verify & Send']")
    private WebElement verifyAndSendButton;

    @FindBy(xpath="//button[normalize-space()='Decline Estimate']")
    private WebElement declineEstimate;

    @FindBy(xpath="//button[normalize-space()='Approve Estimate']")
    private WebElement approveEstimate;

    @FindBy(xpath="//button[normalize-space()='Edit Estimate']")
    private WebElement editEstimateButton;

    @FindBy(xpath = "//input[@name='service_info.[0].quantity']")
    private WebElement quantityFirstElement;

    @FindBy(xpath = "//button[normalize-space()='Update Estimate']")
    private WebElement updateEstimateButton;

    private void updateQuantityInputField(String value, String logLabel) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int maxRetries = 3;
        int attempts = 0;

        while (true) {
            try {
                WebElement inputField = wait.until(ExpectedConditions.visibilityOf(quantityFirstElement));

                if (!Objects.requireNonNull(inputField.getAttribute("value")).trim().isEmpty()) {
                    inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                    inputField.sendKeys(Keys.DELETE);
                    log.info("Cleared existing value from input field.");
                }

                inputField.sendKeys(value);
                log.info("Entered value: " + value + " into quantity field (" + logLabel + ").");
                break;

            } catch (Exception e) {
                attempts++;
                log.info("Attempt " + attempts + " failed while editing quantity (" + logLabel + "). Error: " + e.getMessage());

                if (attempts >= maxRetries) {
                    log.info("Failed to edit quantity after " + maxRetries + " attempts (" + logLabel + ").");
                    throw e;
                }
            }
        }
    }
    public void editEstimateQuantityHours() {
        updateQuantityInputField("30:00:00", "Hours");
    }

    public void editEstimateQuantityPages() {
        updateQuantityInputField("300", "Pages");
    }


}





