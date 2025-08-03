package stepdefinition;

import java.awt.AWTException;
import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.ClientRetainerInviocePOM;
import userdata.TestUserData.UserType;

public class ClientRetainerInvioce extends LibGlobal {
    private ClientRetainerInviocePOM clientRetainerInviocePOM = new ClientRetainerInviocePOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @When("the client navigates to Billing and selects Retainer Invoice")
    public void the_client_navigates_to_billing_and_selects_retainer_invoice() throws AWTException {
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getBillingButton()));
        clientRetainerInviocePOM.getBillingButton().click();
        clientRetainerInviocePOM.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getRetainerInvioce()));
        clientRetainerInviocePOM.getRetainerInvioce().click();
        clientRetainerInviocePOM.getSaSidemenu().click();
    }

    @When("the client clicks the download button")
    public void the_client_clicks_the_download_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getOpen()));
        clientRetainerInviocePOM.getOpen().click();
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

    @When("the client clicks the Pay Now button")
    public void the_client_clicks_the_pay_now_button() {
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getPayNowButton()));
        clientRetainerInviocePOM.getPayNowButton().click();
    }

    @When("the client is redirected to the payment options page")
    public void the_client_is_redirected_to_the_payment_options_page() {
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
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getCardNumber()));
        clientRetainerInviocePOM.getCardNumber().sendKeys(String.valueOf(UserType.CARD_DETAILS.carddetails));
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getExpiationData()));
        clientRetainerInviocePOM.getExpiationData().sendKeys(String.valueOf(UserType.CARD_DETAILS.expiationData));
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getSecurityCode()));
        clientRetainerInviocePOM.getSecurityCode().sendKeys(String.valueOf(UserType.CARD_DETAILS.securityCode));
        driver.switchTo().defaultContent();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 4000) {
            }
            return true;
        });
        clientRetainerInviocePOM.scrollToHelpLink(driver, clientRetainerInviocePOM.getConfirmPay());
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getConfirmPay()));
        clientRetainerInviocePOM.getConfirmPay().click();
    }

    @Then("the client is redirected to the Payment Successful page for retainer")
    public void the_client_is_redirected_to_the_Payment_Successful_page_for_retainer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(clientRetainerInviocePOM.getPaymentSucessfully()));
        boolean paymentSuessfully = clientRetainerInviocePOM.getPaymentSucessfully().getText()
                .contains("Payment Successful");
        LibGlobal.driver.quit();
    }

    @When("the client applies a filter and then clicks the download button")
    public void the_client_applies_a_filter_and_then_clicks_the_download_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getAddfilter()));
        clientRetainerInviocePOM.getAddfilter().click();
        clientRetainerInviocePOM.clickPositionButton(driver, clientRetainerInviocePOM.getThisMonth());

        int retry = 0;
        while (retry < 3) {
            try {
                WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
                wait1.until(ExpectedConditions.elementToBeClickable(clientRetainerInviocePOM.getDownload()));
                clientRetainerInviocePOM.getDownload().click();
                break;
            } catch (StaleElementReferenceException e) {
                retry++;
            }
        }
    }

    @Then("the client should see the file download")
    public void the_client_should_see_the_file_download() {
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        File folder = new File(downloadPath);
        File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
                .filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".pdf")).findFirst()
                .orElse(null));
        Assert.assertNotNull("Downloaded report not found", downloadedFile);
        System.out.println("Downloaded file path: " + downloadedFile.getAbsolutePath());
        LibGlobal.quitDriver();
    }
}