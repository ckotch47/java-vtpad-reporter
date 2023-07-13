import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DataForFilling;
import pages.MainPage;
import pages.RegistrationPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.extension.ExtendWith;
import  reporter.TestResultLoggerExtension;

@ExtendWith(TestResultLoggerExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Тесты оплаты")
public class AppointmentTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
    }
    @Test
    public void searchSpecialist() throws InterruptedException{
        Thread.sleep(3000);//тест кейс номер 5
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        mainPage.searchRole();
        mainPage.pushSearch();
        String actualText = mainPage.getNameSpecialist();
        String expectedText = "Специалист";
        Assertions.assertEquals(expectedText, actualText);
    }
    @Test
    public void getEmptyList() throws InterruptedException {        //кейс 5.1
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        mainPage.searchRole();
        mainPage.pushSearch();
        Thread.sleep(3000);
        mainPage.pushCheckBoxFamily();
        Thread.sleep(8000);
        String actualQuantity = mainPage.checkQuantitySpecialist();
        String expectedQuantity = "0 специалистов";
        Assertions.assertEquals(expectedQuantity, actualQuantity);
    }
    @DisplayName("Проверка количества специалистов при вводе одного специалиста ")
    @Test
    public void searchSpecialistName() throws InterruptedException {       //кейс 5.2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        mainPage.navigationSpecialist();
        Thread.sleep(2000);
        mainPage.waitSpecialist();
        mainPage.fillingDataSpecialist();
        Thread.sleep(8000);
        String actualResult = mainPage.checkQuantitySpecialist();
        String expectedResult = "1 специалист";
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @DisplayName("проверка количества специлистов при вводе неимеющихся специалистов")
    @Test//case 5.3
    public void failedSearchSpecialist() throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        mainPage.navigationSpecialist();
        Thread.sleep(2000);
        mainPage.fillingDataWrongSpecialist();
        Thread.sleep(8000);
        String actualResult = mainPage.checkQuantitySpecialist();
        String expectedResult = "0 специалистов";
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @DisplayName("Проверка количестваа специалистов после очистки фильтра на странице специалистов")
    @Test //case 5.4
    public void clearSearchText() throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        mainPage.navigationSpecialist();
        Thread.sleep(2000);
        mainPage.fillingDataWrongSpecialist();
        Thread.sleep(4000);
        String actualResult = mainPage.checkQuantitySpecialist();
        String expectedResult = "0 специалистов";
        Assertions.assertEquals(expectedResult, actualResult);
        Thread.sleep(3000);
        mainPage.clickButtonClearFilter();
        Thread.sleep(3000);
        String actualResultAfterClearFilter = mainPage.checkQuantitySpecialist();
        String expectedResultAfterClearFilter = "0 специалистов";
        Assertions.assertNotEquals(actualResultAfterClearFilter, expectedResultAfterClearFilter);
    }
    @AfterEach
    public void quit(){
        driver.close();
    }
}
