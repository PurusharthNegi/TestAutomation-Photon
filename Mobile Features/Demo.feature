@Demo
Feature: Verify user can able to create Reading challenge

  @Demo
  Scenario Outline: Verify the user able to create a Reading challenge and invite participants
    Given Launch the  Application
    When Click location dropdown and enter the location and enter the school Name "<location>" and "<SchoolName>"
    And Enter the User Name and Password "<username>" and "<password>" click submit button
    And Verify and Handle Set Preference Page
    And Validate Create Challenge Icon
    And Validate Enter Challenge Name
    And Validate Enter Challenge Description
    And Validate Search and add invite Friend
    And Validate Set Reminder
    And Validate Set Read By Date
    And Validate Search and Add Title
    And Validate Save the Challenge with details
    And Validate Create Challenge
    
    Examples: 
      | location | SchoolName                    | username | password | 
      | New York | Photon_03_Site_A, McHenry, IL | ph5      | ph5      |