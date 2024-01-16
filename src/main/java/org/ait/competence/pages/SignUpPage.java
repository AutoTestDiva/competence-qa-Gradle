package org.ait.competence.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id=':r1:']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id=':r3:']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id=':r5:']")
    WebElement repeatPasswordField;

    @FindBy(xpath = "//button[text()='Register']")
    WebElement registerButton;

    public ProfilePage signUp(String email, String password, String repeatPassword) {
        //typeWithJSExecutor(emailField, email, 0, 200);
        type(emailField, email);
        type(passwordField, password);
        type(repeatPasswordField, repeatPassword);
        click(registerButton);
        return new ProfilePage(driver);
    }

    public SignUpPage signUpNegative(String email, String password, String repeatPassword) {
        type(emailField, email);
        //type(passwordField, password);
        //type(repeatPasswordField, repeatPassword);
        //click(registerButton);
        return this;
    }

    public SignUpPage verifyErrorMessageIsPresent() {
        new ProfilePage(driver).isTextPresent(errorMessage,"E-mail is not valid");
        return this;
    }


}
