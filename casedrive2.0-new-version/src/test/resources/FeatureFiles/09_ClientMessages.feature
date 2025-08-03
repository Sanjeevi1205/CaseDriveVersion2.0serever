Feature: Client Query Functionality

  @Casequery @Regression
  Scenario: Verify the client can send queries to the Admin team
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Case Queries
    And the user clicks the New Query button
    And the user selects the query type, case, and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries page

  @UnreadCasequery @Regression
  Scenario: Verify the client can mark queries as unread
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Case Queries
    And the user clicks the unread button
    Then the user verifies the message is displayed on the Unread page

  @ArchiveCasequery @Regression
  Scenario: Verify the client can archive queries
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Case Queries
    And the user clicks the archive button
    Then the user verifies the message is displayed on the Archived page

  @Billingquery @Regression
  Scenario: Verify the client can send queries to the Admin team
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Billing Queries
    And the user clicks the New Query button
    And the user selects the query type, reason, and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries billing or technical or other page

  @UnreadBillingquery @Regression
  Scenario: Verify the client can mark queries as unread
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Billing Queries
    And the user clicks the unread button
    Then the user verifies the message is displayed on the Unread billing or technical or other page

  @ArchiveBillingquery @Regression
  Scenario: Verify the client can archive queries
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Billing Queries
    And the user clicks the archive billing or technical or other button
    Then the user verifies the message is displayed on the Archived billing or technical or other page

  @Technicalquery @Regression
  Scenario: Verify the client can send queries to the Admin team
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Technical Queries
    And the user clicks the New Query button
    And the user selects the query type, reason, and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries billing or technical or other page

  @UnreadTechnicalquery @Regression
  Scenario: Verify the client can mark queries as unread
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Technical Queries
    And the user clicks the unread button
    Then the user verifies the message is displayed on the Unread billing or technical or other page

  @ArchiveTechnicalquery @Regression
  Scenario: Verify the client can archive queries
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Technical Queries
    And the user clicks the archive billing or technical or other button
    Then the user verifies the message is displayed on the Archived billing or technical or other page

  @Otherquery @Regression
  Scenario: Verify the client can send queries to the Admin team
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Other Queries
    And the user clicks the New Query button
    And the user selects the query type and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries billing or technical or other page

  @UnreadOtherquery @Regression
  Scenario: Verify the client can mark queries as unread
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Other Queries
    And the user clicks the unread button
    Then the user verifies the message is displayed on the Unread billing or technical or other page

  @ArchiveOtherquery @Regression
  Scenario: Verify the client can archive queries
    Given the client logs in using valid credentials
    When the user clicks the Messages menu and selects Other Queries
    And the user clicks the archive billing or technical or other button
    Then the user verifies the message is displayed on the Archived billing or technical or other page