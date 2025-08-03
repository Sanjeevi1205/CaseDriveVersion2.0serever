Feature: Estimate and Task Functionality

  @CaseWiseEstimateCreation @Regression @Casecompletion @Calculations
  Scenario Outline: Verify the admin can create estimate for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Estimate Requested" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the Edit button and edits No of Pages
    And the admin clicks the Save button
    And the admin clicks the action button
    And the admin clicks on the create estimate button
    And the admin enters the quantity for the services and selects the expiry date
    And the admin clicks the Save Estimate button
    And the admin should be notified with a pop-up message Case estimate created successfully
    Then the admin verifies whether the case status is changed to "Estimation in Process"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @VolumeWiseEstimateCreation @Regression @Casecompletion @Calculations
  Scenario Outline: Verify the admin can create estimate for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Estimate Requested" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the Edit button and edits No of Pages
    And the admin clicks the Save button
    And the admin clicks the action button
    And the admin clicks on the create estimate button
    And the admin enters the quantity in pages for the services and selects the expiry date
    And the admin clicks the Save Estimate button
    And the admin should be notified with a pop-up message Case estimate created successfully
    Then the admin verifies whether the case status is changed to "Estimation in Process"
    Examples:
      | Client Name    | Client Type |
      | test211 client | Volume wise |

  @OpenCaseEstimateCreation @Regression @Casecompletion @Calculations
  Scenario Outline: Verify the admin can create estimate for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Open" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the Edit button and edits No of Pages
    And the admin clicks the Save button
    And the admin clicks the action button
    And the admin clicks on the create estimate button
    And the admin enters the quantity for the services and selects the expiry date
    And the admin clicks the Save Estimate button
    And the admin should be notified with a pop-up message Case estimate created successfully
    Then the admin verifies whether the case status is changed to "Estimation in Process"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseDownload @Regression @Casecompletion @Download
  Scenario Outline: Verify that the admin able to download the case files from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    Then the admin downloads a case of "<Client Name>" a "<Client Type>" client with "Open" status and priority "Standard"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseDocsDownload @Regression @Casecompletion @Download
  Scenario Outline: Verify that the admin able to download the source file of the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Open" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks on the Case Docs Submenu
    And the admin clicks on the source files folder
    Then the Admin clicks on the three dot button of the files and assert the download functionality

    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @InternalEstimateHours @Regression @Casecompletion @Mail @QueryFromACase
  Scenario Outline: Verify admin able to send Queries for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Open" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the case queries menu
    And the admin sends a text message to the client regarding the case
    Then verify whether the mail for "New Query Received" is being received
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @AdminAssignTask @Regression @Casecompletion
  Scenario Outline: Verify that the admin can assign a task for the case to the employee from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Open" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin clicks the assign button
    And the admin assigns the tasks from the template to the employees
    And the admin clicks the assign button in the template
    And the admin should be notified with a pop-up message Case task created successfully
    Then the admin verifies whether the case status is changed to "Assigned"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @QualityAudit @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to assign a quality Audit and give mark for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Assigned" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    And the admin verifies whether any quality audit task is assigned to an user
    And the admin clicks the assign button in the Quality audit menu
    And the admin selects the details from the dropdown
    And the admin click on the assign in the assigning modal
    And the admin start auditing the reviewers output and save the Marks
    Then the admin verifies the total quality audit mark given for the case
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseAcknowledgementMail @Regression @Casecompletion @Mail
  Scenario Outline: Verify that the admin able to send acknowledgement mail for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Assigned" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin click on the acknowledgement button
    Then verify whether the mail for "Case Acknowledgement" is being received
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @ReassignTask @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to Re-assign the task for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Assigned" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    And the admin clicks the three dot action of a task in "Assigned" status
    And the admin clicks the re assign button and reassign the task to another employee from another team
    And the admin should see the task status is changed to "Reassigned"
    Then the admin verifies whether the case status is changed to "Work in Progress"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @InternalEstimateHours @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to enter internal estimate hrs for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Work in Progress" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    And the admin enters the internal estimate hours for all the tasks
    Then the admin verifies whether the total estimate hours is calculated correctly
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseTaskPlayaAndPause @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to play and pause a task for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Work in Progress" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    And the admin clicks the play button of a task in "Assigned" status
    And the admin should see the task status is changed to "Work in Progress"
    And the admin clicks the Pause button of a task in "Work in Progress" status
    And the admin select a reason enter a note and clicks the confirm button
    And the admin should see the task status is changed to "Paused"
    Then the admin verifies whether the case status is changed to "Work in Progress"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @WorkInProgressToCompleted @Casecompletion @Regression
  Scenario Outline: Verify that the admin able to complete a task for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Work in Progress" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    And the admin clicks the play button and completes tasks in "Assigned" status
    And the admin should see that all task status is changed to "Completed"
    Then the admin verifies whether the case status is changed to "Completed"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseApprove @Regression @Casecompletion @FileUpload
  Scenario Outline: Verify that the admin able to approve the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Completed" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin clicks on the case approve button
    And the admin upload different file types
    And the admin clicks on the confirm button
    Then the admin verifies whether the case status is changed to "Ready to Deliver"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseWiseCaseDelivery @Regression @Casecompletion @CaseDelivery @Calculations
  Scenario Outline: Verify that the admin able to deliver the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Ready to Deliver" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin clicks on the Upload and deliver button
    And the admin enters the billed amount
    And the admin clicks on the upload and deliver now button in the pre bill table
    Then the admin verifies whether the case status is changed to "Delivered"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseOnHoldAdmin @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to hold the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Open" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin clicks on the Hold button
    And the admin clicks on the confirm button
    And the admin should see the success message that the case has been "Hold"
    Then the admin verifies whether the case status is changed to "On-hold"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseCancelAdmin @Regression @Casecompletion
  Scenario Outline: Verify that the admin able cancel the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Cancel Request" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin clicks on the Cancel button
    And the admin clicks on the confirm button
    And the admin should see the success message that the case has been "Canceled"
    Then the admin verifies whether the case status is changed to "Canceled"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @CaseResumeAdmin @Regression @Casecompletion
  Scenario Outline: Verify that the admin able Resume the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Cancel Request" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin clicks on the resume button
    And the admin clicks on the confirm button
    And the admin should see the success message that the case has been "Resumed"
    Then the admin verifies whether the case status is changed to "Open"
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @TaskDeletion @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to delete a single task for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Assigned" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    And the admin clicks the three dot action of a task in "Assigned" status
    Then the admin clicks the delete button and deletes a task
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @MultitaskDeletion @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to delete a multi task for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Assigned" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    And the admin clicks on the select all task button
    And the Admin clicks on the task action button
    Then the admin clicks on the delete button in the task action and deletes the task
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @SingleTaskAssign @Regression @Casecompletion
  Scenario Outline: Verify that the admin able to assign a single task for the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Assigned" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the task menu
    Then the admin creates a single task by clicking on the add task button
    Examples:
      | Client Name | Client Type |
      | Benish Test | Case wise   |

  @VolumeWiseCaseDelivery @Regression @Casecompletion @Calculations
  Scenario Outline: Verify that the admin able to deliver the case from the "<Client Type>" client
    Given the admin logs into the casedrive URL
    When the admin clicks the Cases side menu and selects Review Cases
    And the admin views a case of "<Client Name>" a "<Client Type>" client with "Ready to Deliver" status and priority "Standard"
    And the admin clicks on the view button
    And the admin clicks the action button
    And the admin clicks on the Upload and deliver button
    And the admin enters the Pages in the quantity fields
    And the admin clicks on the upload and deliver now button in the pre bill table
    Then the admin verifies whether the case status is changed to "Delivered"
    Examples:
      | Client Name    | Client Type |
      | test211 client | Volume wise |





