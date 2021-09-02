Feature: Validating Login Function

  @Login
  Scenario Outline: Verifying Location and School Name
    Given Launch the  Application
    When Click location dropdown and enter the location and enter the school Name "<location>" and "<SchoolName>"
    And Enter the User Name and Password "<username>" and "<password>" click submit button
      Examples: 
      | location | SchoolName                    | username | password |
      | New York |Dlx BnT Digital Site, McHenry, IL | ph5    | ph5   |

