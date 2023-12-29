import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import javax.xml.namespace.QName;

public class testingClass2ForWebDrivers {
    WebDriver driver ;
    @BeforeClass
    public void setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
    }
    @AfterMethod
    public void closeWebDriver() {
        if (driver != null) {
            //driver.quit();
        }
    }

    @Test ( enabled = false )
    public void test1(){
       driver = new FirefoxDriver() ;
        try {
            driver.get("https://www.google.com");
        }
        catch ( Exception e ){
            driver.close();
            Assert.fail( e.getMessage());
        }
        driver.manage().window().maximize();
        Actions actions = new Actions(driver) ;
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }


    @Test ( enabled = false)
    public void test2(){
        driver = new ChromeDriver() ;
        //driver.manage().window().maximize();
        driver.get("https://www.google.com");

    }

    @Test
    public void test3(){
        driver = new FirefoxDriver() ;
        driver.get("https://www.google.com");
        driver.get("https://soundcloud.com/islamchipsyeek/tracks");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().forward();
    }
}
