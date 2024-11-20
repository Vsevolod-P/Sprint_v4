package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MainPage extends BasePage {

    @FindBy(id = "rcc-confirm-button")
    private WebElement cookies;

     private String textLink = ".//div[@id='accordion__panel-%d']/p";
    // private String buttonLink = ".//div[@id='accordion__heading-%d']";
     private String expectedQuestionLink = ".//div[text()='%s']";

    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    // Находим и закрываем куки
    public void closeCookie(){
        cookies.click();
    }

    public String getText(int textIndex, String expectedQuestion){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = driver.findElement(By.xpath(String.format(expectedQuestionLink, expectedQuestion)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(textLink, textIndex))));
        return textElement.getText();
    }
}
