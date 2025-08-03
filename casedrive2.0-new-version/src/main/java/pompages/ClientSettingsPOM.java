package pompages;

import baseclass.LibGlobal;
import com.google.j2objc.annotations.Weak;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ClientSettingsPOM extends LibGlobal {
    public ClientSettingsPOM() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='MuiBox-root css-q2y3yl'])[2]")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[text()='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//input[@name='company.city']")
    private WebElement cityField;

    @FindBy(name = "company.billing_city")
    private WebElement billingCityField;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[text()='Details saved successfully.']")
    private WebElement generalDetailsSuccessPopup;

    @FindBy(xpath = "(//a[@class='ps-menu-button'])[6]")
    private WebElement servicesMenu;

    @FindBy(xpath = "//a[@class='ps-menu-button']//span[text()='Desired Services']")
    private WebElement desiredServicesMenu;

    @FindBy(xpath = "(//span[contains(@class, 'MuiButtonBase-root')])[4]")
    private WebElement APSSummary;

    @FindBy(xpath = "(//button[text()='Request'])[1]")
    private WebElement requestButton;

    @FindBy(xpath = "//div[text()='Service request sent successfully']")
    private WebElement desiredServicesSuccessPopup;

    //Notification
    @FindBy(xpath = "(//a[@class='ps-menu-button'])[8]")
    private WebElement notificationMenu;

    @FindBy(xpath = "//span[text()='Email Settings']")
    private WebElement emailSettingsMenu;

    @FindBy(xpath = "(//span[contains(@class, 'MuiButtonBase-root')])[6]")
    private WebElement caseHoldOption;

    @FindBy(xpath = "//div[text()='Email settings updated']")
    private WebElement emailSuccessPopup;
}