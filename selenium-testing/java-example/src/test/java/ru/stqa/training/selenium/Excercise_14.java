package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Excercise_14 {


    private WebDriver driver;
    private WebDriverWait wait;
    private String mainWindowHandle;




    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);


    }

    @Test
    public void test() {

        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("a.button")).click();

        List<WebElement> links = driver.findElements(By.cssSelector("td#content table [target='_blank']"));
        mainWindowHandle = driver.getWindowHandle();
        for(int i = 0; i < links.size(); i++)
        {
            links.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            String secondWindowHandler = getSecondWindowHandler();
            driver.switchTo().window(secondWindowHandler);
            driver.close();
            driver.switchTo().window(mainWindowHandle);


        }


    }




    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private String getSecondWindowHandler() {
        Set<String> windowHandlers = driver.getWindowHandles();

        for (String h : windowHandlers) {
            if (!h.equals(mainWindowHandle))
                return h;
        }
        return null;
    }


}
