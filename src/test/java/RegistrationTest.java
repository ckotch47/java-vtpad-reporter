import api.UserApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DataForFilling;
import pages.RegistrationPage;
import org.junit.jupiter.api.extension.ExtendWith;
import reporter.TestResultLoggerExtension;
import static org.apache.hc.core5.http.HttpStatus.*;


@ExtendWith(TestResultLoggerExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver  = new ChromeDriver(options);
        driver.get(DataForFilling.registrationUrl);
    }
    @Test
    @DisplayName("Создание пользователя сс правильным кодом из смс")
    public void registration()throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailNoInDatabase();
        registrationPage.fillingPhoneForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingPasswordMobileForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingNewPassword(DataForFilling.passwordForRegistration);
        registrationPage.fillingConfirmPassword(DataForFilling.passwordForRegistration);
        registrationPage.pushButtonConfirm();
        boolean actualDisplayLogin = registrationPage.checkLoginDisplay();
        Assertions.assertTrue(actualDisplayLogin, "После регистрации возврат на страницу входа не произошел, или регистрация не прошлаа");
    }

    @Test
    @DisplayName("Получение ошибки паролей , в создании пользователя, при условии ввода в пароле только цифр")
    public void registrationWithNotSafePassword()throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailNoInDatabase();
        registrationPage.fillingPhoneForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingPasswordMobileForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingNewPassword(DataForFilling.notSavePassword);
        registrationPage.fillingConfirmPassword(DataForFilling.notSavePassword);
        String actualMessageAfterFillingPassword = registrationPage.getTextMessageAfterFillingPassword();
        String expectMessageAfterFillingPassword = "Минимум 1 прописной символ";
        Assertions.assertEquals(expectMessageAfterFillingPassword, actualMessageAfterFillingPassword , "Текст ошибки не совпадает");
    }

    @Test
    @DisplayName("Получение ошибки паролей , в создании пользователя, при условии ввода в пароле только цифр")
    public void registrationWithNotConcidiencePasswords()throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailNoInDatabase();
        registrationPage.fillingPhoneForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingPasswordMobileForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingNewPassword(DataForFilling.notSavePassword);
        registrationPage.fillingConfirmPassword("12345667");
        registrationPage.pushButtonConfirm();
        boolean actualDisplayErrorCoincidence = registrationPage.checkDisplayErrorCoincidence();
        String actualErrorCoincidence = registrationPage.getTextErrorCoincidence();
        String expectedErrorCoincidence = "Пароли должны совпадать";
        Assertions.assertTrue(actualDisplayErrorCoincidence , "Ошибка не появилась");
        Assertions.assertEquals(expectedErrorCoincidence, actualErrorCoincidence , "Текст ошибки не совпадает");
    }

    @Test
    @DisplayName("ввод пароля меньше 8 символов при регистрации")
    public void registrationWithSevenSymbols() throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailNoInDatabase();
        registrationPage.fillingPhoneForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingPasswordMobileForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingNewPassword("12345");
        registrationPage.fillingConfirmPassword("12345");
        String actualMessageAfterFilling = registrationPage.getTextMessageAfterFillingPassword();
        String expectedMessageAfterFilling = "Минимум 8 символов";
        Assertions.assertEquals(expectedMessageAfterFilling, actualMessageAfterFilling , "Текст ошибки не совпадает");
    }

    @Test
    @DisplayName("Ввод надежного пароля")
    public void registrationWithSafePassword() throws InterruptedException{
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailNoInDatabase();
        registrationPage.fillingPhoneForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingPasswordMobileForRegistration();
        registrationPage.pushNextButtonInRegistration();
        registrationPage.fillingNewPassword(DataForFilling.passwordForRegistration);
        registrationPage.fillingConfirmPassword(DataForFilling.passwordForRegistration);
        String actualMessageAfterFilling = registrationPage.getTextMessageAfterFillingPassword();
        String expectedMessageAfterFilling = "Надежный пароль";
        Assertions.assertEquals(expectedMessageAfterFilling, actualMessageAfterFilling , "Текст ошибки не совпадает");
    }

    @AfterEach
    public void quit(){
        driver.quit();
    }

    @AfterEach
    @DisplayName("Удаление пользователя")
    public void deleteUser() throws InterruptedException{
        UserApi userApi = new UserApi();
        Response response = UserApi.loginUser(DataForFilling.emailNotUsedInData, DataForFilling.passwordForRegistration);
        if (response.statusCode() != SC_CREATED){
            System.out.println("Уже удален, либо не создан");
        }
        else {
            String accessToken = response.then().extract().path("accessToken").toString();
            Response response1 = UserApi.getProfile("bearer " + accessToken);
            response1.then().statusCode(SC_OK);
            String idProfile = response1.then().extract().path("user.id").toString();
            Response response2 = UserApi.deleteProfile(idProfile, "bearer "+ accessToken);
            response2.then().statusCode(SC_OK);
            System.out.println("пользователь удалился");
        }

    }
}
