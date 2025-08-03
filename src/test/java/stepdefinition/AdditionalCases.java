package stepdefinition;

import baseclass.LibGlobal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pompages.OrderIntakePOM;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Log
public class AdditionalCases extends LibGlobal {
    private final OrderIntakePOM orderIntakePOM = new OrderIntakePOM();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


    @When("the client clicks on Add Additional Record to add a new case")
    public void theClientClicksOnAdditionalRecordFromTheSideMenu() {
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getSideMenu()));
        orderIntakePOM.getSideMenu().click();
        orderIntakePOM.getAddRecordsBtn().click();
        orderIntakePOM.getAdditionalRecords().click();
    }

    @And("the client select a parent case from the search field")
    public void theClientSelectAParentCaseFromTheSearchField() {
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getSearchCase()));
        orderIntakePOM.getSearchCase().click();
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getSelectCase()));
        orderIntakePOM.getSelectCase().click();
    }

    @And("the client selects a case, enables the expedite button")
    public void theClientSelectsACaseEnablesTheExpediteButton() {
        orderIntakePOM.getExpediteDisabled().click();
        orderIntakePOM.expectedDeliveryButton();
        orderIntakePOM.calenderNxtRetry();
        orderIntakePOM.selectDateRetry();
    }

    @And("the client verifies whether the services of the parent case is selected by default")
    public void theCaseWiseClientVerifiesWhetherTheSelectedServicesAreCorrect() {
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getDoesSelectedService1()));
        String service1Text = orderIntakePOM.getDoesSelectedService1().getText().trim();
        String service2Text = orderIntakePOM.getDoesSelectedService2().getText().trim();
        String service3Text = null;

        try {
            service3Text = orderIntakePOM.getDoesSelectedService3().getText().trim();
            log.info("Third service found: " + service3Text);
        } catch (Exception e) {
            log.info("There is no third service selected in the parent case.");
        }
        List<String> expectedServices = Arrays.asList(
                "Medical Chronology",
                "Narrative Summary",
                "Sorting",
                "Indexing",
                "Sorting & Indexing",
                "Sortex + Chrono + Narrative Bundle",
                "Sortex + Chrono Bundle",
                "Missing Record Identification",
                "Duplicate Record Identification & Extraction",
                "Hyperlinks",
                "Deposition Summary",
                "Expert Medical Opinion",
                "Life Care Planning"
        );

        List<String> presentServices = Arrays.asList(service1Text, service2Text);
        boolean foundService = false;
        for (String service : presentServices) {
            if (expectedServices.contains(service)) {
                log.info("Service found: " + service);
                foundService = true;
                break;
            }
        }
        if (!foundService) {
            log.info("No expected services found in the list.");
            Assert.fail("None of the expected services are present in the selected list.");
        } else {
            log.info("Test Passed: At least one expected service is present.");
        }
        orderIntakePOM.getNextButton().click();
    }

    @And("the client sees the Additional case success message and is redirected to the case list page")
    public void theClientSeesTheAdditionalCaseSuccessMessageAndIsRedirectedToTheCaseListPage() { LibGlobal.verifySuccessMessage(
            orderIntakePOM.getOrderPlacedMsg(),
            "Additional Case added successfully.");
        String expectedCaseListUrlPart = "/client/cases/review?pageIndex=0&search=&status=0&priority=All";
        wait.until(ExpectedConditions.urlContains(expectedCaseListUrlPart));
        String currentCaseListUrl = driver.getCurrentUrl();
        assert currentCaseListUrl != null;
        Assert.assertTrue("The current URL does not contain the expected part", currentCaseListUrl.contains(expectedCaseListUrlPart));
    }
}