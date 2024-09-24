package com.campos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @CsvSource({"testname, testlastname, testemail%s@email.com, testpassword, 01/01/1998"})
    void testSuccessLogin(String name, String lastname, String email, String password, String birthdate) {
        //ARRANGE
        driver.get("https://teststore.automationtesting.co.uk/index.php");

        //ACT
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);

        WebElement signInButton = driver.findElement(By.xpath("//*[@id='_desktop_user_info']/div[contains(@class, 'user-info')]/a"));
        signInButton.click();

        WebElement signUpButton = driver.findElement(By.xpath("//*[@id='content']/div/a"));
        signUpButton.click();

        WebElement inputCheckSocialTitle = driver.findElement(By.xpath("//*[@id='customer-form']//div[contains(@class, 'form-control-valign')]//label[@for='field-id_gender-1']"));
        WebElement inputFirstName = driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-firstname']"));
        WebElement inputLastName = driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-lastname']"));
        WebElement inputEmail = driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-email']"));
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-password']"));
        WebElement inputBirthdate = driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-birthday']"));
        WebElement inputCheckTerms = driver.findElement(By.xpath("//*[@id='customer-form']//input[@name='psgdpr']"));
        WebElement saveButton = driver.findElement(By.xpath("//*[@id='customer-form']/footer/button"));

        inputCheckSocialTitle.click();
        inputFirstName.sendKeys(name);
        inputLastName.sendKeys(lastname);
        inputEmail.sendKeys(String.format(email, randomNumber));
        inputPassword.sendKeys(password);
        inputBirthdate.sendKeys(birthdate);
        inputCheckTerms.click();
        saveButton.click();

        //ASSERT
        WebElement loggedNameUser = driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]//a[@class='account']//span"));
        String[] loggedData = loggedNameUser.getText().split(" ");
        assertEquals(name, loggedData[0]);
        assertEquals(lastname, loggedData[1]);
    }

    @AfterEach
    void finishTest() {
        WebElement signOutButton = driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]//a[contains(@class, 'logout')]"));
        signOutButton.click();
        driver.close();
    }
}
