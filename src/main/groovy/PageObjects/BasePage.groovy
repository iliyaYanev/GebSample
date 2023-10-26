package PageObjects

import geb.Page
import geb.navigator.Navigator
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import java.time.Duration

class BasePage extends Page {

    void clickBrowserBackButton(){
        getDriver().navigate().back()
        sleep(500)
    }

    void refreshTheBrowser(){
        getDriver().navigate().refresh()
    }

    void focusElement(Navigator pageElement) {
        assert pageElement != null
        Actions action = new Actions(getDriver())
        WebElement element = pageElement.singleElement()
        assert element != null : "page element: ${pageElement} and single element: ${element} cannot be null"
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element)
        action.build().perform()
        sleep(100)
    }

    void mouseOverElement(Navigator pageElement){
        Actions builder = new Actions(driver)
        builder.moveToElement(pageElement.firstElement()).perform()
        sleep(100)
    }

    void scrollToBottomOfPage(){
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)")
    }

    void scrollToTopOfPage(){
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(document.body.x, 0)")
    }

    String getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().getText()
    }

    void acceptAlert() {
        driver.switchTo().alert().accept()
    }
}
