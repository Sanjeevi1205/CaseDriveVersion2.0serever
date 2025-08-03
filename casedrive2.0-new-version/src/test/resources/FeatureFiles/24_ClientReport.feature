Feature: Client Report Functionality

  @Casereport @Regression
  Scenario: Verify the client can download the case report
    Given the client logs in using valid credentials
    When the client clicks the reports menu and clicks the case report
    And clicks the export button to download the report
    Then the client opens the report to verify the export report

  @Billingreport @Regression
  Scenario: Verify the client can download the billing report
    Given the client logs in using valid credentials
    When the client clicks the reports menu and clicks the billing report
    And clicks the export button to download the report
    Then the client opens the report to verify the export report

  @Filtercasereport  @Regression
  Scenario: Verify the client can filter the reports
    Given the client logs in using valid credentials
    When the client clicks the reports menu and clicks the case reports details
    And clicks the add filter button to filter the data
    Then the client opens the report to verify the case

  @Filterbillingreport  @Regression
  Scenario: Verify the client can filter the reports
    Given the client logs in using valid credentials
    When the client clicks the reports menu and clicks the billing reports details
    And clicks the add filter button to filter the data
    Then the client opens the report to verify the case