import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Session3Repractice {
    WebDriver driver ;
    SoftAssert softAssert ;
    @BeforeClass
    public void setupFirefox(){
        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    public void PressEdit1(){
        driver = new FirefoxDriver() ;
        softAssert = new SoftAssert() ;
        String websiteURL = "https://the-internet.herokuapp.com/tables" ;

        driver.get(websiteURL);
        WebElement table1 = driver.findElement(By.id("table1")) ;
        List<WebElement> allTableRows = driver.findElements(By.tagName("tr")) ;

        allTableRows.get(2).findElement(By.xpath("//a[@href = '#edit']")).click();
        softAssert.assertEquals(driver.getCurrentUrl() , websiteURL + "#edit");
        driver.close();
        softAssert.assertAll();
    }
    @Test
    public void PressEdit2(){
        driver = new FirefoxDriver() ;
        softAssert = new SoftAssert() ;
        String websiteURL = "https://the-internet.herokuapp.com/tables" ;

        driver.get(websiteURL);
        WebElement table1Row2EditButton = driver.findElement(By.xpath("//table[1]//tr[2]//td[6]//a[1]")) ;

        table1Row2EditButton.click();
        softAssert.assertEquals(driver.getCurrentUrl() , websiteURL + "#edit");
        driver.close();
        softAssert.assertAll();
    }

    @Test
    public void deleteAsEmailEquals(){
        driver = new FirefoxDriver() ;
        softAssert = new SoftAssert() ;
        String websiteURL = "https://the-internet.herokuapp.com/tables" ;
        driver.get(websiteURL);

        String userMail = "jdoe@hotmail.com" ;
        WebElement table1MailDeleteLink = driver.findElement(By.xpath("//table[1]//td[text() = '"+ userMail + "']//following-sibling::td[3]//a[@href = '#delete']")) ;
        table1MailDeleteLink.click();
        softAssert.assertEquals(driver.getCurrentUrl() , websiteURL + "#delete");
        driver.close();
        softAssert.assertAll();

    }

    @Test
    public void addToCartSpecificItemUsingThreadSleep(){
        driver = new FirefoxDriver() ;
        softAssert = new SoftAssert() ;
        String websiteURL = "http://opencart.abstracta.us/index.php?route=product/category&path=24" ;
        driver.get(websiteURL);

        String searchedForItem = "Palm Treo Pro" ;

        WebElement clickAddToCartButton = driver.findElement(By.xpath("//a[text() = '"+ searchedForItem + "']//..//..//..//span[text()='Add to Cart']")) ;
        clickAddToCartButton.click();

        try{
            Thread.sleep(1000);
        }catch (Exception e ){

        }

        WebElement successfulMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']//a[1]")) ;


        softAssert.assertEquals(searchedForItem , successfulMessage.getText());
        driver.close();
        softAssert.assertAll();

    }
    @Test
    public void addToCartSpecificItemUsingImplicitWait(){
        driver = new FirefoxDriver() ;
        softAssert = new SoftAssert() ;
        String websiteURL = "http://opencart.abstracta.us/index.php?route=product/category&path=24" ;
        driver.get(websiteURL);

        String searchedForItem = "Palm Treo Pro" ;

        WebElement clickAddToCartButton = driver.findElement(By.xpath("//a[text() = '"+ searchedForItem + "']//..//..//..//span[text()='Add to Cart']")) ;
        clickAddToCartButton.click();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS) ;
        WebElement successfulMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-success alert-dismissible']//a[1]")) ;


        softAssert.assertEquals(searchedForItem , successfulMessage.getText());
        driver.close();
        softAssert.assertAll();

    }
    @Test
    public void addToCartSpecificItemUsingExplicitWait(){
        driver = new FirefoxDriver() ;
        softAssert = new SoftAssert() ;
        String websiteURL = "http://opencart.abstracta.us/index.php?route=product/category&path=24" ;
        driver.get(websiteURL);

        String searchedForItem = "Palm Treo Pro" ;

        WebElement clickAddToCartButton = driver.findElement(By.xpath("//a[text() = '"+ searchedForItem + "']//..//..//..//span[text()='Add to Cart']")) ;
        clickAddToCartButton.click();

        WebDriverWait wait = new WebDriverWait(driver,2);

        By successfulMessagePath = By.xpath("//div[@class = 'alert alert-success alert-dismissible']//a[1]") ;
        wait.until(ExpectedConditions.visibilityOfElementLocated(successfulMessagePath));
        WebElement successfulMessage = driver.findElement(successfulMessagePath) ;



        softAssert.assertEquals(searchedForItem , successfulMessage.getText());
        driver.close();
        softAssert.assertAll();

    }
}
