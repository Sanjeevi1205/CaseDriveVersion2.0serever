package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.ClientContractPOM;

public class ClientContract extends LibGlobal {
    private ClientContractPOM clientcontract = new ClientContractPOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    //Given is taken from orderintake class
    @When("the client selects the contract sidemenu")
    public void the_client_selects_the_contract_sidemenu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientcontract.getSaSidemenu()));
        clientcontract.getSaSidemenu().click();
        clientcontract.getContractSidemenu().click();
    }

    @When("clicks the Sign button, then clicks it again")
    public void clicks_the_sign_button_then_clicks_it_again() {
        wait.until(ExpectedConditions.elementToBeClickable(clientcontract.getSignButton()));
        clientcontract.getSignButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientcontract.getSignButtonHIPPApage()));
        clientcontract.getSignButtonHIPPApage().click();
    }

    @When("enters the client name, designation, and signature")
    public void enters_the_client_name_designation_and_signature() {
        wait.until(ExpectedConditions.elementToBeClickable(clientcontract.getFullNameField()));
        clientcontract.getFullNameField().sendKeys("Ben");
        clientcontract.getDesignationField().sendKeys("Admin");
        clientcontract.getSignatureField().sendKeys("Ben");
    }

    @When("selects the checkbox to agree")
    public void selects_the_checkbox_to_agree() {
        //clientcontract.getSignatureAgreeCheckbox().click();
    }

    @When("submits the form to complete the process")
    public void submits_the_form_to_complete_the_process() {
        clientcontract.getSubmitButton().click();
    }

    @Then("the client is redirected to the contract page with a view option")
    public void the_client_is_redirected_to_the_contract_page_with_a_view_option() {
        wait.until(ExpectedConditions.elementToBeClickable(clientcontract.getViewButton()));
        boolean view = clientcontract.getViewButton().getText().contains("View");
        System.out.println(view);
    }
}