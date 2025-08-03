package stepdefinition;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import baseclass.LibGlobal;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pompages.AdminResourcePOM;

public class AdminResource extends LibGlobal {
	private AdminResourcePOM adminResourcePOM = new AdminResourcePOM();
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	private String downloadPath;
	private long timeoutInSeconds;
	private WebElement dateField;
	private Object expectedMessage;

//Edit
	@When("the Admin clicks the Resource menu and selects Product Sample")
	public void the_admin_clicks_the_resource_menu_and_selects_product_sample() throws AWTException {
		adminResourcePOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getResources()));
		adminResourcePOM.getResources().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getProductSamples()));
		adminResourcePOM.getProductSamples().click();
		adminResourcePOM.getSaSidemenu().click();
	}

	@When("the Admin clicks the three-dot menu button and selects Edit")
	public void the_admin_clicks_the_three_dot_menu_button_and_selects_edit() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[8]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getNoOfPage()));
		adminResourcePOM.getNoOfPage().click();
		adminResourcePOM.getNoOfPageEdit().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThreeDotbtn()));
		adminResourcePOM.getThreeDotbtn().click();

	}

	@When("the Admin accesses the edit window and makes the necessary changes")
	public void the_admin_accesses_the_edit_window_and_makes_the_necessary_changes() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getEdit())).click();
		List<WebElement> productFields = driver.findElements(By.name("product_name"));
		System.out.println("Total 'product_name' fields found: " + productFields.size());
		if (productFields.isEmpty()) {
			System.out.println("ERROR: 'product_name' field not found!");
			return;
		}
		WebElement productNameField = productFields.get(0);
		wait.until(ExpectedConditions.elementToBeClickable(productNameField));
		try {
			productNameField.clear();
			productNameField.sendKeys("LDMC002_Medical Chronology");
		} catch (InvalidElementStateException e) {
			System.out.println("Element not interactable, using JavaScript to set value.");
			LibGlobal.valueinsert(driver, productNameField, "LDMC002_Medical Chronology");
		}
	}

	@Then("the Admin sees the message Updated successfully.")
	public void the_admin_sees_the_message_updated_successfully() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getUpdatedsuccessfullyPopup()));
		boolean popupMessage = adminResourcePOM.getUpdatedsuccessfullyPopup().getText()
				.contains("updated successfully.");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

//Upload
	@When("the Admin clicks the three-dot menu button and selects Upload")
	public void the_admin_clicks_the_three_dot_menu_button_and_selects_upload() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[8]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getNoOfPage()));
		adminResourcePOM.getNoOfPage().click();
		adminResourcePOM.getNoOfPageUpload().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThreeDotbtn()));
		adminResourcePOM.getThreeDotbtn().click();
	}

	@When("the Admin selects a file for upload")
	public void the_admin_selects_a_file_for_upload() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getUpload())).click();
		WebElement fileInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
		fileInput.sendKeys("C:\\Users\\sanjeevi.p\\Downloads\\AWS\\document for AWS\\file.pdf");
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getSaveUpload())).click();
	}

	@Then("the Admin sees the message Success.")
	public void the_admin_sees_the_message_success() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getUploadsucesspopup()));
		boolean popupMessage = adminResourcePOM.getUploadsucesspopup().getText().contains("Success");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

//View
	@When("the Admin clicks the three-dot menu button and selects View")
	public void the_admin_clicks_the_three_dot_menu_button_and_selects_view() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[8]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getNoOfPage()));
		adminResourcePOM.getNoOfPage().click();
		adminResourcePOM.getNoOfPageUpload().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThreeDotbtn()));
		adminResourcePOM.getThreeDotbtn().click();
	}

	@When("the Admin navigates to a new window displaying the files")
	public void the_admin_navigates_to_a_new_window_displaying_the_files() {
		String mainWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getView()));
		adminResourcePOM.getView().click();
		wait.until(d -> driver.getWindowHandles().size() > 1);
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
	}

	@Then("the Admin successfully downloads the file.")
	public void the_admin_successfully_downloads_the_file() {
		String expectedUrlPart = "https://casedrive-staging.s3.amazonaws.com/doc/samples/";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(d -> !driver.getCurrentUrl().equals("about:blank"));
		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(actualUrl.startsWith(expectedUrlPart));
		System.out.println("URL Assertion Passed!");
		LibGlobal.quitDriver();

	}

//CopyFile 
	@When("the Admin clicks the three-dot menu button and selects Copy")
	public void the_admin_clicks_the_three_dot_menu_button_and_selects_copy() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[8]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getNoOfPage()));
		adminResourcePOM.getNoOfPage().click();
		adminResourcePOM.getNoOfPageUpload().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThreeDotbtn()));
		adminResourcePOM.getThreeDotbtn().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getCopy())).click();
	}

	@Then("the Admin successfully copies the file.")
	public void the_admin_successfully_copies_the_file() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement successMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'File copied successfully')]")));
		Assert.assertTrue(successMessage.isDisplayed());
		System.out.println("File copied successfully!");
		LibGlobal.quitDriver();
	}

