Feature: FAQ page view and connected modules

    As a user
    I require a page I can go to that will supply me with information about frequently asked questions and answers as well, I can also
    use different forms placed in FAQ module like, retrieving password, signing in.

    #1
    Scenario: Checking if hyperlink to Terms & Conditions page is available
        Given Not registered user open ePoints.com
        When Check content of Header for FAQ link
        Then FAQ hyperlink should be available in header
        And Should link to FAQ page

    #2
    Scenario: Checking if FAQ page content is correct
        Given Not registered user open ePoints.com
        And FAQ page is opened
        When Check content of page FAQ
        Then Frequently asked categories and questions should be displayed

    #3
    Scenario: Checking if FAQ chosen question is properly linked
        Given Not registered user open ePoints.com
        And FAQ page is opened
        Then Click on chosen question name
        Then Proper answer for the question should be visible

    #4
    Scenario: Checking if FAQ chosen category is properly linked and it is possible to navigate to add new article page
        Given Not registered user open ePoints.com
        And FAQ page is opened
        Then Open Chosen category page
        And Check content of chosen category page
        Then Go to adding new article page
        And Check content of add article page

    #5
    Scenario Outline: Checking if searcher works properly
        Given Not registered user open ePoints.com
        And FAQ page is opened
        Then Searcher can be used with '<Phrase>'
        And Searcher button is pushable
        Then Results should contain question with '<Phrase>'

    Examples:
        |Phrase|
        |epoints|

    #6
    Scenario: Checking if submit a request forms is available and has proper content
        Given Not registered user open ePoints.com
        And FAQ page is opened
        Then Submit a request form should be opened
        And Submit a request page should has proper content

    #7
    #Scenario Outline: Checking if submit a request forms is usable
        #Given Not registered user open ePoints.com
        #And FAQ page is opened
        #Then Submit a request form should be opened
        #Then Alerts should be displayed after submitting request without filling any data
        #Then All text fields should be editable using '<Email>' '<Subject>' '<Description>'
        #And After submitting request success alert should appear

    #Examples:
        #|Email|Subject|Description|
        #|emailwybitnietestowy@gmail.com|PGS Software S.A. QA test|Please do not replay on this email - this request is done by automated environment|

    #8
    #Scenario: Checking if retrieving password module has proper content and is usable
        #Given Not registered user open ePoints.com
        #And FAQ page is opened
        #Then Open Chosen category page
        #Then Go to adding new article page
        #Then Forgot my password module should be opened
        #Then Alert should be displayed after submitting request without filling any data
        #Then After filling email and submitting success alert should be visible

    #9
    Scenario: Checking if Sign-up to epoints support form has proper content
        Given Not registered user open ePoints.com
        And FAQ page is opened
        Then Open Chosen category page
        Then Go to adding new article page
        Then Sign-up Form should be opened
        And Sing-up to epoints support page should has proper content

    #10
    Scenario Outline: Checking if Sign-up to epoints support form works properly
        Given Not registered user open ePoints.com
        And FAQ page is opened
        Then Open Chosen category page
        Then Go to adding new article page
        Then Sign-up Form should be opened
        Then Needed data should be filled using '<Name>' '<Email>' '<Capcha>'
        #This feature disappear
        #And After singing in verification alert should appear because of wrong capcha code

        Examples:
            |Name|Email|Capcha|
            |Krzysztof|emailwybitnietestowy@gmail.com|I can not write proper code|
