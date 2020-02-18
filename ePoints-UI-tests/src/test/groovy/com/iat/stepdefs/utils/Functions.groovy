package com.iat.stepdefs.utils

import com.iat.repository.impl.UserRepositoryImpl
import geb.Browser
import org.openqa.selenium.JavascriptExecutor

import java.awt.*
import java.text.SimpleDateFormat

class Functions {

    def browser = new Browser()

    int returnRandomValue(int range) {
        return new Random().nextInt(range)
    }

    int returnRandomValue(int min, int max) {
        if (max < min)
            return returnRandomValue(max, min)
        int n = max - min + 1
        return min + new Random().nextInt(n)
    }

    def refreshPage() {
        sleep(1000)
        browser.getDriver().navigate().refresh()
        sleep(1000)
    }

    def clearCookiesAndStorage() {
        // clear local storage, cookies and refresh page
        ((JavascriptExecutor) browser.getDriver()).executeScript("window.localStorage.clear();")
        browser.clearCookies()
        sleep(1000)
        refreshPage()
    }

    def changePageZoom(String zoom) {
        ((JavascriptExecutor) browser.getDriver()).executeScript("document.body.style.zoom = '" + zoom + "';");
    }

    long convertDateToEpochFormat(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format)
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
        Date expDate = sdf.parse(date)
        long epochTime = expDate.getTime()
        return epochTime
    }

    def clearParticularCookieAndRefreshPage(String whichCookies) {
        def cookiesToBeDeleted = whichCookies.split(',')
        for (String cookieToDelete : cookiesToBeDeleted)
            browser.getDriver().manage().deleteCookieNamed(cookieToDelete)

        sleep(500)
        refreshPage()
    }

    long returnEpochOfCurrentDay() {
        return new Date().getTime()
    }

    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    static Random rnd = new Random()

    def returnRandomString(int len) {
        StringBuilder sb = new StringBuilder(len)
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())))
        return sb.toString()
    }

    def closeAllAdditionalTabs() {
        ArrayList<String> tabs = new ArrayList<String>(browser.getDriver().getWindowHandles())
        for (int i = tabs.size() - 1; i > 0; i--) {
            browser.getDriver().switchTo().window(tabs.get(i))
            browser.getDriver().close()
        }
        browser.getDriver().switchTo().window(tabs.get(0))
    }

    def deleteUserFromPointsManager(String email) {
        new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail(email).getUuid())
    }

    def scrolPageUpDown(String direction, int numOfPixels) {
        sleep(1000)
        if (direction.equals('down')) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("window.scrollBy(0," + numOfPixels + ");", "")
        } else {
            ((JavascriptExecutor) browser.getDriver()).executeScript("window.scrollBy(0,-" + numOfPixels + ");", "")
        }
    }

    def scrolPageUpDown(String direction) {
        scrolPageUpDown(direction, "1250")
    }

    def moveToElem(elem) {
        ((JavascriptExecutor) browser.getDriver()).executeScript("window.scrollTo(0, " + elem.getY() + ");")
    }

    def clickPhysicalButton(button) {
        switch (button) {
            case "escape":
                Robot robot = new Robot()
                robot.keyPress(KeyEvent.VK_ESCAPE)
                break;
        }
    }

}