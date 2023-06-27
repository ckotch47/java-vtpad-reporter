package ru.ponimayu.uitest;

import org.junit.jupiter.api.AfterAll;
import ru.vtpad.reporter.TestResultLoggerExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.ponimayu.uitest.pages.DataForFilling;
import ru.ponimayu.uitest.pages.MainPage;
import ru.ponimayu.uitest.pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestResultLoggerExtension.class)
@DisplayName("Тесты регистрации")
public class RegistrationTest {
    private WebDriver driver;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver  = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.get(DataForFilling.deployUrl);
    }



    @Test
    @DisplayName("registrationWithUsedEmail регистрация с зареганным мылом")
    public void registrationWithUsedEmail() throws InterruptedException { //кейс 1.1
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitButtonRegistration();
        Thread.sleep(1000);
        registrationPage.pushRegistrationForm();
        Thread.sleep(1000);
        registrationPage.findEmail();
        Thread.sleep(1000);
        registrationPage.fillingEmailClient();
        Thread.sleep(1000);
        registrationPage.fillingPhone();
        Thread.sleep(1000);
        registrationPage.pushNextButtonInRegistration();
        registrationPage.waitError();
        String actualError = registrationPage.getError();
        String expectedError = "Данный E-mail уже используется";
        assertEquals(expectedError, actualError);
    }

    @Test
    @DisplayName("checkAutorization что бы это не значило")
    public void checkAutorization() { //case 2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        String actualText = mainPage.getWelcomeText();
        String expectedText = "Домашняя страница";
        assertEquals(expectedText, actualText);
    }

    @AfterAll
    public void close(){this.driver.close();}

}
