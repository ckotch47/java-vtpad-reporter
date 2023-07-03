import com.sun.tools.javac.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DataForFilling;
import pages.MainPage;
import pages.RegistrationPage;

public class CreateSlotTest {
    private WebDriver driver;
    @Test
    @Deprecated
    public void createSlot()throws InterruptedException {
        //полный тест на создание слота и запись на консультацию и начало
        // консультации со стороны специалиста
        // (1 текст оповещает , была ли оформлена запись на открытый слот
        // 2 текст показывает была ли запущена консультация со стооны специалист)
        int tempDays = 0;
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectCalendar();
        mainPage.selectNextMonthOnCalendarFromSpecialist();
        tempDays = mainPage.searchEmptySlotFromSpecialist();
        mainPage.pushAddTimeForCreateConsultation();
        mainPage.pushSaveTimeForCreateConsultation();
        mainPage.logout();
        registrationPage.stepsOnAutorizationClient();
        mainPage.navigationSpecialist();
        mainPage.fillingDataSpecialist();
        Thread.sleep(4000);
        mainPage.clientSelectSpecialist();
        Thread.sleep(4000);
        mainPage.selectNextMonthOnCalendarFromClient();
        mainPage.searchEmptySlotFromClient();
        mainPage.makeAppoitmentByClient();
        Thread.sleep(2000);
        mainPage.finalMakeAppoitmentByclient();
        Thread.sleep(15000);
        mainPage.catchFrame();
        mainPage.selectBankCard();
        Thread.sleep(2000);
        mainPage.fillingDataCard();
        Thread.sleep(4000);
        mainPage.checkConfirmModal();
        driver.get(DataForFilling.deployUrl);
        mainPage.logout();
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectCalendar();
        mainPage.selectNextMonthOnCalendarFromSpecialist();
        mainPage.searchOpenSlotBySpecialist(tempDays);
        mainPage.pushButtonChat();
        mainPage.pushStartConsultation();
        mainPage.checkStartConsultation();
    }
    @Test
    public void endConsultation() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationSpecialist();

    }
}
