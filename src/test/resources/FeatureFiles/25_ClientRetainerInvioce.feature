Feature: Retainer Invoice Payment and Download

  @Filedownload @Regression
  Scenario: Verify the client can download the retainer invoice
    Given the private client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Retainer Invoice
    And the client clicks the download button
    Then the client should see the file download

  @Paybutton @Regression
  Scenario: Verify the client can pay the amount for the retainer invoice
    Given the private client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Retainer Invoice
    And the client clicks the Pay Now button
    And the client is redirected to the payment options page
    And the client fills in the card details and clicks the Pay button
    Then the client is redirected to the Payment Successful page for retainer

  @Downloadbutton @Regression
  Scenario: Verify the client can download the retainer invoice using a filter
    Given the private client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Retainer Invoice
    And the client applies a filter and then clicks the download button
    Then the client should see the file download

