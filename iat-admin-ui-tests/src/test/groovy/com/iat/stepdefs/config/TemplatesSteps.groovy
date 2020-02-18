package com.iat.stepdefs.config

import com.iat.pages.DashboardPage
import com.iat.pages.config.AddTemplatePage
import com.iat.pages.config.EcardTemplatesPage
import com.iat.stepdefs.utils.HelpFunctions

import static cucumber.api.groovy.EN.*

def dashboardPage = new DashboardPage()
def helpFunctions = new HelpFunctions()
def templatesPage = new EcardTemplatesPage()
def addTemplatePage = new AddTemplatePage()

String uniqueTemplateName
String existingTemplateName
boolean customTemplateExists = false
boolean defaultTemplateExists = false
int customTemplatesNumber

//Scenario Outline: Templates - check content of create template form
Given(~/^Templates page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToTemplatesPageUsingHomePageLinks()
    at EcardTemplatesPage
    templatesPage = page
}

When(~/^User click add custom template button$/) { ->
    templatesPage.clickAddCustomTemplateButton()
    at AddTemplatePage
    addTemplatePage = page
}


Then(~/^Add custom template page wil be opened$/) { ->
    at AddTemplatePage
}


Then(~/^Add custom template page contains new template name input field$/) { ->
    waitFor { addTemplatePage.templateNameInputField.isDisplayed() }
    assert addTemplatePage.templateNameInputField.isDisplayed()
}

Then(~/^Add custom template page contains drag and drop upload image field$/) { ->
    assert addTemplatePage.dragAndDropImageInput.isDisplayed()
}

Then(~/^Add custom template page contains cancel button$/) { ->
    assert addTemplatePage.cancelButton.isDisplayed()
}

Then(~/^Add custom template page contains disabled save button$/) { ->
    assert addTemplatePage.saveButton.isDisplayed()
    assert addTemplatePage.saveButton.attr('disabled') == 'true'
}

//Scenario Outline: Templates - add new template
When(~/^User provide unique template name$/) { ->
    uniqueTemplateName = 'uniqueAutomatedName_' + helpFunctions.returnCurrentEpochTime()
    addTemplatePage.enterNewTemplateName(uniqueTemplateName)
}

When(~/^User add new image file$/) { ->
    addTemplatePage.addNewImage('image.jpg')
    waitFor { addTemplatePage.ecardPreview.isDisplayed() }
}

When(~/^User click save button on add template page$/) { ->
    addTemplatePage.clickSaveButton()
    waitFor { (addTemplatePage.topNavigation.alertInfo.text() == 'Saved') }
}

Then(~/^Templates page will be opened$/) { ->
    at EcardTemplatesPage
    templatesPage = page
}

Then(~/^New template will be displayed on custom template list$/) { ->
    waitFor { templatesPage.customTemplatesTemplateCardNameBasic[0].text() == uniqueTemplateName }
    assert templatesPage.customTemplatesTemplateCardNameBasic[0].text() == uniqueTemplateName
}

//Scenario Outline: Templates - edit existing template
When(~/^User click on edit button of chosen custom template$/) { ->
    existingTemplateName = templatesPage.customTemplatesTemplateCardNameBasic[0].text()
    templatesPage.clickCustomTemplateCardEditButton(0)
    at AddTemplatePage
    addTemplatePage = page
}

Then(~/^Template name will be updated$/) { ->
    if (customTemplateExists) {
        waitFor { templatesPage.customTemplatesTemplateCardNameBasic[0].text() == uniqueTemplateName }
        assert templatesPage.customTemplatesTemplateCardNameBasic[0].text() == uniqueTemplateName
    }
}

//Scenario Outline: Templates - add new template page, cancel button use
When(~/^User clicks cancel button on add template page$/) { ->
    addTemplatePage.clickCancelButton()
    waitFor { (addTemplatePage.topNavigation.alertInfo.text() == 'Cancelled') }
}

// Scenario Outline: Templates - list of templates
Then(~/^List of custom templates is displayed$/) { ->
    waitFor { templatesPage.customTemplatesArea.isDisplayed() }
    assert templatesPage.customTemplatesArea.isDisplayed()
    //for now we cannot add new templates so wee need to be sure that some exists before test execution
    customTemplateExists = (customTemplatesNumber = templatesPage.customTemplatesTemplateCardBasic.size()) > 0
    defaultTemplateExists = (defaultTemplatesNumber = templatesPage.defaultTemplatesTemplateCardBasic.size()) > 0
}

