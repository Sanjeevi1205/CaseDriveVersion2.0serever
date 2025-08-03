package helper;

import baseclass.LibGlobal;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.OrderIntakePOM;

import java.time.Duration;
import java.util.List;
import java.util.Set;
@Log
public class CaseHelper extends LibGlobal {
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public OrderIntakePOM orderIntakePOM = new OrderIntakePOM();


    public String getDetectedStatus(int rowIndex, int statusColIndex) {
        String statusXpath = "//tr[@data-index='" + rowIndex + "']//td[@data-index='" + statusColIndex + "']";

        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                wait.until(driver -> {
                    WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(statusXpath)));
                    String text = statusElement.getText().trim();
                    return !text.isEmpty() ? text : null;
                });
                return statusXpath;
            } catch (Exception e) {
                log.warning("Attempt " + (attempt + 1) + " failed: " + e.getMessage());
            }
        }
        throw new RuntimeException("Failed to retrieve detected status after retries.");
    }

    public List<WebElement> getVisibleRows(String visibleCellXpath, String rowsXpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(visibleCellXpath)));
        List<WebElement> rows = driver.findElements(By.xpath(rowsXpath));
        log.info("Total rows found: " + rows.size());
        return rows;
    }

    public String getColumnText(WebElement row, int columnIndex) {
        WebElement columnElement = wait.until(ExpectedConditions.visibilityOf(
                row.findElement(By.xpath(".//td[@data-index='" + columnIndex + "']"))));
        return columnElement.getText().trim();
    }
    @SuppressWarnings("unused")
    public boolean processRowsForAction(List<WebElement> rows, String expectedClientName, String expectedStatus,
                                        String expectedPriority, int statusCol, int priorityCol, int clientCol,
                                        int actionCol, String actionType, String actionXpath) {
        for (WebElement row : rows) {
            try {
                String actualStatus = getColumnText(row, statusCol);
                String actualPriority = priorityCol > 0 ? getColumnText(row, priorityCol) : "";
                String actualClientName = clientCol > 0 ? getColumnText(row, clientCol) : "";

                boolean statusMatches = actualStatus.equalsIgnoreCase(expectedStatus);
                boolean priorityMatches = expectedPriority == null || expectedPriority.isEmpty()
                        || actualPriority.equalsIgnoreCase(expectedPriority);
                boolean clientMatches = expectedClientName == null || expectedClientName.isEmpty()
                        || actualClientName.equalsIgnoreCase(expectedClientName);

                log.info("Checking row - Status: " + actualStatus +
                        (expectedPriority != null && !expectedPriority.isEmpty() ? ", Priority: " + actualPriority : "") +
                        (expectedClientName != null && !expectedClientName.isEmpty() ? ", Client Name: " + actualClientName : ""));

                if (statusMatches && priorityMatches && clientMatches) {
                    boolean clicked = clickActionButton(row, actionXpath);

                    if ("download".equalsIgnoreCase(actionType) && clicked) {
                        return assertDownloadOutcome();
                    }
                    return clicked;
                }
            } catch (Exception e) {
                log.warning("Error while processing row: " + e.getMessage());
            }
        }
        return false;
    }
    @SuppressWarnings("unused")
    public boolean processRowsForProfileRoleAction(List<WebElement> rows,
                                                   String expectedProfile, String expectedRole,
                                                   int profileCol, int roleCol,
                                                   int actionCol, String actionType, String actionXpath) {
        for (WebElement row : rows) {
            try {
                String actualProfile = getColumnText(row, profileCol);
                String actualRole = getColumnText(row, roleCol);

                boolean profileMatches = expectedProfile == null || expectedProfile.isEmpty()
                        || actualProfile.equalsIgnoreCase(expectedProfile);
                boolean roleMatches = expectedRole == null || expectedRole.isEmpty()
                        || actualRole.equalsIgnoreCase(expectedRole);

                log.info("Checking row - Profile: " + actualProfile + ", Role: " + actualRole);

                if (profileMatches && roleMatches) {
                    boolean clicked = clickActionButton(row, actionXpath);

                    if ("download".equalsIgnoreCase(actionType) && clicked) {
                        return assertDownloadOutcome();
                    }
                    return clicked;
                }
            } catch (Exception e) {
                log.warning("Error while processing row: " + e.getMessage());
            }
        }
        return false;
    }
    public boolean clickActionButton(WebElement row, String actionXpath) {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                    row.findElement(By.xpath(actionXpath))));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

            try {
                button.click();
            } catch (Exception e) {
                log.warning("Regular click failed. Attempting JS click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }

            return true;
        } catch (Exception e) {
            log.warning("Failed to click action button: " + e.getMessage());
            return false;
        }
    }

    public boolean attemptPagination(String paginationButtonXpath, String rowsXpath, String maxRowsOptionXpath) {
        try {
            WebElement rowPerPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(paginationButtonXpath)));
            rowPerPage.click();

            WebElement maxRowsOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(maxRowsOptionXpath)));
            maxRowsOption.click();

            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(rowsXpath))));
            return true;
        } catch (Exception e) {
            log.warning("Pagination failed: " + e.getMessage());
            return false;
        }
    }

    public boolean assertDownloadOutcome() {
        boolean alertAppeared = false;
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());

            log.info("Alert appeared. Accepting alert...");
            alert.accept();
            alertAppeared = true;


            Set<String> windowHandles = driver.getWindowHandles();
            if (windowHandles.size() > 1) {
                for (String handle : windowHandles) {
                    driver.switchTo().window(handle);
                }
                log.info("This order has been placed using the custom links URL: " + driver.getCurrentUrl());
            }

        } catch (TimeoutException e) {

            log.info("No alert appeared.Checking for error message...");
        }

        try {
            WebDriverWait errorElementWait = new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement errorElement = errorElementWait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getOrderPlacedMsg()));
            String errorMessage = errorElement.getText().trim();
            log.warning("Error message appeared: " + errorMessage);
            Assert.fail("Download failed due to error: " + errorMessage);
            return false;

        } catch (TimeoutException e) {
            log.info("No error message appeared.");
        }

        String currentUrl = driver.getCurrentUrl();
        assert currentUrl != null;
        if (currentUrl.contains("s3.amazonaws.com")) {
            log.info("Test failed: URL contains S3 AmazonBucket URL: " + currentUrl);
            Assert.fail("Download failed: The URL points to an Amazon S3 bucket.");
            return false;
        }
        if (!alertAppeared) {
            log.info("Download considered successful OR may be slow due to file size as no error appeared.");
        }
        return true;
    }

    public String getColumnTextLatest(int rowIndex, int colIndex) {
        String xpath = "//tr[@data-index='" + rowIndex + "']//td[@data-index='" + colIndex + "']";
        return driver.findElement(By.xpath(xpath)).getText().trim();
    }

    public void verifyCaseDetail(String label, String expectedValue, int rowIndex, int columnIndex) {
        String actualValue = getColumnTextLatest(rowIndex, columnIndex);
        log.info(" Verifying " + label + ": Expected = " + expectedValue + ", Actual = " + actualValue);

        if (!actualValue.contains(expectedValue)) {
            throw new AssertionError( label + " mismatch. Expected: " + expectedValue + ", Actual: " + actualValue);
        }

        log.info(label + " matched successfully.");
    }
}