//Share
	@When("the Admin clicks the three-dot menu button and selects Share")
	public void the_admin_clicks_the_three_dot_menu_button_and_selects_share() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[8]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getNoOfPage()));
		adminResourcePOM.getNoOfPage().click();
		adminResourcePOM.getNoOfPageUpload().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThreeDotbtn()));
		adminResourcePOM.getThreeDotbtn().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getShare())).click();
	}

	@When("the Admin enters the necessary details in the input fields")
	public void the_admin_enters_the_necessary_details_in_the_input_fields() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement sender = wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getSender()));
		sender.sendKeys("testclient61@lezdotechmed.com");
		wait.until(ExpectedConditions.visibilityOf(sender));
		sender.sendKeys(Keys.ARROW_DOWN);
		sender.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getSenderPassword())).clear();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getSenderPassword())).sendKeys("Test@123");
		WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='date']")));
		dateField.sendKeys("05232025");
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getSetDownloadLimit())).sendKeys("30");
	}

	@Then("the Admin sees the message Sample added successfully.")
	public void the_admin_sees_the_message_sample_added_successfully() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement successPopup = wait
				.until(ExpectedConditions.visibilityOf(adminResourcePOM.getSampleAddedSuccessfullyMessagePopup()));
		String actualMessage = successPopup.getText();
		String expectedMessage = "Sample added successfully";
		System.out.println("Admin update status: " + actualMessage);
		Assert.assertTrue("Success message is incorrect!", actualMessage.contains(expectedMessage));
		LibGlobal.quitDriver();
	}

//Delete
	@When("the Admin clicks the three-dot menu button and selects Delete")
	public void the_admin_clicks_the_three_dot_menu_button_and_selects_delete() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='MuiBox-root css-0'])[8]")));
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getNoOfPage()));
		adminResourcePOM.getNoOfPage().click();
		adminResourcePOM.getNoOfPageUpload().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThreeDotbtn()));
		adminResourcePOM.getThreeDotbtn().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getDelete())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getDeleteconfirm())).click();
	}

	@Then("the Admin sees the message Sample deleted successfully.")
	public void the_admin_sees_the_message_sample_deleted_successfully() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getDeleteConfirmMessage()));
		boolean popupMessage = adminResourcePOM.getDeleteConfirmMessage().getText().contains("Deleted successfully");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

//expertise
	@When("the Admin clicks the Resource menu and selects Marketing materials")
	public void the_admin_clicks_the_resource_menu_and_selects_marketing_materials() throws AWTException {
		adminResourcePOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getResources()));
		adminResourcePOM.getResources().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getProductSamples()));
		adminResourcePOM.getMarkertingMaterials().click();
		adminResourcePOM.getSaSidemenu().click();
	}

	@When("the Admin clicks the Add menu button and selects Expertise")
	public void the_admin_clicks_the_add_menu_button_and_selects_expertise() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getAddMarketing())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getTypesItems())).click();
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getExpertise()));
		adminResourcePOM.getExpertise().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThumbnail())).click();
		WebElement thumbnailInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[1]")));
		uploadFileWithJS(driver, thumbnailInput, "src/test/resources/Test File Upload/SamplePNGImage_100kbmb.png");
		wait.until(ExpectedConditions.attributeToBeNotEmpty(thumbnailInput, "value"));
	}

	@When("the Admin uploads thumbnail.jpg and attachment.pdf files and clicks the upload button")
	public void the_admin_uploads_thumbnail_jpg_and_attachment_pdf_files_and_clicks_the_upload_button() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getAttachment())).click();
		WebElement attachment = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[2]")));
		uploadFileWithJS(driver, attachment, "src/test/resources/Test File Upload/SamplePNGImage_100kbmb.png");
		wait.until(ExpectedConditions.attributeToBeNotEmpty(attachment, "value"));
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getUploadItems())).click();
	}

	@Then("the Admin sees expertise the message Created successfully.")
	public void the_admin_sees_expertise_the_message_created_successfully() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getCreatedSucessfully()));
		boolean popupMessage = adminResourcePOM.getCreatedSucessfully().getText().contains("Created successfully.");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

