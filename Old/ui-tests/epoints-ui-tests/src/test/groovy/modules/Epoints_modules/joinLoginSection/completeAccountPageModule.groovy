package modules.Epoints_modules.joinLoginSection

import geb.Module

/**
 * Created by kbaranowski on 2014-11-05.
 */
class completeAccountPageModule extends Module {
    static content = {
        header{ $('h1') }
        passwordField{ $('#pass') }
        confirmPasswordField{ $('#retypePass') }
        nameField{ $('input', name: 'firstName') }
        lastNameField{ $('input', name: 'lastName') }
        genderMenWomenIcon{ $('.gender-icon') }
        doneButton{ $('#completeRegBtn') }
        basicAlert{ $('.error') }
        basicLabel{ $('label') }
        allDoneViewHeader{ $('h1') }
    }

    def clickDoneButton(){ waitFor{ doneButton.isDisplayed() }; doneButton.click() }
    def enterPassword(password){ waitFor{ passwordField.isDisplayed() }; passwordField.value(password) }
    def enterPasswordConfirmation(passwordConfirmation){ waitFor{ confirmPasswordField.isDisplayed() }; confirmPasswordField.value(passwordConfirmation) }
    def enterName(name){ waitFor{ nameField.isDisplayed() }; nameField.value(name) }
    def enterLastName(lastName){ waitFor{ lastNameField.isDisplayed() }; lastNameField.value(lastName) }
    def fillAllCompliteAccountData(password, passwordConfirmation, name, lastName){ enterPassword(password); enterPasswordConfirmation(passwordConfirmation); enterName(name); enterLastName(lastName) }
    def selectGenderOption(number){ waitFor{ genderMenWomenIcon.isDisplayed() }; genderMenWomenIcon[number].click() }
}