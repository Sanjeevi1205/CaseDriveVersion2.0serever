Feature: Client case list three dot action functionalities

  @EstimateRequest @Regression @CaseListAction
  Scenario: Verify Client Requests Estimate for Open Case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on Request for estimate button
    And the client sees the success message Estimate Request request is sent
    Then verify the case status changes to "Estimation in Process"

  @HoldRequest @Regression @CaseListAction
  Scenario: Verify Client Requests Hold for Open Case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on Request for Hold button
    And the client sees the success message Case Hold request is sent
    Then verify the case status changes to "Hold Request"

  @ResumeRequest @Regression @CaseListAction
  Scenario: Verify Client Requests Resume for a Case on Hold
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "On-hold" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on Request to Resume button
    And the client sees the success message Resume request is sent
    Then verify the case status changes to "Resume Request"

  @CancelRequest @Regression @CaseListAction
  Scenario: Verify Client Requests Cancel for Open Case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on Request to Cancel button
    And the client sees the success message Case Cancel request is sent
    Then verify the case status changes to "Cancel Request"

  @ExpeditePriorityRequest @Regression @CaseListAction  
  Scenario: Verify Client Requests Change Priority from Standard to Expedite for Open Case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on the change priority button and selects Expedited from the dropdown
    And the client sees the success message for the request is sent
    Then the client verifies that the priority is still in "Standard"

  @ExpeditePriorityRequestExpedited @Regression @CaseListAction 
  Scenario: Verify Client Requests Change Priority from Expedite to Expedite for Open Case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Expedited"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on the change priority button and selects Expedited from the dropdown
    And the client sees a browser alert message priority is already in the "Expedited"
    Then the client verifies that the priority is still in "Expedited"

  @StandardPriorityRequest @Regression @CaseListAction 
  Scenario:  Verify Client Requests Change Priority from Expedite to Standard for Open Case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Expedited"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on the change priority button and selects Standard from the dropdown
    And the client sees the success message for the request is sent
    Then the client verifies that the priority is still in "Expedited"

  @standardPriorityRequestStandard @Regression @CaseListAction 
  Scenario:  Verify Client Requests Change Priority from Standard to Standard for Open Case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the action button
    And the client clicks on the change priority button and selects Standard from the dropdown
    And the client sees a browser alert message priority is already in the "Standard"
    Then the client verifies that the priority is still in "Standard"

  @MissingRecords @Regression @CaseListAction @FileUpload
  Scenario:  Verify Client can able to upload missing records in open status
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Open" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the missing record button
    And the client uploads different file types
    And the client clicks on the confirm button
    Then the client sees an success message for file upload

  @MissingRecordsWorkInProgress @Regression @CaseListAction @FileUpload
  Scenario:  Verify Client can able to upload missing records in Work in progress status
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Work in Progress" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the missing record button
    And the client uploads different file types
    And the client clicks on the confirm button
    Then the client sees an success message for file upload

  @MissingRecordsEstimationInProgress @Regression @CaseListAction @FileUpload
  Scenario:  Verify Client can able to upload missing records in Estimation in process status
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Estimation in Process" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the missing record button
    And the client uploads different file types
    And the client clicks on the confirm button
    Then the client sees an success message for file upload

  @CreatePublicNotes @Regression @CaseListAction
  Scenario:  Verify Client can able to create public notes
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Estimation in Process" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the add notes button in the notes menu
    And the client enters "public notes" in the notes input field
    And the client clicks on the post button
    Then the client verifies whether the "public notes" are being saved in the notes page

  @CreatePrivateNotes @Regression @CaseListAction
  Scenario:  Verify Client can able to create Private notes
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Estimation in Process" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the add notes button in the notes menu
    And the client enters "private notes" in the notes input field
    And the client enables the toggle switch and set it as private
    And the client clicks on the post button
    Then the client verifies whether the "private notes" are being saved in the notes page

  @ClientCaseListDownload @Regression @CaseListAction @Download
  Scenario:  Verify Client can able to download a delivered case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    Then the client clicks on the Download button for a "Delivered" case with priority "Standard"

  @ClientCaseDocsDownload @Regression @Download @CaseListAction
  Scenario:  Verify Client can able to download a deliverable files for delivered case
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Delivered" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on case docs menu
    And the client clicks on the deliverables menu
    Then the client clicks on the download button of the files and assert the download functionality

  @ClientCaseDocsDownload @Regression @Download @CaseListAction
  Scenario:  Verify Client able to send Query to the internal team
    Given the client logs in using valid credentials
    When the client clicks on the Case List and clicks on review cases
    And the client clicks on the three-dot action for an "Delivered" case with priority "Standard"
    And the client clicks on the view button
    And the client clicks on the Case Query menu and enter a message to send for our internal team
    Then verify whether the mail for "New Query Received" is being received