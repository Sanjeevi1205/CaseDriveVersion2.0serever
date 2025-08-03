package stepdefinition;

import java.awt.AWTException;
import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.ClientPaymentPOM;
import pompages.LoginPOM;
import userdata.TestUserData.UserType;

public class ClientPayment extends LibGlobal {
    private ClientPaymentPOM clientpaymentPOM = new ClientPaymentPOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @When("the client clicks the side menu")
    public void the_client_clicks_the_side_menu() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(clientpaymentPOM.getSaSidemenu()));
        clientpaymentPOM.getSaSidemenu().click();
        clientpaymentPOM.scrolldown();
    }

    @When("the client navigates to Billing and selects Invoice")
    public void the_client_navigates_to_billing_and_selects_invoice() throws AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getBillingButton()));
        clientpaymentPOM.getBillingButton().click();
        clientpaymentPOM.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getInvioceButton()));
        clientpaymentPOM.getInvioceButton().click();
        clientpaymentPOM.getSaSidemenu().click();
    }

    @When("the client clicks the Pay Now button redirecting to the payment options page")
    public void the_client_clicks_the_pay_now_button_redirecting_to_the_payment_options_page() {
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getPayNowButton()));
        clientpaymentPOM.getPayNowButton().click();
    }

    @When("the client fills in the card details and clicks the Pay button")
    public void the_client_fills_in_the_card_details_and_clicks_the_pay_button() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//iframe[@role='presentation'])[1]")));
        System.out.println("Page loaded successfully");
        try {
            WebElement iframe = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("(//iframe[@role='presentation'])[1]")));
            driver.switchTo().frame(iframe);

        } catch (Exception e) {
            System.out.println("No iframe found or not necessary to switch");
        }
        WebElement cardTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("card-tab")));
        cardTab.click();
        System.out.println("Card tab clicked");
        driver.switchTo().defaultContent();
        System.out.println("Switched back to default content");
        WebElement cardIframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(cardIframe);
        System.out.println("Switched to card iframe");
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getCardNumber()));
        clientpaymentPOM.getCardNumber().sendKeys(String.valueOf(UserType.CARD_DETAILS.carddetails));
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getExpiationData()));
        clientpaymentPOM.getExpiationData().sendKeys(String.valueOf(UserType.CARD_DETAILS.expiationData));
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getSecurityCode()));
        clientpaymentPOM.getSecurityCode().sendKeys(String.valueOf(UserType.CARD_DETAILS.securityCode));
        driver.switchTo().defaultContent();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 4000) {
            }
            return true;
        });

        clientpaymentPOM.scrollToHelpLink(driver, clientpaymentPOM.getConfirmPay());
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getConfirmPay()));
        clientpaymentPOM.getConfirmPay().click();
    }

    @Then("the client is redirected to the Payment Successful page")
    public void the_client_is_redirected_to_the_payment_successful_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(clientpaymentPOM.getPaymentSucessfully()));
        boolean paymentSuessfully = clientpaymentPOM.getPaymentSucessfully().getText().contains("Payment Successful");
        clientpaymentPOM.quitDriver();
    }

    @When("the client clicks the Download button in the action table")
    public void the_client_clicks_the_download_button_in_the_action_table() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getPaid()));
        clientpaymentPOM.getPaid().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[text()='Download'])[1]")));
        int retry = 0;
        while (retry < 3) {
            try {
                WebElement exportButton = driver.findElement(By.xpath("(//button[text()='Download'])[1]"));
                wait.until(ExpectedConditions.visibilityOf(exportButton));
                break;
            } catch (StaleElementReferenceException e) {
                retry++;
            }
        }
    }

    @Then("the client should verify that the invoice is downloaded successfully")
    public void the_client_should_verify_that_the_invoice_is_downloaded_successfully() {
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        File folder = new File(downloadPath);
        File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
                .filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".pdf")).findFirst()
                .orElse(null));
        Assert.assertNotNull("Downloaded report not found", downloadedFile);
        System.out.println("Downloaded file path: " + downloadedFile.getAbsolutePath());
        LibGlobal.quitDriver();
    }

    @Given("the private client logs in using valid credentials")
    public void the_private_client_logs_in_using_valid_credentials() {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        clientpaymentPOM = new ClientPaymentPOM();
        wait.until(ExpectedConditions.visibilityOf(clientpaymentPOM.getUsername()));
        clientpaymentPOM.getUsername().sendKeys(UserType.TEMPORAY_USER.getUsername());
        clientpaymentPOM.getPassword().sendKeys(UserType.TEMPORAY_USER.getPassword());
        clientpaymentPOM.getLoginbtn().click();
    }

    @When("the client sees the Pay Now button is disabled")
    public void the_client_sees_the_pay_now_button_is_disabled() {
        WebElement payNowButton = wait.until(ExpectedConditions.visibilityOf(clientpaymentPOM.getPayNowButton()));
        if (payNowButton.isEnabled()) {
            throw new AssertionError("Pay Now button is ENABLED, but it should be DISABLED.");
        }
        System.out.println("Verified: Pay Now button is disabled.");
    }

    @Then("the client should remain on the same page with the Pay Now button disabled")
    public void the_client_should_remain_on_the_same_page_with_the_pay_now_button_disabled() {
        String expectedURL = driver.getCurrentUrl(); // Get current page URL before
        WebElement payNowButton = clientpaymentPOM.getPayNowButton();
        if (payNowButton.isEnabled()) {
            throw new AssertionError("Pay Now button is ENABLED, but it should be DISABLED.");
        }
        String actualURL = driver.getCurrentUrl();
        if (!expectedURL.equals(actualURL)) {
            throw new AssertionError("Page URL changed! Expected to stay on: " + expectedURL);
        }
        clientpaymentPOM.driver.quit();
    }

    //Receipts
    @When("the client navigates to Billing and selects Receipts")
    public void the_client_navigates_to_billing_and_selects_receipts() throws AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getBillingButton()));
        clientpaymentPOM.getBillingButton().click();
        clientpaymentPOM.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getReceiptsMenu()));
        clientpaymentPOM.getReceiptsMenu().click();
        clientpaymentPOM.getSaSidemenu().click();
    }

    @When("the client clicks the three dot button of the bill and click the view option")
    public void the_client_clicks_the_three_dot_button_of_the_bill_and_click_the_view_option() {
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getThreeDotButton()));
        clientpaymentPOM.getThreeDotButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getViewOption()));
        clientpaymentPOM.getViewOption().click();
    }

    @Then("the client should navigate to the respective view page")
    public void the_client_should_navigate_to_the_respective_view_page() {
        wait.until(ExpectedConditions.urlContains("client/billing/payments/view/"));
        boolean url = driver.getCurrentUrl().contains("view");
        Assert.assertTrue(url);
        LibGlobal.quitDriver();
    }

    @When("the client clicks the three dot button of the bill and click the download option")
    public void the_client_clicks_the_three_dot_button_of_the_bill_and_click_the_download_option() {
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getThreeDotButton()));
        clientpaymentPOM.getThreeDotButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(clientpaymentPOM.getDownloadOption()));
        clientpaymentPOM.getDownloadOption().click();
    }

    @Then("the client should verify the downloaded file")
    public void the_client_should_verify_the_downloaded_file() {
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        System.out.println(downloadPath);
        File folder = new File(downloadPath);
        File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
                .filter(file -> file.getName().endsWith(".pdf") || file.getName().endsWith(".csv")).findFirst()
                .orElse(null));
        Assert.assertNotNull("Downloaded report not found", downloadedFile);
        downloadedFile.delete();
        LibGlobal.quitDriver();
    }
}