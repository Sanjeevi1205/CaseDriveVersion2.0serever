Feature: Client Case Estimate Approval

  @Approval @Regression
  Scenario: Verify the client can approve the estimate for the case
    Given the client logs in using valid credentials
    When the client clicks the Billing side menu and selects Estimate
    And the client opens the case awaiting approval
    And the client clicks the Approve Estimate button and then clicks the Confirm button
    Then the client is notified with a popup message stating that the case estimation was approved successfully

  @Decline @Regression
  Scenario: Verify the client can decline the estimate for the case
    Given the client logs in using valid credentials
    When the client clicks the Billing side menu and selects Estimate
    And the client opens the case awaiting approval
    And the client clicks the Decline Estimate button and then clicks the Confirm button
    Then the client is notified with a popup message stating that the case estimation was declined successfully