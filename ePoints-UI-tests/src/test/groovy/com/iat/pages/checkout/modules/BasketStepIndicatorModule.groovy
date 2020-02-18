package com.iat.pages.checkout.modules

import geb.Module

class BasketStepIndicatorModule extends Module {

    static content = {
        stepIndicator(wait: true) { $('#stepIndicator') }
        selectedRewardsStep(wait: true) { stepIndicator.find('.step-label', 0) }
        deliveryDetailsStep(wait: true) { stepIndicator.find('.step-label', 1) }
        orderSummaryStep(wait: true) { stepIndicator.find('.step-label', 2) }
        completeStep(wait: true) { stepIndicator.find('.step-label', 3) }
        stepCircle(wait: true) { stepIndicator.find('.step-circle') }
    }
}