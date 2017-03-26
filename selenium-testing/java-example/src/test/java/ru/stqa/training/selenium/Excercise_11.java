package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sebastian on 26.03.2017.
 */
public class Excercise_11 {
    private WebDriver driver;
    private String mail;
    private String password;


    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void test() {

        driver.get("http://localhost:8080/litecart/");
        driver.findElement(By.xpath("//a[text()='New customers click here']")).click();
        enterFirstName();
        enterLastName();
        enterAddress1();
        enterPostCode();
        enterCity();
        enterCountry();
        enterPhone();
        enterPassword();
        enterConfirmedPassword();
        enterEmail();
        submitCreateAccount();

        Assert.assertTrue(isLoggedIn());
        logOut();
        Assert.assertTrue(isLoggedOut());
        logIn();
        Assert.assertTrue(isLoggedIn());
        logOut();
        Assert.assertTrue(isLoggedOut());

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


    private void enterFirstName()
    {
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Allah");
    }

    private void enterLastName()
    {
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Akbar");
    }

    private void enterPostCode()
    {
        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys("01-228");
    }

    private void enterCity()
    {
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys("Warsaw");
    }

    private void enterCountry()
    {
        Select countryList = new Select(driver.findElement(By.cssSelector("select[name='country_code']")));
        countryList.selectByValue("PL");
    }

    private void enterPhone()
    {
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("555555555");
    }

    private void enterAddress1()
    {
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys("dfsdf");
    }

    private void enterPassword()
    {
        password = "Abc#123";
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
    }

    private void enterConfirmedPassword()
    {
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(password);
    }

    private void enterEmail()
    {
        mail = UUID.randomUUID().toString().substring(0,10)+"@wp.pl";
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(mail);
    }

    private void submitCreateAccount()
    {
        driver.findElement(By.cssSelector("button[name='create_account']")).click();
    }

    private boolean isLoggedIn()
    {
        return driver.findElements(By.xpath("//a[text()='Logout']")).size() > 0;

    }

    private boolean isLoggedOut()
    {

        return driver.findElements(By.cssSelector("button[name='login'")).size() > 0;
    }

    private void logOut()
    {
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    private void logIn()
    {
        enterLoginMail();
        enterLoginPassword();
        driver.findElement(By.cssSelector("button[name='login'")).click();

    }

    private void enterLoginMail()
    {
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(mail);

    }

    private void enterLoginPassword()
    {
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);

    }

}

