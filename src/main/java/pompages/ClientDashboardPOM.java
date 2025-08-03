package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class ClientDashboardPOM extends LibGlobal{
	public ClientDashboardPOM(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id = "cases-tab")
	public WebElement casesTab;
	
	@FindBy (id = "pages-tab")
	public WebElement pagesTab;
	
	@FindBy (id = "bill-tab")
	public WebElement billingTab;
	
	@FindBy (id = "billamt-tab")
	public WebElement billedAmountTab;
	
	@FindBy (xpath = "//div[@class='card-body p-0']")
	public WebElement graph;
	
	@FindBy (xpath = "(//span[@class='MuiTypography-root MuiTypography-bold py-2 px-2 css-9j1vi5'])[1]")
	public WebElement casefilterOption;
	
	@FindBy (xpath = "(//span[@class='MuiTypography-root MuiTypography-bold py-2 px-2 css-9j1vi5'])[2]")
	public WebElement costfilterOption;
	
	@FindBy (xpath = "(//button[@type='button'])[28]")
	public WebElement todayFilter;
	
	@FindBy (xpath = "(//button[@type='button'])[29]")
	public WebElement yesterdayFilter;
	
	@FindBy (xpath = "(//span[@class='apexcharts-legend-text'])[3]")
	public WebElement serviceMedicalChronology;
	
	@FindBy (xpath = "(//span[@class='apexcharts-legend-text'])[4]")
	public WebElement serviceIndexing;
	
	@FindBy (xpath = "//p[text()='View All Reports ']")
	public WebElement caseReportbutton;
	
	@FindBy (xpath = "(//button[text()='View All Reports '])[1]")
	public WebElement costReportbutton;
	
	@FindBy (xpath = "(//button[text()='View All Reports '])[2]")
	public WebElement billingReportbutton;
	
	@FindBy (xpath = "(//button[text()='View All Reports '])[3]")
	public WebElement unpaidReportbutton;
	
	@FindBy (xpath = "//button[text()='Case Report']")
	private WebElement caseReportPage;
	
	@FindBy (xpath = "//button[text()='Billing Report']")
	private WebElement billingReportPage;
}