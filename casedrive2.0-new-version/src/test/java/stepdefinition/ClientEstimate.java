package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.ClientEstimatePOM;

public class ClientEstimate extends LibGlobal {
    private ClientEstimatePOM clientestimate = new ClientEstimatePOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @When("the client clicks the Billing side menu and selects Estimate")
    public void the_client_clicks_the_billing_side_menu_and_selects_estimate() {
        wait.until(ExpectedConditions.visibilityOf(clientestimate.getSideMenu()));
        clientestimate.getSideMenu().click();
        clientestimate.getBillingMenu().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientestimate.getEstimateMenu()));
        clientestimate.getEstimateMenu().click();
        clientestimate.getSideMenu().click();
    }

    @When("the client opens the case awaiting approval")
    public void the_client_opens_the_case_awaiting_approval() {
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(By.xpath("//table"), By.xpath("//tr")));
        wait.until(ExpectedConditions.visibilityOf(clientestimate.getAwaitingApprovalStatus()));
        clientestimate.doubleClick(driver, clientestimate.getAwaitingApprovalStatus());
    }

    @When("the client clicks the Approve Estimate button and then clicks the Confirm button")
    public void the_client_clicks_the_approve_estimate_button_and_then_clicks_the_confirm_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientestimate.getApproveButton()));
        clientestimate.getApproveButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientestimate.getConfirmButton()));
        clientestimate.getConfirmButton().click();
    }

    @Then("the client is notified with a popup message stating that the case estimation was approved successfully")
    public void the_client_is_notified_with_a_popup_message_stating_that_the_case_estimation_was_approved_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientestimate.getApprovepopupMessage()));
        boolean text = clientestimate.getApprovepopupMessage().getText().contains("approved");
        System.out.println("Client estimation of the case is " + text);
        LibGlobal.quitDriver();
    }

    //Decline Approval
    @When("the client clicks the Decline Estimate button and then clicks the Confirm button")
    public void the_client_clicks_the_decline_estimate_button_and_then_clicks_the_confirm_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientestimate.getDeclineButton()));
        clientestimate.getDeclineButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientestimate.getConfirmButton()));
        clientestimate.getConfirmButton().click();
    }

    @Then("the client is notified with a popup message stating that the case estimation was declined successfully")
    public void the_client_is_notified_with_a_popup_message_stating_that_the_case_estimation_was_declined_successfully() {
        wait.until(ExpectedConditions.visibilityOf(clientestimate.getDeclinepopupMessage()));
        boolean text = clientestimate.getDeclinepopupMessage().getText().contains("declined");
        System.out.println("Client estimation of the case is " + text);
        LibGlobal.quitDriver();
    }
}