Feature: Dummy feature no 2

  Scenario Outline: Open facebook and wait <waitTime> seconds
    Given I access "https://www.facebook.com/"
    And I wait <waitTime> seconds
    
  Examples: 
      | waitTime | 
      | 5        | 
      | 10       |
      | 8        |
      | 7        |
      
  Scenario: Open facebook and wait 8 seconds
    Given I access "https://www.facebook.com/"
    And I wait 8 seconds
    
  Scenario: Open google and wait 5 seconds
    Given I access "https://www.google.com/"
    And I wait 5 seconds  