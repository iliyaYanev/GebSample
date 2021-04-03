package PageObjects

class HomePage extends BasePage {
    static at = {
        contentContainer.isDisplayed()
        categoriesMenuLabel.isDisplayed()
    }

    static content = {
        contentContainer            { $("#contcont") }
        categoriesMenuLabel         { $("#cat") }
        phonesCategor(wait:true)    { $("#itemc", text: "Phones")}
        laptopsCategory(wait:true)  { $("#itemc", text: "Laptops")}
        monitorsCategory(wait:true) { $("#itemc", text: "Monitors")}
        home                        { $(".nav-link", text: "Home") }
        contact                     { $(".nav-link", text: "Contact") }
        aboutUs                     { $(".nav-link", text: "About us") }
        cart                        { $("#cartur", text: "Cart") }
        signUp                      { $(".nav-link", text: "Sign up") }
        nextButton                  { $("#next2") }
        prevButon                   { $("#prev2")}
        modalFrom                   { $(".modal-content")}
        signUpModalLabel            { $("#signInModalLabel", text: "Sign up")}
        signUpUsernameField         { $("#sign-username")}
        signUpPasswordField         { $("#sign-password") }
        signUpButton                { $(".btn.btn-primary", text: "Sign up") }
    }

    boolean isSignUpModalDisplayed() {
        waitFor {signUpModalLabel.isDisplayed()
        signUpUsernameField.isDisplayed()
        signUpPasswordField.isDisplayed() }
    }

    void enterUsername(String username) {
        signUpUsernameField << username
    }

    void enterPassword(String password) {
        signUpPasswordField.value(password)
    }

    void clickSignUpLink() {
        signUp.click()
    }

    void clickHomeLink() {
        home.click()
    }

    void clickContactLink() {
        contact.click()
    }

     void clickAboutUsLink() {
        aboutUs.click()
    }

    void clickCartLink() {
        cart.click()
    }

    void clickSignUpButton() {
        signUpButton.click()
    }

    void clickLaptopsCategory() {
        laptopsCategory.click()
    }

    void clickMonitorsCategory() {
        monitorsCategory.click()
    }

    void clickProductByName(String productName) {
        waitFor { $("h4>a.hrefch", text: productName).click()}
    }

    void isSignUpSuccessful() {
        assert getAlertText() == "Sign up successful."
        acceptAlert()
    }
}