Then(~/^Each template card contains name$/) { ->
    if (customTemplateExists)
        assert templatesPage.customTemplatesTemplateCardNameBasic.size() == customTemplatesNumber
}

Then(~/^Each template card contains image$/) { ->
    if (customTemplateExists)
        assert templatesPage.customTemplatesTemplateCardImageBasic.size() == customTemplatesNumber
}


Then(~/^Each template card contains remove button$/) { ->
    if (customTemplateExists)
        assert templatesPage.customTemplatesTemplateCardRemoveButtonBasic.size() == customTemplatesNumber
}


Then(~/^Each template card contains edit button$/) { ->
    if (customTemplateExists)
        assert templatesPage.customTemplatesTemplateCardEditButtonBasic.size() == customTemplatesNumber
}


Then(~/^List of default templates is displayed$/) { ->
    assert templatesPage.defaultTemplatesArea.isDisplayed()
}

Then(~/^Each of default templates contains only image and name$/) { ->
    if (defaultTemplateExists) {
        assert templatesPage.defaultTemplatesTemplateCardNameBasic.size() == defaultTemplatesNumber
        assert templatesPage.defaultTemplatesTemplateCardImageBasic.size() == defaultTemplatesNumber
        assert templatesPage.defaultTemplatesTemplateCardRemoveButtonBasic.size() == 0
        assert templatesPage.defaultmTemplatesTemplateCardEditButtonBasic.size() == 0
    }
}

//Scenario Outline: Templates - delete confirmation cancel button
Given(~/^User click on remove button of chosen custom template$/) { ->
    if (customTemplateExists) {
        existingTemplateName = templatesPage.customTemplatesTemplateCardNameBasic[0].text()
        templatesPage.clickCustomTemplateCardRemoveButton(0)
    }
}

When(~/^User click cancel button on template remove confirmation popup$/) { ->
    if (customTemplateExists) {
        waitFor { templatesPage.deleteConfirmationPopup.isDisplayed() }
        templatesPage.clickCancelButtonOnDeletConfirmationPopup()
    }
}

Then(~/^Template remove confirmation popup will be closed$/) { ->
    waitFor { !templatesPage.deleteConfirmationPopup.isDisplayed() }
}

Then(~/^Template will not be removed$/) { ->
    if (customTemplateExists)
        assert templatesPage.customTemplatesTemplateCardNameBasic[0].text() == existingTemplateName
}
//  Scenario Outline: Templates - delete confirmation delete button
When(~/^User click delete button on template remove confirmation popup$/) { ->
    if (customTemplateExists) {
        waitFor { templatesPage.deleteConfirmationPopup.isDisplayed() }
        templatesPage.clickDeleteButtonOnDeleteConfirmationPopup()
        waitFor { (templatesPage.topNavigation.alertInfo.text() == 'Template has been removed') }
    }
}

Then(~/^Template will be removed$/) { ->
    if (customTemplateExists) {
        waitFor { !(templatesPage.customTemplatesTemplateCardNameBasic[0].text() == existingTemplateName) }
        assert !(templatesPage.customTemplatesTemplateCardNameBasic[0].text() == existingTemplateName)
    }
}

//Scenario Outline: Templates - global template settings change
When(~/^User change global template settings '(.+?)'$/) { String globalTemplatesSettings ->
    if (globalTemplatesSettings == 'defaultAndCustom') {
        if (templatesPage.templatesSettingUseDefaultAndCustomRadioButton.attr('aria-checked') == 'false')
            templatesPage.selectTemplatesSettingUseDefaultAndCustomRadioButton()
    } else if (globalTemplatesSettings == 'customOnly') {
        if (templatesPage.templatesSettingUseOnlyCustomRadioButton.attr('aria-checked') == 'false')
            templatesPage.selectTemplatesSettingUseOnlyCustomRadioButton()
    }
}

When(~/^User click save global templates settings button$/) { ->
    templatesPage.clickSaveTemplateSettingsButton()
}

Then(~/^Default templates will be gray out or not according to '(.+?)'$/) { String globalTemplatesSettings ->
    for (int i = 0; i < templatesPage.defaultTemplatesTemplateCardBasic.size(); i++) {
        if (globalTemplatesSettings == 'defaultAndCustom')
            assert !templatesPage.defaultTemplatesTemplateCardBasic[i].hasClass('u-fade')
        else if (globalTemplatesSettings == 'customOnly')
            assert templatesPage.defaultTemplatesTemplateCardBasic[i].hasClass('u-fade')
    }
}