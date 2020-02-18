package com.iat.pages.rewards.modules

import geb.Module

class BreadcrumbModule extends Module {

    static content = {
        breadcrumb(required: true) { $('.breadcrumb-render') }
        breadcrumbNodes(required: true) { breadcrumb.$("a") }
        breadcrumbProductCounter(required: false) {
            String counter = $('.SpendPage-productsCount').text()
            if (counter == null) counter = "0"
            counter.trim().replaceAll(/[(,),\,]/, "").toInteger()
        }
        breadcrumbLastNode { breadcrumbNodes[breadcrumbNodes.size() - 1] }

    }

    List<String> getNodes() {
        return breadcrumbNodes*.text()
    }

    void clickNode(String node) {
        int index = breadcrumbNodes.findIndexOf { it.text() == node }
        breadcrumbNodes[index].click()
        /*if (node != "Home")
            waitFor { breadcrumbNodes[index].parent() == $("h1") }*/
    }

    @Override
    boolean isDisplayed() {
        return breadcrumb.isDisplayed()
    }
}
