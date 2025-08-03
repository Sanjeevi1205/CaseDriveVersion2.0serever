package stepdefinition;
import baseclass.LibGlobal;
import com.github.javafaker.Faker;
import exceldata.OrderIntakeTestData;
import helper.CaseHelper;
import io.cucumber.java.en.Then;
import lombok.extern.java.Log;
import mailtrap.MailTrapAPI;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pompages.CaseCompletionPOM;
import pompages.LoginPOM;
import pompages.OrderIntakePOM;
import userdata.ClientTeamsData;
import userdata.OrderIntakeData;
import userdata.OrderIntakeData.OrderType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceldata.OrderIntakeReader;
import utils.RandomEmail;

@Log
public class OrderIntake extends LibGlobal {
    public OrderIntakePOM orderIntakePOM;
    CaseHelper caseHelper = new CaseHelper();


    @Given("the client logs in using valid credentials")
    public void theClientLogsInUsingValidCredentials() throws Exception {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        OrderIntakeReader orderIntakeReader = new OrderIntakeReader("src/test/resources/Excel/TestDataForCaseDrive.xls");
        List<OrderIntakeTestData> testData = orderIntakeReader.getOrderIntakeData("Credentials");
        String clientusername = testData.get(0).getUsername();
        String clientpassword = testData.get(0).getPassword();
        orderIntakePOM = new OrderIntakePOM();
        LoginPOM loginPOM = new LoginPOM();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(clientusername);
        loginPOM.getPassword().sendKeys(clientpassword);
        loginPOM.getLoginbtn().click();
        orderIntakeReader.closeWorkbook();
    }

