import org.openqa.selenium.WebDriverException
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType

import static java.util.logging.Level.ALL
import static java.util.logging.Level.INFO
import static org.openqa.selenium.logging.LogType.BROWSER
import static org.openqa.selenium.logging.LogType.PERFORMANCE

reportsDir = 'build/geb-spock-reports'
// Wait configuration // https://gebish.org/manual/current/#waiting-configuration
waiting {
    timeout = 15
    retryInterval = 0.5
    presets {
        quick {
            timeout = 2
            retryInterval = 0.2
        }
        slow {
            timeout = 30
            retryInterval = 1
        }
    }
    includeCauseInMessage = true
}
// Implicitly wrap `at` checking with `waitFor` // https://gebish.org/manual/current/#at-check-waiting
atCheckWaiting = true

// Set default values for Content DSL template options // https://gebish.org/manual/current/#template-options-default-values
templateOptions {
    wait = false
    toWait = true
    required = false
}

driver = {
    // See https://chromium.googlesource.com/chromium/src/+/master/chrome/common/chrome_switches.cc
    File chromeDriverPath
    String osName = System. getProperty("os.name")

    if (osName.contains('Windows')) {
        chromeDriverPath = new File('src/test/resources/driver/Windows/chromedriver.exe')
    }
    else if (osName.contains('Linux')) {
        chromeDriverPath = new File('src/test/resources/driver/Linux/chromedriver')
    }
    else {
        chromeDriverPath = new File('src/test/resources/driver/Mac/chromedriver')
    }

    System.setProperty('webdriver.chrome.driver', chromeDriverPath.absolutePath)
    ChromeOptions options = new ChromeOptions()
    def headless = System.getProperty("headless")?.toLowerCase() == "true"

    LoggingPreferences logPrefs = new LoggingPreferences()
    logPrefs.enable(BROWSER, ALL)
    logPrefs.enable(PERFORMANCE, INFO)

    HashMap<String, Object> chromePrefs = new HashMap<String, Object>()
    chromePrefs.put("profile.default_content_settings.popups", 0)
    chromePrefs.put("download.prompt_for_download", false)


    if (headless) {
        options.addArguments("--no-sandbox",
                "disable-setuid-sandbox",
                "headless")
    }

    options.addArguments(
            "--start-maximized",
            "window-size=1920,1080",
            "disable-extensions",
            "disable-default-apps",
            "disable-http2",
            "no-experiments",
            "disable-gpu",
            "disable-dev-shm-usage",
            "no-default-browser-check",
            "no-first-run",
            "disable-popup-blocking",
            "disable-translate",
            "disable-background-networking",
            "disable-background-timer-throttling",
            "disable-renderer-backgrounding",
            "disable-device-discovery-notifications",
            "disable-web-security"
    )

    options.setCapability( "goog:loggingPrefs", logPrefs )
    options.setExperimentalOption("prefs", chromePrefs)
    options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)

    // Setting up the Web Driver
    try {
        return new ChromeDriver(options)
    } catch (WebDriverException wde) {
        println("Failed to create WebDriver instance: \n ${wde.message} \n ${wde.stackTrace}")
        // Wait for five seconds
        Thread.sleep(5000)
        // Initialize driver again
        return new ChromeDriver(options)
    }
}
