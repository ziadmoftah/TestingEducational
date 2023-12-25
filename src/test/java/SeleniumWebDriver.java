import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SeleniumWebDriver {
    @Test ( enabled = false)
    public void test1(){
        System.setProperty("webdriver.chrome.driver" , System.getProperty("user.dir")+"/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver() ;
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
    }
    @Test ( enabled = false)
    public void test2(){
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver() ;
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
    }
    @Test (enabled = false)
    public void test3(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver() ;
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
    }

    @Test ( enabled = false)
    public void test4(){
        SoftAssert softAssert = new SoftAssert() ;
        String currentUrl , currentTitle ;
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver() ;
        driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com");
        driver.navigate().to("https://www.youtube.com/");
        driver.navigate().back();
        softAssert.assertEquals(driver.getCurrentUrl() , "https://www.youtube.com/" , "not youtube");

        //System.out.println(driver.getCurrentUrl() + "-----" + driver.getTitle());
        driver.navigate().forward();
        //System.out.println(driver.getCurrentUrl() + "-----" + driver.getTitle());
        softAssert.assertEquals(driver.getCurrentUrl() , "https://www.youtube.com/" , "not youtube");
        //driver.navigate().refresh();
        driver.close();
        softAssert.assertAll();


    }

    @Test (enabled = false)
    public void test5(){
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver() ;
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        WebElement searchTextBox = driver.findElement(By.id("small-searchterms")) ;
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[2]/form/button")) ;
        searchTextBox.click();
        searchTextBox.sendKeys("iphone");
        searchButton.click();
        WebElement searchResultMessage = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div[2]/div/div[2]/div")) ;
        Assert.assertEquals(searchResultMessage.getText() , "No products were found that matched your criteria.", "search message wrong");
        //driver.navigate().back();
        driver.close();
    }

    @Test
    public void test6(){
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver() ;
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        WebElement searchTextBox = driver.findElement(By.xpath("//input[@id = 'small-searchterms']")) ;
        WebElement searchButton = driver.findElement(By.xpath("//button[text() = 'Search']")) ;
        searchTextBox.click();
        searchTextBox.sendKeys("iphone");
        searchButton.click();
        WebElement searchResultMessage = driver.findElement(By.xpath("//div[@class = 'no-result']")) ;
        Assert.assertEquals(searchResultMessage.getText() , "No products were found that matched your criteria.", "search message wrong");
        //driver.navigate().back();
        //driver.close();
    }

}
