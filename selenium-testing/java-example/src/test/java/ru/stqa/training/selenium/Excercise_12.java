package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sebastian on 26.03.2017.
 */
public class Excercise_12 {
    private WebDriver driver;
    private String productName;



    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void test() {

        driver.get("http://localhost:8080/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        goToCatalog();
        goToAddNewProduct();
        addNewProduct();
        driver.get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        assert(driver.findElements(By.xpath("//a[text()= '"+productName+"']")).size()>0);

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


    private void goToCatalog()
    {
        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
    }

    private void goToAddNewProduct()
    {
        driver.findElement(By.cssSelector("a[href='http://localhost:8080/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")).click();
    }

    private void addNewProduct()
    {

        addProductGeneralInfo();
        addProductInformationInfo();
        addPricesInfo();
        driver.findElement(By.name("save")).click();

    }

    private void addPricesInfo()
    {

        driver.findElement(By.xpath("//a[text()='Prices']")).click();
        setPurchasePrice();
        setPrice();
    }

    private void setPrice()
    {
        WebElement inPriceUSD = driver.findElement(By.cssSelector("input[name='prices[USD]']"));
        inPriceUSD.clear();
        inPriceUSD.sendKeys("20");
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).clear();
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys("3");

        WebElement inPriceEUR = driver.findElement(By.cssSelector("input[name='prices[EUR]']"));
        inPriceEUR.clear();
        inPriceEUR.sendKeys("20");
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).clear();
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys("3");
    }

    private void setPurchasePrice()
    {
        WebElement inPurchasePrice = driver.findElement(By.cssSelector("input[name='purchase_price']"));
        inPurchasePrice.clear();
        inPurchasePrice.sendKeys("1");

        Select inCurrency = new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        inCurrency.selectByIndex(1);
    }

    private void addProductGeneralInfo()
    {
        setStatusEnabled();
        enterProductName();
        enterProductCode();
        setRubberDucksCategory();
        Assert.assertTrue(isRubberDucksDefaultCategory());
        setProductGroups();
        setQuantity();
        setQuantityUnit();
        setDeliveryStatus();
        setSoldOutStatus();
        setImage();
        setDateValidFrom();
        setDateValidTo();
    }

    private void addProductInformationInfo()
    {
        driver.findElement(By.xpath("//a[text()='Information']")).click();
        setManufacturer();
        setKeywords();
        setShortDescription();
        setDescription();
    }

    private void setKeywords()
    {
        driver.findElement(By.name("keywords")).sendKeys("sdsd, sdsd");
    }

    private void setShortDescription()
    {
        driver.findElement(By.name("short_description[en]")).sendKeys("sfsdf");
    }

    private void setDescription()
    {
        driver.findElement(By.name("description[en]")).sendKeys("sdsd");
    }


    private void setManufacturer()
    {
        Select inManufacturer = new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        inManufacturer.selectByIndex(1);
    }

    private void setStatusEnabled()
    {
        driver.findElement(By.cssSelector("input[type='radio'][name='status'][value='1']")).click();
    }

    private void enterProductName()
    {
        productName = UUID.randomUUID().toString().substring(0,5);
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
    }

    private void enterProductCode()
    {
        String productCode = UUID.randomUUID().toString().substring(0,2);
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys(productCode);
    }

    private void setRubberDucksCategory()
    {
        WebElement rootCategory = driver.findElement(By.cssSelector("input[type='checkbox'][name='categories[]'][value='0']"));
        if(rootCategory.isSelected())
            rootCategory.click();

        WebElement rubberDucksCategory = driver.findElement(By.cssSelector("input[type='checkbox'][name='categories[]'][value='1']"));
        if(!rubberDucksCategory.isSelected())
            rubberDucksCategory.click();
    }

    private boolean isRubberDucksDefaultCategory()
    {
        Select defaultCategory = new Select(driver.findElement(By.cssSelector("select[name='default_category_id']")));
        return Integer.valueOf(defaultCategory.getFirstSelectedOption().getAttribute("value")) == 1;

    }


    private void setProductGroups()
    {
        WebElement productGroup = driver.findElement(By.cssSelector("input[type='checkbox'][name='product_groups[]'][value='1-2']"));
        if(!productGroup.isSelected())
            productGroup.click();
    }

    private void setQuantity()
    {
        WebElement inQuantity = driver.findElement(By.cssSelector("input[name='quantity']"));
        inQuantity.clear();
        inQuantity.sendKeys("1");
    }

    private void setQuantityUnit()
    {
        Select inQuantityUnit = new Select(driver.findElement(By.cssSelector("select[name='quantity_unit_id']")));
        inQuantityUnit.selectByIndex(1);
    }

    private void setDeliveryStatus()
    {
        Select inDeliveryStatus = new Select(driver.findElement(By.cssSelector("select[name='delivery_status_id']")));
        inDeliveryStatus.selectByIndex(1);
    }

    private void setSoldOutStatus()
    {
        Select inSoldOutStatus = new Select(driver.findElement(By.cssSelector("select[name='sold_out_status_id']")));
        inSoldOutStatus.selectByIndex(2);
    }

    private void setImage()
    {
        WebElement inImage = driver.findElement(By.cssSelector("input[type='file'][name='new_images[]']"));
        inImage.sendKeys("D:\\programy\\it\\xampp\\htdocs\\litecart\\images\\products\\4-blue-duck-1.png");
    }

    private void setDateValidFrom()
    {
        WebElement inDateValidFrom = driver.findElement(By.cssSelector("input[name='date_valid_from']"));
        inDateValidFrom.sendKeys("03.03.2017");
    }

    private void setDateValidTo()
    {
        WebElement inDateValidTo = driver.findElement(By.cssSelector("input[name='date_valid_to']"));
        inDateValidTo.sendKeys("03.03.2018");
    }

}
