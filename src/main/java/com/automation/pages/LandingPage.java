package com.automation.pages;

import com.automation.components.ElementComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends ElementComponents {

    @FindBy(linkText = "Sign In")
    WebElement signInButton;
    @FindBy(className = "logged-in")
    WebElement welcomeMessage;
    @FindBy(css = "[role='alert']")
    WebElement alertMessage;
    @FindBy(id = "email-error")
    WebElement emailError;
    @FindBy(className = "logo")
    WebElement logo;

    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToSignInPage() {
        signInButton.click();
    }

    public String getWelcomeMessage() {
        waitForTextToVisible(By.className("logged-in"));
        return welcomeMessage.getText();
    }

    public String getAlertMessage() {
        waitForElementToAppear(By.cssSelector("[role='alert']"));
        return alertMessage.getText();
    }

    public String getEmailError() {
        return emailError.getText();
    }

    public boolean isLogoExist() {
        return logo.isDisplayed();
    }
}
