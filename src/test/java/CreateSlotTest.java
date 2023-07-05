import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DataForFilling;
import pages.MainPage;
import pages.RegistrationPage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateSlotTest {
    private static WebDriver driver;

    @BeforeAll
    @DisplayName("полный тест на создание слота и запись на консультацию и начало консультации со стороны специалиста (1 текст оповещает , была ли оформлена запись на открытый слот 2 текст показывает была ли запущена консультация со стооны специалист")
    public static void createSlot() throws InterruptedException {
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
        mainPage.clientSelectSpecialist();
        mainPage.selectNextMonthOnCalendarFromClient();
        mainPage.searchEmptySlotFromClient();
        mainPage.makeAppoitmentByClient();
        mainPage.finalMakeAppoitmentByclient();
        mainPage.catchFrame();
        mainPage.selectBankCard();
        mainPage.fillingDataCard();
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

    private WebDriver driver1;
    @DisplayName("Создание двух окон для проверки аудио-звонка от специалиста ")
    @Test
    public void twoWindwowAudio() throws InterruptedException{//7.5
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        driver1 = new ChromeDriver(options);
        driver1.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage1 = new MainPage(driver1);
        registrationPage.stepsOnAutorizationClient();
        registrationPage1.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        mainPage1.selectConsultationOnMainPage();
        mainPage1.selectAudioConsultation();
        mainPage.waitIncomingCall();
        Boolean actual = mainPage.checkDisplayed();
        Boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }
    @DisplayName("Создание двух окон для проверки видео-звонка от специалиста ")
    @Test
    public void twoWindwowVideo() throws InterruptedException {// 7.3
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--allow-file-access");
        options.addArguments("--allow-file-access-from-files");
        options.addArguments("--autoplay-policy=no-user-gesture-required");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        driver1 = new ChromeDriver(options);
        driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver1.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage1 = new MainPage(driver1);
        registrationPage.stepsOnAutorizationClient();
        registrationPage1.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        mainPage1.selectConsultationOnMainPage();
        mainPage1.selectVideoConsultation();
        mainPage.waitIncomingCall();
        Boolean actual = mainPage.checkDisplayed();
        Boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }
    @DisplayName("Создание двух окон для проверки аудио-звонка от специалиста  с заранее отключенным микрофоном")
    @Test
    public void twoWindowAudioWithoutMicrophone() throws InterruptedException {//7.8
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        driver1 = new ChromeDriver(options);
        driver1.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage1 = new MainPage(driver1);
        registrationPage.stepsOnAutorizationClient();
        registrationPage1.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        mainPage1.selectConsultationOnMainPage();
        mainPage1.selectAudioConsultation();
        mainPage.acceptCall();
        mainPage.checkDisableCallClient();
        mainPage1.checkDisableCallSpecialist();
    }

    @DisplayName("Создание двух окон для проверки расширяемости модального окна звонка при аудио-звонке")
    @Test
    public void sizeWindow() throws InterruptedException{//7.9
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        driver1 = new ChromeDriver(options);
        driver1.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage1 = new MainPage(driver1);
        registrationPage.stepsOnAutorizationClient();
        registrationPage1.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        mainPage1.selectConsultationOnMainPage();
        mainPage1.selectAudioConsultation();
        mainPage.waitIncomingCall();
        int beforeExpand = (mainPage1.getSizeModalCall().getX());
        mainPage1.pushExpandeModal();
        int afterExpand = (mainPage1.getSizeModalCall().getX());
        if(beforeExpand != afterExpand){
            System.out.println("Разворот модального окна произошел");
        }
        else System.out.println("Разворот модального окна не произошел");
    }
    @DisplayName("Проверка сброса аудиозвонка от клиента")
    @Test
    public void cancelAudioCall()throws InterruptedException{//7.6
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        driver1 = new ChromeDriver(options);
        driver1.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage1 = new MainPage(driver1);
        registrationPage.stepsOnAutorizationClient();
        registrationPage1.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        mainPage1.selectConsultationOnMainPage();
        mainPage1.selectAudioConsultation();
        mainPage.cancelIncomingCall();
        mainPage.checkDisableCallClient();
        mainPage1.checkDisableCallSpecialist();
    }

    @DisplayName("Проверка сброса видео-звонка от клиента")
    @Test
    public void cancelVideoCall() throws InterruptedException{//7.7
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        driver1 = new ChromeDriver(options);
        driver1.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage1 = new MainPage(driver1);
        registrationPage.stepsOnAutorizationClient();
        registrationPage1.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        mainPage1.selectConsultationOnMainPage();
        mainPage1.selectVideoConsultation();
        mainPage.cancelIncomingCall();
        mainPage.checkDisableCallClient();
        mainPage1.checkDisableCallSpecialist();
    }
    @DisplayName("Написание привет в чате от специалиста и проверка прихода сообщения клиенту")
    @Test//6.1
    public void sayHelloInChatBySpecialist() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageBySpecialist();
        mainPage.selectConsultationOnMainPage();
        mainPage.fiilingHelloInChat(DataForFilling.messageForSendInConsultation);
        mainPage.pushSendTextInChat();
        mainPage.logout();
        registrationPage.stepsOnAutorizationClient();
        mainPage.selectConsultationOnMainPage();
        String actualText = mainPage.getFirstMessageInChat();
        String expectedText = DataForFilling.messageForSendInConsultation;
        Assertions.assertEquals(expectedText, actualText);
        Thread.sleep(5000);
        driver.quit();
    }
    @DisplayName("Написание привет в чате от клиента и проверка прихода сообщения специалисту")
    @Test//case 7.1
    public void sayHelloByClient() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage.fiilingHelloInChat(DataForFilling.messageForSendInConsultation);
        mainPage.pushSendTextInChat();
        mainPage.logout();
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectConsultationOnMainPage();
        String actualText = mainPage.getFirstMessageInChat();
        String expectedText = DataForFilling.messageForSendInConsultation;
        Assertions.assertEquals(expectedText, actualText);
    }
    @DisplayName("Написание привет в чате и отправка изображения от специалиста и проверка прихода сообщения клиенту")
    @Test
    public void checkUploadFileSpecialist() throws InterruptedException {//6.2
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageBySpecialist();
        mainPage.selectConsultationOnMainPage();
        mainPage.pushPaperClipButton();
        mainPage.uploadFile();
        mainPage.addTextInModalMessageWithImage(DataForFilling.messageForSendConsultationWithImage);
        mainPage.submitUpload();
        mainPage.logout();
        registrationPage.stepsOnAutorizationClient();
        mainPage.selectConsultationOnMainPage();
        boolean a = mainPage.checkDisplayedImage();
        if (mainPage.getFirstMessageInChat().equals(DataForFilling.messageForSendConsultationWithImage) && a ){
            System.out.println("Добавлено изображение");
        }
        else System.out.println("Изображение не добавлено");
    }

    @DisplayName("Написание привет в чате и отправка изображения от клиента и проверка прихода сообщения специалисту")
    @Test
    public void checkUploadFileClient()throws InterruptedException {//7.2
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationClient();
        mainPage.selectPeriodOnMainPageByClient();
        mainPage.selectConsultationOnMainPage();
        mainPage.pushPaperClipButton();
        mainPage.uploadFile();
        mainPage.addTextInModalMessageWithImage(DataForFilling.messageForSendConsultationWithImage);
        mainPage.submitUpload();
        mainPage.logout();
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectConsultationOnMainPage();
        boolean a = mainPage.checkDisplayedImage();
        if (mainPage.getFirstMessageInChat().equals(DataForFilling.messageForSendConsultationWithImage) && a ){
            System.out.println("Добавлено изображение");
        }
        else System.out.println("Изображение не добавлено");
    }
    @DisplayName("Завершение консультации со стороны специалиста")
    @AfterAll
    public static void endConsultation() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MainPage mainPage = new MainPage(driver);
        registrationPage.stepsOnAutorizationSpecialist();
        mainPage.selectPeriodOnMainPageBySpecialist();
        mainPage.selectConsultationOnMainPage();
        mainPage.endConsultation();
        mainPage.selectTematicsForEndConsultation();
        mainPage.approveEndConsultation();
        mainPage.checkEndConsultation();
        driver.close();
    }

    @AfterEach
    public void quit(){
        driver.close();
        driver1.close();
    }
}
