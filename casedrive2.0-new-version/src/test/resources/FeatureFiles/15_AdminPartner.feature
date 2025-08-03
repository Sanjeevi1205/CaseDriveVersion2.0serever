Feature: Admin Partner Functionality

  @Individual @Regression
  Scenario: Verify that the admin can create a partner
    Given the admin logs into the casedrive URL
    When the admin clicks the Partners side menu
    And the admin clicks the Create Partner button
    And the admin enters the basic details of the partner
    And clicks the Create button
    Then the admin is notified with a popup message stating that the partner was created successfully

  @Company @Regression
  Scenario: Verify that the admin can create a company partner
    Given the admin logs into the casedrive URL
    When the admin clicks the Partners side menu
    And the admin clicks the Create Partner button
    And the admin enable the toggle button for company
    And the admin enters the basic business details of the partner
    And clicks the Create button
    Then the admin is notified with a popup message stating that the partner was created successfully

  @AdminPartner @Regression
  Scenario: Verify that the admin can export the list of partners
    Given the admin logs into the casedrive URL
    When the admin clicks the Partners side menu
    And the admin clicks the Export button
    Then the admin opens the report to verify the export file