import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class AccountRegistration {
    SoftAssert softAssert ;
    WebDriver driver ;

    @BeforeClass
    public void setUpDriver(){
        WebDriverManager.firefoxdriver().setup();
    }
    @Test
    public void registrationOfNewUserTest(){
        softAssert = new SoftAssert() ;
        driver =  new FirefoxDriver() ;
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

        //Navigate to the registration page
        WebElement registerIcon = driver.findElement(By.xpath("//a[@class = 'ico-register']")) ;
        registerIcon.click();

        //locate user data fields
            // gender radio buttons
        WebElement genderRadioButtonGroup = driver.findElement(By.id("gender")) ;
        List<WebElement> genderRadioButtons = genderRadioButtonGroup.findElements(By.name("Gender")) ;

        WebElement firstNameInput = driver.findElement(By.xpath("//input[@id = 'FirstName']")) ;
        WebElement secondNameInput = driver.findElement(By.xpath("//input[@id = 'LastName']")) ;
            // Birthdate picking
        WebElement datePickerGroup = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/div[2]/div[4]/div")) ;
        WebElement dateOfBirthDayList = datePickerGroup.findElement(By.name("DateOfBirthDay"));
        WebElement dateOfBirthMonthList = datePickerGroup.findElement(By.name("DateOfBirthMonth")) ;
        WebElement dateOfBirthYearList = datePickerGroup.findElement(By.name("DateOfBirthYear")) ;

        WebElement newsLetterCheckBox = driver.findElement(By.id("Newsletter")) ;
        WebElement emailInput = driver.findElement(By.xpath("//input[@id = 'Email']")) ;
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id = 'Password']"));
        WebElement confirmPasswordInput = driver.findElement(By.xpath("//input[@id = 'ConfirmPassword']"));
        WebElement registerButton = driver.findElement(By.xpath("//button[@id ='register-button']")) ;


        // fill in the user data fields
        genderRadioButtons.get(0).click();  // male
        firstNameInput.sendKeys("Ziad");
        secondNameInput.sendKeys("Moftah");

            // select a birthdate
        Select selectDate = new Select(dateOfBirthDayList) ;
        selectDate.selectByValue("1");
        selectDate = new Select(dateOfBirthMonthList);
        selectDate.selectByVisibleText("January");
        selectDate = new Select(dateOfBirthYearList) ;
        selectDate.selectByValue("2000");

        if ( newsLetterCheckBox.isSelected()){
            newsLetterCheckBox.click();
        }
        emailInput.sendKeys("hsffh@gmail.com");
        passwordInput.sendKeys("123456789");
        confirmPasswordInput.sendKeys("123456789");

        //click registration
        registerButton.click();


        try {
            // locating a successMessage
            WebElement successMessage = driver.findElement(By.className("result")) ;
            softAssert.assertEquals(successMessage.getText() , "Your registration completed", "Registration was not successful");
        }
        catch ( Exception e){
            softAssert.fail("Registration was not successful");
        }

        softAssert.assertAll();

        driver.quit();

    }
}
