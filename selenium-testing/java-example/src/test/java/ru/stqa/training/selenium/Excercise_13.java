package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sebastian on 29.03.2017.
 */
public class Excercise_13 {

    private WebDriver driver;
    private String productName;
    private WebDriverWait wait;
    private int numberOfProductsInCart;



    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


    }

    @Test
    public void test() {

        driver.get("http://localhost:8080/litecart/en/");
        driver.findElement(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1) > a.link")).click();
        selectSize();
        addProductToCart();
        driver.get("http://localhost:8080/litecart/en/");
        driver.findElement(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1) > a.link")).click();
        selectSize();
        addProductToCart();
        driver.get("http://localhost:8080/litecart/en/");
        driver.findElement(By.cssSelector("#box-most-popular > div > ul > li:nth-child(1) > a.link")).click();
        selectSize();
        addProductToCart();

        driver.findElement(By.xpath("//a[text()='Checkout »']")).click();
        removeAllProductsFromCart();

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void addProductToCart()
    {
        numberOfProductsInCart++;

        WebElement counter = driver.findElement(By.className("quantity"));
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.textToBePresentInElement(counter, String.valueOf(numberOfProductsInCart)));

    }

    private void selectSize()
    {

        if(driver.findElements(By.name("options[Size]")).size()>0)
            new Select(driver.findElement(By.name("options[Size]"))).selectByIndex(1);
    }

    private void removeAllProductsFromCart()
    {

        int numberOfProducts = driver.findElements(By.className("shortcut")).size();
        WebElement orderSummaryTable = driver.findElement(By.cssSelector("div#order_confirmation-wrapper table"));
        for(int i = 0; i < numberOfProducts; i++)
        {
            if(i != numberOfProducts-1)
                driver.findElement(By.cssSelector("ul.shortcuts li.shortcut")).click();
            wait.until((WebDriver driver) -> driver.findElement(By.name("remove_cart_item"))).click();
            wait.until(ExpectedConditions.stalenessOf(orderSummaryTable));
            if(i != numberOfProducts-1)
                orderSummaryTable = wait.until((WebDriver driver) -> driver.findElement(By.cssSelector("div#order_confirmation-wrapper table")));

        }


    }

}
