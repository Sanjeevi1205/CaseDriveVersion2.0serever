package stepdefinition;

import java.awt.AWTException;
import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.ClientDrivePOM;

public class ClientDrive extends LibGlobal {
    private ClientDrivePOM clientDrivePOM = new ClientDrivePOM();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @When("the client clicks the drive menu")
    public void the_client_clicks_the_drive_menu() throws AWTException {
        clientDrivePOM.scrolldown();
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getDrive())).click();
    }

    @When("the client opens the with {string} status and selects folder")
    public void the_client_opens_the_with_status_and_selects_folder(String status) {
        driver.switchTo().frame(clientDrivePOM.getSwitchFram());
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getClientList())).click();
        driver.switchTo().defaultContent();
    }

    @When("the client opens the case files folder")
    public void the_client_opens_the_case_files_folder() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(clientDrivePOM.getSwitchFram()));
        WebElement latestFolder = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Case Files']")));
        latestFolder.click();
        driver.switchTo().defaultContent();
    }

    @When("the client opens the latest case folder")
    public void the_client_opens_the_latest_case_folder() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(clientDrivePOM.getSwitchFram()));
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getClientname())).click();
        driver.switchTo().defaultContent();
    }

    @When("the client opens the source file folder")
    public void the_client_opens_the_source_file_folder() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(clientDrivePOM.getSwitchFram()));
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getSourceFile())).click();
        driver.switchTo().defaultContent();
    }

    @When("the client opens the new case folder")
    public void the_client_opens_the_new_case_folder() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(clientDrivePOM.getSwitchFram()));
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getNewCase())).click();

    }

    @Then("the client clicks the three-dot on the file and clicks the open menu")
    public void the_client_clicks_the_three_dot_on_the_file_and_clicks_the_open_menu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getFileMenuDrive())).click();
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getOpenDrive())).click();
        wait.until(ExpectedConditions.urlContains("/drive"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/drive"));
        LibGlobal.driver.quit();
    }

    @Then("the client clicks the three-dot on the file and clicks the details menu")
    public void the_client_clicks_the_three_dot_on_the_file_and_clicks_the_details_menu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getFileMenuDrive())).click();
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getDetails())).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(clientDrivePOM.getFileDetails())).isDisplayed());
        LibGlobal.driver.quit();
    }

    @Then("the client clicks the three-dot on the file and clicks the download menu")
    public void the_client_clicks_the_three_dot_on_the_file_and_clicks_the_download_menu() {
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getFileMenuDrive())).click();
        wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getDownloaded())).click();
        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOf(clientDrivePOM.getDownloadedfiles())).isDisplayed());
        String downloadPath = System.getProperty("user.home") + "/Downloads/";
        File downloadFolder = new File(downloadPath);
        File[] filesBefore = downloadFolder.listFiles();
        Wait<File> fluentWait = new FluentWait<>(downloadFolder).withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

        File downloadedFile = fluentWait.until(folder -> {
            File[] filesAfter = folder.listFiles();
            if (filesAfter != null) {
                for (File file : filesAfter) {
                    if (!isInArray(file, filesBefore) && !file.getName().endsWith(".crdownload")
                            && !file.getName().endsWith(".tmp")) {
                        return file; // Return the found file
                    }
                }
            }
            return null;
        });
        Assert.assertNotNull("The downloaded file was not found in the expected directory: " + downloadPath,
                downloadedFile);
        System.out.println("File downloaded: " + downloadedFile.getName());
        LibGlobal.driver.quit();
    }

    private boolean isInArray(File file, File[] filesArray) {
        if (filesArray == null)
            return false;
        for (File f : filesArray) {
            if (f.getName().equals(file.getName())) {
                return true;
            }
        }
        return false;
    }
//
//		@Then("the client clicks the three-dot on the file and clicks the copy link")
//		public void the_client_clicks_the_three_dot_on_the_file_and_clicks_the_copy_link() {
//			  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(clientDrivePOM.getSwitchFram()));
//			    WebElement fileMenu = wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getFileMenuDrive()));
//			    fileMenu.click();
//			    WebElement copyLink = wait.until(ExpectedConditions.elementToBeClickable(clientDrivePOM.getCopyLink()));
//			    copyLink.click();
//			    WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOf(clientDrivePOM.getCopyLinkConfirmation()));
//			    Assert.assertTrue(confirmationMessage.isDisplayed());
//			    LibGlobal.driver.quit();
//		}
}