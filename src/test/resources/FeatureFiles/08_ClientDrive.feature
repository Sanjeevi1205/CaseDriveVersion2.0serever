Feature: Client Drive Functionality
  @Upload @Regression
  Scenario: Verify the client can view the uploaded document for the case
    Given the client logs in using valid credentials
    When the client clicks the side menu
    When the client clicks the drive menu
    And the client opens the with "Test" status and selects folder
    And the client opens the case files folder
    And the client opens the latest case folder
    And the client opens the source file folder
    And the client opens the new case folder
    Then the client clicks the three-dot on the file and clicks the open menu

  @View @Regression
  Scenario: Verify the client can view the details of the uploaded document for the case
    Given the client logs in using valid credentials
    When the client clicks the side menu
    When the client clicks the drive menu
    And the client opens the with "Test" status and selects folder
    And the client opens the case files folder
    And the client opens the latest case folder
    And the client opens the source file folder
    And the client opens the new case folder
    Then the client clicks the three-dot on the file and clicks the details menu

@Download @Regression
  Scenario: Verify the client can download the uploaded document for the case
    Given the client logs in using valid credentials
    When the client clicks the side menu
    When the client clicks the drive menu
    And the client opens the with "Test" status and selects folder
    And the client opens the case files folder
    And the client opens the latest case folder
    And the client opens the source file folder
    And the client opens the new case folder
    Then the client clicks the three-dot on the file and clicks the download menu

#@postive1
#Scenario: Verify the client can filter the drive to view the document
#Given the client logs in using valid credentials
#When the client clicks the side menu 
#When the client clicks the drive menu
#And the client opens the with "Test" status and selects folder 
#And the client opens the case files folder
#And the client opens the latest case folder
#And the client opens the source file folder
#And the client opens the new case folder
#And the client opens the new case folder
#Then the client clicks the three-dot on the file and clicks the copy link