package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class ClientDrivePOM extends LibGlobal {
	public ClientDrivePOM() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "email")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@class='next-btn btn-15']")
	private WebElement loginbtn;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1asfma']")
	private WebElement saSidemenu;

	@FindBy(xpath = "//span[text()='Drive']")
	private WebElement drive;

	@FindBy(xpath = "//iframe[@src='https://testing-drive.lezdotechmed.com']")
	private WebElement switchFram;

	@FindBy(xpath = "//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root') and contains(@class, 'css-scze1p')]//span[text()='Test']")
	private WebElement clientList;

	@FindBy(xpath = "//span[text()='Case Files']")
	private WebElement caseFile;

	@FindBy(xpath = "(//div[@class='MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 MuiCard-root row-align-between gap-2 p-2 css-scze1p'])[4]")
	private WebElement clientname;

	@FindBy(xpath = "//span[text()='Source Files']")
	private WebElement sourceFile;

	@FindBy(xpath = "//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root') and contains(@class, 'css-scze1p')]//span[text()='New Case']")
	private WebElement newCase;

	@FindBy(xpath = "(//div[contains(@class, 'MuiPaper-root') and contains(@class, 'MuiCard-root')] //div[contains(@class, 'file_menu')])[1]")
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

	@FindBy(xpath = "//span[text()='Modified']")
	private WebElement modified;

	@FindBy(xpath = "//span[text()='This Week']")
	private WebElement thisWeek;

	@FindBy(xpath = "//h2[text()='File details']")
	private WebElement fileDetails;

	@FindBy(xpath = "(//button[@type='button'])[1]")
	private WebElement expectedElement;

	@FindBy(xpath = "//p[text()='Download']")
	private WebElement downloadedfiles;

}
