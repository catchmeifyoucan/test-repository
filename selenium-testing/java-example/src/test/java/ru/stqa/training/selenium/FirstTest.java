package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstTest {

    private WebDriver driver;



    @Before
    public void start()
    {
        driver = new ChromeDriver();


    }

    @Test
    public void testMain()
    {
        driver.get("http://www.onet.pl");

    }

    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }
}
