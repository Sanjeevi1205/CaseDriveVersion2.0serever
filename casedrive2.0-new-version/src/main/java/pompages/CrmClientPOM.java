package pompages;

import java.time.Duration;
import java.util.List;

import lombok.Getter;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import userdata.ClientTeamsData;
import utils.RandomEmail;

@Log
@Getter
public class CrmClientPOM extends LibGlobal {
	public CrmClientPOM() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'CRM')]")
	private WebElement cRMButton;

	@FindBy(xpath= "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1bb6tpb']//*[name()='svg']")
	private WebElement saSidemenu;

	@FindBy(xpath = "//span[contains(text(),'Leads')]")
	private WebElement leadsMenu;

	@FindAll(@FindBy(xpath = "//td[@data-index='7']"))
	private List<WebElement> statusColumns;

	@FindBy(xpath="//tr[@data-index='0']//td[@data-index='7']")
	private WebElement statusElement;

	@FindBy(xpath = "//div[@aria-label='Rows per page']")
	private WebElement rowperpage;

	@FindBy(xpath = "//li[normalize-space()='500']")
	private WebElement maximumPage;

	@FindBy(xpath = "(//button[text()='Convert'])[1]")
	private WebElement convertButton;

	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[8]")
	private WebElement company;

	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[9]")
	private WebElement organization;

	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[10]")
	private WebElement manager;

	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[11]")
	private WebElement division;

	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[12]")
	private WebElement clientType;

	@FindBy(xpath="(//button[text()='Edit'])[1]")
	private WebElement editButton;

	@FindBy(xpath = "//*[name()='path' and contains(@d,'M13 7h-2v4')]")
	private WebElement companyIcon;

	@FindBy(xpath = "//button[normalize-space()='Confirm']")
	private WebElement createCompany;

	@FindBy(xpath= "(//div[@tabindex='-1'][@class='MuiAutocomplete-option MuiBox-root css-0'])[1]")
	private WebElement dropDownValue;

	public void retryClickDropDownValue() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = null;
		int retries = 3;
		while (retries > 0) {
			try {
				element = wait.until(ExpectedConditions.visibilityOf(dropDownValue));
				element.click();
				break;
			} catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
				retries--;
				log.info("StaleElementReferenceException encountered. Retrying... Attempts left: " + retries);
				if (retries == 0) {
					log.info("Failed to click the dropdown value after retries.");
				}
			}
		}}
	@FindBy (xpath= "(//button[text()='Convert'])[3]")
	private WebElement convertinPopup;

	@FindBy (xpath= "//span[normalize-space()='Client Type']")
	private WebElement waitForTextClientType;

	@FindBy(xpath="(//button[text()='Save'])[1]")
	private WebElement saveButton;

	@FindBy(xpath="//tr[@data-index='0']//td[@data-index='3']//button[@tabindex='0']")
	private WebElement doesclientIdTable;

	@FindBy(xpath = "//span[normalize-space()='Send Invite Link']")
	private WebElement sendInvitationLink;

	@FindBy(xpath="//button[normalize-space()='Invite']")
	private WebElement inviteButton;

	@FindBy(xpath="//ol[@dir='ltr']//li")
	private WebElement invitesendSucccesstoaster;

	@FindBy(xpath="//button[normalize-space()='Cancel']")
	private WebElement cancelButton;

	public void retryCancelButton() {
		int retries = 3;
		Duration timeout = Duration.ofSeconds(20);
		LibGlobal.clickWithRetry(driver, cancelButton, retries, timeout);
	}

	@FindBy(xpath = "//div[@class='modal-content']")
	private WebElement confirmationPopUp;

	@FindBy(xpath="//div[@class='p-5 MuiBox-root css-0']")
	private WebElement convertPOPup;

	@FindBy(xpath="//div[@data-option-index='3']")
	private WebElement companyValue;

	@FindBy(xpath="//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary Mui-error MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-sizeSmall MuiInputBase-adornedEnd MuiAutocomplete-inputRoot css-1xd3l1f']//button[@title='Open']//*[name()='svg']")
	private WebElement companyAltDroElement;

	@FindBy(xpath="(//button[@type='button'])[36]")
	private WebElement threedotButton;

	@FindBy(xpath="//span[normalize-space()='View']")
	private WebElement viewbutton;

	@FindBy(xpath="(//span[normalize-space()='Delete'])[1]")
	private WebElement deleteButton;

	@FindBy(xpath="//button[normalize-space()='Confirm']")
	private WebElement confirmDeleteButton;

	@FindBy(xpath="(//div[@class='MuiBox-root css-0'])[7]")
	private WebElement clientProfile;

	@FindBy(xpath="(//span[@class='MuiTypography-root MuiTypography-bold css-1xxw3ug'])")
	private WebElement viewProfile;

	@FindBy(xpath="//div[text()='Lead deleted']")
	private WebElement deletemessage;

	@FindBy(xpath="//tbody/tr[1]/td[9]/button[1]//*[name()='svg']//*[name()='path' and contains(@d,'M6 10c-1.1')]")
	private WebElement latestClientThreeDot;

	@FindBy(xpath = "//span[contains(text(),'Clients')]")
	private WebElement clientsMenu;

	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[11]")
	private WebElement taskTemplateDropdown;

	@FindBy(xpath="//div[@data-option-index='0']")
	private WebElement taskTemplateValue;

	@FindBy(xpath = "//button[normalize-space()='Create']")
	private WebElement createButton;

	@FindBy(xpath="//input[@name='first_name']")
	private WebElement firstName;

	@FindBy(xpath="//input[@name='last_name']")
	private WebElement lastName;

	@FindBy(xpath="//input[@name='email']")
	private WebElement mailInput;

	@FindBy(xpath="//input[@name='phone']")
	private WebElement phoneNumber;

	@FindBy(xpath="//input[@name='job_role']")
	private WebElement jobRole;

	@FindBy(xpath="//input[@id='google-map-demo']")
	private WebElement addressInput;

	@FindBy(xpath="(//div[contains(@class, 'MuiAutocomplete-inputRoot')])[2]")
	private WebElement assignedManagerDropDown;

	@FindBy(xpath="(//div[contains(@class, 'MuiAutocomplete-inputRoot')])[3]")
	private WebElement clientCategoryDropDown;

	@FindBy(xpath="(//div[contains(@class, 'MuiAutocomplete-inputRoot')])[4]")
	private WebElement divisionDropDown;

	@FindBy(xpath="//div[@data-option-index='0']")
	private WebElement firstOption;

	public void retryFirstValue() {
		int retries = 3;
		Duration timeout = Duration.ofSeconds(20);
		LibGlobal.clickWithRetry(driver, firstOption, retries, timeout);
	}

	@FindBy(xpath="(//div[contains(@class, 'MuiAutocomplete-inputRoot')])[10]")
	private WebElement stateDropdown;

	@FindBy(xpath = "(//li[@id='grouped-demo-option-0'])[1]")
	private WebElement stateDropDownValue;

	@FindBy(xpath="//div[@data-option-index='1']")
	private WebElement secondOption;

	@FindBy(xpath = "(//div[contains(@class, 'MuiAutocomplete-inputRoot')]//input[@id='free-solo-2-demo'])[9]")
	private WebElement serviceSelector;

	@FindBy(xpath="//div[@id='free-solo-2-demo-option-0']")
	private WebElement serviceValue1;

	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[10]")
	private WebElement createPageTaskTemplateDropdown;

	@FindBy(xpath="//span[contains(text(),'Companies')]")
	private WebElement companiesMenu;

	@FindBy(xpath="//input[@name='name']")
	private WebElement companyName;

	@FindBy(xpath="//button[normalize-space()='Create company']")
	private WebElement createCompanyButton;


	public void selectFirstOptionInSixDropdowns() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		for (int i = 5; i <= 8; i++) {
			String dropdownXPath = "(//div[contains(@class, 'MuiAutocomplete-inputRoot')])[" + i + "]";
			WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropdownXPath)));
			LibGlobal.clickWithRetry(driver, dropdown, 3, Duration.ofSeconds(5));
			LibGlobal.clickWithRetry(driver, secondOption, 3, Duration.ofSeconds(5));

		}}



}
