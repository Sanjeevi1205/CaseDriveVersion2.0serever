Feature: Client Dashboard

  @SmokeTest
  Scenario: Verify client can filter case analytics
    Given the client logs in using valid credentials
    When the client on the dashboard clicks Cases, Pages, Billed Hours, and Billed Amount to view details
    And clicks the filter option to check filtered data
    Then the client sees the respective filtered data

  @positive
  Scenario: Verify the client can filter the cost analysis
    Given the client logs in using valid credentials
    When the client on the dashboard clicks specified service to view
    And clicks the filter option to check filtered data on cost analysis
    Then the client sees the respective filtered data on cost analysis

  @positive
  Scenario: Verify the client can view the All Reports for case
    Given the client logs in using valid credentials
    When the client clicks the view all reports in Case Analytics and Cost Analysis
    Then the client see the case report page

  @positive
  Scenario: Verify the client can view the All Reports for billing
    Given the client logs in using valid credentials
    When the client clicks the view all reports in Billing Stats and Unpaid Stats
    Then the client see the billing report page