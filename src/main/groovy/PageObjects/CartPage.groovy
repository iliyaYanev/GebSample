package PageObjects

class CartPage extends BasePage {
    static at = {
        pageTitle.isDisplayed()
        placeOrderButton.isDisplayed()
    }
    static content = {
        pageTitle                 { $("div.col-lg-8>h2", text: "Products")}
        placeOrderButton          { $(".btn.btn-success", text: "Place Order")}
        productTitle(wait:true)   { rowIndex -> $("#tbodyid>:nth-child(${rowIndex})>td:nth-child(2)")}
        productPrice(wait:true)   { rowIndex -> $("#tbodyid>:nth-child(${rowIndex})>td:nth-child(3)")}
        placeOrderModalLabel      { $("#orderModalLabel", text: "Place order")}
        placeOrderModalName       { $("#name")}
        placeOrderModalCountry    { $("#country")}
        placeOrderModalCity       { $("#city")}
        placeOrderModalCreditCard { $("#card")}
        placeOrderModalMonth      { $("#month")}
        placeOrderModalYear       { $("#year")}
        purchaseButton            { $(".btn.btn-primary", text: "Purchase")}
        thankYouMessage           { $("div.sa-icon.sa-custom+h2", text: "Thank you for your purchase!")}
        confirmButton             { $(".confirm.btn.btn-lg.btn-primary", text: "OK")}
    }

    String getProductTitleValue(int rowIndex) {
        return productTitle(rowIndex).text()
    }

    String getProductPriceValue(int rowIndex) {
        return productPrice(rowIndex).text()
    }

    void clickPlaceOrderButton() {
        placeOrderButton.click()
    }

    boolean isPlaceOrderModalDisplayed() {
        return waitFor {placeOrderModalLabel.isDisplayed()}
    }

    boolean isThankYouMessageDisplayed() {
        Thread.sleep(500)
        waitFor {thankYouMessage.isDisplayed()}
    }

    void enterName(String name) {
        placeOrderModalName << name
    }

    void enterCountry(String country) {
        placeOrderModalCountry << country
    }

    void enterCity(String city) {
        placeOrderModalCity << city
    }

    void enterCreditCard(String creditCard) {
        placeOrderModalCreditCard << creditCard
    }

    void enterMonth(String month) {
        placeOrderModalMonth << month
    }

    void enterYear(String year) {
        placeOrderModalYear << year
    }

    void clickPurchaseButton() {
        purchaseButton.click()
    }

    void clickConfirmButton() {
        confirmButton.click()
    }
}
