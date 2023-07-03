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

public class TwoWindowTests {
    private WebDriver driver;
    private WebDriver driver1;
    @Test
    public void twoWindwowAudio() throws InterruptedException{//7.5
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        Thread.sleep(5000);
        mainPage.selectPeriodOnMainPageBySpecialist();
        Thread.sleep(4000);
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageByClient();
        Thread.sleep(4000);
        mainPage1.selectConsultationOnMainPage();
        Thread.sleep(4000);
        mainPage1.selectAudioConsultation();
        mainPage.waitIncomingCall();
        Boolean actual = mainPage.checkDisplayed();
        Boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }
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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        Thread.sleep(5000);
        mainPage.selectPeriodOnMainPageByClient();
        Thread.sleep(4000);
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        Thread.sleep(4000);
        mainPage1.selectConsultationOnMainPage();
        Thread.sleep(4000);
        mainPage1.selectVideoConsultation();
        mainPage.waitIncomingCall();
        Boolean actual = mainPage.checkDisplayed();
        Boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void twoWindowAudioWithoutMicrophone() throws InterruptedException {//7.8
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(DataForFilling.deployUrl);
        driver1 = new ChromeDriver(options);
        driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver1.get(DataForFilling.deployUrl);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
        MainPage mainPage = new MainPage(driver);
        MainPage mainPage1 = new MainPage(driver1);
        registrationPage.stepsOnAutorizationClient();
        Thread.sleep(2000);
        registrationPage1.stepsOnAutorizationSpecialist();
        Thread.sleep(5000);
        mainPage.selectPeriodOnMainPageByClient();
        Thread.sleep(4000);
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        Thread.sleep(4000);
        mainPage1.selectConsultationOnMainPage();
        Thread.sleep(3000);
        mainPage1.selectAudioConsultation();
        mainPage.acceptCall();
        Thread.sleep(2000);
        mainPage.checkDisableCallClient();
        mainPage1.checkDisableCallSpecialist();
    }

    @Test
    public void sizeWindow() throws InterruptedException{//7.9
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        Thread.sleep(5000);
        mainPage.selectPeriodOnMainPageByClient();
        Thread.sleep(4000);
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        Thread.sleep(4000);
        mainPage1.selectConsultationOnMainPage();
        Thread.sleep(4000);
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
    @Test
    public void cancelAudioCall()throws InterruptedException{//7.6
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        Thread.sleep(4000);
        mainPage.selectPeriodOnMainPageByClient();
        Thread.sleep(4000);
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        Thread.sleep(4000);
        mainPage1.selectConsultationOnMainPage();
        Thread.sleep(3000);
        mainPage1.selectAudioConsultation();
        Thread.sleep(2000);
        mainPage.cancelIncomingCall();
        mainPage.checkDisableCallClient();
        mainPage1.checkDisableCallSpecialist();
        Thread.sleep(6000);
    }

    @Test
    public void cancelVideoCall() throws InterruptedException{//7.7
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        Thread.sleep(4000);
        mainPage.selectPeriodOnMainPageByClient();
        Thread.sleep(4000);
        mainPage.selectConsultationOnMainPage();
        mainPage1.selectPeriodOnMainPageBySpecialist();
        Thread.sleep(4000);
        mainPage1.selectConsultationOnMainPage();
        Thread.sleep(4000);
        mainPage1.selectVideoConsultation();
        Thread.sleep(3000);
        mainPage.cancelIncomingCall();
        mainPage.checkDisableCallClient();
        mainPage1.checkDisableCallSpecialist();
    }




    @AfterAll
    public void quit(){
        driver.close();
        driver1.close();
    }
}
