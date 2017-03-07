package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class FirstTest {

    private WebDriver driver;



    @Before
    public void start()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void test()
    {
        loginAdmin(driver);
        int numberOfMainMenus = driver.findElements(By.cssSelector("li#app- > a")).size();
        for(int i = 1; i < numberOfMainMenus+1; i++){

            WebElement mainMenuLink = driver.findElement(By.xpath("//li[@id= 'app-']["+i+"]/a"));
            mainMenuLink.click();
            driver.findElement(By.tagName("h1"));
            WebElement listOfNestedLinks;
            if(driver.findElements(By.xpath("//li[@id= 'app-']["+i+"]//ul")).size() > 0){
                listOfNestedLinks = driver.findElement(By.xpath("//li[@id= 'app-']["+i+"]//ul"));
                int numberOfNestedLinks = listOfNestedLinks.findElements(By.tagName("a")).size();

                for(int j = 1; j < numberOfNestedLinks+1 ; j++){
                    listOfNestedLinks = driver.findElement(By.xpath("//li[@id= 'app-']["+i+"]//ul"));
                    listOfNestedLinks.findElement(By.xpath(".//li["+j+"]/a")).click();
                    driver.findElement(By.tagName("h1"));

                }
            }
            driver.get("http://localhost:8080/litecart/admin/");
        }
    }

    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }

    private void loginAdmin(WebDriver wd){

        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }
}
