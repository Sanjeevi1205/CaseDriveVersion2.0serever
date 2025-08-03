@Positive
Feature: Signup functionality

  @PositiveScenario @Regression
  Scenario: Verify that the lead can sign up with a valid email credential.
    Given the lead navigate to the casedrive URL
    When the lead click the create button to signup
    And the lead enter the email address and click next button
    And the lead enter the OTP and click next button
    And the lead enter the password and click next button
    And the lead enter the contact information about him and click next button
    And the lead enter the company information and click next button
    And the lead select the services and click next button
    And the lead select the template for the services and click next button
    Then the lead navigate to the Thankyou page for the successfull signup message

  @OnboardingContinue @Regression
  Scenario: Verify that the lead can upload a template manually from the file explorer.
    Given the lead navigate to the casedrive URL
    When the lead click the create button to signup
    And the lead enter the onboarding email address and click next button
    And the lead enter the OTP and click next button
    And the lead enter the contact information about him and click next button
    And the lead enter the company information and click next button
    And the lead select the services and click next button
    And the lead select the template from the file explorer and click next button
    Then the lead navigate to the Thankyou page for the successfull signup message

  @InvalidEmail @Regression
  Scenario: Verify that the lead cannot sign up with an invalid email credential.
    Given the lead navigate to the casedrive URL
    When the lead click the create button to signup
    And the lead enter the invalid email address and click next button
    Then the lead see the error Please enter a valid email address

  @ExistingUser @Regression
  Scenario: Verify that the lead cannot sign up with an existing user/non-converted account.
    Given the lead navigate to the casedrive URL
    When the lead click the create button to signup
    And the lead enter the non-converted email address and click next button
    Then a popup with the message Email Address Already Exist is displayed.

  @InactiveEmployee @Regression
  Scenario: Verify that an inactive employee account cannot sign up.
    Given the user navigate to the casedrive URL
    When the user click the create button to signup
    And the user enter the inactive employee email address and click next button
    Then a popup with the message Email Address Already Exist is displayed.

  @InactiveClient @Regression
  Scenario: Verify that an inactive client account cannot sign up.
    Given the inactive client navigate to the casedrive URL
    When the client click the create button to signup
    And the client enter the inactive client email address and click next button
    Then a popup with the message Email Address Already Exist is displayed.

  @ResendOTP @Regression
  Scenario: Verify that the lead can sign up with a Resend OTP.
    Given the lead navigate to the casedrive URL
    When the lead click the create button to signup
    And the lead enter the email address and click next button
    And the lead click the Resend OTP button
    And the lead enter the Resend OTP and click next button
    And the lead enter the password and click next button
    And the lead enter the contact information about him and click next button
    And the lead enter the company information and click next button
    And the lead select the services and click next button
    And the lead select the template for the services and click next button
    Then the lead navigate to the Thankyou page for the successfull signup message