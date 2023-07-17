import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DataForFilling;
import pages.MainPage;
import pages.RegistrationPage;

import org.junit.jupiter.api.extension.ExtendWith;
import  reporter.TestResultLoggerExtension;

@ExtendWith(TestResultLoggerExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Авторизация")
public class AutorizationTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver  = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
    }

    @DisplayName("Проверка авторизации пользователя")
    @Test
    public void checkAutorization() throws InterruptedException { //case 2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        String actualText = mainPage.getWelcomeText();
        String expectedText = "Домашняя страница";
        Assertions.assertEquals(expectedText, actualText);
    }

    @DisplayName("Проверка авторизации пользователя с невалидным мэйлом")
    @Test
    public void authorizationWithNonValideMail() { //case 2.1
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingWrongMail();
        registrationPage.fillingPasswordClient();
        registrationPage.pushNextButton();
        String actualError = registrationPage.getErrorMail();
        String expectedText = "Введите валидный E-mail";
        Assertions.assertEquals(expectedText, actualError);
    }

    @DisplayName("Проверка авторизации пользователя с мэйлом в котором содержится кириллица")
    @Test
    public void authorizationWithRussianMail() { //case 2.2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailRussianWords();
        registrationPage.fillingPasswordClient();
        registrationPage.pushNextButton();
        String actualError = registrationPage.getErrorMail();
        String expectedText = "Введите валидный E-mail";
        Assertions.assertEquals(expectedText, actualError);
    }
    @DisplayName("Проверка авторизации пользователя с мэйлом которого нет в базе")
    @Test
    public void authorizationWithMailWhosNoInDatabase()throws InterruptedException{ //case 2.3
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailNoInDatabase();
        registrationPage.fillingPasswordClient();
        registrationPage.pushNextButton();
        registrationPage.waitError();
        String actualError = registrationPage.getErrorForDatabase();
        String expectedText = "Неверный email или пароль";
        Assertions.assertEquals(expectedText, actualError);
    }
    @DisplayName("Проверка авторизации пользователя с неправильным паролем")
    @Test
    public void authorizationWithFalsePassword(){ //case 2.4
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingEmailClient();
        registrationPage.fillingFalsePassword();
        registrationPage.pushNextButton();
        registrationPage.waitError();
        String actualError = registrationPage.getErrorForDatabase();
        String expectedText = "Неверный email или пароль";
        Assertions.assertEquals(expectedText, actualError);
    }
    @DisplayName("Проверка авторизации пользователя без заполнения полей")
    @Test
    public void authorizationWithoutFillingFields(){ //case 2.5
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.pushNextButton();
        registrationPage.waitError();
        String actualError = registrationPage.getErrorForDatabase();
        String expectedText = "Поле необходимо заполнить";
        Assertions.assertEquals(expectedText, actualError);
    }
    @DisplayName("Проверка регистрации пользователя с мэйлом который уже имеется наа платформе")
    @Test
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
        Assertions.assertEquals(expectedError, actualError);
    }

    @DisplayName("Проверка регистрации пользователя с телефоном который имеется в базе")
    @Test
    public void registrationWithUsedPhone() throws InterruptedException{  //кейс 1.2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitButtonRegistration();
        Thread.sleep(1000);
        registrationPage.pushRegistrationForm();
        Thread.sleep(1000);
        registrationPage.findEmail();
        Thread.sleep(1000);
        registrationPage.fillingMailNoInDatabase();
        Thread.sleep(1000);
        registrationPage.fillingPhoneInBase();
        Thread.sleep(1000);
        registrationPage.pushNextButtonInRegistration();
        registrationPage.waitErrorMobile();
        String actualError = registrationPage.getErrorMobile();
        String expectedError = "Данный номер телефона уже используется";
        Assertions.assertEquals(expectedError, actualError);
    }
    @DisplayName("Проверка регистрации пользователя при вводе неправильного кода из смс ")
    @Test
    public void fillingFalsePassword() throws InterruptedException {  //кейс 1.4
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitButtonRegistration();
        Thread.sleep(1000);
        registrationPage.pushRegistrationForm();
        Thread.sleep(1000);
        registrationPage.findEmail();
        Thread.sleep(1000);
        registrationPage.fillingMailNoInDatabase();
        Thread.sleep(1000);
        registrationPage.fillingPhone();
        Thread.sleep(1000);
        registrationPage.pushNextButtonInRegistration();
        registrationPage.waitFieldPassword();
        registrationPage.fillingWrongMobilePassword();
        registrationPage.pushNextInCodeAcceptMobile();
        registrationPage.waitErrorPasswordMobile();
        String actualError = registrationPage.getErrorPasswordMobile();
        String expectedError = "Неверный код";
        Assertions.assertEquals(expectedError, actualError);
    }
    @Test
    public void registrationUserByAdministrator() throws InterruptedException{//case 3 , заполняется только ФИО mail и номер телефона
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        Thread.sleep(1000);
        registrationPage.stepsOnAutorizationAdmin();
        Thread.sleep(1000);
        mainPage.pushButtonUsersAdmin();
        Thread.sleep(3000);
        mainPage.pushCreateUserAdmin();
        Thread.sleep(3000);
        mainPage.stepsForCreateUserByAdmin();
        Thread.sleep(1000);
        mainPage.checkConfirmModalForCreateUserByAdmin();
    }
    @AfterEach
    public void quit(){
        driver.close();
    }
}
