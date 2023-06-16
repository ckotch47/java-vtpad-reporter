import com.sun.tools.javac.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DataForFilling;
import pages.MainPage;
import pages.RegistrationPage;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TwoWindow {
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
        mainPage.selectConsultation();
        mainPage1.selectConsultation();
        Thread.sleep(4000);
        mainPage1.selectAudioConsultation();
        mainPage.waitIncomingCall();
        Boolean actual = mainPage.checkDisplayed();
        Boolean expected = true;
        Assert.assertEquals(expected, actual);
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
        mainPage.selectConsultation();
        mainPage1.selectConsultation();
        Thread.sleep(4000);
        mainPage1.selectVideoConsultation();
        mainPage.waitIncomingCall();
        Boolean actual = mainPage.checkDisplayed();
        Boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
//    @Test
//    public void twoWindowAudioWithoutMicrophone() throws InterruptedException{/////ДОРАБОТАТЬ
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--use-fake-ui-for-media-stream");
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.get(DataForFilling.deployUrl);
//        driver1 = new ChromeDriver(options);
//        driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver1.get(DataForFilling.deployUrl);
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        RegistrationPage registrationPage1 = new RegistrationPage(driver1);
//        MainPage mainPage = new MainPage(driver);
//        MainPage mainPage1 = new MainPage(driver1);
//        registrationPage.stepsOnAutorizationClient();
//        registrationPage1.stepsOnAutorizationSpecialist();
//        Thread.sleep(5000);
//        mainPage.selectConsultation();
//        mainPage1.selectConsultation();
//        Thread.sleep(4000);
//        mainPage1.selectAudioConsultation();
//        mainPage.waitIncomingCall();
//        mainPage.cancelIncomingCall();
//        Thread.sleep(2000);
//        mainPage1.selectCalendar();
//        String actual = mainPage1.checkCalendarText();
//        String expected = "Календарь";
//        Assert.assertEquals(expected, actual);
//    }

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
        mainPage.selectConsultation();
        mainPage1.selectConsultation();
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
//    @Test
//    public void checkUploadFile() throws InterruptedException, AWTException {//непонятно как без инпута
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--use-fake-ui-for-media-stream");
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.get(DataForFilling.deployUrl);
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        MainPage mainPage = new MainPage(driver);
//        registrationPage.stepsOnAutorizationSpecialist();
//        Thread.sleep(3000);
//        mainPage.selectConsultation();
//        Thread.sleep(3000);
//        mainPage.pushPaperClipButton();
//        WebElement upload = driver.findElement(By.xpath(".//label[@class='consultation-modal-file-zone']"));
//        upload.click();
//        Robot ro = new Robot();
//        ro.mouseMove(300 , 400);
//    }




//    @After
//    public void quit(){
//        driver.quit();
//        driver1.quit();
//    }
}
