import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver

def driverInstance
driver = {
    //driverInstance = new FirefoxDriver()

    //System.setProperty('webdriver.ie.driver', 'src/binary/IE/IEDriverServer.exe')
    //driverInstance = new InternetExplorerDriver()
    if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0){
        System.setProperty('webdriver.chrome.driver', 'src/binary/Chrome/chromedriver.exe')
    }
    else {
        System.setProperty('webdriver.chrome.driver', '/usr/bin/chromedriver')
    }

    driverInstance = new ChromeDriver()

    driverInstance.manage().window().maximize()
    driverInstance
}

//atCheckWaiting = true
//driver = "htmlunit"

//QA
    baseUrl = 'https://'
//Stag
    //baseUrl = 'https://stag.epoints.com/'
//local
    //baseUrl = 'http://10.10.30.246:8911/'
//live
    //baseUrl = 'https://epoints.com/'