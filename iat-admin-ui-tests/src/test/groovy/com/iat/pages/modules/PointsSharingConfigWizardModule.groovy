package com.iat.pages.modules

class PointsSharingConfigWizardModule extends PointsSharingConfigModule {

    static content = {
        optionRoot(wait: true) { pageRoot.find("form", 0) }
    }
}