package pompages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class AdminAndClientMessagePOM extends LibGlobal {
	public AdminAndClientMessagePOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//button[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiIconButton-root')])[2]")
    private WebElement sideMenu;

	@FindBy(xpath = "//li//span[text()='Messages']")
	private WebElement messageMenu;
	
	@FindBy (xpath = "//span[text()='Case Queries']")
	private WebElement caseQueriesMenu;
	
	@FindBy (xpath = "(//button[text()='+ New Query'])[1]")
	private WebElement newQueryButton;
	
	@FindBy (xpath = "//input[@value='Case Query']")
	private WebElement queryPopup;
	
	@FindBy (xpath = "//input[@placeholder='Search Case Name']")
	private WebElement caseSelectionField;
	
	@FindBy (xpath = "//div[@class='MuiBox-root css-16x4pvs']")
	private List<WebElement> caseNames;
	
	@FindBy (xpath = "(//div[@class='MuiBox-root css-16x4pvs'])[1]")
	private WebElement firstCase;
	
	@FindBy (xpath = "//textarea[@name='content']")
	private WebElement enterQuery;
	
	@FindBy (xpath = "//button[text()='Create']")
	private WebElement createButton;
	
	@FindBy (xpath = "(//span[@class='MuiTypography-root MuiTypography-light css-9ky806'])[1]")
	private WebElement allQueriesTab;
	
	@FindBy (xpath = "(//div[@class='d-flex flex-column MuiBox-root css-i6bazn'])[1]")
	private WebElement query;
	
	@FindBy (xpath = "//span[text()='Unread']")
	private WebElement unReadButton;
	
	@FindBy (xpath = "//button[text()='Unread']")
	private WebElement unReadTab;
	
	@FindBy (xpath = "//span[text()='Archive']")
	private WebElement archiveButton;
	
	@FindBy (xpath = "//button[text()='Archived']")
	private WebElement archiveTab;
	
	@FindBy (xpath = "//span[text()='Billing Queries']")
	private WebElement billingQueriesMenu;
	
	@FindBy (xpath = "(//input[@id='free-solo-2-demo'])[2]")
	private WebElement reasonField;
	
	@FindBy (xpath = "(//span[@class='MuiTypography-root MuiTypography-light css-9ky806'])[1]")
	private WebElement allQueriesBillingTab;
	
	@FindBy (xpath = "//span[text()='Technical Queries']")
	private WebElement technicalQueriesMenu;
	
	@FindBy (xpath = "//span[text()='Other Queries']")
	private WebElement otherQueriesMenu;

	//Admin Message
	@FindBy(xpath = "(//input[@id='free-solo-2-demo'])[2]")
	private  WebElement selectClientField;

	@FindBy(xpath = "//div[@class=' p-4 modal-body']")
	private WebElement queryPopupPage;

	@FindBy(xpath = "(//div[contains(@class, 'd-flex') and contains(@class, 'flex-column') and contains(@class, 'MuiBox-root') and contains(@class, 'css-0')]//span[contains(@class, 'MuiTypography-root')][last()])[last()]")
	private WebElement adminMessage;

	@FindBy(xpath = "(//span[@class='MuiTypography-root MuiTypography-light css-9ky806'])[1]")
	private WebElement adminAllQueriesTab;

	@FindBy (xpath = "(//input[@id='free-solo-2-demo'])[3]")
	private WebElement adminreasonField;

	@FindBy(xpath = "(//div[@class='d-flex align-items-center MuiBox-root css-a2pnoe'])[1]")
	private WebElement adminQuery;

	@FindBy(xpath = "//span[text()='Archive']")
	private WebElement adminArchiveButton;
}