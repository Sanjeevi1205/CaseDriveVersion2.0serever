package stepdefinition;

import java.awt.AWTException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.AdminAndClientMessagePOM;
import pompages.SignupPOM;

public class ClientMessage extends LibGlobal {
    private SignupPOM signupPOM = new SignupPOM();
    private AdminAndClientMessagePOM clientmessage = new AdminAndClientMessagePOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("the user clicks the Messages menu and selects Case Queries")
    public void the_user_clicks_the_messages_menu_and_selects_case_queries() {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getSideMenu()));
        clientmessage.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getMessageMenu()));
        clientmessage.getMessageMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getCaseQueriesMenu()));
        clientmessage.getCaseQueriesMenu().click();
        clientmessage.getSideMenu().click();
    }

    @When("the user clicks the New Query button")
    public void the_user_clicks_the_new_query_button() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getNewQueryButton()));
        clientmessage.getNewQueryButton().click();
    }

    @When("the user selects the query type, case, and enters the message")
    public void the_user_selects_the_query_type_case_and_enters_the_message() {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getCaseSelectionField()));
        clientmessage.getCaseSelectionField().click();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getCaseNames()));
        clientmessage.getFirstCase().click();
        clientmessage.getEnterQuery().sendKeys("New Query");
    }

    @When("the user clicks the Create button")
    public void the_user_clicks_the_create_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getCreateButton()));
        clientmessage.getCreateButton().click();
    }

    @Then("the user verifies the message is displayed on the All Queries page")
    public void the_user_verifies_the_message_is_displayed_on_the_all_queries_page() {
        wait.until(ExpectedConditions.invisibilityOf(clientmessage.getQueryPopup()));
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAllQueriesTab()));
        boolean text = clientmessage.getAllQueriesTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //CaseQuery2
    @When("the user clicks the unread button")
    public void the_user_clicks_the_unread_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getQuery()));
        clientmessage.getQuery().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getUnReadButton()));
        clientmessage.getUnReadButton().click();
    }

    @Then("the user verifies the message is displayed on the Unread page")
    public void the_user_verifies_the_message_is_displayed_on_the_unread_page() {
        clientmessage.getUnReadTab().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getQuery()));
        boolean text = clientmessage.getAllQueriesTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //CaseQuery3
    @When("the user clicks the archive button")
    public void the_user_clicks_the_archive_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getQuery()));
        clientmessage.getQuery().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getArchiveButton()));
        clientmessage.getArchiveButton().click();
    }

    @Then("the user verifies the message is displayed on the Archived page")
    public void the_user_verifies_the_message_is_displayed_on_the_archived_page() {
        clientmessage.getArchiveTab().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex flex-column MuiBox-root css-i6bazn'])[1]")));
        boolean text = clientmessage.getAllQueriesTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //BillingQuery1
    @When("the user clicks the Messages menu and selects Billing Queries")
    public void the_user_clicks_the_messages_menu_and_selects_billing_queries() {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getSideMenu()));
        clientmessage.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getMessageMenu()));
        clientmessage.getMessageMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getCaseQueriesMenu()));
        clientmessage.getBillingQueriesMenu().click();
        clientmessage.getSideMenu().click();
    }

    @When("the user selects the query type, reason, and enters the message")
    public void the_user_selects_the_query_type_reason_and_enters_the_message() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getReasonField()));
        clientmessage.getReasonField().click();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getCaseNames()));
        clientmessage.getFirstCase().click();
        clientmessage.getEnterQuery().sendKeys("New Query");
    }

    @Then("the user verifies the message is displayed on the All Queries billing or technical or other page")
    public void the_user_verifies_the_message_is_displayed_on_the_all_queries_billing_or_technical_or_other_page() {
        wait.until(ExpectedConditions.invisibilityOf(clientmessage.getQueryPopup()));
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAllQueriesBillingTab()));
        boolean text = clientmessage.getAllQueriesBillingTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //BillingQuery2
    @Then("the user verifies the message is displayed on the Unread billing or technical or other page")
    public void the_user_verifies_the_message_is_displayed_on_the_unread_billing_or_technical_or_other_page() {
        clientmessage.getUnReadTab().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getQuery()));
        boolean text = clientmessage.getAllQueriesBillingTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //BillingArchive
    @When("the user clicks the archive billing or technical or other button")
    public void the_user_clicks_the_archive_billing_or_technical_or_other_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getQuery()));
        clientmessage.getQuery().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getArchiveButton()));
        clientmessage.getArchiveButton().click();
    }

    @Then("the user verifies the message is displayed on the Archived billing or technical or other page")
    public void the_user_verifies_the_message_is_displayed_on_the_archived_billing_or_technical_or_other_page() {
        clientmessage.getArchiveTab().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='MuiTypography-root MuiTypography-light css-9ky806'])[1]")));
        boolean text = clientmessage.getAllQueriesBillingTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //TechnicalQuery
    @When("the user clicks the Messages menu and selects Technical Queries")
    public void the_user_clicks_the_messages_menu_and_selects_technical_queries() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getSideMenu()));
        clientmessage.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getMessageMenu()));
        clientmessage.getMessageMenu().click();
        signupPOM.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getCaseQueriesMenu()));
        clientmessage.getTechnicalQueriesMenu().click();
        clientmessage.getSideMenu().click();
    }

    //OtherQuery
    @When("the user clicks the Messages menu and selects Other Queries")
    public void the_user_clicks_the_messages_menu_and_selects_other_queries() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getSideMenu()));
        clientmessage.getSideMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getMessageMenu()));
        clientmessage.getMessageMenu().click();
        signupPOM.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getOtherQueriesMenu()));
        clientmessage.getOtherQueriesMenu().click();
        clientmessage.getSideMenu().click();
    }

    @When("the user selects the query type and enters the message")
    public void the_user_selects_the_query_type_and_enters_the_message() {
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getEnterQuery()));
        clientmessage.getEnterQuery().sendKeys("New Query");
    }
}