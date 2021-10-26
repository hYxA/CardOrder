package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class CardOrderTest {
    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
//        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
        chromedriver().setup();
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
//        String input = driver.findElement(By.className("paragraph")).getText();
        String actualText = driver.findElement(By.className("paragraph")).getText();
        String expectedText = "&nbsp; Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
    }
//
//    @Test
//    void shouldGet() {
//        driver.get("http://localhost:9999");
//        List<WebElement> textFields = driver.findElements(By.className("input__control"));
//        textFields.get(0).sendKeys("Сыпунькай");
//        textFields.get(1).sendKeys("+79012345678");
//        driver.findElement(By.className("checkbox__box")).click();
//        driver.findElement(By.tagName("button")).click();
////        String input = driver.findElement(By.className("paragraph")).getText();
//        String actualText = driver.findElement(By.className("paragraph")).getText();
//        String expectedText = "&nbsp; Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
//    }@Test
//    void shouldEnterTruNameAndPhone() {
//        driver.get("http://localhost:9999");
//        List<WebElement> textFields = driver.findElements(By.className("input__control"));
//        textFields.get(0).sendKeys("Сыпунькай");
//        textFields.get(1).sendKeys("+79012345678");
//        driver.findElement(By.className("checkbox__box")).click();
//        driver.findElement(By.tagName("button")).click();
////        String input = driver.findElement(By.className("paragraph")).getText();
//        String actualText = driver.findElement(By.className("paragraph")).getText();
//        String expectedText = "&nbsp; Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
//    }
}
