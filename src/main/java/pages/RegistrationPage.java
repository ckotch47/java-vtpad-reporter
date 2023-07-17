package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegistrationPage {
    private WebDriver driver;
    private By mailField = By.cssSelector("input[placeholder='E-mail']");
    private By phoneField = By.cssSelector("input[placeholder='Номер телефона']");
    private By nextButton = By.xpath(".//button[contains(text(),'Войти')]");
    private By buttonRegistration = By.cssSelector("div[class='auth-form__link']");
    private By fieldPassword = By.xpath("//*[@id=\"__layout\"]/div/div/div[2]/div[1]/div/div[2]/form/label[2]/input");
    private By errorMail = By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div/div[2]/form/label[1]/span");
    private By errorMobile = By.xpath("//span[contains(text(),'Данный номер телефона уже используется')]");
    private By nextInRegistration = By.xpath(".//button[contains(text(),'Далее')]");
    private By fieldMobilePassword = By.xpath(".//input[@placeholder='Код полученный в смс']");
    private By errorMobilePassword = By.xpath(".//span[@class='input__error']");
    private By nextInAcceptCodeMobile = By.xpath(".//button[contains(text(),'Далее')]");
    private By fieldNewPassword = By.xpath(".//input[@placeholder='Новый пароль']");
    private By fieldConfirmPassword = By.xpath(".//input[@placeholder='Подтвердите пароль']");
    private By buttonConfirm = By.xpath(".//button[contains(text(),'Готово')]");
    private By textLogin = By.xpath(".//h1[contains(text(), 'Войти в аккаунт')]");
    private By textMessageForCreatePassword = By.cssSelector("div.password-strength-indicator__message");
    private By errorCoincidence = By.cssSelector("span.input__error");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }
    public void waitButtonRegistration(){
        new WebDriverWait(driver,  Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(buttonRegistration));
    }
    public void fillingEmailClient(){
        driver.findElement(mailField).sendKeys(DataForFilling.emailClient);
    }
    public void fillingEmailSpecialist(){
        driver.findElement(mailField).sendKeys(DataForFilling.emailSpecialist);
    }
    public void fillingEmailAdmin(){
        driver.findElement(mailField).sendKeys(DataForFilling.emailAdmin);
    }
    public void fillingPasswordAdmin(){
        driver.findElement(fieldPassword).sendKeys(DataForFilling.passwordAdmin);
    }

    public String getError(){
        return
                driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div/div[2]/form/label[1]/span")).getText();
    }
    public void pushButtonConfirm() throws InterruptedException{
        driver.findElement(buttonConfirm).click();
        Thread.sleep(10000);
    }
    public void waitError(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div/div[2]/form/label[1]/span"));
    }
    public String getErrorForDatabase(){
        return
                driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div/div[2]/form/label[1]/span")).getText();
    }
    public void fillingFalsePassword(){
        driver.findElement(fieldPassword).sendKeys("123456789");
    }

    public void findEmail() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(mailField);
    }
    public void fillingPhoneForRegistration() throws InterruptedException{
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(DataForFilling.phoneForRegistration);
        Thread.sleep(2000);
    }
    public void fillingNewPassword(String newPassword){
        driver.findElement(fieldNewPassword).sendKeys(newPassword);
    }
    public void fillingConfirmPassword(String confirmPassword){
        driver.findElement(fieldConfirmPassword).sendKeys(confirmPassword);
    }
    public void fillingPhone(){
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(DataForFilling.phone);
    }
    public void fillingPhoneInBase(){
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(DataForFilling.phoneInBase);
    }
    public void pushNextButton(){
        driver.findElement(nextButton).click();
    }
    public void fillingPasswordClient(){
        driver.findElement(fieldPassword).sendKeys(DataForFilling.passwordClient);
    }
    public void fillingPasswordSpecialist(){
        driver.findElement(fieldPassword).sendKeys(DataForFilling.passwordSpecialist);
    }
    public void pushRegistrationForm(){
        driver.findElement(buttonRegistration).click();
    }
    public void fillingWrongMail(){
        driver.findElement(mailField).sendKeys("makavelkagmail.com");
    }
    public String getErrorMail(){
        return
                driver.findElement(errorMail).getText();
    }
    public void fillingMailRussianWords(){
        driver.findElement(mailField).sendKeys("русскиеБуквы@mail.com");
    }
    public void fillingMailNoInDatabase()throws InterruptedException{
        driver.findElement(mailField).sendKeys(DataForFilling.emailNotUsedInData);
        Thread.sleep(2000);
    }
    public void waitErrorMobile(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(errorMobile);

    }
    public String getErrorMobile(){
        return
                driver.findElement(errorMobile).getText();
    }
    public void pushNextButtonInRegistration()throws InterruptedException{
        driver.findElement(nextInRegistration).click();
        Thread.sleep(3000);
    }
    public void waitFieldPassword(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(fieldMobilePassword);
    }
    public void fillingWrongMobilePassword(){
        driver.findElement(fieldMobilePassword).sendKeys(DataForFilling.wrongPassword);
    }
    public void fillingPasswordMobileForRegistration(){
        driver.findElement(fieldMobilePassword).sendKeys(DataForFilling.passwordMobileForRegistration);
    }
    public void waitErrorPasswordMobile(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(errorMobilePassword);
    }
    public void pushNextInCodeAcceptMobile(){
        driver.findElement(nextInAcceptCodeMobile).click();
    }
    public String getErrorPasswordMobile(){
        return
                driver.findElement(errorMobilePassword).getText();
    }

    public void stepsOnAutorizationClient() throws InterruptedException{
        fillingEmailClient();
        fillingPasswordClient();
        pushNextButton();
        Thread.sleep(3000);
    }
    public void stepsOnAutorizationSpecialist() throws InterruptedException{
        fillingEmailSpecialist();
        fillingPasswordSpecialist();
        pushNextButton();
        Thread.sleep(4000);
    }
    public void stepsOnAutorizationAdmin(){
        fillingEmailAdmin();
        fillingPasswordAdmin();
        pushNextButton();
    }
    public boolean checkLoginDisplay(){
        return driver.findElement(textLogin).isDisplayed();
    }
    public String getTextMessageAfterFillingPassword(){
        return driver.findElement(textMessageForCreatePassword).getText();
    }
    public boolean checkDisplayErrorCoincidence(){
        return driver.findElement(errorCoincidence).isDisplayed();
    }
    public String getTextErrorCoincidence(){
        return driver.findElement(errorCoincidence).getText();
    }
}
