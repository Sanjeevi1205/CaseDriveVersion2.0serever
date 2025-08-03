package stepdefinition;

import java.awt.AWTException;
import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseclass.LibGlobal;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.AdminReportPOM;

public class AdminReport extends LibGlobal {
	private AdminReportPOM adminReportPOM = new AdminReportPOM();
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	@When("the Admin clicks the Reports menu and selects the Case Report")
	public void the_admin_clicks_the_reports_menu_and_selects_the_case_report() throws AWTException {
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getReports()));
		adminReportPOM.getReports().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getCases()));
		adminReportPOM.getCases().click();
		adminReportPOM.getSaSidemenu().click();
	}

	@When("clicks the Export button to download the report")
	public void clicks_the_export_button_to_download_the_report() {
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getExport()));
		adminReportPOM.getExport().click();
	}

	@Then("the Admin opens the report to verify the exported Case Report")
	public void the_admin_opens_the_report_to_verify_the_exported_case_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}

	@When("clicks the Add Filter button to filter the data")
	public void clicks_the_add_filter_button_to_filter_the_data() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getAddFilter()));
		adminReportPOM.getAddFilter().click();
		adminReportPOM.clickPositionButton(driver, adminReportPOM.getWeek());

		int retry = 0;
		while (retry < 3) {
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
				wait1.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getExport()));
				adminReportPOM.getExport().click();
				break;
			} catch (StaleElementReferenceException e) {
				retry++;
			}
		}
	}

	@When("clicks the Export button to download the filtered report")
	public void clicks_the_export_button_to_download_the_filtered_report() {
		wait.until(ExpectedConditions.visibilityOf(adminReportPOM.getExport()));
		adminReportPOM.getExport().click();
	}

	@Then("the Admin opens the report to verify the exported Case Filter Report")
	public void the_admin_opens_the_report_to_verify_the_exported_case_filter_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		boolean fileFound = wait.until(driver -> {
			File[] files = folder.listFiles((dir, name) -> name.endsWith(".xlsx"));
			return files != null && files.length > 0;
		});
		Assert.assertTrue("Downloaded report not found", fileFound);
		if (fileFound) {
			File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));
			System.out.println("File downloaded successfully: " + files[0].getAbsolutePath());
			files[0].delete();
		}
		if (driver != null) {
			LibGlobal.quitDriver();

		}
	}

	@When("the Admin clicks the Reports menu and selects the Revenue Report")
	public void the_admin_clicks_the_reports_menu_and_selects_the_revenue_report() throws AWTException {
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getReports()));
		adminReportPOM.getReports().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getRevenue()));
		adminReportPOM.getRevenue().click();
		adminReportPOM.getSaSidemenu().click();
	}

	@When("clicks the Export button to download the Revenue report")
	public void clicks_the_export_button_to_download_the_revenue_report() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[10]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getPagesrevenue()));
		adminReportPOM.getPagesrevenue().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getNoOfPagerevenue()));
		adminReportPOM.getNoOfPagerevenue().click();
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='MuiTablePagination-root MuiBox-root css-8k4lth']")));
		int retry = 0;
		while (retry < 3) {
			try {
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
				wait1.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getExport()));
				adminReportPOM.getExport().click();

				break;
			} catch (StaleElementReferenceException e) {
				retry++;
			}
		}

	}

	@Then("the Admin opens the report to verify the exported Revenue Report")
	public void the_admin_opens_the_report_to_verify_the_exported_revenue_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}

	@When("the Admin clicks the Reports menu and selects the Billing Report")
	public void the_admin_clicks_the_reports_menu_and_selects_the_billing_report() throws AWTException {
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getReports()));
		adminReportPOM.getReports().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getBilling()));
		adminReportPOM.getBilling().click();
		adminReportPOM.getSaSidemenu().click();
	}

	@Then("the Admin opens the report to verify the exported Billing Report")
	public void the_admin_opens_the_report_to_verify_the_exported_billing_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}

	@Then("the Admin opens the report to verify the exported Billing Filter Report")
	public void the_admin_opens_the_report_to_verify_the_exported_billing_filter_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}

	@When("the Admin clicks the Reports menu and selects the Client Statistics Report")
	public void the_admin_clicks_the_reports_menu_and_selects_the_client_statistics_report() throws AWTException {
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getReports())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getClientStatistics())).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		int retry = 0;
		while (retry < 3) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getExport())).click();
				break;
			} catch (StaleElementReferenceException e) {
				retry++;
			}
		}
	}

	@Then("the Admin opens the report to verify the exported Client Statistics Report")
	public void the_admin_opens_the_report_to_verify_the_exported_client_statistics_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}

	@When("the Admin clicks the Reports menu and selects the Employee Statistics Report")
	public void the_admin_clicks_the_reports_menu_and_selects_the_employee_statistics_report() throws AWTException {
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getReports())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getEmployeeStatistics())).click();
		adminReportPOM.getSaSidemenu().click();
	}

	@When("clicks the Top Performance button to navigate to the report page")
	public void clicks_the_top_performance_button_to_navigate_to_the_report_page()
			throws AWTException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getTopPerformers())).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[10]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getPages())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getNoOfPage())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getEmployeeExport())).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInputBase-colorPrimary css-65nzm0']")));
		int retry = 0;
		while (retry < 3) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getEmployeeExport())).click();
				break;
			} catch (StaleElementReferenceException e) {
				retry++;
			}
		}
	}

	@Then("the Admin opens the report to verify the exported Top Performance Report")
	public void the_admin_opens_the_report_to_verify_the_exported_top_performance_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}

	@When("clicks the Client Wise Metrics button to navigate to the report page")
	public void clicks_the_client_wise_metrics_button_to_navigate_to_the_report_page() throws AWTException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getClientWiseMetrics())).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[10]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getPages())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getNoOfPage())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getEmployeeExport())).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInputBase-colorPrimary css-65nzm0']")));
		int retry = 0;
		while (retry < 3) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getEmployeeExport())).click();
				break;
			} catch (StaleElementReferenceException e) {
				retry++;
			}
		}

	}

	@Then("the Admin opens the report to verify the exported Client Wise Metrics Report")
	public void the_admin_opens_the_report_to_verify_the_exported_client_wise_metrics_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}

	@When("the Admin clicks the Reports menu and selects the Partner Report")
	public void the_admin_clicks_the_reports_menu_and_selects_the_partner_report() throws AWTException {
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getReports())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getPartnerStatistics())).click();
		adminReportPOM.getSaSidemenu().click();
	}

	@When("clicks the Export button to download the report PartnerStatistics")
	public void clicks_the_export_button_to_download_the_report_partner_statistics() throws AWTException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		adminReportPOM.scrolldown();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[10]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getPagepartner())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getNoOfPagepartner())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getExportpartner())).click();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//span[@class='MuiTypography-root MuiTypography-body2 MuiTypography-alignCenter css-o9es6e']")));
		int retry = 0;
		while (retry < 3) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(adminReportPOM.getExportpartner())).click();
				break;
			} catch (StaleElementReferenceException e) {
				retry++;
			}
		}
	}

	@Then("the Admin opens the report to verify the exported Partner Statistics Report")
	public void the_admin_opens_the_report_to_verify_the_exported_partner_statistics_report() {
		String downloadPath = System.getProperty("user.home") + "/Downloads/";
		File folder = new File(downloadPath);
		System.out.println("Checking for downloaded report in: " + downloadPath);
		File downloadedFile = wait.until(driver -> Arrays.stream(folder.listFiles())
				.filter(file -> file.getName().endsWith(".xlsx") || file.getName().endsWith(".csv")).findFirst()
				.orElse(null));
		Assert.assertNotNull("Downloaded report not found", downloadedFile);
		System.out.println("Report found: " + downloadedFile.getAbsolutePath());
		downloadedFile.delete();
		LibGlobal.quitDriver();
	}
}