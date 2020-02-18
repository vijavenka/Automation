package com.iat.pages.points.pointsPageModules

import geb.Module

class BreadcrumbModule extends Module {

    static content = {
        breadcrumb(wait: true) { $('.breadcrumb') }
        breadcrumbSingleElementBasic(wait: true) { breadcrumb.find('li') }
    }

}