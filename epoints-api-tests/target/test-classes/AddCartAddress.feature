Feature: epoints, while adding/editing/deleting a address in add cart.

Scenario: PD-326 Adding an address in add cart
Given In epoints
When User is logged in
And Add address API is called
Then Address will be added to the user

Scenario: PD-326 Editing an address in add cart
Given In epoints
When User is logged in
And Edit address API is called
Then Address will be edited to the user

Scenario: PD-326 Fetching an address in add cart
Given In epoints
When User is logged in
And Get address API is called
Then All address will be fetched to the user

Scenario: PD-326 Deleting an address in add cart
Given In epoints
When User is logged in
And Delete address API is called
Then Address will be deleted to the user
