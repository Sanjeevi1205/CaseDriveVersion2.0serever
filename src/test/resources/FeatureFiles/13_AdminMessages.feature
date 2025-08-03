Feature: Admin Query Functionality

  @Casequery @Regression
  Scenario: Verify the admin can send queries to the Client
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Case Queries
    And the user clicks the New Query button
    And the user selects the query type, client, case, and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries messages page

  @UnreadCasequery @Regression
  Scenario: Verify the admin can mark queries as unread
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Case Queries
    And the user clicks the unread query button
    Then the user verifies the message is displayed on the Unread message page

  @ArchiveCasequery @Regression
  Scenario: Verify the admin can archive queries
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Case Queries
    And the user clicks the archive query button
    Then the user verifies the message is displayed on the Archived message page

  @Billingquery @Regression
  Scenario: Verify the admin can send queries to the Client
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Billing Queries
    And the user clicks the New Query button
    And the user selects the query type, client, reason, and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries billing or technical or other message page

  @UnreadBillingquery @Regression
  Scenario: Verify the admin can mark queries as unread
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Billing Queries
    And the user clicks the unread query button
    Then the user verifies the message is displayed on the Unread billing or technical or other message page

  @ArchiveBillingquery @Regression
  Scenario: Verify the admin can archive queries
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Billing Queries
    And the user clicks the archive billing or technical or other query button
    Then the user verifies the message is displayed on the Archived billing or technical or other message page

  @Technicalquery @Regression
  Scenario: Verify the admin can send queries to the Client
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Technical Queries
    And the user clicks the New Query button
    And the user selects the query type, client, reason, and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries billing or technical or other message page

  @UnreadTechnicalquery @Regression
  Scenario: Verify the admin can mark queries as unread
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Technical Queries
    And the user clicks the unread query button
    Then the user verifies the message is displayed on the Unread billing or technical or other message page

  @ArchiveTechnicalquery @Regression
  Scenario: Verify the admin can archive queries
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Technical Queries
    And the user clicks the archive billing or technical or other query button
    Then the user verifies the message is displayed on the Archived billing or technical or other message page

  @Otherquery @Regression
  Scenario: Verify the admin can send queries to the Client
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Other Queries
    And the user clicks the New Query button
    And the user selects the query type, client and enters the message
    And the user clicks the Create button
    Then the user verifies the message is displayed on the All Queries billing or technical or other message page

  @UnreadOtherquery @Regression
  Scenario: Verify the admin can mark queries as unread
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Other Queries
    And the user clicks the unread query button
    Then the user verifies the message is displayed on the Unread billing or technical or other message page

  @ArchiveOtherquery @Regression
  Scenario: Verify the admin can archive queries
    Given the admin logs into the casedrive URL
    When the user clicks the Messages menu and selects Other Queries
    And the user clicks the archive billing or technical or other query button
    Then the user verifies the message is displayed on the Archived billing or technical or other message page