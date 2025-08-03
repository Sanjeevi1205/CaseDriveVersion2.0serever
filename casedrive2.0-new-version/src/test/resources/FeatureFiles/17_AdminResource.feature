@AdminResource
Feature: Admin Resource

  @Edit @Regression
  Scenario: Verify that the Admin can edit the resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Product Sample
    And the Admin clicks the three-dot menu button and selects Edit
    And the Admin accesses the edit window and makes the necessary changes
    And clicks the Save button
    Then the Admin sees the message Updated successfully.

  @Upload @Regression
  Scenario: Verify that the Admin can upload a resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Product Sample
    And the Admin clicks the three-dot menu button and selects Upload
    And the Admin selects a file for upload
    Then the Admin sees the message Success.

  @View @Regression
  Scenario: Verify that the Admin can view a resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Product Sample
    And the Admin clicks the three-dot menu button and selects View
    And the Admin navigates to a new window displaying the files
    Then the Admin successfully downloads the file.

  @CopyFile @Regression
  Scenario: Verify that the Admin can copy the resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Product Sample
    And the Admin clicks the three-dot menu button and selects Copy
    And clicks the Save button
    Then the Admin successfully copies the file.

  @Share @Regression
  Scenario: Verify that the Admin can share the resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Product Sample
    And the Admin clicks the three-dot menu button and selects Share
    And the Admin enters the necessary details in the input fields
    And clicks the Save button
    Then the Admin sees the message Sample added successfully.

  @Delete @Regression
  Scenario: Verify that the Admin can delete the resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Product Sample
    And the Admin clicks the three-dot menu button and selects Delete
    Then the Admin sees the message Sample deleted successfully.

  @Expertise @Regression
  Scenario: Verify that the Admin can add expertise in the resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Marketing materials
    And the Admin clicks the Add menu button and selects Expertise
    And the Admin uploads thumbnail.jpg and attachment.pdf files and clicks the upload button
    Then the Admin sees expertise the message Created successfully.

  @Services @Regression
  Scenario: Verify that the Admin can add services in the resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Marketing materials
    And the Admin clicks the Add menu button and selects services
    And the Admin uploads thumbnail.jpg and attachment.pdf files and clicks the upload button
    Then the Admin sees services the message Created successfully

  @Industry @Regression
  Scenario: Verify that the Admin can add industry in the resource
    Given the admin logs into the casedrive URL
    When the Admin clicks the side menu
    And the Admin clicks the Resource menu and selects Marketing materials
    And the Admin clicks the Add menu button and selects industry
    And the Admin uploads thumbnail.jpg and attachment.pdf files and clicks the upload button
    Then the Admin sees industry the message Created successfully
#@test
#Scenario: Verify that the Admin can add test in the resource  
    #Given the admin logs into the casedrive URL   
    #When the Admin clicks the side menu  
    #And the Admin clicks the Resource menu and selects Marketing materials  
    #And the Admin clicks the  Add menu button and selects test  
    #And the Admin uploads thumbnail.jpg and attachment.pdf files and clicks the upload button  
    #Then the Admin sees test the message Created successfully
    
 @FAQCase @Regression
Scenario: Verify that the Admin can add FAQ support in the Resource section
 Given the admin logs into the casedrive URL 
 When the Admin clicks the side menu
 And the Admin clicks the Resource menu and selects FAQ Support
 And the Admin clicks the Add button and selects Case
 And the Admin clicks the dropdown menu and selects Case FAQ
 And the Admin enters the question description and answer
 And the Admin clicks the Upload button
 Then the Admin sees the message Created successfully under the section
   
@FAQEdit @Regression 
Scenario: Verify that the Admin can update FAQ support in the Resource section
Given the admin logs into the casedrive URL
When the Admin clicks the side menu
And the Admin clicks the Resource menu and selects FAQ Support
And the Admin clicks the edit button to change case details
And the Admin clicks the Upload button
Then the Admin sees the message Updated successfully under the section

@FAQDeleted @Regression
Scenario: Verify that the Admin can delete FAQ support in the Resource section
Given the admin logs into the casedrive URL
When the Admin clicks the side menu
And the Admin clicks the Resource menu and selects FAQ Support
And the Admin clicks the Delete button
Then the Admin sees the message Deleted successfully. under the section
    
    
    