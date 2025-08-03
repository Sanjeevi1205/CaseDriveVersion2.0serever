package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class AdminReportPOM extends LibGlobal {
	public AdminReportPOM() {
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

	@FindBy(xpath = "//a[text()='Help']")
	private WebElement scroll;

	@FindBy(xpath = "//span[text()='Reports']")
	private WebElement reports;

	@FindBy(xpath = "//span[text()='Case']")
	private WebElement cases;

	@FindBy(xpath = "//span[text()='Revenue']")
	private WebElement revenue;

	@FindBy(xpath = "(//span[text()='Billing'])[2]")
	private WebElement billing;

	@FindBy(xpath = "//span[text()='Client Statistics']")
	private WebElement clientStatistics;

	@FindBy(xpath = "(//span[text()='Employee Statistics'])[1]")
	private WebElement employeeStatistics;

	@FindBy(xpath = "//span[text()='Partner Statistics']")
	private WebElement partnerStatistics;

	@FindBy(xpath = "//span[text()='Add Filter']")
	private WebElement addFilter;

	@FindBy(xpath = "//div[text()=' Export']")
	private WebElement exportrevenue;
	
	@FindBy(xpath = "//div[@role='combobox']")
	private WebElement pagesrevenue;
	
	@FindBy(xpath = "//li[@data-value='500']")
	private WebElement noOfPagerevenue;
	
	@FindBy(xpath = "//div[text()=' Export']")
	private WebElement export;

	@FindBy(xpath = "//div[@role='combobox']")
	private WebElement pages;
	
	@FindBy(xpath = "//div[@role='combobox']")
	private WebElement pagepartner;

	@FindBy(xpath = "//div[text()=' Export']")
	private WebElement exportpartner;
	
	@FindBy(xpath = "(//div[@class='MuiBox-root css-0'])[10]")
	private WebElement loadingpartner;
	
	@FindBy(xpath = "//li[@data-value='500']")
	private WebElement noOfPagepartner;
	
	@FindBy(xpath = "(//div[@class='MuiBox-root css-0'])[10]")
	private WebElement loading;

	@FindBy(xpath = "//li[@data-value='500']")
	private WebElement noOfPage;

	@FindBy(xpath = "//div[@class='d-flex align-items-center MuiBox-root css-1r99qxv']")
	private WebElement employeeExport;

	@FindBy(xpath = "//span[text()='Today']")
	private WebElement today;

	@FindBy(xpath = "//span[text()='Yesterday']")
	private WebElement yesterday;

	@FindBy(xpath = "//span[text()='This Week']")
	private WebElement week;

	@FindBy(xpath = "//span[text()='Last Week']")
	private WebElement lastweek;

	@FindBy(xpath = "//span[text()='This Month']")
	private WebElement month;

	@FindBy(xpath = "//span[text()='Last Month']")
	private WebElement lastmonth;

	@FindBy(xpath = "//span[text()='Last 6 Months']")
	private WebElement last6months;

	@FindBy(xpath = "//span[text()='This Year']")
	private WebElement year;

	@FindBy(xpath = "//span[text()='Last Year']")
	private WebElement lastyear;

	@FindBy(xpath = "(//span[text()='View Detailed Report'])[1]")
	private WebElement topPerformers;

	@FindBy(xpath = "(//span[text()='View Detailed Report'])[2]")
	private WebElement clientWiseMetrics;

}