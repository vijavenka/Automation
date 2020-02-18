Feature: Administration

    Scenario Outline: Delete users from database which will be use in registration tests
        Given Administration action - delete user from db '<email>'

           Examples:
             |email|
             |emailwybitnietestowy3@gmail.com|
             |emailwybitnietestowy12@gmail.com|
             |newemail1989@wp.pl|

    Scenario Outline: Set number of points in points manager for basic user
        Given Administration action - set number of points in points manager for '<email>'

            Examples:
              |email|
              |emailwybitnietestowy@gmail.com|

    Scenario Outline: Set user personal data in user account, for main user
        Given Administration action - set personal data for '<email>'

            Examples:
              |email|
              |emailwybitnietestowy@gmail.com|

    Scenario Outline: Clear iat_epoints_test@gmail_com account facebook connection flag
        Given Administration action - delete facebook flag from db '<email>'

           Examples:
              |email|
              |iat.epoints.test@gmail.com|



