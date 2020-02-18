package com.iat.stepdefs.usersManagement

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.userProfile.LoginPage
import com.iat.pages.usersManagement.CreateUserPage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.ExcelUtilities
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JdbcDatabaseConnector

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.After

def createUserPage = new CreateUserPage()
def helpFunctions = new HelpFunctions()
def excelUtilities = new ExcelUtilities()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)
String fileName
String createUsersEmails = ''

After('@removeCreatedUsersByBulkUpload') {
    def emails = createUsersEmails.split(",")
    String userId
    emails.each { String email ->
        userId = new UserRepositoryImpl().findByEmail(email).uuid
        println "Removing user " + userId
        new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(userId)
        mySQLConnector.execute("DELETE FROM UserToken WHERE user_id in (SELECT id FROM User WHERE epointsUuid = '$userId')")
        mySQLConnector.execute("DELETE FROM UserAuthority WHERE userId in (SELECT id FROM User WHERE epointsUuid = '$userId')")
        mySQLConnector.execute("DELETE FROM User WHERE epointsUuid = '" + userId + "'")
    }
}

Given(~/^User with a certain bulk upload permissions is signed in to iat$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.managerLogin, Config.managerPassword)
}

And(~/^Bulk upload page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToAddUserPageUsingHomePageLinks()
    at CreateUserPage
    createUserPage = page

    createUserPage.clickAddUserByBulkUploadTab()
    createUsersEmails = ''
}

Then(~/^There is a drop down box$/) { ->
    waitFor { createUserPage.bulkUploadDropbox.isDisplayed() }
}

And(~/^There is an inactive \'Add users\' button$/) { ->
    waitFor { createUserPage.bulkUploadButton.isDisplayed() }
    waitFor { createUserPage.bulkUploadButton.findAll { it.attr('disabled') == 'true' }.isDisplayed() }
}

When(~/^User uploads file '(.*)'$/) { String _fileName ->
    fileName = _fileName
    createUserPage.uploadFileWithUserAndDepartmentsStructure(fileName, "src//bulkUploadFiles//")
}

When(~/^User uploads file '(.*)' with new users$/) { String _fileName ->
    helpFunctions.waitSomeTime(4000)
    fileName = _fileName
    String path = "src//bulkUploadFiles//"

    String email1 = "automatedemailinbulk" + helpFunctions.returnCurrentEpochTime().toString() + "@gmail.com"
    String email2 = "automatedemail2inbulk" + helpFunctions.returnCurrentEpochTime().toString() + "@gmail.com"
    String emails = email1 + "," + email2

    if (createUsersEmails.isEmpty() == false)
        createUsersEmails += ","
    createUsersEmails += emails

    excelUtilities.openHeadless(fileName, path)
    excelUtilities.modifyCellsForColumn("Email", emails)
    excelUtilities.close()
    helpFunctions.waitSomeTime(Config.waitMedium)

    createUserPage.uploadFileWithUserAndDepartmentsStructure(fileName, path)
}

Then(~/^Button \'Add users\' started to be active$/) { ->
    waitFor { createUserPage.bulkUploadButton.findAll { !(it.attr('disabled') == 'true') }.isDisplayed() }
}

And(~/^Afterwards button \'Add users\' is clicked$/) { ->
    waitFor { createUserPage.bulkUploadButton.findAll { !(it.attr('disabled') == 'true') }.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium)
    createUserPage.bulkUploadButton.click()
}

Then(~/^Operation is successfully finished$/) { ->
    waitFor(20) { createUserPage.bulkUploadAlertInfo.isDisplayed() }
    assert createUserPage.bulkUploadAlertInfo.text() == 'Users have been added successfully'
}

And(~/^Users are added to the system$/) { ->
    def emails = createUsersEmails.split(",")
    String userId
    helpFunctions.waitSomeTime(15000) //simplest way to ensure user creation in dynamo
    emails.each { String email ->
        userId = new UserRepositoryImpl().findByEmail(email).uuid
        assert userId != null
    }
}

When(~/^User uploads image file$/) { ->
    createUserPage.uploadFileWithUserAndDepartmentsStructure('image.jpg', "src//images//")
}

Then(~/^Information about bad file extension occurs$/) { ->
    waitFor { createUserPage.bulkUploadInvalidFileExtensionWarning.isDisplayed() }
}

//Scenario: Bulk upload users - errors in file
Then(~/^Operation is not successfully finished$/) { ->
    waitFor { createUserPage.bulkUploadErrorInfo.isDisplayed() }
}

Then(~/^Error message describing all wrong rows is displayed$/) { ->
    assert createUserPage.bulkUploadErrorInfo.text().replace('//n', '').replace(' ', '').equals(('Error details:\n' +
            '\n' +
            '#2 First Name \n' +
            'Empty field\n' +
            '#3 Last Name \n' +
            'Empty field\n' +
            '#4 Gender \n' +
            'Incorrect gender (male, female only allowed)\n' +
            '#5 Date of Birth \n' +
            'Incorrect date format, should be: dd/mm/yyy\n' +
            '#6 Email \n' +
            'Empty field\n' +
            '#7 Department Name \n' +
            'Empty field\n' +
            '#9 Company Start Date \n' +
            'Incorrect date format, should be: dd/mm/yyy').replace('//n', '').replace(' ', ''))
}

When(~/^Adding delete flag to existing file '(.*)'$/) { String _fileName ->
    helpFunctions.waitSomeTime(4000)
    fileName = _fileName
    String path = "src//bulkUploadFiles//"

    excelUtilities.modifyCellsForColumn("Delete", "1,1")
    excelUtilities.close()
    helpFunctions.waitSomeTime(Config.waitMedium)

    createUserPage.uploadFileWithUserAndDepartmentsStructure(fileName, path)
}