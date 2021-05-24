package com.ptit.social;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSeleniumTest {
    @Test
    public void testGoogleTitle(){
        String baseUrl = "google.com";
        String expectedTitle = "Google";
        WebDriver driver = new FirefoxDriver();
        driver.get(baseUrl);
        String title = driver.getTitle();
        assertThat(title).isEqualTo(expectedTitle);
    }
}
