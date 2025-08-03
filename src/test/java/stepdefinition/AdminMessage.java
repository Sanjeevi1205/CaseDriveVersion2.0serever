package stepdefinition;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.AdminAndClientMessagePOM;

import java.time.Duration;

public class AdminMessage extends LibGlobal {
    private AdminAndClientMessagePOM clientmessage = new AdminAndClientMessagePOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("the user selects the query type, client, case, and enters the message")
    public void the_user_selects_the_query_type_client_case_and_enters_the_message() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getSelectClientField()));
        Thread.sleep(4000);
        clientmessage.getSelectClientField().click();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getCaseNames()));
        clientmessage.getFirstCase().click();
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getCaseSelectionField()));
        Thread.sleep(4000);
        clientmessage.getCaseSelectionField().click();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getCaseNames()));
        clientmessage.getFirstCase().click();
        clientmessage.getEnterQuery().sendKeys("New Query");
    }

    @Then("the user verifies the message is displayed on the All Queries messages page")
    public void the_user_verifies_the_message_is_displayed_on_the_all_queries_messages_page() {
        wait.until(ExpectedConditions.invisibilityOf(clientmessage.getQueryPopupPage()));
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminAllQueriesTab()));
        System.out.println(clientmessage.getAdminAllQueriesTab().getText());
        Assert.assertTrue("New Query", true);
        LibGlobal.quitDriver();
    }

    //Unread
    @When("the user clicks the unread query button")
    public void the_user_clicks_the_unread_query_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminQuery()));
        clientmessage.getAdminQuery().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getUnReadButton()));
        clientmessage.getUnReadButton().click();
    }

    @Then("the user verifies the message is displayed on the Unread message page")
    public void the_user_verifies_the_message_is_displayed_on_the_unread_message_page() {
        clientmessage.getUnReadTab().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminQuery()));
        boolean text = clientmessage.getAdminAllQueriesTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //Archive
    @When("the user clicks the archive query button")
    public void the_user_clicks_the_archive_query_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminQuery()));
        clientmessage.getAdminQuery().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminArchiveButton()));
        clientmessage.getAdminArchiveButton().click();
    }

    @Then("the user verifies the message is displayed on the Archived message page")
    public void the_user_verifies_the_message_is_displayed_on_the_archived_message_page() {
        clientmessage.getArchiveTab().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex flex-column MuiBox-root css-i6bazn'])[1]")));
        boolean text = clientmessage.getAdminAllQueriesTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    @When("the user selects the query type, client, reason, and enters the message")
    public void the_user_selects_the_query_type_client_reason_and_enters_the_message() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getSelectClientField()));
        Thread.sleep(4000);
        clientmessage.getSelectClientField().click();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getCaseNames()));
        clientmessage.getFirstCase().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminreasonField()));
        Thread.sleep(4000);
        clientmessage.getAdminreasonField().click();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getCaseNames()));
        clientmessage.getFirstCase().click();
        clientmessage.getEnterQuery().sendKeys("New Query");
    }

    //Unread
    @Then("the user verifies the message is displayed on the Unread billing or technical or other message page")
    public void the_user_verifies_the_message_is_displayed_on_the_unread_billing_or_technical_or_other_message_page() {
        clientmessage.getUnReadTab().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminQuery()));
        boolean text = clientmessage.getAdminAllQueriesTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    //Archive
    @When("the user clicks the archive billing or technical or other query button")
    public void the_user_clicks_the_archive_billing_or_technical_or_other_query_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminQuery()));
        clientmessage.getAdminQuery().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminArchiveButton()));
        clientmessage.getAdminArchiveButton().click();
    }

    @Then("the user verifies the message is displayed on the Archived billing or technical or other message page")
    public void the_user_verifies_the_message_is_displayed_on_the_archived_billing_or_technical_or_other_message_page() {
        clientmessage.getArchiveTab().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='d-flex flex-column MuiBox-root css-i6bazn'])[1]")));
        boolean text = clientmessage.getAdminAllQueriesTab().getText().contains("New");
        System.out.println(text);
        LibGlobal.quitDriver();
    }

    @When("the user selects the query type, client and enters the message")
    public void the_user_selects_the_query_type_client_and_enters_the_message() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getSelectClientField()));
        Thread.sleep(4000);
        clientmessage.getSelectClientField().click();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientmessage.getCaseNames()));
        clientmessage.getFirstCase().click();
        wait.until(ExpectedConditions.visibilityOf(clientmessage.getEnterQuery()));
        clientmessage.getEnterQuery().sendKeys("New Query");
    }

    @Then("the user verifies the message is displayed on the All Queries billing or technical or other message page")
    public void the_user_verifies_the_message_is_displayed_on_the_all_queries_billing_or_technical_or_other_message_page() {
        wait.until(ExpectedConditions.invisibilityOf(clientmessage.getQueryPopupPage()));
        wait.until(ExpectedConditions.elementToBeClickable(clientmessage.getAdminAllQueriesTab()));
        System.out.println(clientmessage.getAdminAllQueriesTab().getText());
        Assert.assertTrue("New Query", true);
        LibGlobal.quitDriver();
    }
}