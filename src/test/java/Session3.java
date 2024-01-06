import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Session3 {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeClass
    public void setupFireFox() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    public void editTest1() {
        String webSiteLink = "https://the-internet.herokuapp.com/tables";
        softAssert = new SoftAssert();
        driver = new FirefoxDriver();
        driver.get(webSiteLink);

        WebElement table1 = driver.findElement(By.id("table1"));
        WebElement table1Body = table1.findElement(By.tagName("tbody"));
        List<WebElement> table1Rows = table1Body.findElements(By.tagName("tr"));

        WebElement table1Row2 = table1Rows.get(1);
        WebElement table1Row2EditLink = table1Row2.findElement(By.linkText("edit"));

        table1Row2EditLink.click();
        softAssert.assertEquals(driver.getCurrentUrl(), webSiteLink + "#edit");


        softAssert.assertAll();


    }

    @Test
    public void editTest2() {
        String webSiteLink = "https://the-internet.herokuapp.com/tables";
        softAssert = new SoftAssert();
        driver = new FirefoxDriver();
        driver.get(webSiteLink);

        WebElement table1Row2EditLink = driver.findElement(By.xpath("//table[@id = 'table1']//tr[2]//a[@href = '#edit']"));
        table1Row2EditLink.click();
        softAssert.assertEquals(driver.getCurrentUrl(), webSiteLink + "#edit");


        softAssert.assertAll();

    }

    @Test
    public void deleteTest1() {
        String webSiteLink = "https://the-internet.herokuapp.com/tables";
        softAssert = new SoftAssert();
        driver = new FirefoxDriver();
        driver.get(webSiteLink);
        WebElement table1DeleteButton = driver.findElement(By.xpath("//table[@id = 'table1']//td[text() = 'jdoe@hotmail.com']//..//a[text() ='delete']"));
        table1DeleteButton.click();

        softAssert.assertEquals(driver.getCurrentUrl(), webSiteLink + "#delete");


        softAssert.assertAll();

    }

    @Test
    public void addIphoneToCartTest() {
        String webSiteLink = "http://opencart.abstracta.us/index.php?route=product/category&path=24";
        softAssert = new SoftAssert();
        driver = new FirefoxDriver();
        driver.get(webSiteLink);
        driver.manage().window().maximize();

        WebElement addToCartButton = driver.findElement(By.xpath("//img[@title = 'iPhone']//..//..//..//div[@class = 'button-group']//button[1]"));
        addToCartButton.click();

    }

    @Test
    public void printAllLinksInPage() {
        final int totalNumberOfLinks = 46 ;
        String webSiteLink = "https://the-internet.herokuapp.com";
        softAssert = new SoftAssert();
        driver = new FirefoxDriver();
        driver.get(webSiteLink);
        driver.manage().window().maximize();
        List<WebElement> allLinks = driver.findElements(By.tagName("a")) ;
        //System.out.println(allLinks.size());
        softAssert.assertEquals(allLinks.size() , totalNumberOfLinks);

        softAssert.assertAll();
    }
}