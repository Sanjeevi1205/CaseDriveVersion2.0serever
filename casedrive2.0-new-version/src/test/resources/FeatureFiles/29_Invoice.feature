Feature: Generate Invoice Functionality

  @CaseWise @Regression
  Scenario: Verify that the admin can generate an invoice for a casewise client
    Given the admin logs into the casedrive URL
    When the admin clicks the Billing side menu and selects Generate Invoice
    And selects an invoice with the status "Pending Verification"
    And clicks the Edit Invoice button
    And rearranges the order of services and enters the discount amount
    And clicks the Update button
    And adds internal notes and clicks the Generate Invoice button
    Then the invoice status should change to "Invoice Generated"

  @MonthWise @Regression
  Scenario: Verify that the admin can generate an invoice for a month-wise client
    Given the admin logs into the casedrive URL
    When the admin clicks the Billing side menu and selects Generate Invoice
    And clicks the Monthly Invoice tab and selects the client company
    And selects an invoice with the status "Pending Verification"
    And clicks the Edit Invoice button
    And rearranges the order of services and enters the discount amount
    And clicks the Update button
    And adds internal notes and clicks the Verify Invoice button
    And then returns to the company, clicks the three-dot button, and selects the Generate Invoice option
    Then the invoice status should change to "Invoice Generated"

  @CaseWiseInvoice @Regression
  Scenario: Verify that the admin can send an invoice for a casewise client
    Given the admin logs into the casedrive URL
    When the admin clicks the Billing side menu and selects Invoice
    And selects an invoice with the status of "Draft"
    And clicks the Edit Invoice button
    And rearranges the order of services and enters the discount amount
    And clicks the Save button
    And generate the excel file to attach
    And adds internal notes and clicks the Send Invoice button
    Then the popup indicates the admin with success message

  @MonthWiseInvoice @Regression
  Scenario: Verify that the admin can send an invoice for a monthwise client
    Given the admin logs into the casedrive URL
    When the admin clicks the Billing side menu and selects Invoice
    And clicks the Monthly Invoice tab
    And selects an invoice with the status of "Draft"
    And clicks the Edit Invoice button
    And rearranges the order of services and enters the discount amount
    And clicks the Save button
    And generate the excel file to attach
    And adds internal notes and clicks the Send Invoice button
    Then the popup indicates the admin with success message