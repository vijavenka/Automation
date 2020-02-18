Feature: Add points to users via webservice

As an ePoints client
I want to be able to add ePoints to my users
So that they benefit from using my site
And are subsequently more engaged

Scenario: Adding confirmed points to ePoints user
	Given That user has an ePoints account
	When The user performs some earning action that awards confirmed ePoints
	Then His balance of confirmed ePoints will increase

Scenario: Adding CONFIRMED points to ePoints user with not exceeded cap

Scenario: Adding CONFIRMED points to ePoints user with exceeded cap

Scenario: Adding CONFIRMED points to ePoints user when client didn't hit quota

Scenario: Adding CONFIRMED points to ePoints user when client hit quota

Scenario: Adding CONFIRMED points to ePoints user when cap and quota are not exceeded

Scenario: Adding CONFIRMED points to ePoints user when cap is exceeded, but not quota

Scenario: Adding CONFIRMED points to ePoints user when cap is not exceeded, but quota is

Scenario: Adding CONFIRMED points to ePoints user when cap and quota are exceeded

Scenario: Adding any kind of points to ePoints user

Scenario: Adding PENDING points to ePoints user

Scenario: Adding DECLINED points to ePoints user

Scenario: Adding REDEEMED points to ePoints user

Scenario: Adding SPENT points to ePoints user

Scenario: Adding points to non ePoints user

Scenario: Adding points to deleted user

Scenario: Adding points where tag is missing