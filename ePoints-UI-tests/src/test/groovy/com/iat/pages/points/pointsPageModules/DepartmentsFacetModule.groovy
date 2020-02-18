package com.iat.pages.points.pointsPageModules

import geb.Module

class DepartmentsFacetModule extends Module {

    static content = {
        departmentFacet(wait: true) { $('.departments-filter') }
        departmentFacetHeader(wait: true) { departmentFacet.find('.header-text') }
        departmentFacetClearButton(wait: true) { departmentFacet.find('.clear-btn') }
        departmentFacetDepartmentButtonBasic(wait: true) { departmentFacet.find('.departments-filter-list').find('li') }
        departmentFacetDepartmentButtonActiveBasic(wait: true) {
            departmentFacet.find('.departments-filter-list').find('li.active')
        }
    }

    def selectDepartment(number) {
        waitFor { departmentFacetDepartmentButtonBasic[number].isDisplayed() }
        departmentFacetDepartmentButtonBasic[number].click()
    }

    def selectDepartmentByName(departmentName) {
        waitFor { departmentFacetDepartmentButtonBasic.size() > 0 }
        int index = departmentFacetDepartmentButtonBasic.findIndexOf { it.text().contains(departmentName) }
        departmentFacetDepartmentButtonBasic[index].click()
    }

    def clickClearButtonOnDepartmentFacet() {
        waitFor { departmentFacetClearButton.isDisplayed() }
        departmentFacetClearButton.click()
    }

}