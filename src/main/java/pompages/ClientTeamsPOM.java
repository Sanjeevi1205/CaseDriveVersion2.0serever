package pompages;

import baseclass.LibGlobal;
import lombok.Getter;
import lombok.extern.java.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

@Log
@Getter
public class ClientTeamsPOM extends LibGlobal {

    public ClientTeamsPOM() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'Teams')]")
    private WebElement teams;

    @FindBy(xpath = "//li[contains(text(), 'Revoke')]")
    private WebElement revokeButton;

    public void revokeButtonRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, revokeButton, retries, timeout);
    }

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-auto css-1w1h23g']//button[@type='button'][normalize-space()='Add New Member']")
    private WebElement addNewMemberBtn;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement mailInputField;

    public void mailInputFieldRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, mailInputField, retries, timeout);
    }

    @FindBy(xpath="//td[@data-index='7']//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-65aruc']")
    private WebElement threeDotAction;

    @FindBy(xpath = "(//span[contains(@class, 'MuiButtonBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary PrivateSwitchBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary css-fizi60')])[1]")
    private WebElement administratorToggle;

    @FindBy(xpath = "//button[normalize-space()='Invite']")
    private WebElement inviteButton;

    @FindBy(xpath = "//input[@name='is_notification']")
    private WebElement allowNotification;

    @FindBy(xpath = "//div[@aria-label='Rows per page']")
    private WebElement rowperpage;

    @FindBy(xpath = "//li[normalize-space()='50']")
    private WebElement maximumPage;

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//ol[@dir='ltr']//li")
    private WebElement successMessage;

    @FindBy(xpath = "//li[normalize-space()='View']")
    private WebElement viewButton;

    @FindBy(xpath = "//button[contains(text(),'Edit')]")
    private WebElement editButton;

    @FindBy(xpath = "(//span[contains(@class, 'MuiSwitch-switchBase') and contains(@class, 'MuiSwitch-colorPrimary')])[1]")
    private WebElement notificationToggle;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//span[contains(text(),'invalid email address')]")
    private WebElement invalidMailAlert;
}