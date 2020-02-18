package com.iat.pages.modules

class ReasonsGlobalSettingsWizardModule extends ReasonsGlobalSettingsModule {

    static content = {
        globalSettingsForm(wait: true) { $('div.ui-wizard-form') }
    }
}