package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseclass.LibGlobal;
import lombok.Getter;

@Getter
public class ClientCaseListPOM extends LibGlobal {
    public ClientCaseListPOM() {
        PageFactory.initElements(driver, this);}

    @FindBy(xpath="//li[normalize-space()='Estimate Request']")
    private WebElement estimateRequest;

    @FindBy(xpath="//tr[@data-index='0']//td[@data-index='6']")
    private WebElement caseStatusElement;

    @FindBy(xpath = "//td[@data-index='6']")
    private WebElement statusColumn;

    @FindBy(xpath="//ol[@dir='ltr']//li")
    private WebElement successMessage;

    @FindBy(xpath="(//div[@class='MuiListItemIcon-root css-xvvc1w']//*[name()='svg'])[1]")
    private WebElement viewButton;

    @FindBy(xpath = "(//button[contains(text(),'Action')])[1]")
    private WebElement actionButton;

    @FindBy(xpath = "(//span[contains(@class, 'MuiTypography-root') and contains(@class, 'MuiTypography-light')])[1]")
    private WebElement viewPageStatus;

    @FindBy(xpath="//button[normalize-space()='Confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//li[normalize-space()='Hold Request']")
    private WebElement holdReqButton;

    @FindBy(xpath = "//textarea[@id='outlined-multiline-static']")
    private WebElement reasonInput;

    @FindBy(xpath = "//li[normalize-space()='Resume Request']")
    private WebElement resumeReqButton;

    @FindBy(xpath = "//li[normalize-space()='Cancel Request']")
    private WebElement cancelReqButton;

    @FindBy(xpath = "//li[normalize-space()='Change Priority']")
    private WebElement changePriority;

    @FindBy(xpath = "//li[normalize-space()='Standard']")
    private WebElement standard;

    @FindBy(xpath = "//li[normalize-space()='Expedited']")
    private WebElement expedited;

    @FindBy(xpath="//button[normalize-space()='Request']")
    private WebElement requestButton;

    @FindBy(xpath="//input[@id='combo-box-demo' and @role='combobox' and contains(@class, 'MuiAutocomplete-inputFocused') and @value='Standard']")
    private WebElement priorityStandardDropdown;

    @FindBy(xpath = "(//p[contains(@class, 'MuiTypography-root') and contains(@class, 'MuiTypography-body1')])[3]")
    private WebElement viewPagePriority;

    @FindBy(xpath = "(//button[contains(text(),'Missing Records')])[1]")
    private WebElement missingRecordsButton;

    @FindBy(xpath = "//button[normalize-space()='Notes']")
    private WebElement notes;

    @FindBy(xpath = "(//button[normalize-space()='+ Add Note'])[1]")
    private WebElement addNotes;

    @FindBy(xpath = "//textarea[@placeholder='Write a note...']")
    private WebElement noteInputField;

    @FindBy(xpath = "//button[normalize-space()='Post']")
    private WebElement postButton;

    @FindBy(xpath = "//span[@class='MuiButtonBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary PrivateSwitchBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary css-fizi60']")
    private WebElement privateToggle;

    @FindBy (xpath = "//tr[@class='MuiTableRow-root css-m6jsh7']")
    private WebElement notesTable;

    @FindBy(xpath="//button[normalize-space()='Case Docs']")
    private WebElement caseDocsMenu;

    @FindBy(xpath="//span[normalize-space()='Source Files']")
    private WebElement sourceFileFolder;

    @FindBy (xpath="//li[normalize-space()='Download']")
    private WebElement downloadButton;

    @FindBy(xpath="//span[normalize-space()='Deliverables']")
    private WebElement deliverableButton;










}