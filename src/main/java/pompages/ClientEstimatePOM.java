package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class ClientEstimatePOM extends LibGlobal {
	public ClientEstimatePOM() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1asfma']")
    private WebElement sideMenu;
	
	@FindBy (xpath = "(//div[@class='MuiBox-root css-0'])[3]")
	private WebElement billingMenu;
	
	@FindBy (xpath = "//span[text()='Estimates']")
	private WebElement estimateMenu;
	
	@FindBy (xpath = "(//span[text()='Awaiting Approval'])[1]")
	private WebElement awaitingApprovalStatus;
	
	@FindBy (xpath = "//button[text()='Approve Estimate']")
	private WebElement approveButton;
	
	@FindBy (xpath = "//button[text()='Decline Estimate']")
	private WebElement declineButton;
	
	@FindBy (xpath = "//button[text()='Confirm']")
	private WebElement confirmButton;
	
	@FindBy (xpath = "//div[text()='Case estimation approved successfully.']")
	private WebElement approvepopupMessage;
	
	@FindBy (xpath = "//div[text()='Case estimation declined successfully.']")
	private WebElement declinepopupMessage;
}
