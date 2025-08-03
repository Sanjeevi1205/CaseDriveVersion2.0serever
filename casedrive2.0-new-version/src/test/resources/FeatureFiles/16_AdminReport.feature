@Positive
Feature: Admin Report Functionality

  @Casedownload @Regression
  Scenario: Verify that the Admin can download the case report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Case Report
    And clicks the Export button to download the report
    Then the Admin opens the report to verify the exported Case Report

  @Caseaddfilter @Regression
  Scenario: Verify that the Admin can apply a filter to the case report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Case Report
    And clicks the Add Filter button to filter the data
    And clicks the Export button to download the filtered report
    Then the Admin opens the report to verify the exported Case Filter Report

  @Revenue @Regression
  Scenario: Verify that the Admin can download the revenue report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Revenue Report
    And clicks the Export button to download the Revenue report
    Then the Admin opens the report to verify the exported Revenue Report
    
  @Billingdownload @Regression
  Scenario: Verify that the Admin can download the billing report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Billing Report
    And clicks the Export button to download the report
    Then the Admin opens the report to verify the exported Billing Report

  @Billingaddfilter @Regression
  Scenario: Verify that the Admin can apply a filter to the billing report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Billing Report
    And clicks the Add Filter button to filter the data
    And clicks the Export button to download the filtered report
    Then the Admin opens the report to verify the exported Billing Filter Report

  @ClientStatistics @Regression
  Scenario: Verify that the Admin can download the Client Statistics report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Client Statistics Report
    And clicks the Export button to download the report
    Then the Admin opens the report to verify the exported Client Statistics Report

  @TopPerformance @Regression
  Scenario: Verify that the Admin can download the Employee Statistics report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Employee Statistics Report
    And clicks the Top Performance button to navigate to the report page
    And clicks the Export button to download the report
    Then the Admin opens the report to verify the exported Top Performance Report

  @ClientWiseMetrics @Regression
  Scenario: Verify that the Admin can download the Client Wise Metrics report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Employee Statistics Report
    And clicks the Client Wise Metrics button to navigate to the report page
    And clicks the Export button to download the report
    Then the Admin opens the report to verify the exported Client Wise Metrics Report

  @PartnerStatistics @Regression
  Scenario: Verify that the Admin can download the Partner Statistics report
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Reports menu and selects the Partner Report
    And clicks the Export button to download the report
    Then the Admin opens the report to verify the exported Partner Statistics Report