import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.OrderPage;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestOrderUpButton {
    private WebDriver driver;
    String userName;
    String userSurname;
    String userLocation;
    String userSubway;
    String userPhoneNumber;
    String userCalendar;
    String rentalPeriod;// срок аренды
    String color;
    String userComment;

    @BeforeClass
    public static void beforeClass(){
        WebDriverManager.chromedriver().setup();
    }

    public TestOrderUpButton (String userName,
                              String userSurname,
                              String userLocation,
                              String userSubway,
                              String userPhoneNumber,
                              String userCalendar,
                              String rentalPeriod,
                              String color,
                              String userComment) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userLocation = userLocation;
        this.userSubway = userSubway;
        this.userPhoneNumber = userPhoneNumber;
        this.userCalendar = userCalendar;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.userComment = userComment;
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {"Васян", "Пупкин", "Москва, улица Вязовка","Сокол","89997772233","23.12.2025","трое суток", "black", " "}
        };
    }

    @Test
    public void TestOrderUpButtonTest(){
        OrderPage orderPage = new OrderPage(driver);
        orderPage.closeCookie();
        driver.findElement(By.className("Button_Button__ra12g")).click();
        boolean succesOrder = orderPage.fillOrderForm(userName,
                userSurname,
                userLocation,
                userSubway,
                userPhoneNumber,
                userCalendar,
                rentalPeriod,
                color,
                userComment);
        assertTrue(succesOrder);
    }

    @After
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }
}
