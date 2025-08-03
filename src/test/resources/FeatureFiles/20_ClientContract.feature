Feature: Client Contract

  @Contract @Regression
  Scenario: Verify the client can sign the HIPAA BAA contract
    Given the client logs in using valid credentials
    When the client selects the contract sidemenu
    And clicks the Sign button, then clicks it again
    And enters the client name, designation, and signature
    And selects the checkbox to agree
    And submits the form to complete the process
    Then the client is redirected to the contract page with a view option