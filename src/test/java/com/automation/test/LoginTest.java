package com.automation.test;

import com.automation.base.Base;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginTest extends Base {
    @Test(groups = {"Login"}, dataProvider = "credentials", priority = -1)
    public void verifyLoginWithValidCredentials(HashMap<String, String> input) {
        landingPage.goToSignInPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginApplication(input.get("email"), input.get("password"));

        Assert.assertEquals(landingPage.getWelcomeMessage(),
                "Welcome, " + properties.getProperty("user") + "!");
    }

    @Test(dependsOnMethods = "verifyLoginWithInvalidEmail", groups = "Login")
    public void verifyLoginWithInvalidCredentials() {
        landingPage.goToSignInPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginApplication("email@email.com", "password");
        Assert.assertEquals(landingPage.getAlertMessage(), "Incorrect CAPTCHA");

//                Assert.assertEquals(landingPage.getAlertMessage(),
//                "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
    }

    @Test
    public void verifyLoginWithInvalidEmail() {
        landingPage.goToSignInPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginApplication("email", "password");

        Assert.assertEquals(landingPage.getEmailError(),
                "Please enter a valid email address (Ex: johndoe@domain.com).");
    }

    @DataProvider(name = "credentials")
    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("email", "alexander.kirubhakaran@gmail.com");
//        map.put("password", "Alex@christy1");
//
//        HashMap<String, String> mapNew = new HashMap<>();
//        mapNew.put("email", "alexander.anto@outlook.com");
//        mapNew.put("password", "Alex@christy1");

//        return new Object[][]{{map}, {mapNew}};
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
                + "/src/main/resources/credentials.json");
        return new Object[][]{{data.get(0)}};
    }
}
