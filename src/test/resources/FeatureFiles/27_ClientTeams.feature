Feature: Client Teams Functionality

  @InviteMemberAndSignUp @Regression @Teams
  Scenario: Verify the client can add a team member
    Given the client logs in using valid credentials
    When the client clicks the Teams menu
    And the client clicks the Add New Member button
    And the client enters the email and selects the role as Administrator
    And the client enables the allow notifcation checkbox
    And the client clicks the invite button
    And the client sees an success message to notify that the member is invited
    And the client verifies the added member is displayed on the team list page
    Then the member signs up using the link from the email

  @MemberEdit @Regression @Teams
  Scenario: Verify the client can edit the added team member
    Given the client logs in using valid credentials
    When the client clicks the Teams menu
    And the client clicks the three-dot button and selects the view option
    And the client clicks the edit button
    And the client enables the share email toggle
    And the client saves the changes
    Then the client sees an success message to notify that the member details has been saved

  @PendingMemberRevoke @Regression @Teams
  Scenario: Verify the client can revoke the team member in pending status
    Given the client logs in using valid credentials
    When the client clicks the Teams menu
    And the client clicks the three-dot button of a member with "Pending" status and selects the three dot action
    And the client clicks the Revoke button
    And the client clicks on confirm button in the confirmation popup
    Then the client sees an success message to notify that the member is deleted

  @CompletedMemberRevoke @Regression @Teams
  Scenario: Verify the client cannot revoke the team member in Onboarded status
    Given the client logs in using valid credentials
    When the client clicks the Teams menu
    And the client clicks the three-dot button of a member with "Completed" status and selects the three dot action
    And the client clicks the Revoke button
    Then the client sees an alert message notifying that cannot revoke

  @TeamsNegative1 @Regression @Teams
  Scenario: Verify the client able to invite an Invalid Email
    Given the client logs in using valid credentials
    When the client clicks the Teams menu
    And the client clicks the Add New Member button
    And the client enters an invalid email and selects the role as Administrator
    And the client enables the allow notifcation checkbox
    And the client clicks the invite button
    Then the client should see an error message indicating that the email is Invalid

  @TeamsNegative2 @Regression @Teams
  Scenario: Verify the client able to invite an Email Already Exists in the Database
    Given the client logs in using valid credentials
    When the client clicks the Teams menu
    And the client clicks the Add New Member button
    And the client enters an emailId that already exists and selects the role as Administrator
    And the client enables the allow notifcation checkbox
    And the client clicks the invite button
    Then the client should see an error message indicating that the email is already associated with a team member
