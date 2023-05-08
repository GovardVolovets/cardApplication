package ru.netology.cardApplication;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class cardApplicationTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    public void positiveTestCorrectData() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void positiveTestOneLetterName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("И");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void positiveTestTwoLetterName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ли");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void positiveTestLongName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Пабло Диего Хосе Франсиско де Паула Хуан Непомусено Мария де лос Ремедиос Сиприяно де ла Сантисима Тринидад Мартир Патрико Клютас и Руис");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestNameEnglishLetters() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Harry Potter");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestNameSpecialCharacters() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер&Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestNameEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestNameOneSpace() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestNameTab() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("    ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestNameExoticLanguage() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("שלום");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneNoPlus() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("79095554433");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneOneSpace() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneTab() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("    ");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneNoNumber() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneTwelveDigits() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+790955544331");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneElevenDigits() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7909555443");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneLetters() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("qwerty");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneNumbersAndLetters() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7909555443q");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestPhoneNumbersAndSpecialCharacters() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7909555443&");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.tagName("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElements(By.className("input__sub")).get(1).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negativeTestNameConsentCheckboxNotSet() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Стивен Пол Гейгер-Торонто");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79095554433");

        driver.findElement(By.tagName("button")).click();

        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")).getText();

        Assertions.assertEquals(expected, actual);
    }
}
