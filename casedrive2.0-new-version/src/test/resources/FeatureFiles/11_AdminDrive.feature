Feature: Admin Drive Functionality

  @Viewuploaded @Regression
  Scenario: Verify the Admin can view the uploaded document for the case
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    When the Admin clicks the drive menu
    And the Admin opens the with "Test" status and selects folder
    And the Admin opens the case files folder "<foldername>"
    And the Admin opens the client files folder "<foldername>"
    And the Admin opens the latest case folder
    And the Admin opens the client case folder "<foldername>"
    And the Admin opens the source file folder
    And the Admin opens the new case folder
    Then the Admin clicks the three-dot on the file and clicks the open menu

  @Viewdetails @Regression
  Scenario: Verify the Admin can view the details of the uploaded document for the case
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    When the Admin clicks the drive menu
    And the Admin opens the with "Test" status and selects folder
    And the Admin opens the case files folder "<foldername>"
    And the Admin opens the client files folder "<foldername>"
    And the Admin opens the latest case folder
    And the Admin opens the client case folder "<foldername>"
    And the Admin opens the source file folder
    And the Admin opens the new case folder
    Then the Admin clicks the three-dot on the file and clicks the details menu

  @Downloaddocument @Regression
  Scenario: Verify the Admin can download the uploaded document for the case
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    When the Admin clicks the drive menu
    And the Admin opens the with "Test" status and selects folder
    And the Admin opens the case files folder "<foldername>"
    And the Admin opens the client files folder "<foldername>"
    And the Admin opens the latest case folder
    And the Admin opens the client case folder "<foldername>"
    And the Admin opens the source file folder
    And the Admin opens the new case folder
    Then the Admin clicks the three-dot on the file and clicks the download menu

  @Copylink  @Regression
  Scenario: Verify the Admin can filter the drive to view the document
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    When the Admin clicks the drive menu
    And the Admin opens the with "Test" status and selects folder
    And the Admin opens the case files folder "<foldername>"
    And the Admin opens the client files folder "<foldername>"
    And the Admin opens the latest case folder
    And the Admin opens the client case folder "<foldername>"
    And the Admin opens the source file folder
    And the Admin opens the new case folder
    And the Admin opens the new case folder
    Then the Admin clicks the three-dot on the file and clicks the copy link

  @Rename  @Regression
  Scenario: Verify the Admin can filter the drive to view the document
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    When the Admin clicks the drive menu
    And the Admin opens the with "Test" status and selects folder
    And the Admin opens the case files folder "<foldername>"
    And the Admin opens the client files folder "<foldername>"
    And the Admin opens the latest case folder
    And the Admin opens the client case folder "<foldername>"
    And the Admin opens the source file folder
    And the Admin opens the new case folder
    And the Admin opens the new case folder
    Then the Admin clicks the three-dot on the file and clicks the rename

  @Bin  @Regression
  Scenario: Verify the Admin can filter the drive to view the document
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    When the Admin clicks the drive menu
    And the Admin opens the with "Test" status and selects folder
    And the Admin opens the case files folder "<foldername>"
    And the Admin opens the client files folder "<foldername>"
    And the Admin opens the latest case folder
    And the Admin opens the client case folder "<foldername>"
    And the Admin opens the source file folder
    And the Admin opens the new case folder
    And the Admin opens the new case folder
    Then the Admin clicks the three-dot on the file and clicks the bin