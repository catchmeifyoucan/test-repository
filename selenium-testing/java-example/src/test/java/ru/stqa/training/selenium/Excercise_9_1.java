package ru.stqa.training.selenium;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
/**
 * Created by Sebastian on 14.03.2017.
 */
public class Excercise_9_1 {

    private WebDriver driver;


    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void test() {
        loginAdmin(driver);
        driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
        List<WebElement> linksToCountries = driver.findElements(By.cssSelector("td#content tr.row > td:nth-child(5) > a"));
        List<String> actualNamesOfCountries = getNames(linksToCountries, false);
        List<String> sortedNamesOfCountries = getsortedNames(actualNamesOfCountries);

        for(int i = 2; i < linksToCountries.size()+2; i++) {

            WebElement linkToCountry = driver.findElement(By.cssSelector("td#content tr.row:nth-child("+i+") > td:nth-child(5) > a"));
            int numberOfZones = Integer.parseInt(linkToCountry.findElement(By.xpath("../following-sibling::td[1]")).getText());

            //if country has 1 zone this zone 'is sorted'
            if(numberOfZones > 1){

                linkToCountry.click();
                List<WebElement> rowsOfZones = driver.findElements(By.cssSelector("table#table-zones tr"));
                List<String> actualNamesOfZones = getNames(rowsOfZones, true);
                List<String> sortedNamesOfZones = getsortedNames(actualNamesOfZones);
                assert(actualNamesOfZones.equals(sortedNamesOfZones));
                driver.get("http://localhost:8080/litecart/admin/?app=countries&doc=countries");
            }
        }
        assert(actualNamesOfCountries.equals(sortedNamesOfCountries));
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

    private List<String> getNames(List<WebElement> l, boolean isZone){

        List<String> names = new ArrayList<String>();
        WebElement we;
        for(int i = 0; i<l.size(); i++){
            we = l.get(i);
            if(isZone && i != 0 && i != l.size()-1){
                names.add(we.findElement(By.xpath(".//td[3]")).getText());
            } else if(!isZone){
                names.add(we.getText());
            }
        }
        return names;

    }

    private List<String> getsortedNames(List<String> l){

        List<String> sortedNames = l.stream().sorted().collect(Collectors.toList());;
        return sortedNames;
    }

}


