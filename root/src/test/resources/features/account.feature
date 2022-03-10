Feature: Create an account
  As a web user, I want to be able to register on the platform

  Scenario: Create an account
    Given A web user with email "julio.ripoll@osoco.es", name "Julio", surname "Ripoll" and birthday "1982-11-27T00:00:00Z"
    When send create account request
    Then the user is successfully registered

  Scenario: Create an account without email
    Given A web user with email "", name "Julio", surname "Ripoll" and birthday "1982-11-27T00:00:00Z"
    When send create account request
    Then user cannot register