package com.iat.stepdefs.pointsAllocationManagement

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.pointsAllocationManagement.GrantDepartmentsPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JdbcDatabaseConnector

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.Before

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def grantDepartmentsPage = new GrantDepartmentsPage()
def helpFunctions = new HelpFunctions()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)

def allocatedPointsNumber
int availablePointsNumber
int departmentToBeAwarded = 0

Before('@setAvailablePointsForAdmin') {
    //TODO make it by requests not database
    availablePointsNumber = Config.availablePointsNumber
    int reasonManagerToUserMin = Config.reasonManagerToUserMin
    int reasonManagerToUserMax = Config.reasonManagerToUserMax
    int reasonUserToUserMin = Config.reasonUserToUserMin
    int reasonUserToUserMax = Config.reasonUserToUserMax
    String adminUsername = new Config().superAdminLogin
    String externalId = mySQLConnector.getSingleResult("SELECT partnerId FROM User WHERE username = '$adminUsername'")
    //remove all reasons
    mySQLConnector.execute("DELETE FROM ECardsReason WHERE partnerId = '$externalId'")
    //set 2 default tags
    //TODO
    //set 2 default reasons
    mySQLConnector.execute("INSERT INTO ECardsReason (id,createdAt,updatedAt,managerToUserMax,managerToUserMin,managerToUserReasonRange,name,userToUserMax,userToUserMin,userToUserReasonRange,deleted,partnerId,tagName) VALUES ('22102016111','2016-02-17 09:45:36','2016-02-17 09:45:36','$reasonManagerToUserMax','$reasonManagerToUserMin','DEFINE','reason1','$reasonUserToUserMax','$reasonUserToUserMin','DEFINE',0,'$externalId','reason1kb')")
    mySQLConnector.execute("INSERT INTO ECardsReason (id,createdAt,updatedAt,managerToUserMax,managerToUserMin,managerToUserReasonRange,name,userToUserMax,userToUserMin,userToUserReasonRange,deleted,partnerId,tagName) VALUES ('22102016112','2016-02-17 09:45:36','2016-02-17 09:45:36','$reasonManagerToUserMax','$reasonManagerToUserMin','DEFINE','reason2','$reasonUserToUserMax','$reasonUserToUserMin','DEFINE',0,'$externalId','reason2kb')")
    //remove all templates
    mySQLConnector.execute("DELETE FROM ECardsTemplate WHERE partnerId = '$externalId'")
    //set 3 custom templates
    mySQLConnector.execute("INSERT INTO ECardsTemplate (id,createdAt,updatedAt,imageUrl,isDefault,name,thumbnailUrl,deleted,partnerId) VALUES ('22102016111','2016-02-17 12:49:55','2016-02-17 12:51:54','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'customTemplate1','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'" + externalId + "')")
    mySQLConnector.execute("INSERT INTO ECardsTemplate (id,createdAt,updatedAt,imageUrl,isDefault,name,thumbnailUrl,deleted,partnerId) VALUES ('22102016112','2016-02-17 12:49:55','2016-02-17 12:51:54','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'customTemplate3','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'" + externalId + "')")
    mySQLConnector.execute("INSERT INTO ECardsTemplate (id,createdAt,updatedAt,imageUrl,isDefault,name,thumbnailUrl,deleted,partnerId) VALUES ('22102016113','2016-02-17 12:49:55','2016-02-17 12:51:54','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'customTemplate2','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'" + externalId + "')")
    //set 2 default templates
    mySQLConnector.execute("INSERT INTO ECardsTemplate (id,createdAt,updatedAt,imageUrl,isDefault,name,thumbnailUrl,deleted,partnerId) VALUES ('22102016114','2016-02-17 12:49:55','2016-02-17 12:51:54','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG',1,'defaultTemplate1','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'" + externalId + "')")
    mySQLConnector.execute("INSERT INTO ECardsTemplate (id,createdAt,updatedAt,imageUrl,isDefault,name,thumbnailUrl,deleted,partnerId) VALUES ('22102016115','2016-02-17 12:49:55','2016-02-17 12:51:54','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-images/reason_11d1cff9-2f2e-4ae5-b716-c1ab3f26b833_8ffbf48722c0c9850c50453d9cdf5446.JPEG',1,'defaultTemplate2','https://iat-admin-public-images-qa.s3.amazonaws.com/reasons-thumbnails/reason_thumbnail_2901fa32-d102-4ee3-9854-24d6eaf985aa_8ffbf48722c0c9850c50453d9cdf5446.JPEG',0,'" + externalId + "')")
    //update available points for allocation number
    mySQLConnector.execute("UPDATE ECardsPartnerBucket SET amount = '$availablePointsNumber' WHERE externalId = '$externalId'")
    //update defined manager to user global values
    mySQLConnector.execute("Update ECardsSettings SET managerToUserMax = '$reasonManagerToUserMax' WHERE partnerId = '$externalId'")
    mySQLConnector.execute("Update ECardsSettings SET managerToUserMin = '$reasonManagerToUserMin' WHERE partnerId = '$externalId'")
    //TODO add department structure
}

