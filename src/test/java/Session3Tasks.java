import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Session3Tasks {
    WebDriver driver ;
    SoftAssert softAssert ;

    @BeforeClass
    public void setupFirefox(){
        WebDriverManager.firefoxdriver().setup();
    }


    @Test
    public void ValidateGoogleSearchOutputLinks(){
        driver = new FirefoxDriver() ;
        softAssert = new SoftAssert() ;
        WebDriverWait wait = new WebDriverWait(driver,20) ;

        String WebsitUrl = "https://www.google.com/" ;
        driver.get(WebsitUrl);


        String []searchTerm = {"Animal","Bird"}  ;
        WebElement searchButton ;
        WebElement searchBox;
        By searchResultsStatusId = By.id("result-stats") ;
        By searchBoxId = By.id("APjFqb") ;
        Actions actions = new Actions(driver) ;



        searchBox = driver.findElement(searchBoxId);
        searchBox.sendKeys(searchTerm[0] + Keys.ENTER);


        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsStatusId)) ;
        }
        catch (Exception e){
            softAssert.fail("Number of results did not show up on page");
        }
        searchBox = driver.findElement(searchBoxId) ;
        searchBox.clear();
        searchBox.sendKeys(searchTerm[1]);
        searchButton = driver.findElement(By.xpath("//div[@class= 'zgAlFc']//span")) ;
        searchButton.click();

        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsStatusId)) ;
        }
        catch (Exception e){
            softAssert.fail("Number of results did not show up on page");
        }



        //get to the end of the page
        while ( !driver.findElement(By.xpath("//div[@id= 'fbarcnt']")).isDisplayed() ){
            actions.keyDown(Keys.LEFT_CONTROL).sendKeys(Keys.END).keyUp(Keys.ALT).build().perform();
        }


        softAssert.assertAll();
        driver.quit();


    }
}
