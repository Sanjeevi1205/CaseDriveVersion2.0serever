package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class AdminResourcePOM extends LibGlobal {

	public AdminResourcePOM() {
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

	@FindBy(xpath = "//span[text()='Resources']")
	private WebElement resources;

	@FindBy(xpath = "//span[text()='Product Samples']")
	private WebElement productSamples;

	@FindBy(xpath = "//div[@role='combobox']")
	private WebElement noOfPage;

	@FindBy(xpath = "//li[@data-value='500']")
	private WebElement noOfPageEdit;

	@FindBy(xpath = "//li[@data-value='500']")
	private WebElement noOfPageUpload;

	@FindBy(xpath = "//div[@class='MuiTablePagination-root MuiBox-root css-8k4lth']")
	private WebElement pageNo;

	@FindBy(xpath = "(//button[contains(@class, 'MuiIconButton-root')])[22]")
	private WebElement threeDotbtn;

	@FindBy(xpath = "(//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1rrn10e'])[1]")
	private WebElement edit;

	@FindBy(xpath = "(//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1rrn10e'])[2]")
	private WebElement upload;

	@FindBy(xpath = "(//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1rrn10e'])[3]")
	private WebElement view;

	@FindBy(xpath = "(//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1rrn10e'])[4]")
	private WebElement share;

	@FindBy(xpath = "(//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1rrn10e'])[5]")
	private WebElement copy;

	@FindBy(xpath = "(//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1rrn10e'])[6]")
	private WebElement delete;

	@FindBy(xpath = "(//div[@class='_body-modal_zcpzi_12 modal-body'])")
	private WebElement loading;

	@FindBy(xpath = "(//iframe[@role='presentation'])[3]")
	private WebElement iFrame;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveEdit;

	@FindBy(xpath = "(//input[@class='MuiTypography-root MuiTypography-body1 did-floating-input css-1u0otzy'])[1]")
	private WebElement productName;

	@FindBy(xpath = "//div[text()='updated successfully.']")
	private WebElement updatedsuccessfullyPopup;

	@FindBy(xpath = "//div[text()='Success']")
	private WebElement uploadsucesspopup;

	@FindBy(id = "icon")
	private WebElement downloadBtn;

	@FindBy(xpath = "//h5[text()='Choose from PC']")
	private WebElement choosefromPC;

	@FindBy(xpath = "(//button[contains(@class, 'MuiButton-root') and contains(@class, 'MuiButton-save')])[2]")
	private WebElement saveUpload;

	@FindBy(xpath = "//input[@id='tags-outlined']")
	private WebElement sender;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement senderPassword;

	@FindBy(xpath = "//input[@type='date']")
	private WebElement dataSetup;

	@FindBy(xpath = "//input[@type='date']")
	private WebElement setUpdate;

	@FindBy(xpath = "//input[@type='number']")
	private WebElement expiryDate;

	@FindBy(xpath = "//input[@type='number']")
	private WebElement setDownloadLimit;

	@FindBy(xpath = "//div[(text()='Sample added successfully')]")
	private WebElement sampleAddedSuccessfullyMessagePopup;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveshare;

	@FindBy(xpath = "(//button[contains(@class, 'MuiButton-savePrimary')])[2]")
	private WebElement deleteconfirm;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement deleteCancel;

	@FindBy(xpath = "//button[text()='Close']")
	private WebElement editCancel;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement uploadCancel;

	@FindBy(xpath = "//div[contains(text(),'Deleted successfully')]")
	private WebElement deleteConfirmMessage;

	@FindBy(xpath = "//span[text()='Marketing Material']")
	private WebElement markertingMaterials;

	@FindBy(xpath = "(//button[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiButton-savePrimary')])[1]")
	private WebElement addMarketing;

	@FindBy(xpath = "//div[@role='combobox']")
	private WebElement typesItems;

	@FindBy(xpath = "// div[text()='Created successfully.']")
	private WebElement createdSucessfully;

	@FindBy(xpath = "// div[text()='Created successfully.']")
	private WebElement createdSucessfullyService;

	@FindBy(xpath = "// div[text()='Created successfully.']")
	private WebElement createdSucessfullyIndustry;

	@FindBy(xpath = "//li[text()='Expertise']")
	private WebElement expertise;

	@FindBy(xpath = "//li[text()='Industry']")
	private WebElement industry;

	@FindBy(xpath = "//li[text()='Test']")
	private WebElement test;

	@FindBy(xpath = "//li[text()='Services']")
	private WebElement services;

	@FindBy(xpath = "//label[@for='thumbnail']")
	private WebElement thumbnail;

	@FindBy(xpath = "//label[@for='attachment']")
	private WebElement attachment;

	@FindBy(xpath = "//button[text()='Upload']")
	private WebElement uploadItems;

	@FindBy(xpath = "//button[text()='Upload']")
	private WebElement uploadItemsServices;

	@FindBy(xpath = "//button[text()='Upload']")
	private WebElement uploadItemsIndustry;

	@FindBy(xpath = "//span[text()='FAQ and Support']")
	private WebElement faq;

	@FindBy(xpath = "(//button[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiButton-savePrimary')])[1]")
	private WebElement faqadd;

	@FindBy(xpath = "(//div[@role='combobox'])[1]")
	private WebElement faqcase;

	@FindBy(xpath = "(//div[@role='combobox'])[2]")
	private WebElement partner;

	@FindBy(xpath = "//li[text()='Cases']")
	private WebElement faqCases;

	@FindBy(xpath = "//li[text()='Partner']")
	private WebElement faqClientselection;

	@FindBy(xpath = "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-19z7vi5']//input")
	private WebElement question;

	@FindBy(xpath = "//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-multiline css-1lb6h6u']//textarea")
	private WebElement answer;

	@FindBy(xpath = "//button[text()='Upload']")
	private WebElement faqUploaded;

	@FindBy(xpath = "// div[text()='Created successfully.']")
	private WebElement faqSucessmessage;

	@FindBy(xpath = "(//div[@id='panel1-content']//button//*[name()='svg' and @data-testid='EditRoundedIcon'])[1]")
	private WebElement faqEdit;

	@FindBy(xpath = "//textarea[@id='outlined-multiline-static' and @name='answer']")
	private WebElement faqclear;

	@FindBy(xpath = "//textarea[@id='outlined-multiline-static' and @name='answer']")
	private WebElement faqInput;
//
	@FindBy(xpath = "(//button[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiButton-savePrimary')])[2]")
	private WebElement faqEditUploaded;

	@FindBy(xpath = "//div[text()='Updated successfully.']")
	private WebElement faqEditUpdatedMessage;

	@FindBy(xpath = "(//div[@id='panel1-content']//button[2])[1]")
	private WebElement faqDeleteded;

	@FindBy(xpath = "//div[text()='Deleted successfully.']")
	private WebElement faqDeletePop;

}
