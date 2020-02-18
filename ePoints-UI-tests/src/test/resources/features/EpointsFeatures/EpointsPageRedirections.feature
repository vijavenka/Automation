Feature: Epoints redirections
  As a user
  I want to
  So that I

  @Regression @EpointsPageRedirection @United
  Scenario Outline: BUSINESS ACCOUNT UNITED's - accessing the United pages without permissions should open sign in page
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User opens subpage <subpage>
    Then Login page with all needed elements will be displayed
    And After submitting United login data the subpage <subpage> is opened

    Examples:
      | userMainPartner | loginState | subpage                     |
      | epoints         | not logged | /united                     |
#
      | epoints         | not logged | /united/rewards             |
#
      | epoints         | not logged | /united/points              |
#
      | epoints         | not logged | /united/rewards/$REWARD     |
#
      | epoints         | not logged | /united/my-account/profile  |
      | united          | identified | /united/my-account/profile  |
#
      | epoints         | not logged | /united/my-account/activity |
      | united          | identified | /united/my-account/activity |
#
      | epoints         | not logged | /united/my-account/rewards  |
      | united          | identified | /united/my-account/rewards  |

  @Regression @EpointsPageRedirection @United
  Scenario Outline: BUSINESS ACCOUNT UNITED's - accessing the United pages as epoints-only should redirect user to epoints home page
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User opens subpage <subpage>
    Then User is redirect to the subpage /

    Examples:
      | userMainPartner | loginState | subpage                     |
      | epoints         | logged     | /united                     |
#
      | epoints         | logged     | /united/rewards             |
#
      | epoints         | logged     | /united/points              |
#
      | epoints         | logged     | /united/rewards/$REWARD     |
#
      | epoints         | logged     | /united/my-account/profile  |
#
      | epoints         | logged     | /united/my-account/activity |
#
      | epoints         | logged     | /united/my-account/rewards  |

  @Regression @EpointsPageRedirection @United
  Scenario Outline: BUSINESS ACCOUNT UNITED's - accessing the United landing page should redirect United user to United home page
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User opens subpage /united/join
    Then User is redirect to the subpage /united

    Examples:
      | userMainPartner   | loginState |
      | united            | logged     |
      | united identified | identified |

  @Regression @EpointsPageRedirection @United
  Scenario Outline: BUSINESS ACCOUNT UNITED's - accessing the United landing page should be possible for not United user only
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User opens subpage /united/join
    Then User is redirect to the subpage /united/join

    Examples:
      | userMainPartner | loginState |
      | epoints         | not logged |
      | epoints         | logged     |

  @Regression @EpointsPageRedirection @United
  Scenario Outline: BUSINESS ACCOUNT UNITED's - accessing the United pages without permissions should open sign in page - after providing epoints account the epoints home page should be opened
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User opens subpage <subpage>
    Then Login page with all needed elements will be displayed
    And After submitting epoints login data the subpage / is opened

    Examples:
      | userMainPartner | loginState        | subpage                     |
      | epoints         | not logged        | /united                     |
#
      | epoints         | not logged        | /united/rewards             |
#
      | epoints         | not logged        | /united/points              |
#
      | epoints         | not logged        | /united/rewards/$REWARD     |
#
      | epoints         | not logged        | /united/my-account/profile  |
      | united          | united identified | /united/my-account/profile  |
#
      | epoints         | not logged        | /united/my-account/activity |
      | identified      | united identified | /united/my-account/activity |
#
      | epoints         | not logged        | /united/my-account/rewards  |
      | identified      | united identified | /united/my-account/rewards  |


  @Regression @EpointsPageRedirection @United
  Scenario Outline: BUSINESS ACCOUNT UNITED's - accessing the United pages as United signed-in / recognized user
    Given Epoints home page is opened by <userMainPartner> <loginState> user with cookie panel not visible
    When User opens subpage <subpage>
    Then User is redirect to the subpage <subpage>

    Examples:
      | userMainPartner | loginState | subpage                     |
      | united          | logged     | /united                     |
      | united          | logged     | /united/rewards             |
      | united          | logged     | /united/points              |
      | united          | logged     | /united/rewards/$REWARD     |
      | united          | logged     | /united/my-account/profile  |
      | united          | logged     | /united/my-account/activity |
      | united          | logged     | /united/my-account/rewards  |
#
      | united          | identified | /united                     |
      | united          | identified | /united/rewards             |
      | united          | identified | /united/points              |
      | united          | identified | /united/rewards/$REWARD     |