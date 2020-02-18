//package com.iat.stepdefs.prizesSection
//
//import com.iat.Config
//import com.iat.pages.home.EpointsHomePage
//import com.iat.pages.prizesSection.PrizesPage
//import com.iat.repository.impl.UserRepositoryImpl
//import com.iat.stepdefs.utils.Functions
//import com.iat.stepdefs.utils.JdbcDatabaseConnector
//import geb.Browser
//
//import static cucumber.api.groovy.EN.*
//import static org.hamcrest.MatcherAssert.assertThat
//import static org.hamcrest.Matchers.containsString
//import static org.hamcrest.Matchers.is
//
//def epointsHomePage = new EpointsHomePage()
//def prizePage = new PrizesPage()
//def userRepository = new UserRepositoryImpl()
//
//def func = new Functions()
//def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)
//def browser = new Browser()
//
//Given(~/^Prizes page is opened by '(.+?)' user$/) { String userLoginState ->
//    to EpointsHomePage
//    epointsHomePage = page
//    func.clearCookiesAndStorage()
//    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
//    if (userLoginState == 'logged' || userLoginState == 'identified') {
//        epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
//        if (userLoginState == 'identified')
//            func.clearParticularCookieAndRefreshPage('SESSION')
//    }
//    epointsHomePage.headerModule.clickOnPrizesButton()
//    at PrizesPage
//    prizePage = page
//}
//
//Given(~/^User has '(.+?)' spins awarded for confirmed transactions$/) { String spinsAvailable ->
//    String userId = userRepository.findByEmail(Config.epointsUser).getUuid()
//
//    if (spinsAvailable == 'true') {
//        if (Integer.parseInt(mySQLConnector.getSingleResult("SELECT count(*) FROM Spin WHERE userUuid = '$userId'")) < 1)
//            mySQLConnector.execute("INSERT INTO Spin (id, spinUuid, userUuid, originalPointsId, spunPointsId, status, createdAt) VALUES ('1', '1', '$userId', '1', '1', 'AVAILABLE', '2017-06-27 00:00:00')")
//    } else if (spinsAvailable == 'false')
//        mySQLConnector.execute("DELETE FROM Spin WHERE userUuid = '$userId'")
//}
//
//Then(~/^Page content is correct according to '(.+?)' and '(.+?)'$/) { String userLoginState, String spinsAvailable ->
//    if ((userLoginState == 'logged' || userLoginState == 'identified') && spinsAvailable == 'true') {
//        waitFor { prizePage.roulette.isDisplayed() }
//        assertThat("Roulette is not available", prizePage.roulette.isDisplayed())
//    }
//
//    if (((userLoginState == 'logged' || userLoginState == 'identified') && spinsAvailable == 'false') || (userLoginState == 'not logged' && (spinsAvailable == 'false' || spinsAvailable == 'true'))) {
//        assertThat("First block title is not available or has wrong content", prizePage.descriptionSectionTitle.text().trim(), is("epoints Roulette Wheel"))
//        assertThat("Shop to claim spin button is not available or has wrong content", prizePage.shopToClaimSpinButton.text().trim(), is("Shop to claim spin"))
//        assertThat("First section main info is not available or has wrong content", prizePage.descriptionSectionMainInfo.text().trim(), containsString("Get your FREE spin of the epoints Roulette Wheel"))
//
//        assertThat("First section additional info is not available or has wrong content", prizePage.descriptionSectionAdditionalInfo.text().trim(), containsString("Win up to 20,000ep (£100)"))
//        assertThat("First section additional info is not available or has wrong content", prizePage.descriptionSectionAdditionalInfo.text().trim(), containsString("Everyones's a winner"))
//        assertThat("First section additional info is not available or has wrong content", prizePage.descriptionSectionAdditionalInfo.text().trim(), containsString("Shop to enter"))
//        assertThat("First section additional info is not available or has wrong content", prizePage.descriptionSectionAdditionalInfo.text().trim(), containsString("No stake - nothing to lose"))
//
//        assertThat("Roulette should not be available", !prizePage.roulette.isDisplayed())
//
//        assertThat("Second section Header info is not available or has wrong content", prizePage.rouletteWheelHeader.text().trim(), containsString("Roulette Wheel"))
//        assertThat("Second section Title info is not available or has wrong content", prizePage.rouletteWheelTitle.text().trim(), is("Win 1,000ep (£5) to 20,000ep (£100)"))
//        assertThat("Second section description info is not available or has wrong content", prizePage.rouletteWheelDescription.text().trim(), containsString("Win with the epoints Roulette Wheel after every purchase"))
//        assertThat("Second section image is not available", prizePage.rouletteWheelImage.isDisplayed())
//        assertThat("Shop to claim spin button is not available or has wrong content", prizePage.shopToClaimButton.text().trim(), is("Shop to claim spin"))
//
//
//        assertThat("Shop to claim spin button is not available or has wrong content", prizePage.shopToClaimButton.text().trim(), is("Shop to claim spin"))
//
//        if ((userLoginState == 'logged' || userLoginState == 'identified') && spinsAvailable == 'false')
//            assertThat("0 spins info is not available or has wrong content", prizePage.numberOfSpinsLeft.text().trim(), is("No spins available"))
//        if (userLoginState == 'not logged' && (spinsAvailable == 'false' || spinsAvailable == 'true')) {
//            assertThat("First section sign in info is not available or has wrong content", prizePage.signInEpointsInfo.text().trim(), is("Sign in to spin the wheel"))
//            assertThat("First section sign in link is not available or has wrong content", prizePage.signInEpointsLink.text().trim(), is("Sign in"))
//        }
//    }
//
//}
//When(~/^Button from '(.+?)' which redirects user to 'points\/online' page will be clicked$/) { String buttonClicked ->
//    switch (buttonClicked) {
//        case "firstSection":
//            prizePage.shopToClaimSpinButton.click()
//            break
//        case "spinsNumber":
//            prizePage.numberOfSpinsLeft.click()
//            break
//        case "secondSection":
//            prizePage.shopToClaimButton.click()
//            break
//    }
//}
//
//When(~/^User click sign in button available on first information section$/) { ->
//    sleep(2000) //temporary wait until classes will be changed on frontend side
//    waitFor { prizePage.signInEpointsLink.isDisplayed() }
//    prizePage.signInEpointsLink.click()
//}
//
//Then(~/^Login modal with all needed elements will be displayed over prizes page$/) { ->
//    waitFor { prizePage.loginModalModule.loginModalHeader.isDisplayed() }
//    assertThat("Login modal header is not available or has wrong content", prizePage.loginModalModule.loginModalHeader.text().trim(), is("Sign in"))
//    assertThat("Login modal email input field label is not available or has wrong content", prizePage.loginModalModule.loginModalEmailInputFieldLabel.text().trim(), is("Email address"))
//    assertThat("Login modal email input field is not available", prizePage.loginModalModule.loginModalEmailInputField.isDisplayed())
//    assertThat("Login modal password input field label is not available or has wrong content", prizePage.loginModalModule.loginModalPasswordInputFieldLabel.text().trim(), is("Password"))
//    assertThat("Login modal password input field is not available", prizePage.loginModalModule.loginModalPasswordInputField.isDisplayed())
//    assertThat("Login modal forgot password link is not available or has wrong content", prizePage.loginModalModule.loginModalForgotPasswordLink.text().trim(), is("Forgot password?"))
//    assertThat("Login modal sign in button is not available or has wrong content", prizePage.loginModalModule.loginModalSignInButton.text().trim(), is("Sign in"))
//    assertThat("Login modal sign in with facebook button is not available or has wrong content", prizePage.loginModalModule.loginModalSignInWithFacebookButton.text().trim(), is("Sign in with facebook"))
//}
//
//Then(~/^He will be redirected to \/points\/online page$/) { ->
//    assertThat("User was not redirected to /points/online page", browser.currentUrl.contains("/points/online"))
//}
//
