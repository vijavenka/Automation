package modules.Epoints_modules.joinLoginSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-05.
 */
class changePasswordPageModule extends Module{
    static content = {
        pageSectionText{ $('.section') }
        pageHeader{ $('h1') }
        changePasswordForm{ $('#changePassForm') }
        labelPassword{  changePasswordForm.find('label', for: 'pass') }
        passwordInputField{ changePasswordForm.find('#pass') }
        passwordAlert(required: false){ $('.error',0) }
        labelConfirmPassword{ changePasswordForm.find('label', for: 'retypePass') }
        confirmPasswordInputField{ changePasswordForm.find('#retypePass') }
        passwordConfirmationAlert(required: false){ changePasswordForm.find('.error',1) }
        changeButton{ changePasswordForm.find('#changePassBtn') }
        alertSuccess(required: false){ $('.alert-success') }
        passwordStrenghtElement(required: false){ $('.meter') }
    }
    def enterPassword(password){ waitFor{ passwordInputField.isDisplayed() }; passwordInputField.value(password) }
    def enterPasswordConfirmation(passConfirmation){ waitFor{ confirmPasswordInputField.isDisplayed() }; confirmPasswordInputField.value(passConfirmation) }
    def fillChangePasswordForm(password, passConfirmation){ enterPassword(password); enterPasswordConfirmation(passConfirmation)}
    def clickChangePasswordbutton(){ waitFor{ changeButton.isDisplayed() }; changeButton.click() }
}