Feature: User Management Functionality

  @EmployeeCreationNegativeScenario @Regression @UserManagement @AdminSettings
  Scenario: Verify that the admin cannot add an employee  without adding any basic details
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon
    And the admin was navigated to the settings page
    And the admin clicks on the side menu of settings page
    And the admin clicks on the user management menu
    And the admin clicks on the employees menu
    And the admin clicks on the add employee button
    And the admin clicks on the save button without entering the basic information about the employee
    Then the admin should see error message appearing from some mandatory fields

  @standardUser @Regression @UserManagement @AdminSettings
  Scenario: Verify that the admin able to add an standard user
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon and clicks on user management
    And the admin clicks on the employees menu
    And the admin clicks on the add employee button
    And the admin enters the details in all mandatory fields
    And the admin select the "Kulasekaram" as an work location
    And the admin adds an employee to the "Standard User" Profile
    And the admin set the role as "Project Staff"
    And the admin select the "Team Lead" as reporting to
    And the admin select the "Manager Test" as approved by
    And the admin clicks on the save button
    Then the admin should see a success message for employee creation

  @EmployeeInvitation @Regression @UserManagement @MailCredentials @AdminSettings
  Scenario: Verify that the admin able to send Invite to employees
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon and clicks on user management
    And the admin clicks on the employees menu
    And the admin clicks on the three dot action of any employee
    And the admin clicks on the send invitation button and confirms
    And the admin should see a success message for invite mail
    And the employee logs in using the credentials from email

  @ProjectAdministrator @Regression @UserManagement @AdminSettings
  Scenario: Verify that the admin able to add an Project Manager
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon and clicks on user management
    And the admin clicks on the employees menu
    And the admin clicks on the add employee button
    And the admin enters the details in all mandatory fields
    And the admin select the "Kulasekaram" as an work location
    And the admin adds an employee to the "Project Administrator" Profile
    And the admin set the role as "Project Manager"
    And the admin select the "admin admin" as reporting to
    And the admin select the "admin admin" as approved by
    And the admin clicks on the save button
    Then the admin should see a success message for employee creation

  @UserTeams @Regression @UserManagement @TeamLead @AdminSettings
  Scenario: Verify that the admin able to add an Team Lead
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon and clicks on user management
    And the admin clicks on the employees menu
    And the admin clicks on the add employee button
    And the admin enters the details in all mandatory fields
    And the admin select the "Kulasekaram" as an work location
    And the admin adds an employee to the "Task Manager" Profile
    And the admin set the role as "Team Lead"
    And the admin select the "Manager Test" as reporting to
    And the admin select the "admin admin" as approved by
    And the admin clicks on the save button
    Then the admin should see a success message for employee creation

  @UserTeams @Regression @UserManagement @TeamLead @AdminSettings
  Scenario: Verify that the admin able to create a new team
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon and clicks on user management
    And the admin clicks on the teams menu
    And the admin clicks on the create team button
    And the admin creates a team by providing required details and clicks on the save button
    Then the admin should see a success message for team creation

  @StandardUserToTeamLead @Regression @UserManagement @EditEmployee @AdminSettings
  Scenario: Verify that the admin able to edit and change an employee's profile and role
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon and clicks on user management
    And the admin clicks on the employees menu
    And the admin clicks an employee with "Standard User" role and "Project Staff" profile
    And the admin clicks on the view button in the employee row
    And the admin click on the edit button on employee details page
    And the admin adds an employee to the "Task Manager" Profile
    And the admin set the role as "Team Lead"
    And the admin select the "Manager Test" as reporting to
    And the admin select the "admin admin" as approved by
    And the admin clicks on the update button
    Then the admin should see a success message for details update

  @AddCompanyNegative @Regression @UserManagement @AdminSettings
  Scenario: Verify that the admin cannot be able to create organisation company
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon
    And the admin was navigated to the settings page
    And the admin clicks on the side menu of settings page
    And the admin clicks on the general setting menu
    And the admin clicks on the Companies menu
    And the admin clicks on the add companies button
    And the admin clicks on the save button
    Then the admin should see the mandatory fields error messages in company creation page

  @AddCompany @Regression @UserManagement @@AdminSettings
  Scenario: Verify that the admin be able to create organisation company
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon
    And the admin was navigated to the settings page
    And the admin clicks on the side menu of settings page
    And the admin clicks on the general setting menu
    And the admin clicks on the Companies menu
    And the admin clicks on the add companies button
    And the admin enter the mandatory details to create company
    And the admin clicks on the save button
    Then the admin should see a success message for company creation

  @DeleteEmployee @Regression @UserManagement @AdminSettings
  Scenario: Verify that the admin able to delete an employees
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon and clicks on user management
    And the admin clicks on the employees menu
    And the admin clicks on the three dot action of any employee
    And the admin clicks on the delete button
    And the admin clicks on the confirm button
    Then the admin should see a success message for employee deletion

  @TaskTemplateCreation @Regression @AdminSettings
  Scenario: Verify that the admin able to create a task Template
    Given the admin logs into the casedrive URL
    When the admin clicks on the settings icon
    And the admin was navigated to the settings page
    And the admin clicks on the side menu of settings page
    And the admin clicks on task menu and clicks on the task template button
    And the admin clicks on the new template button
    And the admin creates a task template
    Then the admin should see a success message for task template creation