//services
	@When("the Admin clicks the Add menu button and selects services")
	public void the_admin_clicks_the_add_menu_button_and_selects_services() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getAddMarketing())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getTypesItems())).click();
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getServices()));
		adminResourcePOM.getServices().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThumbnail())).click();
		WebElement thumbnailInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[1]")));
		uploadFileWithJS(driver, thumbnailInput, "src/test/resources/Test File Upload/SamplePNGImage_100kbmb.png");
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getAttachment())).click();
		WebElement attachment = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[2]")));
		uploadFileWithJS(driver, attachment, "src/test/resources/Test File Upload/SamplePNGImage_100kbmb.png");
		wait.until(ExpectedConditions.attributeToBeNotEmpty(attachment, "value"));
	}

	@Then("the Admin sees services the message Created successfully")
	public void the_admin_sees_services_the_message_created_successfully() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getCreatedSucessfullyService()));
		boolean popupMessage = adminResourcePOM.getCreatedSucessfullyService().getText()
				.contains("Created successfully.");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

//industry
	@When("the Admin clicks the Add menu button and selects industry")
	public void the_admin_clicks_the_add_menu_button_and_selects_industry() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getAddMarketing())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getTypesItems())).click();
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getIndustry()));
		adminResourcePOM.getIndustry().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getThumbnail())).click();
		WebElement thumbnailInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[1]")));
		uploadFileWithJS(driver, thumbnailInput, "src/test/resources/Test File Upload/SamplePNGImage_100kbmb.png");
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getAttachment())).click();
		WebElement attachment = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='file'])[2]")));
		uploadFileWithJS(driver, attachment, "src/test/resources/Test File Upload/SamplePNGImage_100kbmb.png");
		wait.until(ExpectedConditions.attributeToBeNotEmpty(attachment, "value"));
	}

	@Then("the Admin sees industry the message Created successfully")
	public void the_admin_sees_industry_the_message_created_successfully() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getCreatedSucessfullyIndustry()));
		boolean popupMessage = adminResourcePOM.getCreatedSucessfullyIndustry().getText()
				.contains("Created successfully.");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

//FAQ
	@When("the Admin clicks the Resource menu and selects FAQ Support")
	public void the_admin_clicks_the_resource_menu_and_selects_faq_support() throws AWTException {

		adminResourcePOM.scrolldown();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getResources()));
		adminResourcePOM.getResources().click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getFaq()));
		adminResourcePOM.getFaq().click();
		adminResourcePOM.getSaSidemenu().click();

	}

	@When("the Admin clicks the Add button and selects Case")
	public void the_admin_clicks_the_add_button_and_selects_case() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getFaqadd())).click();
	}

	@When("the Admin clicks the dropdown menu and selects Case FAQ")
	public void the_admin_clicks_the_dropdown_menu_and_selects_case() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getFaqcase())).click();
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getFaqCases())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getPartner())).click();
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getFaqClientselection())).click();
	}

	@When("the Admin enters the question description and answer")
	public void the_admin_enters_the_question_description_and_answer() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getQuestion())).sendKeys("Test");
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getAnswer())).sendKeys("Test");
	}

	@When("the Admin clicks the Upload button")
	public void the_admin_clicks_the_upload_button() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getFaqUploaded()));
		adminResourcePOM.getFaqUploaded().click();
	}

	@Then("the Admin sees the message Created successfully under the section")
	public void the_admin_sees_the_message_created_successfully_under_the_section() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getFaqSucessmessage()));
		boolean popupMessage = adminResourcePOM.getFaqSucessmessage().getText().contains("Created successfully.");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

	@When("the Admin clicks the edit button to change case details")
	public void the_admin_clicks_the_edit_button_to_change_case_details() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getFaqEdit())).click();
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getFaqclear())).clear();
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getFaqInput())).sendKeys("Test");

	}

	@Then("the Admin sees the message Updated successfully under the section")
	public void the_admin_sees_the_message_updated_successfully_under_the_section() {
		wait.until(ExpectedConditions.visibilityOfAllElements(adminResourcePOM.getFaqEditUploaded()));
		adminResourcePOM.getFaqEditUploaded().click();
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getFaqEditUpdatedMessage()));
		boolean popupMessage = adminResourcePOM.getFaqEditUpdatedMessage().getText().contains("Updated successfully.");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}

	@When("the Admin clicks the Delete button")
	public void the_admin_clicks_the_delete_button() {
		wait.until(ExpectedConditions.elementToBeClickable(adminResourcePOM.getFaqDeleteded())).click();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		System.out.println("Alert text: " + alert.getText()); // Optional: just for debug
		alert.accept();

	}

	@Then("the Admin sees the message Deleted successfully. under the section")
	public void the_admin_sees_the_message_deleted_successfully_under_the_section() {
		wait.until(ExpectedConditions.visibilityOf(adminResourcePOM.getFaqDeletePop()));
		boolean popupMessage = adminResourcePOM.getFaqDeletePop().getText().contains("Updated successfully.");
		System.out.println("admin update status: " + popupMessage);
		LibGlobal.quitDriver();
	}
}
