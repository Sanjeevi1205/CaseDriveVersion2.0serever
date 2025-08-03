Feature: Client Billing and Payment

  @PaymentSuccessful @Regression
  Scenario: Verify the client successfully makes a payment
    Given the client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Invoice
    And the client clicks the Pay Now button redirecting to the payment options page
    And the client fills in the card details and clicks the Pay button
    Then the client is redirected to the Payment Successful page

  @Paymentdownloaded @Regression
  Scenario: Verify the client successfully downloads an invoice
    Given the client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Invoice
    And the client clicks the Download button in the action table
    Then the client should verify that the invoice is downloaded successfully

  @Paymentdisabled @Regression
  Scenario: Verify the client is unable to make a payment
    Given the private client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Invoice
    And the client sees the Pay Now button is disabled
    Then the client should remain on the same page with the Pay Now button disabled
 
  @Viewpage @Regression
  Scenario: Verify the client able to view the Receipts
    Given the client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Receipts
    And the client clicks the three dot button of the bill and click the view option
    Then the client should navigate to the respective view page

  @Downloadedfile @Regression
  Scenario: Verify the client able to download the Receipts
    Given the client logs in using valid credentials
    When the client clicks the side menu
    And the client navigates to Billing and selects Receipts
    And the client clicks the three dot button of the bill and click the download option
    Then the client should verify the downloaded file