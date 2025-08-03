package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class ClientReportPOM extends LibGlobal {
	public ClientReportPOM() {
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

	@FindBy(xpath = "(//span[text()='Reports'])[1]")
	private WebElement clientReport;

	@FindBy(xpath = "//span[text()='Case Report']")
	private WebElement caseReport;

	@FindBy(xpath = "//span[text()='Billing Report']")
	private WebElement billingReport;

	@FindBy(xpath = "//span[text()='Add Filter']")
	private WebElement addfilter;

	@FindBy(xpath = "//div[text()=' Export']")
	private WebElement export;
	
	@FindBy(xpath = "//a[text()='Help']")
	private WebElement clientScroll;

	@FindBy(xpath = "//div[@class='MuiBox-root css-0'])[12]")
	private WebElement clientprofile;
	
	@FindBy(xpath = "(//span[@class='MuiTypography-root MuiTypography-bold css-1xxw3ug'])[1]")
	private WebElement clientusermail;
	
	@FindBy (xpath = "//table/tbody/tr")
	private WebElement staleness;
	
	@FindBy(xpath = "//table/tbody/tr")
	private WebElement tableTb;
	
	@FindBy (xpath = "//span[text()='This Year']")
	private WebElement thisMonth;
	
}
