package com.iat.pages.points.unitedPointsPageModules

import geb.Module

class LatestDealModule extends Module {

    static content = {
        epointsIcon(wait: true, required: true) { $('.CriteriaCard-icon') }
        price(wait: true, required: true) { $('.CriteriaCard-amount') }
        dealName(wait: true, required: true) { $('.CriteriaCard-description') }
        dealImage(wait: true, required: true) { $('.CriteriaCard-image') }
    }
}