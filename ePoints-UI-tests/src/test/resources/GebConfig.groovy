import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver

def driverInstance

driver = {

    if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {

        switch (System.getProperty("geb.browser")) {
            case "chrome":
                Map<String, Object> prefs = new HashMap<String, Object>();

                prefs.put("profile.default_content_setting_values.notifications", 2)
                prefs.put("profile.default_content_settings.popups", 0)
                prefs.put("download.prompt_for_download", false)

                ChromeOptions options = new ChromeOptions()
                options.setExperimentalOption("prefs", prefs)
                options.addArguments("--disable-notifications")
                options.addArguments("--disable-extensions")
                options.addArguments("--disable-print-preview")
//                options.addArguments("--window-size=1280,800")
//                options.addArguments("--start-fullscreen")

//                System.setProperty('webdriver.chrome.driver', 'src/binary/Chrome/chromedriver.exe')
                System.setProperty('webdriver.chrome.driver', 'src/binary/Chrome/chromedriver')
                driverInstance = new ChromeDriver(options)
                break
            case "firefox":
                System.setProperty('webdriver.gecko.driver', 'src/binary/Firefox/geckodriver.exe')
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
                Map<String, Object> prefs = new HashMap<String, Object>();

                prefs.put("profile.default_content_setting_values.notifications", 2)
                prefs.put("profile.default_content_settings.popups", 0)
                prefs.put("download.prompt_for_download", false)

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-print-preview");
//                options.addArguments("--window-size=1280,800")
//                options.addArguments("--start-fullscreen")

//                System.setProperty('webdriver.chrome.driver', '/usr/bin/chromedriver')
                System.setProperty('webdriver.chrome.driver', 'src/binary/Chrome/chromedriver')
                driverInstance = new ChromeDriver(options)
                break
            case "firefox":
                System.setProperty('webdriver.gecko.driver', 'src/binary/Firefox/geckodriver.exe')
                driverInstance = new FirefoxDriver()
                break
            case "ie":
                System.setProperty('webdriver.ie.driver', 'src/binary/IE/IEDriverServer.exe')
                driverInstance = new InternetExplorerDriver()
                break
        }
    }
    driverInstance.manage().window().maximize()
    driverInstance
}

reportsDir = new File("build/reports/screenshotsDirectory")

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