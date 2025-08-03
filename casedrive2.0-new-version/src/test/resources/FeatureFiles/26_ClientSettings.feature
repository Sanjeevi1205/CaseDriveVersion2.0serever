Feature: Client Settings Functionality
  @GeneralSettings @Regression
  Scenario: Verify that the client can modify their details
    Given the client logs in using valid credentials
    When the client clicks on the Settings icon
    And the client clicks on the Edit button
    And the client modify their details in general details page
    And the client clicks the Save button
    Then the client notified with the popup message Details saved successfully.

  @DesiredServices @Regression
  Scenario: Verify that the client can request a new service to add
    Given the client logs in using valid credentials
    When the client clicks on the Settings icon
    And the client clicks the Services and Desired Services sidemenu
    And the client clicks on the Edit button
    And the client select the service to add
    And the client clicks the Request button
    Then the client notified with the popup message Service request sent successfully

  @Notifications @Regression
  Scenario: Verify that the client can modify the email notifications
    Given the client logs in using valid credentials
    When the client clicks on the Settings icon
    And the client clicks the Notification and Email Settings sidemenu
    And the client clicks on the Edit button
    And the client deselect the required mail service
    And the client clicks the Save button
    Then the client notified with the popup message Email settings updated