//Scenario Outline: Grant departments - grant department page content
Given(~/^User with points department allocation permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

When(~/^Recognise department page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToGrantDepartmentsPageUsingHomePageLinks()
    at GrantDepartmentsPage
    grantDepartmentsPage = page
    waitFor { !grantDepartmentsPage.loader.isDisplayed() }
    waitFor { grantDepartmentsPage.entitisTree.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitShort)
}

Then(~/^The whole departments tree is displayed$/) { ->
    waitFor { grantDepartmentsPage.entitisTree.isDisplayed() }
    assert grantDepartmentsPage.entitisTree.isDisplayed()
}

Then(~/^Available points to allocate is available$/) { ->
    waitFor { grantDepartmentsPage.availablePointsForAllocation.isDisplayed() }
    assert grantDepartmentsPage.availablePointsForAllocation.isDisplayed()
}

Then(~/^History button is available$/) { ->
    waitFor { grantDepartmentsPage.historyButton.isDisplayed() }
    assert grantDepartmentsPage.historyButton.isDisplayed()
}

Then(~/^Reason input is available$/) { ->
    waitFor { grantDepartmentsPage.reasonMessageInputField.isDisplayed() }
    assert grantDepartmentsPage.reasonMessageInputField.isDisplayed()
    assert grantDepartmentsPage.reasonMessageInputLabel.text() == 'Here you can add a reason for this allocation'
}

Then(~/^Points Allocation summary is available$/) { ->
    waitFor { grantDepartmentsPage.totalPointsToAlocate.isDisplayed() }
    assert grantDepartmentsPage.totalPointsToAlocate.isDisplayed()
}

Then(~/^Pounds Allocation summary is available$/) { ->
    waitFor { grantDepartmentsPage.totalPoundsToAlocate.isDisplayed() }
    assert grantDepartmentsPage.totalPoundsToAlocate.isDisplayed()
}

Then(~/^He is able to browse through all departments in the tree$/) { ->
    //TODO
}

//Scenario Outline: Grant departments - department points allocation, total summary
When(~/^User clicks allocate next to a chosen department$/) { ->
    grantDepartmentsPage.clickChosenNodeAllocateButton(departmentToBeAwarded)
}

When(~/^Chooses 1 or more points$/) { ->
    allocatedPointsNumber = helpFunctions.returnRandomValue(10) * 200
    grantDepartmentsPage.enterPointsValueInChosenNode(departmentToBeAwarded, allocatedPointsNumber)
}

Then(~/^The number total points to allocate is increased$/) { ->
    waitFor {
        grantDepartmentsPage.totalPointsToAlocate.text().replace(',', '').contains(allocatedPointsNumber.toString())
    }
    assert grantDepartmentsPage.totalPointsToAlocate.text().replace(',', '').contains(allocatedPointsNumber.toString())
}

Then(~/^The total is also displayed as a pound value$/) { ->
    assert grantDepartmentsPage.totalPoundsToAlocate.text().contains((allocatedPointsNumber / 200).toString())
}

Then(~/^Proper points value appears next to a department$/) { ->
    assert grantDepartmentsPage.entityNodePointsValueInputBasic[departmentToBeAwarded].value().contains(allocatedPointsNumber.toString())
}

//Scenario Outline: Grant departments - department points allocation, total summary when cancel button clicked
Given(~/^There are some points added to chosen department$/) { ->
    grantDepartmentsPage.clickChosenNodeAllocateButton(departmentToBeAwarded)
    allocatedPointsNumber = helpFunctions.returnRandomValue(10) * 200
    grantDepartmentsPage.enterPointsValueInChosenNode(departmentToBeAwarded, allocatedPointsNumber)
    waitFor {
        grantDepartmentsPage.totalPointsToAlocate.text().replace(',', '').contains(allocatedPointsNumber.toString())
    }
}

When(~/^User clicks cancel next to department points$/) { ->
    grantDepartmentsPage.clickChosenNodeCancelButton(departmentToBeAwarded)
}

Then(~/^The number total points to allocate is decreased$/) { ->
    waitFor { grantDepartmentsPage.totalPointsToAlocate.text().contains("0") }
}

Then(~/^Allocate button appears next to chosen department$/) { ->
    assert grantDepartmentsPage.entityNodeAllocateButtonBasic[departmentToBeAwarded].isDisplayed()
}

//Scenario Outline: Grant departments - department points allocation, allocated points exceeds available
Given(~/^Message field is filled properly$/) { ->
    grantDepartmentsPage.enterPointsAllocationReason("Allocation reason automated test department")
    helpFunctions.scrolPageUpDown("up")
}

When(~/^User chose to allocate more points than available$/) { ->
    availablePointsNumber = Integer.parseInt(grantDepartmentsPage.availablePointsForAllocation.text().replace(',', ''))
    grantDepartmentsPage.clickChosenNodeAllocateButton(departmentToBeAwarded)
    grantDepartmentsPage.enterPointsValueInChosenNode(departmentToBeAwarded, availablePointsNumber + 1)
    waitFor {
        grantDepartmentsPage.totalPointsToAlocate.text().replace(',', '').contains((availablePointsNumber + 1).toString())
    }
}

Then(~/^Save button stay in inactive state$/) { ->
    waitFor { grantDepartmentsPage.saveButton.attr('disabled') == 'true' }
    assert grantDepartmentsPage.saveButton.attr('disabled') == 'true'
    assert grantDepartmentsPage.totalPointsToAlocate.hasClass('overdrawn')
    assert grantDepartmentsPage.totalPoundsToAlocate.hasClass('overdrawn')
}

//Scenario Outline: Grant departments - department points allocation, allocated points not exceeds available
When(~/^User chose to allocate proper number of points$/) { ->
    availablePointsNumber = Integer.parseInt(grantDepartmentsPage.availablePointsForAllocation.text().replace(',', ''))
    grantDepartmentsPage.clickChosenNodeAllocateButton(departmentToBeAwarded)
    grantDepartmentsPage.enterPointsValueInChosenNode(departmentToBeAwarded, availablePointsNumber / 2)
    waitFor {
        grantDepartmentsPage.totalPointsToAlocate.text().replace(',', '').contains((availablePointsNumber / 2).toString())
    }
}

When(~/^User will click save button$/) { ->
    grantDepartmentsPage.clickSaveButton()
}

Then(~/^Confirm dialog box is displayed$/) { ->
    waitFor { grantDepartmentsPage.confirmationPopup.isDisplayed() }
}

Then(~/^He can either confirm or cancel the allocation$/) { ->
    assert grantDepartmentsPage.confirmationPopupInfo.text() == 'Are you sure you want to award ' + (availablePointsNumber / 2).toString() + ' points to this department?'
    assert grantDepartmentsPage.confirmationPopupConfirmButton.isDisplayed()
    assert grantDepartmentsPage.confirmationPopupCancelButton.isDisplayed()
    grantDepartmentsPage.clickCancelButtonOnAllocationConfirmationPopup()
}

//Scenario Outline: Grant departments - department points allocation, confirmation popup cancel button
When(~/^He clicks cancel button on department points allocation confirmation popup$/) { ->
    grantDepartmentsPage.clickCancelButtonOnAllocationConfirmationPopup()
}

Then(~/^No points are allocated$/) { ->
    assert grantDepartmentsPage.availablePointsForAllocation.text().replace(',', '') == availablePointsNumber.toString()
    assert !grantDepartmentsPage.topNavigation.alertInfo.isDisplayed()
}

Then(~/^Admin can edit provided data$/) { ->
    //TODO ?
}

///Scenario Outline: Grant departments - department points allocation, confirmation popup save button
When(~/^He clicks confirm button on department points allocation confirmation popup$/) { ->
    grantDepartmentsPage.clickConfirmButtonOnAllocationConfirmationPopup()
}

Then(~/^Virtual points are granted to proper departments$/) { ->
    waitFor {
        grantDepartmentsPage.availablePointsForAllocation.text().replace(',', '') == (availablePointsNumber / 2).toString()
    }
    assert grantDepartmentsPage.availablePointsForAllocation.text().replace(',', '') == (availablePointsNumber / 2).toString()
    assert grantDepartmentsPage.availablePoundsForAllocation.text().replace(',', '').contains("£" + ((availablePointsNumber / 200) / 2).toString() + ".00")
}

Then(~/^Confirmation info is displayed$/) { ->
    waitFor { grantDepartmentsPage.topNavigation.alertInfo.isDisplayed() }
    assert grantDepartmentsPage.topNavigation.alertInfo.text() == 'Points have been awarded'
    assert !grantDepartmentsPage.entityNodeCancelButtonBasic[departmentToBeAwarded].isDisplayed()
    assert grantDepartmentsPage.totalPointsToAlocate.text().contains("0")
}