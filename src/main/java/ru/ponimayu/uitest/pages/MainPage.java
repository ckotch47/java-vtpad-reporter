package ru.ponimayu.uitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class MainPage {
    private WebDriver driver;
    private By welcome = By.xpath(".//div[contains(text(),'Домашняя страница')]");
    private By searchRole = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/form/div[2]/div/div/div[1]/input");
    private By searchButton = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/form/div[6]/a");
    private By nameSpecialist = By.xpath(".//span[contains(text(),'Специалист')]");
    private By checkBoxFamily = By.xpath("/html/body/div[1]/div/div/main/div/div[2]/div/form/div/div[2]/div[7]/div/label");
    private By numberSpecialist = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/h2");
    private By listSpecialists = By.xpath("/html/body/div[1]/div/div/aside/div/div/nav/ul/li[4]/a/div");
    private By specialistForClickable = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/div/div[1]/div[2]/div[1]/a");
    private By dataSpecialist = By.xpath(".//input[@placeholder='Иванов Иван Иванович']");
    private By buttonBeginConsultation = By.xpath(".//button[contains(text(),'Начать консультацию')]");
    private By fieldTextConsultation = By.xpath(".//textarea[@placeholder='Введите текст...']");
    private By sendButton  = By.xpath(".//div[@class='consultation-send__button'][1]");
    private By logoutButton = By.xpath(".//div[@class='aside-logout__text']");
    private By firstMessageInChat = By.xpath(".//div[@class='__view']/div[@class='consultation-chat__group'][last()]/div[@class='consultation-chat__messages'][last()]/div[@class='consultation-chat__message consultation-chat-message consultation-chat-message_type_companion consultation-chat-message'][last()]/div[@class='consultation-chat-message__body'][last()]/div[@class='consultation-chat-message__area']");

    private By clearFilter = By.xpath(".//button[@type='reset']");
    private By selectCalendar = By.xpath(".//a[@href='/calendar']");
    private By buttonNewSlot = By.xpath(".//button[contains(text(),'Новый слот')]");
    private By buttonMonthOnCalendar = By.xpath(".//div[@class='vc-title']");
    private By buttonForSelectOctober = By.xpath(".//span[@data-id='2023.10']");
    private By buttonDateForCreateSlot = By.xpath(DataForFilling.dateForAppoitment);
    private By buttonPlusOnCreateSlot = By.xpath(".//*[name()='svg'][@class='specialist-calendar-aside__timepicker-add icon sprite-icons'][1]");
    private By buttonSaveSlot = By.xpath(".//button[contains(text(),'Сохранить')]");
    private By selectTimeForSelectAppoitment = By.xpath(".//div[@class='specialist-detail-aside-slots-item specialist-detail-aside-slots__item']");
    private By finalMakeAppointment = By.xpath(".//button[contains(text(),'Записаться')]");
    private By modalPayment = By.xpath(".//div[@class='Text__StyledTextSpan-sc-9bqqn7-0 gUeNhY qa-payment-option-title Preview__StyledTitle-aie99n-0 hVHItB']" +
            "//div[@class='Text__StyledTextSpan-sc-9bqqn7-0 gUeNhY qa-payment-option-title Preview__StyledTitle-aie99n-0 hVHItB']/child::node()[last()]");
    private By selectConsultationFromSpecialist = By.xpath(".//div[@class='consultations-filter-list__data']//div[@class='striped-table__td striped-table__item-column'][normalize-space()='16.06.23 13:00 - 14:00']");
    private By audioConsultation = By.xpath(".//div[@class='consultation-format__actions']//div[1]");
    private By videoConsultation = By.xpath(".//div[@class='consultation-format__actions']//div[2]");
    private By incomingCall = By.xpath(".//div[@name='incomingCall']");
    private By cancelIncomingCall = By.xpath(".//div[@class='consultation-modal-incoming-call__action consultation-modal-incoming-call__action_type_decline']");
    private By textHeader = By.xpath(".//h1[@class='layout-header__title']");
    private By modalCall = By.xpath(".//div[@class='consultation-modal-call']");
    private By buttonExpandModal = By.xpath(".//div[@class='consultation-modal-call__action']");
    private By paperClipButton = By.xpath(".//*[name()='svg'][@class='consultation-send__attach icon sprite-icons'][1]");
    private By uploadFile = By.xpath(".//label[@class='consultation-modal-file-zone']");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
public void waitElement(){//ожидание открытия главной страницы
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(welcome);
    }
    public String getWelcomeText()//получение текста "добро пожаловать"
    {
        return
                driver.findElement(welcome).getText();
    }
    public void searchRole(){//поиск на главной странице по направлению "коуч"
        driver.findElement(searchRole).sendKeys("Коуч");
        driver.findElement(By.xpath(".//ul[@class='vs__dropdown-menu'][last()]")).click();
    }
    public void pushSearch(){//нажатии на кнопку "найти" на главной странице
        driver.findElement(searchButton).click();
    }
    public String getNameSpecialist(){//получение имени специалиста
        return
                driver.findElement(nameSpecialist).getText();
    }
    public void pushCheckBoxFamily(){//нажатие на чекбокс "семейный" в фильтре в поиске
        driver.findElement(checkBoxFamily).click();
    }

    public void waitQuantity(){

    }

    public String checkQuantitySpecialist(){//получение специалистов над списком
        return driver.findElement(numberSpecialist).getText();
    }
    public void navigationSpecialist(){//нажатии на кнопку "специалисты в боковом меню
        driver.findElement(listSpecialists).click();
    }
    public void waitSpecialist(){//ожидание когда специалисты будут кликабельные
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(specialistForClickable);
    }
    public void fillingDataSpecialist() {//заполнение в поле имя в поиске специалиста
        driver.findElement(dataSpecialist).sendKeys("Консультант Специалист");
    }
    public void fillingDataWrongSpecialist(){//заполнение в поле имя в поиске специалиста
        driver.findElement(dataSpecialist).sendKeys("Такого специалиста не найти которого нет");
    }
    public void selectConsultationBySpecialist(){//выбор консультации за специалиста
        driver.findElement(selectConsultationFromSpecialist).click();
    }
    public void waitBegin(){//ожидание появления кнопки начало консультации
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(buttonBeginConsultation);
    }
    public void waitPaymentModal(){
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(modalPayment);
    }
    public String  getPaymentText(){
        return
                driver.findElement(modalPayment).getText();
    }
    public void beginConsultation(){//нажатие на кнопку "начать консультацию"
        driver.findElement(buttonBeginConsultation).click();
    }
    public void fiilingHelloInChat(String hello){//отправка сообщения в чат
        driver.findElement(fieldTextConsultation).sendKeys(hello);
    }
    public void pushSendTextInChat(){//нажатие на кнопку отправки
        driver.findElement(sendButton).click();
    }
    public void logout(){//выход с профиля
        driver.findElement(logoutButton).click();
    }
    public String getFirstMessageInChat(){//получение первого текста в чате
        return
                driver.findElement(firstMessageInChat).getText();
    }
    public void clickButtonClearFilter(){//нажатие на кнопку "сбросить фильтр" в поиске специалистов
        driver.findElement(clearFilter).click();
    }
    public void selectCalendar(){//нажатие на календарь в левом меню
        driver.findElement(selectCalendar).click();
    }
    public void clickNewSlot(){//нажатие на кнопку новый слот
        driver.findElement(buttonNewSlot).click();
    }
    public void selectMonthForCreateSlot(){//выборм месяца в календаре
        driver.findElement(buttonMonthOnCalendar).click();
        driver.findElement(buttonForSelectOctober).click();
    }
    public void createSlot(){//создание слота за специалиста
        driver.findElement(buttonDateForCreateSlot).click();
        driver.findElement(buttonDateForCreateSlot).click();
        driver.findElement(buttonPlusOnCreateSlot).click();
        driver.findElement(buttonSaveSlot).click();
    }
    public void selectSpecialist(){//выбор специалиста
        driver.findElement(nameSpecialist).click();
    }
    public void makeAppoitment(){//оформление слота
        driver.findElement(buttonDateForCreateSlot).click();
        driver.findElement(selectTimeForSelectAppoitment).click();
        driver.findElement(finalMakeAppointment).click();
    }
    public void selectConsultation(){
        driver.findElement(selectConsultationFromSpecialist).click();
    }
    public void selectAudioConsultation(){
        driver.findElement(audioConsultation).click();
    }
    public void selectVideoConsultation(){
        driver.findElement(videoConsultation).click();
    }
    public void waitIncomingCall(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(incomingCall);
    }
    public Boolean  checkDisplayed(){
        return
        driver.findElement(incomingCall).isDisplayed();
    }
    public void cancelIncomingCall(){
        driver.findElement(cancelIncomingCall).click();
    }
    public String checkCalendarText(){
        return
                driver.findElement(textHeader).getText();
    }
    public Rectangle getSizeModalCall(){
        return
                driver.findElement(modalCall).getRect();
    }
    public void pushExpandeModal(){
        driver.findElement(buttonExpandModal).click();
    }
    public void pushPaperClipButton(){
        driver.findElement(paperClipButton).click();
    }
    public void uploadFile(){
        driver.findElement(uploadFile).click();
    }
}
