package com.automation.pages;

import com.automation.components.ElementComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalogPage extends ElementComponents {

    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(css = ".ng-animating")
    WebElement spinner;
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public ProductCatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList() {
        waitForElementToAppear(By.cssSelector(".mb-3"));
        return products;
    }

    public WebElement getProduct(String productName) {
        return getProductList()
                .stream()
                .filter(element -> element.findElement(By.cssSelector("h5")).getText().equals(productName))
                .collect(Collectors.toList())
                .get(0);
    }

    public void addProductToCart(String productName) {
        WebElement product = getProduct(productName);
        product.findElement(addToCart).click();

        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }
}
