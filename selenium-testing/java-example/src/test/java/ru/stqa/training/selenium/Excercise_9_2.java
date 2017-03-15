package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Sebastian on 15.03.2017.
 */
public class Excercise_9_2 {

    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void test() {
        loginAdmin(driver);
        driver.get("http://localhost:8080/litecart/admin/?app=geo_zones&doc=geo_zones");
        int  numberOfLinksToCountries = driver.findElements(By.cssSelector("table.dataTable tr.row > td:nth-child(3) > a")).size();

        for(int i = 2; i < numberOfLinksToCountries+2; i++) {

            WebElement linkToCountry = driver.findElement(By.cssSelector("table.dataTable tr.row:nth-child("+i+") > td:nth-child(3) > a"));
            linkToCountry.click();
            List<WebElement> rowsOfZones = driver.findElements(By.cssSelector("table#table-zones tr > td:nth-child(3) > select"));
            List<String> actualNamesOfZones = getNames(rowsOfZones);
            List<String> sortedNamesOfZones = getsortedNames(actualNamesOfZones);
            assert(actualNamesOfZones.equals(sortedNamesOfZones));
            driver.get("http://localhost:8080/litecart/admin/?app=geo_zones&doc=geo_zones");

        }

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void loginAdmin(WebDriver wd){

        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    private List<String> getNames(List<WebElement> l){

        List<String> names = new ArrayList<String>();
        Select s;
        for(WebElement w: l){
            s = new Select(w);
            names.add(s.getFirstSelectedOption().getText());

        }

        return names;

    }

    private List<String> getsortedNames(List<String> l){

        List<String> sortedNames = l.stream().sorted().collect(Collectors.toList());;
        return sortedNames;
    }

}


