package Specs

import PageObjects.CartPage
import PageObjects.HomePage
import PageObjects.ProdDetailsPage
import com.github.javafaker.Faker
import spock.lang.Shared

class HomePageSpec extends BaseSpec {

    @Shared
    Faker faker = new Faker()

    void signUp() {
        given: "We are a the home page"
            HomePage homePage = at HomePage
        when: "We click on the sign up link"
            homePage.clickSignUpLink()
        then: "Sign up form is displayed"
            homePage.isSignUpModalDisplayed()
        and: "Fill out the sign up form"
            homePage.enterUsername(faker.name().username())
            homePage.enterPassword(faker.name().bloodGroup())
            homePage.clickSignUpButton()
        then: "User is registered and we are back at the home page"
            homePage.isSignUpSuccessful()
            at HomePage
    }

    void addItemToCart() {
        given: "We are at the home page"
            HomePage homePage = at HomePage
        when: "Click on the laptops category"
            homePage.clickLaptopsCategory()
            homePage.clickProductByName("MacBook air")
        then: "We are at the product details page"
            ProdDetailsPage prodDetailsPage = at ProdDetailsPage
        and: "The correct product is displayed"
            prodDetailsPage.getProductNameValue() == "MacBook air"
            prodDetailsPage.getProductPriceValue() == "\$700 *includes tax"
        when: "add the product to the cart"
            prodDetailsPage.clickAddToCartButton()
        then: "the product is successfully added"
            prodDetailsPage.isProductAddedToCart()
    }

    void purchaseItem() {
        given: "We are at the home page"
            HomePage homePage = at HomePage
        when: "Click on the monitors category"
            homePage.clickMonitorsCategory()
            homePage.clickProductByName("ASUS Full HD")
        then: "We are at the product details page"
            ProdDetailsPage prodDetailsPage = at ProdDetailsPage
        and: "The correct product is displayed"
            prodDetailsPage.getProductNameValue() == "ASUS Full HD"
            prodDetailsPage.getProductPriceValue() == "\$230 *includes tax"
        when: "add the product to the cart"
            prodDetailsPage.clickAddToCartButton()
        then: "the product is successfully added"
            prodDetailsPage.isProductAddedToCart()
        when: "We go back to the home page"
            prodDetailsPage.clickBackToHome()
            at HomePage
        and: "Navigate to the cart page"
            homePage.clickCartLink()
        then: "We are successfully navigated the the cart"
            CartPage cartPage = at CartPage
        and:"The correct product is in the cart"
            cartPage.getProductTitleValue(1) == "ASUS Full HD"
            cartPage.getProductPriceValue(1) == "230"
        when: "We place the order"
            cartPage.clickPlaceOrderButton()
        then: "Place order modal is displayed"
            cartPage.isPlaceOrderModalDisplayed()
        when: "Populate the needed information"
            cartPage.enterName(faker.name().firstName())
            cartPage.enterCountry(faker.country().name())
            cartPage.enterCity(faker.address().city())
            cartPage.enterCreditCard(faker.number().digits(10))
            cartPage.enterMonth(faker.number().numberBetween(1, 12).toString())
            cartPage.enterYear(faker.number().digits(4))
        and: "Click on the purchase button"
            cartPage.clickPurchaseButton()
        then: "The purchase is finalised"
            cartPage.isThankYouMessageDisplayed()
        when: "click the confirm button"
            cartPage.clickConfirmButton()
        then: "We are navigated back to the home page"
            at HomePage
    }
}
