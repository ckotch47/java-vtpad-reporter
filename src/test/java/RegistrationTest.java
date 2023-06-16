import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DataForFilling;
import pages.MainPage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(DataForFilling.deployUrl);
    }

    @Test
    public void checkAutorization() { //case 2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.waitElement();
        String actualText = mainPage.getWelcomeText();
        String expectedText = "Домашняя страница";
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void authorizationWithNonValideMail() { //case 2.1
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingWrongMail();
        registrationPage.fillingPasswordClient();
        registrationPage.pushNextButton();
        String actualError = registrationPage.getErrorMail();
        String expectedText = "Введите валидный E-mail";
        Assert.assertEquals(expectedText, actualError);
    }

    @Test
    public void authorizationWithRussianMail() { //case 2.2
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailRussianWords();
        registrationPage.fillingPasswordClient();
        registrationPage.pushNextButton();
        String actualError = registrationPage.getErrorMail();
        String expectedText = "Введите валидный E-mail";
        Assert.assertEquals(expectedText, actualError);
    }
    @Test
    public void authorizationWithMailWhosNoInDatabase(){ //case 2.3
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingMailNoInDatabase();
        registrationPage.fillingPasswordClient();
        registrationPage.pushNextButton();
        registrationPage.waitError();
        String actualError = registrationPage.getErrorForDatabase();
        String expectedText = "Неверный email или пароль";
        Assert.assertEquals(expectedText, actualError);
    }
    @Test
    public void authorizationWithFalsePassword(){ //case 2.4
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillingEmailClient();
        registrationPage.fillingFalsePassword();
        registrationPage.pushNextButton();
        registrationPage.waitError();
        String actualError = registrationPage.getErrorForDatabase();
        String expectedText = "Неверный email или пароль";
        Assert.assertEquals(expectedText, actualError);
    }
    @Test
    public void authorizationWithoutFillingFields(){ //case 2.5
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.pushNextButton();
        registrationPage.waitError();
        String actualError = registrationPage.getErrorForDatabase();
        String expectedText = "Поле необходимо заполнить";
        Assert.assertEquals(expectedText, actualError);
    }
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
        Assert.assertEquals(expectedError, actualError);
    }
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
        Assert.assertEquals(expectedError, actualError);
    }
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
        Assert.assertEquals(expectedError, actualError);
    }
    @After
    public void quit(){
        driver.quit();
    }
}
