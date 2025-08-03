package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class AdminDrivePOM extends LibGlobal {
	public AdminDrivePOM() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "email")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement loginbtn;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1bb6tpb']")
	private WebElement saSidemenu;

	@FindBy(xpath = "//span[text()='Drive']")
	private WebElement drive;

	@FindBy(xpath = "//iframe[@src='https://testing-drive.lezdotechmed.com']")
	private WebElement switchFrame;

	@FindBy(tagName = "iframe")
	private WebElement frame;

	@FindBy(xpath = "//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root') and contains(@class, 'css-scze1p')]//span[text()='CaseDrive2.0']")
	private WebElement clientList;

	@FindBy(xpath = "//span[text()='Case Files']")
	private WebElement caseFile;

	@FindBy(xpath = "//span[text()='Source Files']")
	private WebElement sourceFile;

	@FindBy(xpath = "//span[text()='New Case']")
	private WebElement newCase;

	@FindBy(xpath = "(//button[@type='button'])[13]")
	private WebElement nextPageBtn;

	@FindBy(xpath = "(//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root')] //div[contains(@class, 'file_menu')])[3]")
	private WebElement fileMenuDrive;
	
	@FindBy(xpath = "//p[text()='Open']")
	private WebElement openDrive;

	@FindBy(xpath = "//p[text()='Details']")
	private WebElement details;

	@FindBy(xpath = "//p[text()='Download']")
	private WebElement downloaded;

	@FindBy(xpath = "//p[text()='Copy Link']")
	private WebElement copyLink;

	@FindBy(xpath = "//p[text()='Copy Link']")
	private WebElement copyLinkConfirmation;

	@FindBy(xpath = "//p[text()='Share']")
	private WebElement share;

	@FindBy(xpath = "//span[text()='Manage Access']")
	private WebElement manageAccess;

	@FindBy(xpath = "//p[text()='Bin']")
	private WebElement bin;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement binCancel;

	@FindBy(xpath = "//p[text()='Rename']")
	private WebElement rename;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement save;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement renamecancel;

	@FindBy(xpath = "//div[text()='Name changed']")
	private WebElement nameChange;

	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement binConfirm;

	@FindBy(xpath = "//div[text()='File moved to bin successfully']")
	private WebElement binConfirmMessage;

	@FindBy(xpath = "//span[text()='Modified']")
	private WebElement modified;

	@FindBy(xpath = "//span[text()='This Week']")
	private WebElement thisWeek;

	@FindBy(xpath = "//h2[text()='File details']")
	private WebElement fileDetails;

	@FindBy(xpath = "(//button[@type='button'])[1]")
	private WebElement expectedElement;

	@FindBy(xpath = "//p[text()='Download']")
	private WebElement downloadedFiles;
}