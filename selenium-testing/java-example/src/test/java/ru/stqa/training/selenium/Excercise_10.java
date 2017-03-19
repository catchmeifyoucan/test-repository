package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sebastian on 16.03.2017.
 */
public class Excercise_10 {
    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void test() {

        driver.get("http://localhost:8080/litecart/");

        WebElement firstProduct = driver.findElement(By.cssSelector("div#box-campaigns li:nth-child(1).product.column.shadow.hover-light"));
        String productNameFromMainPage = firstProduct.findElement(By.cssSelector("div.name")).getText();
        WebElement productRegularPriceFromMainPage = firstProduct.findElement(By.cssSelector("s.regular-price"));
        WebElement productCampaignPriceFromMainPage = firstProduct.findElement(By.cssSelector("strong.campaign-price"));
        String sproductRegularPriceFromMainPage = productRegularPriceFromMainPage.getText();
        String sproductCampaignPriceFromMainPage = productCampaignPriceFromMainPage.getText();
        String colorRegularPriceFromMainPage = productRegularPriceFromMainPage.getCssValue("color");
        String decorationRegularPriceFromMainPage = productRegularPriceFromMainPage.getCssValue("text-decoration");
        String colorCampaignPriceFromMainPage = productCampaignPriceFromMainPage.getCssValue("color");
        String decorationCampaignPriceFromMainPage = productCampaignPriceFromMainPage.getCssValue("font-weight");

        firstProduct.click();

        String productNameFromProductPage = driver.findElement(By.cssSelector("h1.title")).getText();
        WebElement productRegularPriceFromProductPage = driver.findElement(By.cssSelector("div.information s.regular-price"));
        WebElement productCampaignPriceFromProductPage = driver.findElement(By.cssSelector("div.information strong.campaign-price"));
        String sproductRegularPriceFromProductPage = productRegularPriceFromProductPage.getText();
        String sproductCampaignPriceFromProductPage = productCampaignPriceFromProductPage.getText();
        String colorRegularPriceFrompProductPage = productRegularPriceFromProductPage.getCssValue("color");
        String decorationRegularPriceFromProductPage = productRegularPriceFromProductPage.getCssValue("text-decoration");
        String colorCampaignPriceFromProductPage = productCampaignPriceFromProductPage.getCssValue("color");
        String decorationCampaignPriceFromProductPage = productCampaignPriceFromProductPage.getCssValue("font-weight");

        assert(productNameFromMainPage.equals(productNameFromProductPage));
        assert(sproductCampaignPriceFromMainPage.equals(sproductCampaignPriceFromProductPage));
        assert(sproductRegularPriceFromMainPage.equals(sproductRegularPriceFromProductPage));
        assert(colorCampaignPriceFromMainPage.equals(colorCampaignPriceFromProductPage));
        assert(decorationCampaignPriceFromMainPage.equals(decorationCampaignPriceFromProductPage));
        assert(decorationRegularPriceFromMainPage.equals(decorationRegularPriceFromProductPage));
        assert(colorRegularPriceFromMainPage.equals(colorRegularPriceFrompProductPage));






    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }



}
