
Feature: Partners page

    As a user
    I require a page with proper form
    So I can easily contact with ePoints owners to create partnership

        Scenario: LEAD GEN SCREEN - add new submission form UI to epoints (RD-1645) - page content
            Given Not registered user open ePoints.com
            When User use partner button
            Then Proper form with needed fields will be visible

        Scenario: LEAD GEN SCREEN - add new submission form UI to epoints (RD-1645) - alert behaviour
            Given Not registered user open ePoints.com
            When User use partner button
            And Submit button will be pressed without filling any data
            Then Proper alerts will be shown