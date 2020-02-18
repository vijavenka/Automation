package stepdefs.EpointsStepDefs.checkoutSection

import cucumber.api.java.After
import cucumber.api.java.Before
import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-03-03.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  ------------------------------------------------------------------------ Checkout - delivery details page
    // ---------------------------------------------------------------------------------------------------- page content
    String chosenRedemptionName
    String chosenRedemptionCost
    Given(~/^Delivery details checkout page is opened with one product in it by logged user clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        epoints.clickSpendButton()

        waitFor{ epoints.spendPage.departmentCardBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        while(epoints.spendPage.departmentCardBasic[random].hasClass('is-disabled')){
            random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        }
        epoints.spendPage.clickChosenDepartmentCard(random)
        //TODO Remove this  after fix
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
        //clear basket if needed
        if(!epoints.basket.basketSmallViewSelectedRewardsButton.isDisplayed()){
            epoints.basket.openBasketPreview()
        }
        Thread.sleep(1000)
        if(epoints.basket.basketSmallPreviewOption.size()>0){
            epoints.basket.clickViewSelectedRewardButton()
            epoints.basket.clickRemoveAllItemsButton()
            epoints.basket.clickRemoveAllItemsYesButton()
            epoints.basket.clickBackToRewardsButton()
        }
        //clear basket if needed
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.size() == 40 }
        random = func.returnRandomValue(epoints.browseRewardsPage.redemptionCardBasic.size())
        chosenRedemptionName = epoints.browseRewardsPage.redemptionCardNameBasic[random].text()
        chosenRedemptionCost = epoints.browseRewardsPage.redemptionCardPointsValueBasic[random].text()
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
        Thread.sleep(1000)
        //TODO Uncomment this after fix
        if(!epoints.basket.basketSmallViewSelectedRewardsButton.isDisplayed()){
            epoints.basket.openBasketPreview()
        }
        epoints.basket.clickViewSelectedRewardButton()
        Thread.sleep(1000)
        epoints.basket.clickOrderRewardButton()
        waitFor{ epoints.basket.stepIndicator.hasClass('current-step-2') }
        assert epoints.basket.selectedRewardsStep.text() == envVar.checkoutStep1
        assert epoints.basket.deliveryDetailsStep.text() == envVar.checkoutStep2
        assert epoints.basket.orderSummaryStep.text() == envVar.checkoutStep3
        assert epoints.basket.completeStep.text() == envVar.checkoutStep4
        assert epoints.basket.stepCircle.size() == 4
    }
    When(~/^User look on delivery details page$/) { ->
        waitFor{ epoints.basket.deliveryDetailsHeader.isDisplayed() }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutDeliveryURL }
        assert epoints.basket.deliveryDetailsHeader.text() == envVar.deliveryDetailsHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutDeliveryURL
    }
    Then(~/^He can sse that delivery detail page contains address cards section$/) { ->
        waitFor{ epoints.basket.ddDeliveryAddresCardBasic.isDisplayed() }
        assert epoints.basket.ddDeliveryAddresCardBasic.size() >= 2
        assert epoints.basket.ddDeliveryAddresCardPlusButton.isDisplayed()
        assert epoints.basket.ddDeliveryAddresCardAddNewAddressButton.isDisplayed()
    }
    Then(~/^First address card is filled with contact data$/) { ->
        def mysql = new jdbc('points')
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[0].text().contains(mysql.get("SELECT firstName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[0].text().contains(mysql.get("SELECT lastName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[1].text().contains(mysql.get("SELECT houseNumberOrName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[1].text().contains(mysql.get("SELECT street FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[2].text().contains(mysql.get("SELECT townOrCity FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[3].text().contains(mysql.get("SELECT county FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[4].text().contains(mysql.get("SELECT country FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.ddDeliveryAddresCardDetailsBasic[5].text().contains(mysql.get("SELECT postcode FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
        mysql.close()
    }
    Then(~/^Delivery details page contains redemption selection section$/) { ->
        waitFor{ epoints.basket.ddItemsSelectionRedemptionNameBasic.isDisplayed() }
        assert epoints.basket.ddItemsSelectionEditButton.isDisplayed()
        assert epoints.basket.ddItemsSelectionRedemptionNameBasic.isDisplayed()
        assert epoints.basket.ddItemsSelectionRedemptionPointsValueBasic.isDisplayed()
        assert epoints.basket.ddItemsSelectionTotalEpointsNeeded.isDisplayed()
    }
    Then(~/^Redemption selection section contains proper product and points values$/) { ->
        waitFor{ epoints.basket.ddItemsSelectionRedemptionNameBasic.isDisplayed() }
        assert epoints.basket.ddItemsSelectionRedemptionNameBasic.text().contains(chosenRedemptionName)
        assert epoints.basket.ddItemsSelectionRedemptionPointsValueBasic.text() == chosenRedemptionCost
        def calculatedRedmptionsCost = 0
        for(int i=0; i<epoints.basket.ddItemsSelectionRedemptionPointsValueBasic.size()-1; i++){
            calculatedRedmptionsCost = calculatedRedmptionsCost + Integer.parseInt(epoints.basket.ddItemsSelectionRedemptionPointsValueBasic[i].text().replace(',',''))
        }
        assert calculatedRedmptionsCost == Integer.parseInt(epoints.basket.ddItemsSelectionTotalEpointsNeeded.text().replace(',',''))
    }
    Then(~/^Delivery details page contains delivery info section$/) { ->
        waitFor{ epoints.basket.ddDeliveryInformationBox.isDisplayed() }
        assert epoints.basket.ddDeliveryInformationBox.isDisplayed()
    }
    Then(~/^Delivery details page contains navigation buttons$/) { ->
        waitFor{ epoints.basket.ddNextButton.isDisplayed() }
        waitFor{ epoints.basket.ddBackButton.isDisplayed() }
        assert epoints.basket.ddNextButton.isDisplayed()
        assert epoints.basket.ddBackButton.isDisplayed()
    }

    // 1.2 //  ------------------------------------------------------------------------ Checkout - delivery details page
    // --------------------------------------------------------------------------- redemptions selection box edit button
    When(~/^User click edit button in redemptions selection box$/) { ->
        epoints.basket.clickEditButtonInProductsSelectionBox()
    }

    // 1.3 //  ------------------------------------------------------------------------ Checkout - delivery details page
    // ----------------------------------------------------------------------------------------------------- back button
    When(~/^User click back button on delivery details page$/) { ->
        epoints.basket.clickBackButtonOnOrderSummaryPage()
    }

    // 1.4 //  ------------------------------------------------------------------------ Checkout - delivery details page
    // ----------------------------------------------------------------------------------------------------- next button
    When(~/^User click next button on delivery details page$/) { ->
        epoints.basket.clickNextButtonOnDeliveryDetailsPage()
    }
    Then(~/^He will be redirected to order summary step$/) { ->
        waitFor{ epoints.basket.deliveryDetailsHeader.text() == envVar.orderSummaryHeader }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutSummaryURL }
        assert epoints.basket.deliveryDetailsHeader.text() == envVar.orderSummaryHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutSummaryURL
    }

    // 1.5 //  ------------------------------------------------------------------------ Checkout - delivery details page
    // ------------------------------------------------------------------------ new address form content / cancel button
    When(~/^User click add new address button$/) { ->
        epoints.basket.clickAddNewAddresButton()
    }
    Then(~/^New address form will be displayed$/) { ->
        waitFor{ epoints.basket.deliveryForm.isDisplayed() }
        assert epoints.basket.cancelButton.isDisplayed()
        assert epoints.basket.firstNameLabel.text() == envVar.deliveryNameLabel
        assert epoints.basket.firstNameInputField.isDisplayed()
        assert epoints.basket.lastNameLabel.text() == envVar.deliveryLastNameLabel
        assert epoints.basket.lastNameInputField.isDisplayed()
        assert epoints.basket.houseNumberLabel.text() == envVar.deliveryHouseNumberLabel
        assert epoints.basket.houseNumberInputField.isDisplayed()
        assert epoints.basket.streetLabel.text() == envVar.deliveryStreetLabel
        assert epoints.basket.streetInputField.isDisplayed()
        assert epoints.basket.townLabel.text() == envVar.deliveryTownLabel
        assert epoints.basket.townInputField.isDisplayed()
        assert epoints.basket.coutyLabel.text() == envVar.deliveryCountyLabel
        assert epoints.basket.countyInputField.isDisplayed()
        assert epoints.basket.coutryLabel.text() == envVar.deliveryCoutryLabel
        assert epoints.basket.coutryDDL.isDisplayed()
        assert epoints.basket.postcodeLabel.text() == envVar.deliveryPostcodeLabel
        assert epoints.basket.postcodeInputField.isDisplayed()
        assert epoints.basket.findAddressButton.isDisplayed()
        assert epoints.basket.useAsContactDetailsCheckbox.isDisplayed()
    }
    When(~/^User click cancel button on new address form$/) { ->
        epoints.basket.clickCancelButtonOnNewAddressForm()
    }
    Then(~/^New addres form will be closed$/) { ->
        waitFor{ !epoints.basket.deliveryForm.isDisplayed() }
        assert !epoints.basket.deliveryForm.isDisplayed()
    }

    // 1.6 //  ------------------------------------------------------------------------ Checkout - delivery details page
    // -------------------------------------------------------------------------------------------------- address finder
    Given(~/^New address form is opened$/) { ->
        epoints.basket.clickAddNewAddresButton()
    }
    When(~/^User fill post code input field$/) { ->
        epoints.basket.enterPostCode(envVar.UKPostcode)
    }
    When(~/^Click find address button$/) { ->
        epoints.basket.clickFindAddressButton()
    }
    When(~/^User select some address from proposed addresses$/) { ->
        epoints.basket.expandAddressDDL()
        Thread.sleep(1000)
        epoints.basket.clickChosenAddressDDLOption(1)
    }
    Then(~/^House number, street, town inputs will be automatically filled with data$/) { ->
        waitFor{ epoints.basket.houseNumberInputField.value().size() > 0 }
        assert epoints.basket.houseNumberInputField.value().size() > 0
        assert epoints.basket.streetInputField.value().size() > 0
        assert epoints.basket.townInputField.value().size() > 0
    }

    // 1.7 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
    // ------------------------------------------------------------------------------------------- alerts for UK country
    // 1.8 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
    // ------------------------------------------------------------------------------------------- alerts for IE country
    When(~/^User fill all new address form fields for country (.+?)$/) {String country->
        if(country == 'UK'){
            epoints.basket.fillAllNewAddressFormFields('firstName','lastName','houseNr','streetName','townName','countyName','UK','postcode')
        }else if(country == 'IE'){
            epoints.basket.fillAllNewAddressFormFields('firstName','lastName','houseNr','streetName','townName','countyName','IE','postcode')
        }
    }
    When(~/^Delete all data from new address form$/) { ->
        epoints.basket.fillAllNewAddressFormFields(' ',' ',' ',' ',' ',' ',' ',' ')
    }
    Then(~/^Fields required alerts will be displayed (.+?) post code field$/) {String poscodeRequired ->
        waitFor{ epoints.basket.validErrorBasic.isDisplayed() }
        assert epoints.basket.validErrorBasic[0].text() == envVar.deliveryNameRequiredAlert
        assert epoints.basket.validErrorBasic[1].text() == envVar.deliveryLastNameRequiredAlert
        assert epoints.basket.validErrorBasic[2].text() == envVar.deliveryHouseNumberRequiredAlert
        assert epoints.basket.validErrorBasic[3].text() == envVar.deliveryStreetRequiredAlert
        assert epoints.basket.validErrorBasic[4].text() == envVar.deliveryTownRequiredAlert
        assert epoints.basket.validErrorBasic[5].text() == envVar.deliveryCountyRequiredAlert
        //assert epoints.basket.validErrorBasic[6].text() == envVar
        if(poscodeRequired == 'including'){
            assert epoints.basket.validErrorBasic[7].text() == envVar.deliveryPostcodeRequiredAlert
        }else if(poscodeRequired == 'excluding') {
            assert !epoints.basket.validErrorBasic[7].isDisplayed()
        }
    }

    // 1.9 //  ------------------------------------------------------------------------ Checkout - delivery details page
    // ------------------------------------------------------------------------------- next button / remembering address
    String firstName = 'firstName' + func.returnRandomValue(9)
    String lastName = 'lastName' + func.returnRandomValue(9)
    String houseNr = 'houseNr' + func.returnRandomValue(9)
    String street = 'street' + func.returnRandomValue(9)
    String town = 'town' + func.returnRandomValue(9)
    String county = 'county' + func.returnRandomValue(9)
    When(~/^User fill all new address form fields$/) { ->
        epoints.basket.fillAllNewAddressFormFields(firstName,lastName,houseNr,street,town,county,'UK',envVar.UKPostcode)
    }
    Then(~/^He will see that entered address data were remembered on address card$/) { ->
        epoints = page
        waitFor{ epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[0].text().contains(firstName) }
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[0].text().contains(firstName)
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[0].text().contains(lastName)
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[1].text().contains(houseNr)
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[1].text().contains(street)
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[2].text().contains(town)
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[3].text().contains(county)
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[4].text().contains('UK')
        assert epoints.basket.ddDeliveryAddresSecondCardDetailsBasic[5].text().contains(envVar.UKPostcode)
    }

    // 1.10 //  ----------------------------------------------------------------------- Checkout - delivery details page
    // ------------------------------------------------------------------------------ use as my contact details checkbox
    When(~/^Select 'use as my contact details' checkbox$/) { ->
        epoints.basket.selectUseAsMyContactDetailsCheckbox()
    }
    Then(~/^User contact data will be updated$/) { ->
        def mysql = new jdbc('points')
        waitFor(10){  mysql.get("SELECT houseNumberOrName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == houseNr }
            assert mysql.get("SELECT firstName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == 'Krzysztof'
            assert mysql.get("SELECT lastName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == 'Baranowski'
            assert  mysql.get("SELECT houseNumberOrName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == houseNr
            assert mysql.get("SELECT street FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == street
            assert mysql.get("SELECT townOrCity FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == town
            assert mysql.get("SELECT county FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == county
            assert  mysql.get("SELECT country FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == 'UK'
            assert  mysql.get("SELECT postcode FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1) == envVar.UKPostcode
        mysql.close()
    }
    @Before('@setDefaultPersonalAndContactDataBefore')
    public void setDefaultPersonalAndContactDataBefore(){
        def envVar = new envVariables()
        def mysql = new jdbc('points')
            //personal details
            mysql.update("UPDATE points_manager.User SET firstName = 'Krzysztof' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET lastName = 'Baranowski' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET title = 'Mrs' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET gender = 'MALE' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET birthDate = '2989-06-08 12:20:12' WHERE email = '"+envVar.testUserEmail+"'")
            //conctact details
            mysql.update("UPDATE points_manager.User SET mobileNumber = '695805680' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET houseNumberOrName = '10' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET street = 'Głowna' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET townOrCity = 'Krasowice' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET county = 'Opolskie' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET country = 'UK' WHERE email = '"+envVar.testUserEmail+"'")
            mysql.update("UPDATE points_manager.User SET postcode = '46-100' WHERE email = '"+envVar.testUserEmail+"'")
        mysql.close()
    }