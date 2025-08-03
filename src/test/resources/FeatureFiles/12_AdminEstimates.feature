Feature: Admin Case Estimate Functionality

  @CaseWiseAdminEstimationSend @Regression @AdminEstimate
  Scenario Outline: Verify that the admin can send an estimate for a <Client Type> client
    Given the admin logs into the casedrive URL
    When the admin clicks on the side menu and clicks on the estimate menu
    And the admin selects a created estimate of "<Client ID>" and "<Client Type>" in "Estimation in Process"
    And the admin clicks on the edit estimate button
    And the admin enter change the hrs in the first service
    And the admin clicks on the update estimate button
    And the admin clicks on verify and send button
    And the admin should see the success message that the case has been "estimation sent"
    Then the admin verifies whether the estimation status is changed to "Awaiting Approval"
    Examples:
      | Client ID    | Client Type |
      | LDTM001-C241 | Case wise   |

  @CaseWiseAdminEstimationDecline @AdminEstimate
  Scenario Outline: Verify that the admin can decline an estimate for a <Client Type> client
    Given the admin logs into the casedrive URL
    When the admin clicks on the side menu and clicks on the estimate menu
    And the admin selects a created estimate of "<Client ID>" and "<Client Type>" in "Awaiting Approval"
    And the admin clicks on decline estimate button
    And the admin should see the success message that the case has been "estimation declined"
    Then the admin verifies whether the estimation status is changed to "Estimate Declined"
    Examples:
      | Client ID    | Client Type |
      | LDTM001-C241 | Case wise   |

  @CaseWiseAdminEstimationEdit @AdminEstimate
  Scenario Outline: Verify that the admin can update a declined estimate for a <Client Type> client
    Given the admin logs into the casedrive URL
    When the admin clicks on the side menu and clicks on the estimate menu
    And the admin selects a created estimate of "<Client ID>" and "<Client Type>" in "Estimate Declined"
    And the admin clicks on the edit estimate button
    And the admin enter change the hrs in the first service
    And the admin clicks on the update estimate button
    And the admin should see the success message that the case has been "estimation updated"
    Then the admin verifies whether the estimation status is changed to "Estimation in Process"
    Examples:
      | Client ID    | Client Type |
      | LDTM001-C241 | Case wise   |

  @VolumeWiseAdminEstimationSend @Regression @AdminEstimate
  Scenario Outline: Verify that the admin can send an estimate for a <Client Type> client
    Given the admin logs into the casedrive URL
    When the admin clicks on the side menu and clicks on the estimate menu
    And the admin selects a created estimate of "<Client ID>" and "<Client Type>" in "Estimation in Process"
    And the admin clicks on the edit estimate button
    And the admin enter change the pages in the first service
    And the admin clicks on the update estimate button
    And the admin clicks on verify and send button
    And the admin should see the success message that the case has been "estimation sent"
    Then the admin verifies whether the estimation status is changed to "Awaiting Approval"
    Examples:
      | Client ID    | Client Type |
      | LDTM190-C190 | Volume wise |


