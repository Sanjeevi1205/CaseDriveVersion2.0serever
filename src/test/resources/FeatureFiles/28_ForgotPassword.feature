Feature: Forgot password functionality for valid email

  @PositiveScenario @Regression
  Scenario: Verify that the client can change the password with a valid email credential.
    Given the client navigates to the Forgot Password page
    When the client click the forgot password link
    And the client enter the valid email credential
    And the client enter the OTP get from the email
    And the client enter the New and Confirm Password to continue
    Then a popup with the message Your password has been changed successfully is displayed.

  @NegativeScenario @Regression
  Scenario: Verify that the client cannot change the password with an invalid email credential.
    Given the client navigates to the Forgot Password page
    When the client click the forgot password link
    And enter the Invalid email credential
    Then an error popup emailexist the client of an invalid email.

  @Alreadyexists @Regression
  Scenario: Verify that a non-existing user cannot change the password.
    Given the client navigates to the Forgot Password page
    When the client click the forgot password link
    And enter the non-existing email credential
    Then an error popup notifies Your email address already exists is displayed.

  @Nonconvertedclient @Regression
  Scenario: Verify that a non-converted client cannot change the password.
    Given the client navigates to the Forgot Password page
    When the client click the forgot password link
    And enters a non-converted email credential
    When the client enter the OTP get from the email
    And the client enter the New and Confirm Password to continue
    When the client attempts to log in with the non-converted email credential
    Then a popup with the message Your email address  exists is displayed.

  @Inactiveclient @Regression
  Scenario: Verify that an inactive client cannot change the password.
    Given the client navigates to the Forgot Password page
    When the client click the forgot password link
    And enters a invalid internal user email credential
    When the client enter the OTP get from the email
    And the client enter the New and Confirm Password to continue
    When the client attempts to log in with the invalid InternalUser email credential
    Then an error popup notifies Your account is currently inactive is displayed.

  @ResendOTP @Regression
  Scenario: Verify that the client can change the password with a valid email credential with Resend OTP.
    Given the client navigates to the Forgot Password page
    When the client click the forgot password link
    And the client enter the valid email credential
    And the client click the Resend OTP button
    And the client enter the Resend OTP and click next button
    And the client enter the New and Confirm Password to continue
    Then a popup with the message Your password has been changed successfully is displayed.