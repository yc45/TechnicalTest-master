Feature: Find restaurants using JUST EAT
  As a hungry customer I want to be able to find restaurants in my area and order food

  Scenario: Search for restaurants in an area
    Given I want food in "AR51 1AA"
    When I search for restaurants ""
    Then I should see some restaurants in "AR51 1AA"

  #Scenario: User successfully creates a new account
   # Given I am at the create account page
    #When I enter the required information
    #Then A new JUST EAT account is created

  #Scenario: User adds some food to checkout
    #Given I want food in "AR51 1AA"
    #When I search for restaurants "howe"
    #Then I should be able to choose "Alison Howe - Sales Demo"
    #And I should be able to add "Special Garlic Chicken" to checkout