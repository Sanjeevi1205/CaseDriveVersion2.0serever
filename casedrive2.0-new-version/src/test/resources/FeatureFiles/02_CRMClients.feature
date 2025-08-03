Feature: Verify the Lead to Client conversion in Casedrive CRM

  @ConversionNegativeScenario @Regression @crm
  Scenario: Verify the admin cannot convert the lead to a client without selecting required dropdown values
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin select the lead in Onboard completed status to convert as client
    And the admin click Edit button and create company
    And the admin saves the changes and click convert button
    And the admin does not select the required dropdown options from the pop-up window
    Then the admin verifies that an error message is displayed, indicating the dropdown values must be selected

  @ClientConversion @Regression @crm
  Scenario: Verify the admin can able to convert the lead as client
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin select the lead in Onboard completed status to convert as client
    And the admin click Edit button and create company
    And the admin saves the changes and click convert button
    And the admin select the required dropdown options from the pop-up window
    And the admin click Convert button to confirm the changes
    And the admin navigates to the Client page to confirm the conversion
    Then the admin verifies whether the client ID is being generated automatically for the client

  @ClientEdit @Regression @crm
  Scenario: Verify the admin can able to edit the client details Page
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the clients
    And the admin clicks on the three dot action and view a client
    And the admin edit some details in the client details page
    And the admin saves the changes
    Then the admin should should sees the confirmation message in a toaster

  @CreateClient @Regression @crm  @MailCredentials
  Scenario: Verify the admin can able to create a client
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the clients
    And the admin clicks on the create button
    And the admin fill the information about the client
    And the admin clicks on the create button
    And the admin should should sees the confirmation message for client creation
    Then the client logs in using the credentials from email