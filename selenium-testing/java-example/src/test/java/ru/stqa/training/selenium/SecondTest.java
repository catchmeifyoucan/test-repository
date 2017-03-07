package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class SecondTest {

    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void test() {
        driver.get("http://localhost:8080/litecart/en/");

        List<WebElement> mostPopularProducts = driver.findElements(By.cssSelector("div#box-most-popular div.image-wrapper"));
        List<WebElement> campaignProducts = driver.findElements(By.cssSelector("div#box-campaigns div.image-wrapper"));
        List<WebElement> latestProducts = driver.findElements(By.cssSelector("div#box-latest-products div.image-wrapper"));

        int expectedNumberOfStickers = mostPopularProducts.size() + campaignProducts.size() + latestProducts.size();
        int actualNumberOfStickers = getNumberOfStickers(mostPopularProducts) + getNumberOfStickers(campaignProducts) + getNumberOfStickers(latestProducts);

        assertTrue(expectedNumberOfStickers == actualNumberOfStickers);

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private int getNumberOfStickers(List<WebElement> l) {

        int numberOfStickers = 0;
        for(WebElement w : l){
            if(w.findElements(By.cssSelector("div[class *= 'sticker']")).size() == 1)
                numberOfStickers++;
        }
        return numberOfStickers;

    }
}


