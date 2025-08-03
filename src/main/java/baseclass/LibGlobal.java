package baseclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.logging.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.EmailReportSender;

@Log


public class LibGlobal {
    private static final Logger logger = Logger.getLogger(LibGlobal.class.getName());
    static {
        try {
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }
            File logDir = new File("logs");
            if (!logDir.exists()) logDir.mkdirs();

            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String logFileName = "logs/automation-" + dateStr + ".log";

            FileHandler fileHandler = new FileHandler(logFileName, 5 * 1024 * 1024, 3, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            rootLogger.addHandler(fileHandler);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);
            rootLogger.addHandler(consoleHandler);

            logger.info("Logging initialized with daily log file: " + logFileName);
            deleteOldLogFiles();
        } catch (IOException e) {
            System.out.println("Error setting up file logger: " + e.getMessage());
        }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                EmailReportSender.sendReport(
                        "sanjeevi.p@lezdotechmed.com",
                        "benish.s@lezdotechmed.com","harikrishnan.s@lezdotechmed.com"
                );
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                System.out.println("Exception in shutdown hook while sending report:\n" + sw);
            }
        }));
    }

    public static WebDriver driver;

    public static WebDriver initializeDriver() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--force-device-scale-factor=1.00");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return driver;
    }

    public static void openURL(WebDriver driver) {
        String url = System.getProperty("env.url");
        if (url == null || url.isEmpty()) {
            url = System.getenv("env.url");
        }

        log.info("Environment URL from system property: " + url);

        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException(
                    "Environment URL not provided. Please set 'env.url' in Run Configuration.");
        }

        driver.manage().window().maximize();
        driver.get(url);
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void closeNotification() throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void scrollToHelpLink(WebDriver driver, WebElement scroll) {
        Actions a = new Actions(driver);
        a.moveToElement(scroll).perform();
    }

    public static void doubleClick(WebDriver driver, WebElement doubleclick) {
        Actions a = new Actions(driver);
        a.doubleClick(doubleclick).perform();

    }

    public static void clickPositionButton(WebDriver driver, WebElement positionbutton) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", positionbutton);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", positionbutton);
        js.executeScript("arguments[0].click();", positionbutton);
        js.executeScript("arguments[0].scrollIntoView(true);", positionbutton);
        js.executeScript("arguments[0].scrollIntoView(true);", positionbutton);

    }

    public static void selectoption(WebElement element) {
        Select s = new Select(element);
        s.selectByIndex(1);
    }

    public static void scrolldown() throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_PAGE_DOWN);
        r.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    public static void scrollup(int times) throws AWTException {
        Robot r = new Robot();
        for (int i = 0; i < times; i++) {
            r.keyPress(KeyEvent.VK_PAGE_UP);
            r.keyRelease(KeyEvent.VK_PAGE_UP);
            try {
                Thread.sleep(300); // Small delay between scrolls
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exportButtonClick() throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);

    }

    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        Actions a = new Actions(driver);
        a.dragAndDrop(source, target).build().perform();
    }

    public static void scrollRight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(300,0)");
    }

    public static void mouseHover(WebElement target) {
        Actions a = new Actions(driver);
        a.moveToElement(target).perform();
    }

    public static void valueinsert(WebDriver driver, WebElement element, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1];", element, value);
    }

    public static void setDateWithJS(WebDriver driver, WebElement dateElement, String dateValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1];", dateElement, dateValue);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", dateElement);
        dateElement.sendKeys(Keys.TAB);

    }

    public void uploadFileWithJS(WebDriver driver, WebElement fileInput, String relativePath) {
        File file = new File(relativePath);
        String absolutePath = file.getAbsolutePath();
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + absolutePath);
        }
        log.info("Uploading file: " + absolutePath);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display = 'block';", fileInput);
        fileInput.sendKeys(absolutePath);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", fileInput);
    }

    public static void clickWithRetry(WebDriver driver, WebElement element, int retries, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        while (retries > 0) {
            try {
                element = wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            } catch (StaleElementReferenceException | TimeoutException | ElementClickInterceptedException e) {
                retries--;
                log.info("Exception encountered. Retrying... Attempts left: " + retries);
                if (retries == 0) {
                    log.info("Failed to click after retries.");
                }
            }
        }
    }

    public static void verifySuccessMessage(WebElement messageElement, String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.visibilityOf(messageElement));
            String actualText = messageElement.getText();
            boolean isSuccess = actualText.contains(expectedMessage);
            log.info("Success message found: " + isSuccess + " | Actual text: " + actualText);
        } catch (Exception e) {
            log.info("Success message was not found within the timeout.");
        }
    }

    private static void deleteOldLogFiles() {
        File logDir = new File("logs");
        if (!logDir.exists() || !logDir.isDirectory()) return;

        File[] logFiles = logDir.listFiles();
        if (logFiles == null) return;

        long currentTime = System.currentTimeMillis();
        long sevenDaysInMillis = 7L * 24 * 60 * 60 * 1000;

        for (File logFile : logFiles) {
            if (logFile.isFile()) {
                long lastModified = logFile.lastModified();
                if (currentTime - lastModified > sevenDaysInMillis) {
                    boolean deleted = logFile.delete();
                    if (deleted) {
                        log.info("Deleted old log file: " + logFile.getName());
                    } else {
                        log.warning("Failed to delete old log file: " + logFile.getName());
                    }
                }
            }
        }
    }

}
