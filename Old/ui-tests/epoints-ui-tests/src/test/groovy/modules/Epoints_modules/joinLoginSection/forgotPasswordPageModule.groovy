package modules.Epoints_modules.joinLoginSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-05.
 */
class forgotPasswordPageModule extends Module{
    static content = {
        pageHeader{ $('.row').find('h1') }
        pageMainInformation{ $('.row').find('p') }
        forgotPasswordForm{ $('#forgotPassForm') }
        emailInputFieldLabel{ forgotPasswordForm.find('label') }
        emailInputField{ forgotPasswordForm.find('input') }
        sendButton{ forgotPasswordForm.find('#passReset') }
        emailIsRequiredAlert{ forgotPasswordForm.find('.error') }
        emaildoesNotExistAlert{ forgotPasswordForm.find('.alert-danger') }
        emailSendingSuccesAlert{ forgotPasswordForm.find('.alert-success') }
    }
    def enterEmail(email){ waitFor{ emailInputField.isDisplayed() }; emailInputField.value(email) }
    def clickSendButton(){ waitFor{ sendButton.isDisplayed() }; sendButton.click() }
}