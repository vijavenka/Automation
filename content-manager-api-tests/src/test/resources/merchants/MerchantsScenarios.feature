Feature: Merchants - getting merchants via content-manager
  As an external epoints partner
  I want to be able to call API interface
  So that I can pull latest merchants from the system

  @test
  Scenario: Merchants - get merchants list
    Given Conent manager is responding to healthchecks
    When Call for merchants list is made
    Then List of active merchants is returned
