Feature: Verify the functionalities in the Lead Menu

  @Regression @Leads @crm
  Scenario: Admin views lead details
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin selects a lead with Verification Pending or Onboard Pending and clicks on the three-dot menu
    And the admin clicks on the view button in the three dot action
    Then the admin should be navigated to a view page that display the leads personal details

  @Regression @Leads @crm
  Scenario: Admin deletes lead details
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin selects a lead with Verification Pending or Onboard Pending and clicks on the three-dot menu
    And the admin clicks on delete button in the three dot action
    Then a confirmation popup for lead delete is displayed and confirmed

  @Regression @Leads @crm
  Scenario: Admin cancels the delete action for lead details
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin selects a lead with Verification Pending or Onboard Pending and clicks on the three-dot menu
    And the admin clicks on delete button in the three dot action
    And the admin clicks the Cancel button to prevent the lead from being deleted
    Then the admin should remain on the same page

  @Regression @Leads @crm
  Scenario: Verify the invitation link notification to the user for signup
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin selects a lead with Verification Pending or Onboard Pending and clicks on the three-dot menu
    And clicks on the send invitation action and click on the invite button
    Then a confirmation popup for Invite Mail Send successfully is displayed and confirmed

  @Regression @crm
  Scenario: Verify whether the cancel functionality in the confirmation popup is working
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin selects a lead with Verification Pending or Onboard Pending and clicks on the three-dot menu
    And clicks on the send invitation action and click on the Cancel button
    Then the admin should remain on the same page

  @Regression @crm @createLeads
  Scenario: Verify whether the admin able to create a lead
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Leads
    And the admin clicks on the create button
    And the admin enter some basic information in the mandatory fields
    And the admin clicks on the create button
    Then the admin should see a success message for lead creation

  @CreateCompany @crm @Regression
  Scenario: Verify whether the admin able to create a company
    Given the admin logs into the casedrive URL
    When the admin clicks the CRM menu and click on the Companies
    And the admin clicks on the create button
    And the admin enter some company information in the mandatory fields
    And the admin clicks on the create company button
    Then the admin should see a success message for clients company creation