package com.iat.stepdefs

import com.iat.Config
import com.iat.stepdefs.utils.VideoRecorder
import geb.Browser
import geb.binding.BindingUpdater
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

VideoRecorder videoRecorder = new VideoRecorder().getInstance()

Before() {
    println "NEW TEST STARTED *****************************************************************************************"
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.browser.clearCookies()
    bindingUpdater.browser.cleanReportGroupDir()
    bindingUpdater.initialize()


    if (Config.makeScreenshotAndVideo) {
        //remove videos from previous tests execution
        videoRecorder.removePreviousTestExecutionVideos()
        //start test recording
        videoRecorder.startRecording()
    }
}

After() { scenario ->
    if (Config.makeScreenshotAndVideo) {
        //stop test recording
        videoRecorder.stopRecording()
        //print file name
        videoRecorder.displayCreatedMovieFiles()

        if (scenario.isFailed()) {
            //this goes to target/screenshotsDirectory
            browser.reportGroup scenario.getName()
            browser.report(scenario.getName())
            //this goes to .html reports file
            byte[] screenshot = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            File[] files = new File("build//reports//videoDirectory").listFiles()
        } else
            videoRecorder.deleteLatestCreatedMovieFile()
    }
    bindingUpdater.remove()
    println "TEST ENDED ***********************************************************************************************"
}