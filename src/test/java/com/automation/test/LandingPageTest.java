package com.automation.test;

import com.automation.base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTest extends Base {

    @Test
    public void verifyLogoExists() {
        Assert.assertTrue(landingPage.isLogoExist());
    }
}
