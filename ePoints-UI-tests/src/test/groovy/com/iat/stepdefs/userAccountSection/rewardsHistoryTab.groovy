package com.iat.stepdefs.userAccountSection

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.ContactUsPage
import com.iat.pages.userAccount.rewardsHistory.RewardHistoryModule
import com.iat.pages.userAccount.rewardsHistory.RewardsHistoryPage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import com.iat.stepdefs.utils.JdbcDatabaseConnector

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

EpointsHomePage epointsHomePage = new EpointsHomePage()
RewardsHistoryPage rewardsHistoryPage = new RewardsHistoryPage()

def func = new Functions()
def envVar = new envVariables()
def random
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)

// 1.1 //  ----- ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082)
// ------------------------------------------------------------------------------------------ page and table content
Given(~/^Rewards history page is opened by logged user in '(.+?)' context$/) { String partner ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.unitedUser, Config.unitedUserPassword)
    sleep(1000)
    if (partner.toLowerCase() == "united") {
        epointsHomePage.accountSwitcher.unitedSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "United" }
    } else if (partner.toLowerCase() == "epoints") {
        epointsHomePage.accountSwitcher.epointsSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "epoints" }
    }
    epointsHomePage.goToUserRewardsHistoryPage()
    at RewardsHistoryPage
    rewardsHistoryPage = page
}
Then(~/^He can see that page header has proper name$/) { ->
    assertThat("Wrong rewards page header", rewardsHistoryPage.rewardsHistoryHeader.text(), is(envVar.rewardsHistoryPageHeader));
}
Then(~/^Reward history table has proper columns$/) { ->
    waitFor { rewardsHistoryPage.rewardsTableHeaderColumnNameDate.text() == envVar.rewardsHistoryPageColumnLabelDate }
    assertThat("Wrong date column label", rewardsHistoryPage.rewardsTableHeaderColumnNameDate.text(), is(envVar.rewardsHistoryPageColumnLabelDate))
    assertThat("Wrong reward column label", rewardsHistoryPage.rewardsTableHeaderColumnNameReward.text(), is(envVar.rewardsHistoryPageColumnLabelReward))
    assertThat("Wrong delivery details column label", rewardsHistoryPage.rewardsTableHeaderColumnNameDeliveryDetails.text(), is(envVar.rewardsHistoryPageColumnLabelDD))
}
Then(~/^Reward history page has proper content according to DB for '(.+?)' context$/) { String partner ->
    HashMap<Integer, List> rewardsInPoints
    if (partner == "epoints")
        rewardsInPoints = mySQLConnector.getResult("SELECT points.createdAt, activityInfo, userId, points.productId FROM points_manager.Points points JOIN points_manager.Product product ON points.productId = product.id WHERE points.status='REDEEMED' and points.userId='" + mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey='" + new UserRepositoryImpl().findByEmail(Config.unitedUser).getUuid() + "'") + "' AND product.productScope is null ORDER BY points.id DESC", Arrays.asList("createdAt", "activityInfo", "userId", "productId"))
    else
        rewardsInPoints = mySQLConnector.getResult("SELECT points.createdAt, activityInfo, userId, points.productId FROM points_manager.Points points JOIN points_manager.Product product ON points.productId = product.id WHERE points.status='REDEEMED' and points.userId='" + mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey='" + new UserRepositoryImpl().findByEmail(Config.unitedUser).getUuid() + "'") + "' AND product.productScope = 'united' ORDER BY points.id DESC", Arrays.asList("createdAt", "activityInfo", "userId", "productId"))

    def iterator = 0
    for (RewardHistoryModule reward : rewardsHistoryPage.rewards) {
        String rewardName = reward.name
        //TODO problem with parsing date in format '03 Oct 2017'
//        assertThat("Wrong reward date for product: $rewardName", rewardsInPoints.get(iterator).get(0).toString(), containsString(convertToString(reward.date, DateTimeUtil.Format.ddMMMyyyy_2)))
        assertThat("Missing product image for product: $rewardName", reward.image.isDisplayed())
        assertThat("Wrong redemption name!", rewardName.replace(" ", ""), is(rewardsInPoints.get(iterator).get(1).toString().replace(" ", "")))
        String userId = rewardsInPoints.get(iterator).get(2).toString()
        String productId = rewardsInPoints.get(iterator).get(3).toString()
        assertThat("Wrong redemption quantity for product: $rewardName", reward.epointsSum, is((Integer.parseInt(mySQLConnector.getSingleResult("SELECT quantity FROM points_manager.Product WHERE id = $productId")) * Integer.parseInt(mySQLConnector.getSingleResult("SELECT numPoints FROM points_manager.Product WHERE id = $productId")))))
        String orderId = mySQLConnector.getSingleResult("SELECT orderId FROM points_manager.Product WHERE id = $productId")
        assertThat("Wrong address name for product: $rewardName", reward.address.contains(mySQLConnector.getSingleResult("SELECT name FROM points_manager.RedemptionOrder WHERE userId = $userId AND id = $orderId")))
        assertThat("Wrong address 1 for product: $rewardName", reward.address, allOf(
                containsString(mySQLConnector.getSingleResult("SELECT address1 FROM points_manager.RedemptionOrder WHERE userId = $userId AND id = $orderId")),
                containsString(mySQLConnector.getSingleResult("SELECT address2 FROM points_manager.RedemptionOrder WHERE userId = $userId AND id = $orderId")),
                containsString(mySQLConnector.getSingleResult("SELECT county FROM points_manager.RedemptionOrder WHERE userId = $userId AND id = $orderId")),
                containsString(mySQLConnector.getSingleResult("SELECT country FROM points_manager.RedemptionOrder WHERE userId = $userId AND id = $orderId")),
                containsString(mySQLConnector.getSingleResult("SELECT postcode FROM points_manager.RedemptionOrder WHERE userId = $userId AND id = $orderId"))
        ))
        assertThat("Missing contact us link for product: $rewardName", reward.contactUsLink.isDisplayed())
        iterator++
        if (iterator > 1)
            break
    }
}

// 1.2 //  ----- ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082)
// ------------------------------------------------------------------------------------- working of contact us links
When(~/^User click on chosen redemption contact us link$/) { ->
    waitFor { rewardsHistoryPage.rewards.size() > 0 }
    random = func.returnRandomValue(rewardsHistoryPage.rewards.size())
    rewardsHistoryPage.clickChosenContactUsLink(random)
}
Then(~/^He will be redirected to contact us help page$/) { ->
    at ContactUsPage
}

// 1.3 //  ----- ANGULAR - covert existing account area - "Rewards History" to Angular for desktop &mobile(NBO-3082)
// -------------------------------------------------------------------------------------- empty rewards history page
Given(~/^Rewards history page is opened$/) { ->
    to RewardsHistoryPage
    rewardsHistoryPage = page
}
Then(~/^He will see that rewards history page is empty$/) { ->
    assertThat("Missing rewards history empty page info", !rewardsHistoryPage.rewardsTable.isDisplayed())
}
Then(~/^No rewards history info is displayed$/) { ->
    waitFor { rewardsHistoryPage.noRewardsHistoryYetInfo.isDisplayed() }
    assertThat("Wrong rewards history empty page info", rewardsHistoryPage.noRewardsHistoryYetInfo.text(), is(envVar.noRewardsYetInfo))
}