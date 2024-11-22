import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.MainPage;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainPageTestText {
    private WebDriver driver;
    String expectedText;
    int textIndex;
    String expectedQuestion;

    @BeforeClass
    public static void beforeClass(){
        WebDriverManager.chromedriver().setup();
    }

    public MainPageTestText (int textIndex, String expectedQuestion, String expectedText) {
        this.expectedQuestion = expectedQuestion;
        this.expectedText = expectedText;
        this.textIndex = textIndex;
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {0,"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1,"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."}
        };
    }
    @Test
    public void textListTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookie();
        String actualText = mainPage.getText(textIndex, expectedQuestion);
        assertEquals(expectedText, actualText);
    }


    @After
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }
}
