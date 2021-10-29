package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class CardOrderTest {
    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldEnterTruNameAndPhone() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(By.className("input__control"));
        textFields.get(0).sendKeys("Сыпунькай");
        textFields.get(1).sendKeys("+79012345678");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        String actualText = driver.findElement(By.className("paragraph")).getText();
        String expectedText = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    void shouldGetErrorByName() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(By.className("input__control"));
        textFields.get(0).sendKeys("Сыпунькай123");
        textFields.get(1).sendKeys("+79012345678");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        List<WebElement> textFields2 = driver.findElements(By.className("input__sub"));

        String actualText = textFields2.get(0).getText();
        String expectedText = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";

        Assertions.assertEquals(expectedText, actualText);

    }

//    @Test
//    void shouldGetErrorByNameWithCSS() {
//        driver.get("http://localhost:9999");
//        List<WebElement> textFields = driver.findElements(By.className("input__control"));
//        textFields.get(0).sendKeys("Сыпунькай123");
//        textFields.get(1).sendKeys("+79012345678");
//        driver.findElement(By.className("checkbox__box")).click();
//        driver.findElement(By.tagName("button")).click();
//
//        String actualText = driver.findElement(By.cssSelector("[type='input_invalid']")).getText();
//        String expectedText = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
//
//        Assertions.assertEquals(expectedText, actualText);
//
//    }

    @Test
    void shouldGetErrorByPhone() {
        driver.get("http://localhost:9999");
        List<WebElement> textFields = driver.findElements(By.className("input__control"));
        textFields.get(0).sendKeys("Сыпунькай");
        textFields.get(1).sendKeys("+7901df45678");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.tagName("button")).click();
        List<WebElement> textFields2 = driver.findElements(By.className("input__sub"));
        String actualText = textFields2.get(1).getText();
        String expectedText = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";

        Assertions.assertEquals(expectedText, actualText);
    }
}
