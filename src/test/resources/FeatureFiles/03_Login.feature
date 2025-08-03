Feature: Login functionality with valid credentials

  @Login @Regression
  Scenario: Verify that the client can log in with valid credentials.
    Given the client navigate to the casedrive URL
    When the client enters the valid credentials
    Then the client should navigate to the homepage

  @InvalidEmailLogin @Regression
  Scenario: Verify that the client cannot log in with an invalid email credential.
    Given the client navigate to the casedrive URL
    When the client enters the invalid email credential
    Then an error popup notifies the client of an invalid email.

  @ValidEmailInvalidPassword @Regression
  Scenario: Verify that the client cannot log in with a valid email and an invalid password.
    Given the client navigate to the casedrive URL
    When the client enters the valid email and an invalid password.
    Then an error popup notifies the client of an invalid password.

  @NotConvertedClient @Regression
  Scenario: Verify that the client cannot log in before being converted to a client.
    Given the client navigate to the casedrive URL
    When the client enters the non-converted valid credentials
    Then an error popup notifies the client of an pending account verification.

  @Non-ExistingUser @Regression
  Scenario: Verify that a non-existing user cannot log in.
    Given the client navigate to the casedrive URL
    When the client enters the undefined user credential
    Then an error popup notifies the client of an invalid email.

  @InactiveAccount @Regression
  Scenario: Verify that the client cannot log in when the account is inactive.
    Given the client navigate to the casedrive URL
    When the client enters the Inactive email credential
    Then an error popup notifies the client of account inactive or removed.

  @InactiveUserAccount @Regression
  Scenario: Verify that the internal user cannot log in when the account is inactive.
    Given the internal user navigate to the casedrive URL
    When the internal user enters the Inactive email credential
    Then an error popup notifies the internal user of account inactive or removed.