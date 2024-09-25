package com.campos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage {
    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignIn() {
        driver.findElement(By.xpath("//*[@id='_desktop_user_info']/div[contains(@class, 'user-info')]/a")).click();
    }

    public void clickSignUp() {
        driver.findElement(By.xpath("//*[@id='content']/div/a")).click();
    }

    public void fillForm(String name, String lastname, String email, String password, String birthdate) {
        driver.findElement(By.xpath("//*[@id='customer-form']//label[@for='field-id_gender-1']")).click();
        driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-firstname']")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-email']")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='customer-form']//input[@id='field-birthday']")).sendKeys(birthdate);
        driver.findElement(By.xpath("//*[@id='customer-form']//input[@name='psgdpr']")).click();
    }

    public void submitForm() {
        driver.findElement(By.xpath("//*[@id='customer-form']/footer/button")).click();
    }

    public String getLoggedUserName() {
        WebElement loggedNameUser = driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]//a[@class='account']//span"));
        return loggedNameUser.getText();
    }

    public void logout() {
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]//a[contains(@class, 'logout')]")).click();
    }
}
