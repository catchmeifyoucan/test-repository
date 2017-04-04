package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sebastian on 04.04.2017.
 */
public class Excercise_17 {


    private WebDriver driver;





    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);



    }

    @Test
    public void test() {

        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");


        int numberOfRows = driver.findElements(By.xpath("//tr[@class='row']")).size();
        int numberOfProducts = driver.findElements(By.xpath("//tr[@class='row']/td[3]/a[contains(@href, 'product_id')] ")).size();
        int firstIndexOfProduct = numberOfRows - numberOfProducts;
        for(int i = firstIndexOfProduct+1; i < numberOfProducts+firstIndexOfProduct+1; i++)
        {
            driver.findElement(By.xpath("//tr[@class='row']["+i+"]/td[3]/a[contains(@href, 'product_id')] ")).click();
            driver.get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");

            Assert.assertTrue(driver.manage().logs().get("browser").getAll().size()==0);
        }



    }







    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
