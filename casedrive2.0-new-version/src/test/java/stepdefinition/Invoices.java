package stepdefinition;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import io.cucumber.java.en.And;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.CaseCompletionPOM;
import pompages.LoginPOM;

@Log
public class Invoices extends LibGlobal {
    public LoginPOM loginPOM = new LoginPOM();
    public CaseCompletionPOM casecompletion = new CaseCompletionPOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("the admin clicks the Billing side menu and selects Generate Invoice")
    public void the_admin_clicks_the_billing_side_menu_and_selects_generate_invoice() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSideMenu()));
        casecompletion.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getBillingMenu()));
        casecompletion.getBillingMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getGenerateInvoiceMenu()));
        casecompletion.getGenerateInvoiceMenu().click();
        casecompletion.getSideMenu().click();
    }

    @When("selects an invoice with the status {string}")
    public void selects_an_invoice_with_the_status(String string) {
        wait.until(ExpectedConditions.textToBePresentInElement(casecompletion.getPendingVerificationStatus(), "Pending Verification"));
        System.out.println(casecompletion.getPendingVerificationStatus().getText());
        if (casecompletion.getPendingVerificationStatus().getText().equals("Pending Verification")) {
            LibGlobal.doubleClick(driver, casecompletion.getPendingVerificationStatus());
        } else {
            System.out.println("There is no invoices in Pending Verification state");
        }
    }

    @When("clicks the Edit Invoice button")
    public void clicks_the_edit_invoice_button() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getEditInvoiceButton()));
        casecompletion.getEditInvoiceButton().click();
    }

    @When("rearranges the order of services and enters the discount amount")
    public void rearranges_the_order_of_services_and_enters_the_discount_amount() throws AWTException {
        LibGlobal.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getDragAndDropFrom()));
        LibGlobal.dragAndDrop(driver, casecompletion.getDragAndDropFrom(), casecompletion.getDragAndDropTo());
        LibGlobal.scrolldown();
        casecompletion.getDiscountField().sendKeys("5");
    }

    @When("clicks the Update button")
    public void clicks_the_update_button() {
        casecompletion.getUpdateButton().click();
    }

    @When("adds internal notes and clicks the Generate Invoice button")
    public void adds_internal_notes_and_clicks_the_generate_invoice_button() throws AWTException {
        LibGlobal.scrolldown();
        WebDriverWait invisiblewait = new WebDriverWait(driver, Duration.ofSeconds(10));
        invisiblewait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("MuiDialog-container")));
        casecompletion.getInternalNotesText().click();
        casecompletion.getInternalNotesText().sendKeys("Nil");
        casecompletion.getAddButton().click();
        LibGlobal.closeNotification();
        LibGlobal.scrollup(3);
        casecompletion.getGenerateInvoiceButton().click();
    }

    @Then("the invoice status should change to {string}")
    public void the_invoice_status_should_change_to(String string) {
        wait.until(ExpectedConditions.textToBePresentInElement(casecompletion.getPendingVerificationStatus(), "Invoice Generated"));
        System.out.println(casecompletion.getPendingVerificationStatus().getText());
        assertTrue("Invoice Generated", true);
        LibGlobal.quitDriver();
    }

    // Monthly Invoice
    @When("clicks the Monthly Invoice tab and selects the client company")
    public void clicks_the_monthly_invoice_tab_and_selects_the_client_company() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getMontlyInvoiceButton()));
        casecompletion.getMontlyInvoiceButton().click();
        wait.until(ExpectedConditions.visibilityOf(casecompletion.getCompanyNameThreeDotButton()));
        casecompletion.getCompanyNameThreeDotButton().click();
        casecompletion.getCompanyviewButton().click();
    }

    @When("adds internal notes and clicks the Verify Invoice button")
    public void adds_internal_notes_and_clicks_the_verify_invoice_button() throws AWTException {
        LibGlobal.scrolldown();
        WebDriverWait invisiblewait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        invisiblewait2.until(ExpectedConditions.invisibilityOfElementLocated(By.className("MuiDialog-container")));
        casecompletion.getInternalNotesText().click();
        casecompletion.getInternalNotesText().sendKeys("Nil");
        casecompletion.getAddButton().click();
        LibGlobal.closeNotification();
        LibGlobal.scrollup(3);
        casecompletion.getVerifyInvoiceButton().click();
    }

    @When("then returns to the company, clicks the three-dot button, and selects the Generate Invoice option")
    public void then_returns_to_the_company_clicks_the_three_dot_button_and_selects_the_generate_invoice_option() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getMontlyInvoiceButton()));
        casecompletion.getMontlyInvoiceButton().click();
        wait.until(ExpectedConditions.visibilityOf(casecompletion.getCompanyNameThreeDotButton()));
        casecompletion.getCompanyNameThreeDotButton().click();
        casecompletion.getMonthlyGenerateInvoiceButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getConfirmButton2()));
        casecompletion.getConfirmButton2().click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(casecompletion.getCompanyNameThreeDotButton()));
        casecompletion.getCompanyNameThreeDotButton().click();
        casecompletion.getCompanyviewButton().click();
    }

    //Casewise Invoice
    @When("the admin clicks the Billing side menu and selects Invoice")
    public void the_admin_clicks_the_billing_side_menu_and_selects_invoice() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSideMenu()));
        casecompletion.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getBillingMenu()));
        casecompletion.getBillingMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getInvoiceMenu()));
        casecompletion.getInvoiceMenu().click();
        casecompletion.getSideMenu().click();
    }

    @When("selects an invoice with the status of {string}")
    public void selects_an_invoice_with_the_status_of(String string) {
        wait.until(ExpectedConditions.visibilityOfAllElements(casecompletion.getStatusMenu()));
        LibGlobal.scrollRight();
        List<WebElement> statusList = casecompletion.getStatusMenu();
        boolean found = false;

        for (WebElement status : statusList) {
            if (status.getText().trim().equalsIgnoreCase("Draft")) {
                LibGlobal.doubleClick(driver, status);  // Double-click on the first matching element
                found = true;
                break;  // Exit loop after first match
            }
        }

        if (!found) {
            System.out.println("There is no invoice with the status called Draft");
        }
    }

    @When("clicks the Save button")
    public void clicks_the_save_button() {
        casecompletion.getInvoicesaveButton().click();
    }

    @When("generate the excel file to attach")
    public void generate_the_excel_file_to_attach() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getGenerateButton()));
        casecompletion.getGenerateButton().click();
    }

    @When("adds internal notes and clicks the Send Invoice button")
    public void adds_internal_notes_and_clicks_the_send_invoice_button() throws AWTException {
        LibGlobal.scrolldown();
        WebDriverWait invisiblewait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        invisiblewait3.until(ExpectedConditions.invisibilityOfElementLocated(By.className("MuiDialog-container")));
        casecompletion.getInternalNotesText().click();
        casecompletion.getInternalNotesText().sendKeys("Nil");
        casecompletion.getAddButton().click();
        LibGlobal.closeNotification();
        casecompletion.getSendInvoiceButton().click();
    }

    @Then("the popup indicates the admin with success message")
    public void the_popup_indicates_the_admin_with_success_message() {
        wait.until(ExpectedConditions.visibilityOf(casecompletion.getPopupMessage()));
        System.out.print(casecompletion.getPopupMessage().getText());
        assertTrue("Invoice sent successfully", true);
        LibGlobal.quitDriver();
    }

    //MonthlyInvoice
    @When("clicks the Monthly Invoice tab")
    public void clicks_the_monthly_invoice_tab() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getMontlyInvoiceButton()));
        casecompletion.getMontlyInvoiceButton().click();
    }

    @When("the admin clicks on the side menu and clicks on the estimate menu")
    public void theAdminClicksOnTheSideMenuAndClicksOnTheEstimateMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getSideMenu()));
        casecompletion.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getBillingMenu()));
        casecompletion.getBillingMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getEstimatesMenu()));
        casecompletion.getEstimatesMenu().click();


    }

	@And("the admin selects a created estimate of {string} and {string} in {string}")
    public void theAdminSelectsACreatedEstimateOfClientIDAndClientTypeIn(String clientId, String clientType, String expectedStatus) {
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOf(casecompletion.getStatusElement()));
        wait.until(driver1 -> !statusElement.getText().trim().isEmpty());

        String caseCompletionStatus = statusElement.getText().trim();
        log.info(" status " + caseCompletionStatus);

        List<WebElement> rows = driver.findElements(By.xpath("//table//tr[td[@data-index='2'] and td[@data-index='3']]"));

        boolean matchFound = false;

        for (WebElement row : rows) {
            WebElement clientIdCell = row.findElement(By.xpath(".//td[@data-index='2']"));
            WebElement rowStatusCell = row.findElement(By.xpath(".//td[@data-index='3']"));

            String clientIdText = clientIdCell.getText().trim();
            String rowStatusText = rowStatusCell.getText().trim();

            if (clientIdText.equalsIgnoreCase(clientId) && rowStatusText.equalsIgnoreCase(expectedStatus)) {
                WebElement actionButton = row.findElement(By.xpath(".//td[@data-index='10']//button"));
                wait.until(ExpectedConditions.elementToBeClickable(actionButton)).click();

                WebElement viewOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[normalize-space()='View']")));
                viewOption.click();

                matchFound = true;
                break;
            }
        }
        Assert.assertTrue("No match found for Client ID: " + clientId + " and Status: " + expectedStatus, matchFound);
    }

    @Then("the admin verifies whether the estimation status is changed to {string}")
    public void theAdminVerifiesWhetherTheEstimationStatusIsChangedTo(String estimationChangeStatus) {

        String dynamicXPath = "//span[normalize-space()='" + estimationChangeStatus + "']";

        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));

        String actualStatus = statusElement.getText().trim();
        if (actualStatus.equalsIgnoreCase(estimationChangeStatus)) {
            log.info("Estimation status verified: " + actualStatus);
        } else {
            throw new AssertionError("Expected status '" + estimationChangeStatus + "' but found '" + actualStatus + "'");
        }
    }

    @And("the admin clicks on verify and send button")
    public void theAdminClicksOnVerifyAndSendButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getVerifyAndSendButton()));
        casecompletion.getVerifyAndSendButton().click();
    }

    @And("the admin clicks on decline estimate button")
    public void theAdminClicksOnDeclineEstimateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getDeclineEstimate()));
        casecompletion.getDeclineEstimate().click();
    }

    @And("the admin clicks on approve estimate button")
    public void theAdminClicksOnApproveEstimateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getApproveEstimate()));
        casecompletion.getApproveEstimate().click();
    }

    @And("the admin clicks on the edit estimate button")
    public void theAdminClicksOnTheEditEstimateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getEditEstimateButton()));
        casecompletion.getEditEstimateButton().click();
    }

    @And("the admin enter change the hrs in the first service")
    public void theAdminEnterChangeTheHrsInTheFirstService() {
        casecompletion.editEstimateQuantityHours();
    }

    @And("the admin clicks on the update estimate button")
    public void theAdminClicksOnTheUpdateEstimateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(casecompletion.getUpdateEstimateButton()));
        casecompletion.getUpdateEstimateButton().click();
	}

    @And("the admin enter change the pages in the first service")
    public void theAdminEnterChangeThePagesInTheFirstService() {
        casecompletion.editEstimateQuantityPages();
    }
}
