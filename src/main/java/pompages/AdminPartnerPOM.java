package pompages;

import baseclass.LibGlobal;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AdminPartnerPOM extends LibGlobal {
    public AdminPartnerPOM(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1bb6tpb']//*[name()='svg']")
    private WebElement sideMenu;

    @FindBy(xpath = "(//span[@class='ps-menu-label css-1w0bdvs'])[35]")
    private WebElement partnerMenu;

    @FindBy(xpath = "//button[text()='+ Create Partner']")
    private WebElement createPartnerButton;

    @FindBy(name = "first_name")
    private WebElement firstName;

    @FindBy(name = "last_name")
    private WebElement lastName;

    @FindBy(name = "phone")
    private WebElement phoneNumber;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[1]")
    private WebElement country;

    @FindBy(xpath = "(//div[@id='demo-simple-select-helper'])[2]")
    private WebElement state;

    @FindBy(name = "zipcode")
    private WebElement zipcode;

    @FindBy(xpath = "//input[@id='free-solo-2-demo']")
    private WebElement timeZone;

    @FindBy(xpath = "(//button[text()='Create'])[1]")
    private WebElement createButton;

    @FindBy(xpath = "//div[text()='Partner created successfully']")
    private WebElement popupMessage;

    @FindBy(xpath = "//span[@class='MuiButtonBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary PrivateSwitchBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary css-fizi60']")
    private WebElement toggleButton;

    @FindBy(name = "business_name")
    private WebElement businessName;

    @FindBy(name = "employee_id_number")
    private WebElement employeeID;

    @FindBy(name = "reg_business_phone")
    private WebElement businessPhone;

    @FindBy(name = "business_website")
    private WebElement website;

    @FindBy(name = "email")
    private WebElement businessEmail;

    @FindBy(name = "business_address")
    private WebElement industry;

    @FindBy(name = "business_zipcode")
    private WebElement businessZipcode;

    @FindBy(xpath = "//div[text()=' Export']")
    private WebElement exportButton;

    }
