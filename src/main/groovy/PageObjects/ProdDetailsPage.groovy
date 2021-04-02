package PageObjects


class ProdDetailsPage extends BasePage {
    static at = {
        productName.isDisplayed()
        addToCartButton.isDisplayed()
    }

    static content = {
        backToHome         { $("#nava")}
        productName        { $("h2.name")}
        productImage       { $("div.product-image")}
        productPrice       { $("h3.price-container")}
        productDescription { $("#myTabContent")}
        addToCartButton    { $(".btn.btn-success", text: "Add to cart")}
    }

    String getProductNameValue() {
        return productName.text()
    }

    String getProductPriceValue() {
        return productPrice.text()
    }

    String getDescription() {
        return productDescription.text()
    }

    void clickAddToCartButton() {
        addToCartButton.click()
    }

    void clickBackToHome() {
        backToHome.click()
    }

    void isProductAddedToCart() {
        assert getAlertText() == "Product added"
        acceptAlert()
    }
}