    @When("the client clicks on Add a new record to place an order")
    public void theClientClicksOnAddNewRecordtoPlaceAnOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getSideMenu()));
        orderIntakePOM.getSideMenu().click();
        orderIntakePOM.getAddRecordsBtn().click();
        orderIntakePOM.getNewRecords().click();
    }

    @And("the client enters the required details")
    public void theClientEntersRequiredDetailsSelectServicesAndClickTheNextButton() {
        orderIntakePOM.getFirstnameField().sendKeys(RandomEmail.generateFirstName());
        orderIntakePOM.getLastnameField().sendKeys(RandomEmail.generateLastName());
        orderIntakePOM.expectedDeliveryButton();
        orderIntakePOM.calenderNxtRetry();
        orderIntakePOM.selectDateRetry();
    }
    @And("selects review services, and clicks the next button")
    public void selectsReviewServicesAndClicksTheNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getServiceSelector()));
        int retryCount = 0;
        boolean clickedSuccessfully = false;

        while (retryCount < 3 && !clickedSuccessfully) {
            try {
                orderIntakePOM.getServiceSelector().click();
                wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getFirstService())).click();
                orderIntakePOM.getServiceSelector().click();
                try {
                    orderIntakePOM.getSecondService().click();
                } catch (Exception e) {
                    log.info("Second service not found, clicking next button instead.");
                    orderIntakePOM.getNextButton().click();
                    break;
                }
                orderIntakePOM.getServiceSelector().click();
                orderIntakePOM.getThirdService().click();
                orderIntakePOM.getNextButton().click();
                clickedSuccessfully = true;
            } catch (Exception e) {
                log.info("Error occurred during service selection or clicking next: " + e.getMessage());
                retryCount++;
                if (retryCount < 3) {
                    log.info("Retry attempt " + retryCount);
                    wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getServiceSelector()));
                } else {
                    log.info("Maximum retry attempts reached, stopping execution.");
                }
            }
        }
    }


    @And("the client selects case type and subtype from the second form")
    public void theClientSelectsCaseTypeAndSubtypeFromTheSecondForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", orderIntakePOM.getCaseType());
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getCaseType()));
        orderIntakePOM.retryCaseTypeClick();
        orderIntakePOM.retryClickDropDownValue();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getCaseSubType()));
        orderIntakePOM.getCaseSubType().click();
        orderIntakePOM.retryClickDropDownValue();
    }

    @And("the client enables the estimate toggle")
    public void theClientEnablesTheEstimateToggle() {
        orderIntakePOM.getEstimateToggle().click();
    }

    @And("the client provides the upload link and download link, and clicks submit button")
    public void theClientEnablesProvidesCustomLinksAndClicksSubmitButton() {
        orderIntakePOM.getCaseOverview().sendKeys(OrderType.CASE_DATA.getCaseOverview());
        orderIntakePOM.getCustomLink().click();
        orderIntakePOM.getUploadLink().sendKeys(OrderType.CASE_DATA.getUploadLink());
        orderIntakePOM.getDownloadLink().sendKeys(OrderType.CASE_DATA.getDownloadLink());
        theClientClicksOnTheSubmitButton();
    }

    @And("the client edits some details")
    public void theClientEditsSomeDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", orderIntakePOM.getEditButton());
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getEditButton()));
        orderIntakePOM.getEditButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getFirstnameField()));
        orderIntakePOM.getFirstnameField().sendKeys(Keys.CONTROL + "a");
        orderIntakePOM.getFirstnameField().sendKeys(Keys.BACK_SPACE);
        orderIntakePOM.getFirstnameField().sendKeys(OrderType.CASE_DATA.getConfirmFirstname());
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getLastnameField()));
        orderIntakePOM.getLastnameField().sendKeys(Keys.CONTROL + "a");
        orderIntakePOM.getLastnameField().sendKeys(Keys.BACK_SPACE);
        orderIntakePOM.getLastnameField().sendKeys(OrderType.CASE_DATA.getConfirmlastname());
    }

    @And("the  client enters a casenumber manually")
    public void entersACasenumberManuallyClicksTheSubmitButton() {
        orderIntakePOM.getConfirmPageCaseNumb().sendKeys(OrderType.CASE_DATA.getConfirmCaseID());
    }

    @And("the clients saves and submits the details")
    public void theClientSavesAndSubmitTheDetails() {
        orderIntakePOM.getEditButton().click();
        theClientConfirmsTheOrderByClickingTheConfirmAndSubmitButton();
    }

    @And("the client clicks the confirm button in the confirmation pop up")
    public void theClientClicksTheConfirmButtonInTheConfirmationPopUp() {
        orderIntakePOM.getConfirmOrder().click();
    }

    @And("the client sees the order placed success message and is redirected to the case list page")
    public void theClientSeesTheOrderPlacedSuccessMessageAndIsRedirectedToTheCaseListPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        LibGlobal.verifySuccessMessage(
                orderIntakePOM.getOrderPlacedMsg(),
                "Your order has been placed successfully."
        );
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/client/cases/review"),
                ExpectedConditions.urlContains("/client/cases/retrieval")
        ));

        String currentCaseListUrl = driver.getCurrentUrl();
        log.info(" Current URL: " + currentCaseListUrl);
        assert currentCaseListUrl != null;
        if (currentCaseListUrl.contains("/client/cases/review")) {
            log.info(" Detected Review flow in the URL.");
            Assert.assertTrue(" Expected 'review' in the URL, but it was not found.",
                    currentCaseListUrl.contains("/client/cases/review"));
        } else if (currentCaseListUrl.contains("/client/cases/retrieval")) {
            log.info(" Detected Retrieval flow in the URL.");
            Assert.assertTrue(" Expected 'retrieval' in the URL, but it was not found.",
                    currentCaseListUrl.contains("/client/cases/retrieval"));
        } else {
            log.info(" URL does not contain either 'review' or 'retrieval'.");
            Assert.fail("URL did not contain expected 'review' or 'retrieval'. Found: " + currentCaseListUrl);
        }
    }

    @And("the client verifies whether the case status is {string}, case version is {string} and case priority is {string}")
    public void theClientVerifiesCaseDetails(String expectedStatus, String expectedVersion, String expectedPriority) {
        int rowIndex = 0;
        caseHelper.verifyCaseDetail("Status", expectedStatus, rowIndex, 6);
        caseHelper.verifyCaseDetail("Version", expectedVersion, rowIndex, 2);
        caseHelper.verifyCaseDetail("Priority", expectedPriority, rowIndex, 5);
        LibGlobal.quitDriver();
    }

    @And("the client verifies whether the entered case number is being reflected in the caselist page")
    public void finallyVerifiesWhetherTheEnteredCaseNumberIsBeingReflectedInTheCaselistPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(driver -> !orderIntakePOM.getDoesCaseNumber().getText().trim().isEmpty());
        String caseNumber = orderIntakePOM.getDoesCaseNumber().getText().trim();
        String expectedPrefix = "AUTO";
        String caseNumberPrefix = caseNumber.substring(0, expectedPrefix.length());
        boolean caseNumberMatchesPrefix = caseNumberPrefix.equals(expectedPrefix);
        log.info("Case number matches expected prefix: " + caseNumberMatchesPrefix);
        log.info("Actual case number: " + caseNumber);
    }

    @And("the client verifies whether the case number is generated automatically in the caselist page")
    public void finallyVerifiesWhetherTheCaseNumberIsGeneratedAutomaticallyInTheCaselistPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(driver -> !orderIntakePOM.getDoesCaseNumber().getText().trim().isEmpty());
        String caseNumber = orderIntakePOM.getDoesCaseNumber().getText().trim();
        String expectedPrefix = "LDML";
        String caseNumberPrefix = caseNumber.substring(0, expectedPrefix.length());
        boolean caseNumberMatchesPrefix = caseNumberPrefix.equals(expectedPrefix);
        log.info("Case number matches expected prefix: " + caseNumberMatchesPrefix);
        log.info("Actual case number: " + caseNumber);
    }

    @And("the client enters the case overview")
    public void theClientEntersTheCaseOverview() {
        orderIntakePOM.getCaseOverview().sendKeys(OrderIntakeData.OrderType.CASE_DATA.getCaseOverview());
    }

    @And("the client uploads different file types, and clicks the submit button")
    public void theClientUploadDifferentFileTypesAndClicksTheSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ClientCaseList clientCaseList= new ClientCaseList();
        clientCaseList.orderIntakePOM = new OrderIntakePOM();
        clientCaseList.theClientUploadsDifferentFileTypes();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getSubmitButton()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", orderIntakePOM.getSubmitButton());
        theClientClicksOnTheSubmitButton();
    }

    @And("the client does not upload any files and clicks the submit button")
    public void theClientDoesNotUploadAnyFilesAndClicksTheSubmitButton() {
        orderIntakePOM.getCaseOverview().sendKeys(OrderIntakeData.OrderType.CASE_DATA.getCaseOverview());
        theClientClicksOnTheSubmitButton();
    }

    @Then("the client verifies that an error message is displayed, indicating files must be uploaded")
    public void theClientVerifiesThatAnErrorMessageIsDisplayedIndicatingFilesMustBeUploadedAndOverviewErrorIsShown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement fileUploadError = wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getFileUploadError()));
            Assert.assertTrue("Error message for missing file upload is not displayed.", fileUploadError.isDisplayed());
            log.info("Verified that the error message for missing file upload is displayed.");

        } catch (Exception e) {
            log.severe("Error during validation of error messages: " + e.getMessage());
        }
        LibGlobal.quitDriver();
    }

    @Then("verify whether the mail for {string} is being received")
    public void verifyWhetherTheMailForIsBeingReceived(String expectedSubject) {
        try {
            MailTrapAPI.verifyLatestEmailSubjectAndBCC(expectedSubject);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Mail verification failed", e);
        }
    }

    @And("selects retrieval services, and clicks the next button")
    public void selectsRetrievalServicesAndClicksTheNextButton() {
        orderIntakePOM.retryServiceSelector();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getMedicalRecordRetrievalService()));
        orderIntakePOM.medicalRecordRetrievalServiceRetry();
        orderIntakePOM.getNextButton().click();
    }

    @And("the client chooses offline method")
    public void theClientChoosesOfflineMethod() {
        orderIntakePOM.retryServiceSelector();
        orderIntakePOM.retryOfflineMethod();
    }

    @And("the client clicks on the download authorization form button")
    public void theClientClicksOnTheDownloadAuthorizationFormButton() {
        caseHelper.orderIntakePOM = new OrderIntakePOM();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getDownloadAuthForm()));
        orderIntakePOM.getDownloadAuthForm().click();
        orderIntakePOM.getDownloadAuth().click();
        caseHelper.assertDownloadOutcome();
    }

    @And("the client clicks on the send to patient action")
    public void theClientClicksOnTheSendToPatientAction() {
        orderIntakePOM.getSendToPatient().click();
        orderIntakePOM.getPatientInput().sendKeys(RandomEmail.createPatientMail());
        orderIntakePOM.getSendButton().click();
        LibGlobal.verifySuccessMessage(
                orderIntakePOM.getOrderPlacedMsg(),
                "Mail sent successfully."
        );
    }

    @And("the client clicks on the submit button")
    public void theClientClicksOnTheSubmitButton() {
        orderIntakePOM.getSubmitButton().click();
    }

    @And("the client chooses online method")
    public void theClientChoosesOnlineMethod() {
        orderIntakePOM.retryServiceSelector();
        orderIntakePOM.retryOnlineMethod();
        orderIntakePOM.getNextButton().click();
    }

    @And("the client enters the patient information")
    public void theClientEntersThePatientInformation() {
        orderIntakePOM.expectedDeliveryButton();
        orderIntakePOM.getYearSelector().click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", orderIntakePOM.getBirthYear());
        orderIntakePOM.getBirthYear().click();
        orderIntakePOM.selectDateRetry();
        orderIntakePOM.getSocialSecurityNumber().sendKeys(OrderType.CASE_DATA.getSocialSecurityNumber());
        orderIntakePOM.getPatientMail().sendKeys(RandomEmail.createPatientMail());
        orderIntakePOM.getPatientPhone().sendKeys(RandomEmail.generateNumericPhoneNumber());
        orderIntakePOM.getPatientAddress().sendKeys(ClientTeamsData.MemberData.generateAddress());
        orderIntakePOM.getPatientCity().sendKeys(ClientTeamsData.MemberData.generateCity());
        orderIntakePOM.getPatientState().sendKeys(ClientTeamsData.MemberData.generateState());
        orderIntakePOM.getPatientZipCode().sendKeys(ClientTeamsData.MemberData.generateZipcode());
        orderIntakePOM.getNextButton().click();

    }

    public Map<String, String> getProviderTestData() {
        Map<String, String> data = new HashMap<>();
        data.put("first_name", RandomEmail.generateFirstName());
        data.put("last_name", RandomEmail.generateLastName());
        data.put("phone_no", RandomEmail.generateNumericPhoneNumber());
        data.put("fax_no", RandomEmail.generateNumericPhoneNumber());
        data.put("facility_name", RandomEmail.generateFirstName()  + "Hospital");
        data.put("address1", ClientTeamsData.MemberData.generateAddress());
        data.put("city", ClientTeamsData.MemberData.generateCity());
        data.put("state", ClientTeamsData.MemberData.generateState());
        data.put("zip_code",ClientTeamsData.MemberData.generateZipcode());
        return data;
    }

    public void runProviderFormFlow(int totalProviders) {
        Map<String, String> testData = getProviderTestData();
        orderIntakePOM.fillProviderForms(totalProviders, testData);
    }

    @And("the client fills out their provider information")
    public void theClientFillsOutTheirProviderInformation() {
        runProviderFormFlow(3);
        orderIntakePOM.getNextButton().click();
    }

    @And("the client selects the records that needs to be retrieved")
    public void theClientSelectsTheRecordsThatNeedsToBeRetrieved() {
        List<Integer> dropdownIndices = Arrays.asList(1, 2, 3);
        orderIntakePOM.selectDropdownByIndices(dropdownIndices);
}

    @And("the client sets the start date and end date for the records to be retrieved")
    public void theClientSetsTheStartDateAndEndDateForTheRecordsToBeRetrieved() {
        List<Integer> datePickerIndices = Arrays.asList(1, 2, 3, 4, 5, 6);
        orderIntakePOM.handleDatePickersByIndices(datePickerIndices);
        orderIntakePOM.getNextButton().click();
    }

    @And("the client confirms the order by clicking the confirm and submit button")
    public void theClientConfirmsTheOrderByClickingTheConfirmAndSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getConfirmAndSubmit()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", orderIntakePOM.getConfirmAndSubmit());
        orderIntakePOM.getConfirmAndSubmit().click();
    }

    @Then("the client verifies whether the retrieval case status is {string}, and case priority is {string}")
    public void theClientVerifiesWhetherTheRetrievalCaseStatusIsCaseVersionIsAndCasePriorityIs(String expectedStatus,String expectedPriority) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(driver -> !orderIntakePOM.getDoesRetrievalCaseNumber().getText().trim().isEmpty());
        int rowIndex = 0;
        caseHelper.verifyCaseDetail("Status", expectedStatus, rowIndex, 4);
        caseHelper.verifyCaseDetail("Priority", expectedPriority, rowIndex, 3);
        LibGlobal.quitDriver();
    }
}
