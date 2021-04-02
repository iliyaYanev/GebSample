package Modules

import geb.Module

class FooterModule extends Module {
    static content = {
        aboutUsLabel(wait:true) { $("div.caption>h4>b", text: "About Us") }
        getInTouChLabel(wait:true) { $("div.caption>h4>b", text: "Get in Touch")}
    }

    void isAboutUsLabelDisplayed() {
        waitFor {aboutUsLabel.isDisplayed()}
    }

    void isGetInTouchLabelDisplayed() {
        waitFor {getInTouChLabel.isDisplayed()}
    }
}
