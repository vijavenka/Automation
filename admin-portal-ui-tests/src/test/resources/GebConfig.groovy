import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver

def driverInstance

driver = {

    if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {

        switch (System.getProperty("geb.browser")) {
            case "chrome":
                System.setProperty('webdriver.chrome.driver', 'src/binary/Chrome/chromedriver.exe')
                driverInstance = new ChromeDriver()
                break
            case "firefox":
                driverInstance = new FirefoxDriver()
                break
            case "ie":
                System.setProperty('webdriver.ie.driver', 'src/binary/IE/IEDriverServer.exe')
                driverInstance = new InternetExplorerDriver()
                break

        }
    } else {
        switch (System.getProperty("geb.browser")) {
            case "chrome":
                System.setProperty('webdriver.chrome.driver', '/usr/bin/chromedriver')
                driverInstance = new ChromeDriver()
                break
            case "firefox":
                driverInstance = new FirefoxDriver()
                break
        }
    }
    driverInstance.manage().window().maximize()
    driverInstance
}

waiting {
    timeout = 10
}

//waiting {
//    presets {
//        slow {
//            timeout = 10
//            retryInterval = 0.2
//        }
//        quick {
//            timeout = 3
//        }
//    }
//}