package com.campos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthTest {

    WebDriver driver;
    SignUpPage signUpPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        signUpPage = new SignUpPage(driver);
    }

    @ParameterizedTest
    @CsvSource({"testname, testlastname, testemail%s@email.com, testpassword, 01/01/1998"})
    void testSuccessLogin(String name, String lastname, String email, String password, String birthdate) {
        //ARRANGE
        driver.get("https://teststore.automationtesting.co.uk/index.php");

        //ACT
        Random random = new Random();
        int randomNumber = 10000 + random.nextInt(90000);
        String formattedEmail = String.format(email, randomNumber);

        signUpPage.clickSignIn();
        signUpPage.clickSignUp();
        signUpPage.fillForm(name, lastname, formattedEmail, password, birthdate);
        signUpPage.submitForm();

        //ASSERT
        String[] loggedData = signUpPage.getLoggedUserName().split(" ");
        assertEquals(name, loggedData[0], "First name does not match.");
        assertEquals(lastname, loggedData[1], "Last name does not match.");
    }

    @AfterEach
    void finishTest() {
        signUpPage.logout();
        driver.close();
    }
}
