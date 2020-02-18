Feature: Epoints API: Health check
  As an system administrator
  I want to have endpoint on epoints api
  So that I could always check condition of my application

  @Regression @PositiveCase @HealthCheck @qa @stag @prod
  Scenario: Healthcheck controller - application response
    Given Epoints API is up and running
    When User makes healthcheck call
    Then He should receive 200 OK response