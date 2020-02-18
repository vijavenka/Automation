Feature: eachperson, employee selector

# PD-720 eachperson, employee selector, /leaderboard API has to include nominationName

Scenario: PD-720 eachperson, employee selector, leaderboard/nominationReceivedPeer API has to include nominationName
Given In eachperson, logged in
And employee selector, leaderboard/nominationReceivedPeer API is called
Then It has to include nominationName in all the records

Scenario: PD-720 eachperson, employee selector, leaderboard/nominationReceivedManager API has to include nominationName
Given In eachperson, logged in
And employee selector, leaderboard/nominationReceivedManager API is called
Then It has to include nominationName in all the records

Scenario: PD-720 eachperson, employee selector, leaderboard/nominationExternal API has to include nominationName
Given In eachperson, logged in
And employee selector, leaderboard/nominationExternal API is called
Then It has to include nominationName in all the records

Scenario: PD-720 eachperson, employee selector, leaderboard/nominationsReceivedTotal API has to include nominationName
Given In eachperson, logged in
And employee selector, leaderboard/nominationsReceivedTotal API is called
Then It has to include nominationName in all the records
