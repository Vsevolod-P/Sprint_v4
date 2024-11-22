package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class OrderPage extends BasePage {

    @FindBy(id = "rcc-confirm-button")
    private WebElement cookies;

    private String subwayLink = ".//div[text()='%s']";
    private String rentalPeriodLink = ".//div[(@class='Dropdown-option') and (text()='%s')]";

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    // Находим и закрываем куки
    public void closeCookie() {
        cookies.click();
    }
    // Основной тест заполнения формы
    public boolean fillOrderForm (String userName,
                                 String userSurname,
                                 String userLocation,
                                 String userSubway,
                                 String userPhoneNumber,
                                 String userCalendar,
                                 String rentalPeriod,
                                 String color,
                                 String userComment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@placeholder='* Имя']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@placeholder='* Фамилия']")).sendKeys(userSurname);
        driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(userLocation);
        driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(userPhoneNumber);
        driver.findElement(By.xpath("//input[@placeholder='* Станция метро']")).sendKeys(userSubway);
        driver.findElement(By.xpath(String.format(subwayLink, userSubway))).click();
        driver.findElement(By.xpath("//div[@class='Order_NextButton__1_rCA']/button")).click();
        driver.findElement(By.xpath("//input[@placeholder='* Когда привезти самокат']")).sendKeys(userCalendar);
        driver.findElement(By.xpath("//span[@class='Dropdown-arrow']")).click();
        driver.findElement(By.xpath(String.format(rentalPeriodLink, rentalPeriod))).click();
        driver.findElement(By.id(color)).click();
        driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']")).sendKeys(userComment);
        driver.findElement(By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']")).click();
        driver.findElement(By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Да']")).click();
        WebElement succesPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']")));
        return succesPage.isDisplayed();


    }
}