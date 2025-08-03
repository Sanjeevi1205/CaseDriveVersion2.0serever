package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.ClientDashboardPOM;

public class ClientDashboard extends LibGlobal {
    private ClientDashboardPOM clientdashboard = new ClientDashboardPOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    //Given is taken from OrderIntake class
    @When("the client on the dashboard clicks Cases, Pages, Billed Hours, and Billed Amount to view details")
    public void the_client_on_the_dashboard_clicks_cases_pages_billed_hours_and_billed_amount_to_view_details() {
        wait.until(ExpectedConditions.elementToBeClickable(clientdashboard.getPagesTab()));
        clientdashboard.getPagesTab().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body p-0']")));
        clientdashboard.getBillingTab().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body p-0']")));
        clientdashboard.getBilledAmountTab().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body p-0']")));
        clientdashboard.getCasesTab().click();
    }

    @When("clicks the filter option to check filtered data")
    public void clicks_the_filter_option_to_check_filtered_data() {
        clientdashboard.getCasefilterOption().click();
    }

    @Then("the client sees the respective filtered data")
    public void the_client_sees_the_respective_filtered_data() {
        wait.until(ExpectedConditions.visibilityOfAllElements(clientdashboard.getCasefilterOption()));
        clientdashboard.getTodayFilter().click();
        boolean filter = clientdashboard.getCasefilterOption().getText().contains("Today");
        System.out.println(filter);
        clientdashboard.getYesterdayFilter().click();
        boolean filter1 = clientdashboard.getCasefilterOption().getText().contains("Yesterday");
        System.out.println(filter1);
        LibGlobal.quitDriver();
    }

    @When("the client on the dashboard clicks specified service to view")
    public void the_client_on_the_dashboard_clicks_specified_service_to_view() {
        wait.until(ExpectedConditions.visibilityOf(clientdashboard.getServiceMedicalChronology()));
        clientdashboard.getServiceMedicalChronology().click();
        clientdashboard.getServiceMedicalChronology().click();
        clientdashboard.getServiceIndexing().click();
        clientdashboard.getServiceIndexing().click();
    }

    @When("clicks the filter option to check filtered data on cost analysis")
    public void clicks_the_filter_option_to_check_filtered_data_on_cost_analysis() {
        clientdashboard.getCostfilterOption().click();
    }

    @Then("the client sees the respective filtered data on cost analysis")
    public void the_client_sees_the_respective_filtered_data_on_cost_analysis() {
        wait.until(ExpectedConditions.visibilityOfAllElements(clientdashboard.getCostfilterOption()));
        clientdashboard.getTodayFilter().click();
        boolean filter = clientdashboard.getCostfilterOption().getText().contains("Today");
        System.out.println(filter);
        clientdashboard.getYesterdayFilter().click();
        boolean filter1 = clientdashboard.getCostfilterOption().getText().contains("Yesterday");
        System.out.println(filter1);
        LibGlobal.quitDriver();
    }

    //All Report
    @When("the client clicks the view all reports in Case Analytics and Cost Analysis")
    public void the_client_clicks_the_view_all_reports_in_case_analytics_and_cost_analysis() {
        wait.until(ExpectedConditions.visibilityOfAllElements(clientdashboard.getCaseReportbutton()));
        clientdashboard.getCaseReportbutton().click();
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientdashboard.getCostReportbutton()));
        clientdashboard.getCostReportbutton().click();
    }

    @Then("the client see the case report page")
    public void the_client_see_the_case_report_page() {
        wait.until(ExpectedConditions.visibilityOf(clientdashboard.getCaseReportPage()));
        boolean reportVerification = clientdashboard.getCaseReportPage().getText().contains("Case");
        System.out.println(reportVerification);
        LibGlobal.quitDriver();
    }

    //All Report
    @When("the client clicks the view all reports in Billing Stats and Unpaid Stats")
    public void the_client_clicks_the_view_all_reports_in_billing_stats_and_unpaid_stats() {
        wait.until(ExpectedConditions.visibilityOfAllElements(clientdashboard.getBillingReportbutton()));
        clientdashboard.getBillingReportbutton().click();
        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfAllElements(clientdashboard.getUnpaidReportbutton()));
        clientdashboard.getUnpaidReportbutton().click();
    }

    @Then("the client see the billing report page")
    public void the_client_see_the_billing_report_page() {
        wait.until(ExpectedConditions.visibilityOf(clientdashboard.getBillingReportPage()));
        boolean reportVerification = clientdashboard.getBillingReportPage().getText().contains("Billing");
        System.out.println(reportVerification);
        LibGlobal.quitDriver();
    }
}