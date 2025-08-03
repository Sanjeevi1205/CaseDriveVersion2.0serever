Feature: OrderIntake- Additional Case

  @AdditionalCaseCustomLink  @Regression @OrderIntake
  Scenario: Verify that the Client adds an additional case with custom links
    Given the client logs in using valid credentials
    When the client clicks on Add Additional Record to add a new case
    And the client select a parent case from the search field
    And the client selects a case, enables the expedite button
    And the client verifies whether the services of the parent case is selected by default
    And the client provides the upload link and download link, and clicks submit button
    And the client clicks the confirm button in the confirmation pop up
    And the client sees the Additional case success message and is redirected to the case list page
    And the client verifies whether the case status is "Open", case version is "Additional" and case priority is "Expedited"
    Then verify whether the mail for "Additional Case" is being received


  @AdditionalCaseFileUpload @Regression @OrderIntake @FileUpload
  Scenario: Verify that the client adds an additional case by uploading different file types
    Given the client logs in using valid credentials
    When the client clicks on Add Additional Record to add a new case
    And the client select a parent case from the search field
    And the client selects a case, enables the expedite button
    And the client verifies whether the services of the parent case is selected by default
    And the client enters the case overview
    And the client uploads different file types, and clicks the submit button
    And the client clicks the confirm button in the confirmation pop up
    And the client sees the Additional case success message and is redirected to the case list page
    And the client verifies whether the case status is "Open", case version is "Additional" and case priority is "Expedited"
    Then verify whether the mail for "Additional Case" is being received
