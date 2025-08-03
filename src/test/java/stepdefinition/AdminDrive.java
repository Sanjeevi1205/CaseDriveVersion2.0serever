package stepdefinition;

import java.awt.AWTException;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.AdminDrivePOM;

public class AdminDrive extends LibGlobal {
	private AdminDrivePOM adminDrivePOM;
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	private WebElement newCaseButton;

	@When("the Admin clicks the side menu")
	public void theAdminClicksTheSideMenu() {
		adminDrivePOM = new AdminDrivePOM();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getSaSidemenu()));
		adminDrivePOM.getSaSidemenu().click();
	}

	@When("the Admin clicks the drive menu")
	public void the_admin_clicks_the_drive_menu() throws AWTException {
		adminDrivePOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getDrive())).click();
	}

	@When("the Admin opens the with {string} status and selects folder")
	public void the_admin_opens_the_with_status_and_selects_folder(String string) {
		driver.switchTo().frame(adminDrivePOM.getSwitchFrame());
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getClientList())).click();
		driver.switchTo().defaultContent();
	}

	@When("the Admin opens the case files folder {string}")
	public void the_admin_opens_the_case_files_folder(String expectedFolderName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		boolean isFolderFound = false;
		while (!isFolderFound) {
			try {
				List<WebElement> folderNames = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.xpath("//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root')]//span")));
				for (WebElement folder : folderNames) {
					String folderText = folder.getText().trim();
					System.out.println("Checking folder: " + folderText);

					if (folderText.equalsIgnoreCase("Elizabeth Taub")) {
						System.out.println("Folder 'Elizabeth Taub' found. Clicking...");
						folder.click();
						isFolderFound = true;
						break;
					}
				}

				if (!isFolderFound) {
					List<WebElement> nextPageBtn = driver
							.findElements(By.xpath("(//button[contains(@aria-label,'Go to next page')])[1]"));
					if (!nextPageBtn.isEmpty() && nextPageBtn.get(0).isEnabled()) {
						System.out.println("Navigating to the next page...");
						nextPageBtn.get(0).click();
					} else {
						System.out.println("No more pages. Folder not found.");
						break;
					}
				}

			} catch (StaleElementReferenceException e) {
				System.out.println("Stale Element Exception encountered. Retrying...");
			}
		}
		driver.switchTo().defaultContent();
	}

	@When("the Admin opens the client files folder {string}")
	public void the_admin_opens_the_client_files_folder(String string) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		boolean isFolderFound = false;
		while (!isFolderFound) {
			try {
				List<WebElement> folderNames = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
						.xpath("//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root')]//span")));
				for (WebElement folder : folderNames) {
					String folderText = folder.getText().trim();
					System.out.println("Checking folder: " + folderText);
					if (folderText.equalsIgnoreCase("Elizabeth Taub")) {
						System.out.println("Folder 'Elizabeth Taub' found. Clicking...");
						folder.click();
						isFolderFound = true;
						break;
					}
				}

				if (!isFolderFound) {
					List<WebElement> nextPageBtn = driver
							.findElements(By.xpath("(//button[contains(@aria-label,'Go to next page')])[1]"));
					if (!nextPageBtn.isEmpty() && nextPageBtn.get(0).isEnabled()) {
						System.out.println("Navigating to the next page...");
						nextPageBtn.get(0).click();
					} else {
						System.out.println("No more pages. Folder not found.");
						break;
					}
				}

			} catch (StaleElementReferenceException e) {
				System.out.println("Stale Element Exception encountered. Retrying...");
			}
		}
		driver.switchTo().defaultContent();
	}

	@When("the Admin opens the latest case folder")
	public void the_admin_opens_the_latest_case_folder() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getCaseFile())).click();
		driver.switchTo().defaultContent();
	}

	@When("the Admin opens the client case folder {string}")
	public void the_admin_opens_the_client_case_folder(String string) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		boolean isFolderFound = false;
		while (!isFolderFound) {
			try {
				List<WebElement> folderNames = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
						"//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root')] //span[contains(text(), 'Elizabeth Taub MD')]")));
				for (WebElement folder : folderNames) {
					String folderText = folder.getText().trim();
					System.out.println("Checking folder: " + folderText);
					if (folderText.equalsIgnoreCase("Elizabeth Taub MD")) {
						System.out.println("Folder 'Elizabeth Taub MD' found. Clicking...");
						folder.click();
						isFolderFound = true;
						break;
					}
				}
				if (!isFolderFound) {
					List<WebElement> nextPageBtn = driver
							.findElements(By.xpath("(//button[contains(@aria-label,'Go to next page')])[1]"));
					if (!nextPageBtn.isEmpty() && nextPageBtn.get(0).isEnabled()) {
						System.out.println("Navigating to the next page...");
						nextPageBtn.get(0).click();
					} else {
						System.out.println("No more pages. Folder not found.");
						break;
					}
				}

			} catch (StaleElementReferenceException e) {
				System.out.println("Stale Element Exception encountered. Retrying...");
			}
		}
		driver.switchTo().defaultContent();
	}

	@When("the Admin opens the source file folder")
	public void the_admin_opens_the_source_file_folder() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getSourceFile())).click();
		driver.switchTo().defaultContent();
	}

	@When("the Admin opens the new case folder")
	public void the_admin_opens_the_new_case_folder() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getNewCase())).click();
		driver.switchTo().defaultContent();
	}

	@Then("the Admin clicks the three-dot on the file and clicks the open menu")
	public void the_admin_clicks_the_three_dot_on_the_file_and_clicks_the_open_menu() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getFileMenuDrive())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getOpenDrive())).click();
		wait.until(ExpectedConditions.urlContains("/drive"));
		Assert.assertTrue(driver.getCurrentUrl().contains("/drive"));
		LibGlobal.driver.quit();
	}

	@Then("the Admin clicks the three-dot on the file and clicks the details menu")
	public void the_admin_clicks_the_three_dot_on_the_file_and_clicks_the_details_menu() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getFileMenuDrive())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getDetails())).click();
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(adminDrivePOM.getFileDetails())).isDisplayed());
		LibGlobal.driver.quit();
	}

	@Then("the Admin clicks the three-dot on the file and clicks the download menu")
	public void the_admin_clicks_the_three_dot_on_the_file_and_clicks_the_download_menu() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getFileMenuDrive())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getDownloaded())).click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOf(adminDrivePOM.getDownloadedFiles())).isDisplayed());
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
						return file;
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

	@Then("the Admin clicks the three-dot on the file and clicks the copy link")
	public void the_admin_clicks_the_three_dot_on_the_file_and_clicks_the_copy_link() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getFileMenuDrive())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getCopyLink())).click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOf(adminDrivePOM.getCopyLinkConfirmation())).isDisplayed());
		LibGlobal.driver.quit();
	}

	@Then("the Admin clicks the three-dot on the file and clicks the rename")
	public void the_admin_clicks_the_three_dot_on_the_file_and_clicks_the_rename() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getFileMenuDrive())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getRename())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getSave())).click();
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(adminDrivePOM.getNameChange())).isDisplayed());
		LibGlobal.driver.quit();
	}

	@Then("the Admin clicks the three-dot on the file and clicks the bin")
	public void the_admin_clicks_the_three_dot_on_the_file_and_clicks_the_bin() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(adminDrivePOM.getSwitchFrame()));
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getFileMenuDrive())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getBin())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminDrivePOM.getBinConfirm())).click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOf(adminDrivePOM.getBinConfirmMessage())).isDisplayed());
		LibGlobal.driver.quit();
	}
}