package com.iat.pages.userAccount.ecardsManagement

class EcardsActivityPage extends EcardsHistoryPage {

    static url = '/my-account/ecards/activity'
    static atCheckWaiting = true
    static at = { waitFor { title.equals('Company ecards activity | epoints') } }

    static content = {
        ecardActivityTitle(wait: true) { $(".EcardsActivity-title") }
    }

    int getTimeAfterSentInSeconds(int index) {
        int value
        String timeText = ecardAddedDate[index].text()

        if (timeText.contains("second")) value = 1
        else if (timeText.contains("seconds")) return 1
        else if (timeText.contains("a minute")) return 60
        else if (timeText.contains("minutes")) return 60 * timeText.find(/\d+/).toInteger()
        else if (timeText.contains("an hour")) return 3600
        else if (timeText.contains("hours")) return 3600 * timeText.find(/\d+/).toInteger()
        else if (timeText.contains("a day")) return 86400
        else if (timeText.contains("days")) return 86400 * timeText.find(/\d+/).toInteger()
        else throw "Absence of defined time unit"
    }

    def checkIfTime1NotGreaterThanTime2(int index1, int index2) {
        if (ecardAddedDate[index1].text() == ecardAddedDate[index2].text()) return true
        assert getTimeAfterSentInSeconds(index1) <= getTimeAfterSentInSeconds(index2)
    }

}