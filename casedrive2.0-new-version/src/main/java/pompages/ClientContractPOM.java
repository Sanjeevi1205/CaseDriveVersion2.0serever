package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseclass.LibGlobal;
import lombok.Getter;
@Getter
public class ClientContractPOM extends LibGlobal {
	public ClientContractPOM(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1asfma']")
	private WebElement saSidemenu;
	
	@FindBy (xpath = "(//span[text()='Contracts'])[1]")
	private WebElement contractSidemenu;
	
	@FindBy (xpath = "//button[text()='Sign']")
	private WebElement signButton;
	
	@FindBy (xpath = "//button[text()='Sign']")
	private WebElement signButtonHIPPApage;
	
	@FindBy (name = "full_name")
	private WebElement fullNameField;
	
	@FindBy (name = "designation")
	private WebElement designationField;
	
	@FindBy (name = "signature_name")
	private WebElement signatureField;
	
	@FindBy (name = "e_agree")
	private WebElement signatureAgreeCheckbox;
	
	@FindBy (xpath = "//button[text()='Submit']")
	private WebElement submitButton;
	
	@FindBy (xpath = "//button[text()='View']")
	private WebElement viewButton;
}