package stepdefs
import geb.Browser
import org.openqa.selenium.JavascriptExecutor

import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 13.06.14
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */

public class Functions {

    def browser = new Browser()

    def returnRandomValue(border){
        Random rand = new Random();
        int random = rand.nextInt(border);
        return  random;
    }

    def refreshPage(){
        Thread.sleep(1000)
        browser.getDriver().navigate().refresh()
        Thread.sleep(1000)
    }

    def clearCookiesAndStorage(){
        // clear local storage, cookies and refresh page
        browser.clearCookies()
        ((JavascriptExecutor) browser.getDriver()).executeScript("window.localStorage.clear();")
        refreshPage()
    }

    def long convertDateToEpochFormat(String date, String format){
        SimpleDateFormat sdf  = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone( "GMT" ));
        Date expDate = sdf.parse(date);
        long epochTime = expDate.getTime();
        return epochTime
    }

    def clearParticularCookieAndRefreshPage(String whichCookie){
        browser.getDriver().manage().deleteCookieNamed(whichCookie)
        refreshPage()
    }

    def long returnEpochOfCurrentDay(){
        Date todayDate = new Date()
        return todayDate.getTime()
    }

    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();
    def returnRandomString(int len){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    def closeAllAdditionalTabs(){
        ArrayList<String> tabs = new ArrayList<String> (browser.getDriver().getWindowHandles());
        for(int i=tabs.size()-1;i>0;i--){
            browser.getDriver().switchTo().window(tabs.get(i));
            browser.getDriver().close()
        }
        browser.getDriver().switchTo().window(tabs.get(0));
    }

}