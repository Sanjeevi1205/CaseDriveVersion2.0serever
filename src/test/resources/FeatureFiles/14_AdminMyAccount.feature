Feature: Admin My Account Functionalities
  #MyProfile
  @Profile @Regression
  Scenario: Verify that the admin can edit their profile
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Edit button
    And the user modifies their profile details
    And the user clicks the Save button
    Then the changes should be reflected on the account page

  @SecondaryEmail @Regression
  Scenario: Verify that the admin can add their secondary email
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Add Email button
    And the user enters their email details
    And the user clicks the Add button
    And the user enters the OTP
    And the user clicks the Verify button
    Then the user is notified with the popup message New email address has been added successfully.

  @EmptyMail @Regression
  Scenario: Verify that the admin cannot add empty secondary email
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Add Email button
    And the user clicks the Add button
    Then the user is notified with an error message Please Enter the Email Address

  @InvalidMail @Regression
  Scenario: Verify that the admin cannot add invalid secondary email
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Add Email button
    And the user enters their invalid email details
    And the user clicks the Add button
    Then the user is notified with an error message Please Enter the Correct Email

  @DeleteMail @Regression
  Scenario: Verify that the admin can delete secondary email
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the delete button and confirm the action
    Then the user is notified with the popup message Your email address has been deleted successfully.

  @PhoneNumber @Regression
  Scenario: Verify that the admin can add secondary phonenumber
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Add mobile number button
    And the user enter the phone number and clicks save button
    Then the user is notified with the popup message Mobile Number Added Successfully.

  @EmptyNumber @Regression
  Scenario: Verify that the admin cannot add empty secondary phonenumber
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Add mobile number button
    And the clicks save button
    Then the user is notified with an error message Enter the Phone Number

  @MoreNumbers @Regression
  Scenario: Verify that the admin cannot add morethan 10digit secondary phonenumber
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Add mobile number button
    And the user enter 10digit phone number and clicks save button
    Then the user is notified with an error message Phone number cannot be more than 10 digits.

  @DeleteNumber @Regression
  Scenario: Verify that the admin can able to delete the secondary phonenumber
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the delete button and confirm the action
    Then the user is notified with the popup message Your mobile number has been deleted successfully.

  @SetPrimary @Regression
  Scenario: Verify that the admin can set the primary email
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the set primary button and confirm the action
    Then the user logs out of the current system

  #Security
  @Password @Regression
  Scenario: Verify that the admin can set the password
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the security sidemenu
    And the user clicks the change password button
    And the user enter the current and new password
    And the user clicks the update button
    Then the user logs out of the current system

  @WrongPassword @Regression
  Scenario: Verify that the admin cannot set the wrong password
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the security sidemenu
    And the user clicks the change password button
    And the user enter the current and invalid new password
    And the user clicks the update button
    Then the user notified with the error message

  #Sessions
  @Sessions @Regression
  Scenario: Verify that the admin can terminate the previous session
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Sessions sidemenu
    And the user terminate the previous session and confirm the action
    Then the user notified with the popup message Active sessions has been terminated successfully

  @AllSessions @Regression
  Scenario: Verify that the admin can terminate all the session
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the Sessions sidemenu
    And the user terminate all button and confirm the action
    Then the user logs out of the current system

  @MFA @Regression
  Scenario: Verify that the client can enable the MFA
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the MFA sidemenu
    And the user clicks the Enable toggle button and confirm the action
    And the user clicks the Setup button and clicks the enable button
    Then the user notified with a popup message Updated successfully

  @MFA2 @Regression
  Scenario: Verify that the client can disable the Email
    Given the admin logs into the casedrive URL
    When the user enter the MFA OTP
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the MFA sidemenu
    And the user clicks the Disable button and confirm the action
    Then the user notified with a popup message Updated successfully

  @MFA3 @Regression
  Scenario: Verify that the client can disable the MFA
    Given the admin logs into the casedrive URL
    When the user clicks on the profile and selects the My Account option
    And the user navigates to a new tab
    And the user clicks the MFA sidemenu
    And the user clicks the Enable toggle button and clicks the disable button
    Then the user notified with a popup message Authentication disabled