package pages;

import org.openqa.selenium.*;

import javax.swing.*;
import java.util.concurrent.TimeUnit;


public class MainPage {
    private WebDriver driver;
    private By welcome = By.xpath("/html/body/div[1]/div/div/aside/div/div/nav/ul/li[1]/a/div");
    private By searchRole = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/form/div[2]/div/div/div[1]/input");
    private By searchButton = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/form/div[6]/a");
    private By nameSpecialist = By.xpath(".//span[contains(text(),'Специалист')]");
    private By checkBoxFamily = By.xpath("/html/body/div[1]/div/div/main/div/div[2]/div/form/div/div[2]/div[7]/div/label");
    private By numberSpecialist = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/h2");
    private By listSpecialists = By.xpath(".//div[contains(text(),'Специалисты')]");
    private By specialistForClickable = By.xpath("/html/body/div[1]/div/div/main/div/div[1]/div/section/div/div[1]/div[2]/div[1]/a");
    private By dataSpecialist = By.xpath(".//input[@placeholder='Иванов Иван Иванович']");
    private By fieldTextConsultation = By.xpath(".//textarea[@placeholder='Введите текст...']");
    private By sendButton = By.xpath(".//div[@class='consultation-send__button'][1]");
    private By logoutButton = By.xpath(".//div[@class='aside-logout__text']");
    private By firstMessageInChat = By.xpath(".//div[@class='__view']/div[last()]/div[last()]/div[last()]/div[@class='consultation-chat-message__body']/div[@class='consultation-chat-message__area']");
    private By lastMessageForImage = By.xpath(".//div[@class='__view']/div[last()]/div[last()]/div[last()]/div[@class='consultation-chat-message__body']/div[@class='consultation-chat-message__image']");
    private By clearFilter = By.xpath(".//button[@type='reset']");
    private By selectCalendar = By.xpath(".//div[@class='aside-menu__label'][contains(text(), 'Календарь')]");
    private By audioConsultation = By.xpath(".//div[@class='consultation-format__actions']//div[1]");
    private By videoConsultation = By.xpath(".//div[@class='consultation-format__actions']//div[2]");
    private By incomingCall = By.xpath(".//div[@name='incomingCall']");
    private By cancelIncomingCall = By.xpath(".//div[@class='consultation-modal-incoming-call__action consultation-modal-incoming-call__action_type_decline']");
    private By modalCall = By.xpath(".//div[@class='consultation-modal-call']");
    private By buttonExpandModal = By.xpath(".//div[@class='consultation-modal-call__action']");
    private By paperClipButton = By.xpath(".//*[name()='svg'][@class='consultation-send__attach icon sprite-icons'][1]");
    private By uploadFile = By.xpath(".//input[@class='visually-hidden']");
    private By submitUpload = By.xpath(".//label[@class='consultation-modal-file-input']//*[name()='svg']");
    private By addTextInModalMessageWithImage = By.xpath(".//input[@placeholder='Добавить подпись']");
    private By acceptCall = By.xpath("//div[@class='consultation-modal-incoming-call__action consultation-modal-incoming-call__action_type_accept']");
    private By buttonUsersAdmin = By.xpath(".//div[@class='aside-menu__label'][contains(text(), 'Пользователи')]");
    private By buttonCreateUser = By.xpath(".//a[contains(text(),'Добавить пользователя')]");
    private By fieldDataForCreateUser = By.xpath(".//input[@placeholder='ФИО']");
    private By fieldMailForCreateUser = By.xpath(".//input[@placeholder='E-mail']");
    private By fieldNumberForCreateUser = By.xpath(".//input[@placeholder='Телефон']");
    private By buttonFinalCreateUser = By.xpath(".//button[contains(text(),'Добавить')]");
    private By confirmCreateUserByAdmin = By.xpath(".//div[@class='admin-customer-add-confirm-modal']");
    private By buttonNextMonthFromSpecialist = By.xpath(".//div[@class='calendar-consultations__arrows']//*[name()='svg'][last()]");
    private By addTimeForCreateConsultation = By.xpath(".//div[@class='page-main__aside-wrap']//*[name()='svg'][@class='specialist-calendar-aside__timepicker-add icon sprite-icons']");
    private By saveTimeForCreateConsultation = By.xpath(".//button[contains(text(),'Сохранить')]");
    private By selectNextMonthFromClient = By.xpath(".//div[@class='vc-arrow is-right']//*[name()='svg']");
    private By selectSlotByClient = By.xpath(".//div[@class='specialist-detail-aside-slots-item__body']");
    private By selectBankCardOnModalPayments = By.xpath(".//div[@class='Text__StyledTextSpan-sc-9bqqn7-0 gUeNhY qa-payment-option-title Preview__StyledTitle-aie99n-0 hVHItB']");
    private By buttonFinalMakeAppoitment = By.xpath(".//button[contains(text(),'Записаться')]");
    private By numbCard = By.xpath(".//input[@name='card-number']");
    private By expiredMonth = By.xpath(".//input[@name='expiry-month']");
    private By expiredYear = By.xpath(".//input[@name='expiry-year']");
    private By cvvCode = By.xpath(".//input[@name='security-code']");
    private By buttonSendCash = By.xpath(".//button[@type='submit']");
    private By buttonChat = By.xpath(".//a[contains(text(), 'Чат')]");
    private By buttonStartConsultation = By.xpath(".//button[contains(text(), 'Начать консультацию')]");
    private By buttonCalendarOnMainPage = By.cssSelector("span.date-picker-range__label");
    private By nextMonthOnMainPage = By.xpath(".//div[@class='vc-container vc-indigo']/div[@class='vc-pane-container']/div[@class='vc-arrows-container title-left']/div[@class='vc-arrow is-right'][last()]");
    private By buttonForSelectFirstPeriod = By.xpath(".//span[@aria-label='пятница, 11 августа 2023 г.']");
    private By buttonForSelectSecondPeriod  = By.xpath("//span[@aria-label='среда, 30 августа 2023 г.']");
    private By selectConsultationOnMainPage = By.xpath("//div[@class='striped-table__td striped-table__item-column']/span[contains(text(), 'Открыта')]");
    private By buttonCalendarOnMainPageByClient = By.xpath(".//div[@class='consultations-filter-list-head']/span['date-picker-range consultations-filter-list__date date-picker-range_theme_white']/span[@class='date-picker-range__label']");
    private By buttonEndConsulation = By.xpath(".//button[contains(text(), 'Завершить консультацию')]");
    private By fieldSelectTematics = By.xpath(".//input[@placeholder='Укажите тематику']");
    private By selectFamilyInList = By.xpath(".//ul[@class='vs__dropdown-menu']/li/span");
    private By approveEndConsultation = By.xpath(".//button[contains(text(), 'Готово')]");
    private By writeConclusion = By.xpath(".//button[contains(text(), 'Написать заключение')]");



    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void switchFrame(){
        driver.switchTo().frame("iFrameResizer0");
    }
    public void catchFrame(){
            try {
                switchFrame();
                System.out.println("Модальное окно для оплаты найдено");
            }catch (NoSuchFrameException exception){
                System.out.println("Модальное окно для оплаты не найдено");
            }
        }
    public void waitModal(){
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.findElement(selectBankCardOnModalPayments);
    }


    public void waitElement() {//ожидание открытия главной страницы
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(welcome);
    }

    public String getWelcomeText()//получение текста "добро пожаловать"
    {
        return
                driver.findElement(welcome).getText();
    }

    public void searchRole() {//поиск на главной странице по направлению "коуч"
        driver.findElement(searchRole).sendKeys("Коуч");
        driver.findElement(By.xpath(".//ul[@class='vs__dropdown-menu'][last()]")).click();
    }

    public void pushSearch() {//нажатии на кнопку "найти" на главной странице
        driver.findElement(searchButton).click();
    }

    public String getNameSpecialist() {//получение имени специалиста
        return
                driver.findElement(nameSpecialist).getText();
    }
    public void clientSelectSpecialist()throws InterruptedException{
        driver.findElement(nameSpecialist).click();
        Thread.sleep(4000);
    }

    public void pushCheckBoxFamily() {//нажатие на чекбокс "семейный" в фильтре в поиске
        driver.findElement(checkBoxFamily).click();
    }


    public String checkQuantitySpecialist() {//получение специалистов над списком
        return driver.findElement(numberSpecialist).getText();
    }

    public void navigationSpecialist() throws InterruptedException{//нажатии на кнопку "специалисты в боковом меню
        driver.findElement(listSpecialists).click();
        Thread.sleep(5000);
    }

    public void waitSpecialist() {//ожидание когда специалисты будут кликабельные
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(specialistForClickable);
    }

    public void fillingDataSpecialist()throws InterruptedException {//заполнение в поле имя в поиске специалиста
        driver.findElement(dataSpecialist).sendKeys("Консультант Специалист");
        Thread.sleep(4000);
    }

    public void fillingDataWrongSpecialist() {//заполнение в поле имя в поиске специалиста
        driver.findElement(dataSpecialist).sendKeys("Такого специалиста не найти которого нет");
    }


    public void fiilingHelloInChat(String hello) {//отправка сообщения в чат
        driver.findElement(fieldTextConsultation).sendKeys(hello);
    }

    public void pushSendTextInChat() {//нажатие на кнопку отправки
        driver.findElement(sendButton).click();
    }

    public void logout() throws InterruptedException {//выход с профиля
        driver.findElement(logoutButton).click();
        Thread.sleep(7000);
    }

    public String getFirstMessageInChat() {//получение первого текста в чате
        return
                driver.findElement(firstMessageInChat).getText();
    }

    public boolean checkDisplayedImage() {
        return
                driver.findElement(lastMessageForImage).isDisplayed();
    }

    public void clickButtonClearFilter() {//нажатие на кнопку "сбросить фильтр" в поиске специалистов
        driver.findElement(clearFilter).click();
    }

    public void selectCalendar() throws InterruptedException {//нажатие на календарь в левом меню
        driver.findElement(selectCalendar).click();
        Thread.sleep(2000);
    }

    public void selectNextMonthOnCalendarFromSpecialist() throws InterruptedException {
        driver.findElement(buttonNextMonthFromSpecialist).click();
        Thread.sleep(7000);
    }
    public void selectNextMonthOnCalendarFromClient()throws InterruptedException{
        driver.findElement(selectNextMonthFromClient).click();
        Thread.sleep(5000);
    }


    public void selectAudioConsultation() throws InterruptedException{
        driver.findElement(audioConsultation).click();
        Thread.sleep(3000);
    }

    public void selectVideoConsultation() throws InterruptedException{
        driver.findElement(videoConsultation).click();
        Thread.sleep(3000);
    }

    public void waitIncomingCall() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(incomingCall);
    }

    public Boolean checkDisplayed() {
        return
                driver.findElement(incomingCall).isDisplayed();
    }

    public void cancelIncomingCall() {
        driver.findElement(cancelIncomingCall).click();
    }


    public Rectangle getSizeModalCall() {
        return
                driver.findElement(modalCall).getRect();
    }

    public void pushExpandeModal() {
        driver.findElement(buttonExpandModal).click();
    }

    public void pushPaperClipButton() {
        driver.findElement(paperClipButton).click();
    }

    public void uploadFile() throws InterruptedException{
        driver.findElement(uploadFile).sendKeys(DataForFilling.photoForSendInChat);
        Thread.sleep(3000);
    }

    public void submitUpload() {
        driver.findElement(submitUpload).click();
    }

    public void addTextInModalMessageWithImage(String hello) {
        driver.findElement(addTextInModalMessageWithImage).sendKeys(hello);
    }

    public void acceptCall() throws InterruptedException{
        driver.findElement(acceptCall).click();
        Thread.sleep(2000);
    }

    public void pushButtonUsersAdmin() {
        driver.findElement(buttonUsersAdmin).click();
    }

    public void pushCreateUserAdmin() {
        driver.findElement(buttonCreateUser).click();
    }

    public void fillingDataForCreateUserByAdmin() {
        driver.findElement(fieldDataForCreateUser).sendKeys(DataForFilling.dataForCreateUserByAdmin);
    }

    public void fillingEmailForCreateUserByAdmin() {
        driver.findElement(fieldMailForCreateUser).sendKeys(DataForFilling.emailForCreateUserByAdmin);
    }

    public void fillingNumberForCreateUserByAdmin() {
        driver.findElement(fieldNumberForCreateUser).sendKeys(DataForFilling.numberForCreateUserByAdmin);
    }

    public void pushFinalCreateUserByAdmin() {
        driver.findElement(buttonFinalCreateUser).click();
    }

    public void stepsForCreateUserByAdmin() {
        fillingDataForCreateUserByAdmin();
        fillingEmailForCreateUserByAdmin();
        fillingNumberForCreateUserByAdmin();
        pushFinalCreateUserByAdmin();
    }
    public void pushAddTimeForCreateConsultation(){
        driver.findElement(addTimeForCreateConsultation).click();
    }
    public void pushSaveTimeForCreateConsultation()throws InterruptedException{
        driver.findElement(saveTimeForCreateConsultation).click();
        Thread.sleep(3000);
    }
    public void selectBankCard()throws InterruptedException{
        try {
            driver.findElement(selectBankCardOnModalPayments).click();
        }
        catch (NoSuchElementException exception){
            System.out.println("Не найден ввод карты");
        }
        Thread.sleep(3000);
    }
    public void fillingDataCard()throws InterruptedException{
        try {
            driver.findElement(numbCard).sendKeys(DataForFilling.cardForTesting);
            driver.findElement(expiredMonth).sendKeys(DataForFilling.monthForTesting);
            driver.findElement(expiredYear).sendKeys(DataForFilling.yearForTesting);
            driver.findElement(cvvCode).sendKeys(DataForFilling.cvvForTesting);
            driver.findElement(buttonSendCash).click();
        }catch (NoSuchElementException e){
            System.out.println("Не найдены поля для ввода данных");
        }
        Thread.sleep(4000);
    }


    public void checkConfirmModalForCreateUserByAdmin() {
        try {
            driver.findElement(confirmCreateUserByAdmin).isDisplayed();
            System.out.println("Пользователь создан");
        } catch (NoSuchElementException exception) {
            System.out.println("Пользователь не создан, так как нет окна подтверждения");
        }
    }

    public void checkDisableCallClient() {
        try {
            driver.findElement(modalCall).isDisplayed();
            System.out.println("Модальное окно работает у клиента, оно должно быть закрыто");
        } catch (NoSuchElementException exception) {
            System.out.println("Модальное окно не найдено у клиента , все хорошо");
        }
    }

    public void checkDisableCallSpecialist()throws InterruptedException {
        try {
            driver.findElement(modalCall).isDisplayed();
            System.out.println("Модальное окно работает у специалиста, оно должно быть закрыто");
        } catch (NoSuchElementException exception) {
            System.out.println("Модальное окно не найдено у специалиста, все хорошо");
        }
        Thread.sleep(6000);
    }
    public void checkConfirmModal()throws InterruptedException{
        try{
            driver.findElement(By.xpath(".//h2[@class='confirm-modal__title']")).isDisplayed();
            System.out.println("Модальное окно о успешной записи появилось");
        }catch (NoSuchElementException exception){
            System.out.println("Модальное окно о успешной записи не появилось");
            driver.findElement(buttonFinalMakeAppoitment).click();
        }
        Thread.sleep(6000);
    }

    public int searchEmptySlotFromSpecialist()throws InterruptedException{
        driver.findElement(By.xpath(".//button[@class='add-button specialist-calendar-aside__slot-button']")).click();
        int tempDay = -1;
        for (int i = 14;i<30;i++){
            String actualClass = driver.findElement(By.xpath(".//span[@aria-disabled='false'][contains(text(),"+ i + ")]")).getAttribute("class").toString();
             if (actualClass.equals("vc-day-content vc-focusable")){
                tempDay = i;
                break;
            }
    }
        if(tempDay != -1){
            driver.findElement(By.xpath(".//span[@aria-disabled='false'][contains(text(),"+ tempDay + ")]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(".//span[@aria-disabled='false'][contains(text(),"+ tempDay + ")]")).click();
        }
        else{
            System.out.println("Не найдено свободного слота");
        }
        Thread.sleep(2000);
        return tempDay;
    }

    public void searchEmptySlotFromClient() throws InterruptedException{
        int tempDay = -1;
        for (int i = 14;i<30;i++){
            String actualClass = driver.findElement(By.xpath(".//span[@aria-disabled='false'][contains(text(),"+ i + ")]")).getAttribute("class").toString();
            if (actualClass.equals("vc-day-content vc-focusable vc-day-content_type_available")){
                tempDay = i;
                break;
            }
        }
        if(tempDay != -1){
            driver.findElement(By.xpath(".//span[@aria-disabled='false'][contains(text(),"+ tempDay + ")]")).click();
        }
        else{
            System.out.println("Не найдено свободного слота");
        }
    }
    public void searchOpenSlotBySpecialist(int tempDays) throws InterruptedException{
        driver.findElement(By.xpath(".//div[@class='calendar-consultations-item__number'][contains(text()," + tempDays + ")]")).click();
        Thread.sleep(4000);
    }
    public void makeAppoitmentByClient() throws InterruptedException{
        driver.findElement(selectSlotByClient).click();
        Thread.sleep(2000);
    }
    public void finalMakeAppoitmentByclient() throws InterruptedException{
        driver.findElement(buttonFinalMakeAppoitment).click();
        Thread.sleep(15000);
    }
    public void pushButtonChat()throws InterruptedException{
        driver.findElement(buttonChat).click();
        Thread.sleep(6000);
    }
    public void pushStartConsultation() throws InterruptedException{
        driver.findElement(buttonStartConsultation).click();
        Thread.sleep(3000);
    }
    public void checkStartConsultation(){
        try {
            driver.findElement(buttonStartConsultation).isDisplayed();
            System.out.println("Ошибка . Консультация не началась");
        }catch (NoSuchElementException exception){
            System.out.println("все хорошо, консультация началась");
        }
    }
    public void selectPeriodOnMainPageBySpecialist()throws InterruptedException{
        driver.findElement(buttonCalendarOnMainPage).click();
        driver.findElement(nextMonthOnMainPage).click();
        driver.findElement(buttonForSelectFirstPeriod).click();
        driver.findElement(buttonForSelectSecondPeriod).click();
        Thread.sleep(4000);
    }
    public void selectPeriodOnMainPageByClient() throws InterruptedException{
        driver.findElement(buttonCalendarOnMainPageByClient).click();
        driver.findElement(nextMonthOnMainPage).click();
        driver.findElement(buttonForSelectFirstPeriod).click();
        driver.findElement(buttonForSelectSecondPeriod).click();
        Thread.sleep(4000);
    }
    public void selectConsultationOnMainPage()throws InterruptedException{
        driver.findElement(selectConsultationOnMainPage).click();
        Thread.sleep(4000);
    }
    public void endConsultation(){
        driver.findElement(buttonEndConsulation).click();
    }
    public void selectTematicsForEndConsultation() throws InterruptedException{
        driver.findElement(fieldSelectTematics).sendKeys("С");
        Thread.sleep(2000);
        driver.findElement(selectFamilyInList).click();
        Thread.sleep(2000);
    }
    public void approveEndConsultation()throws InterruptedException{
        driver.findElement(approveEndConsultation).click();
        Thread.sleep(3000);
    }
    public void checkEndConsultation(){
        try {
            driver.findElement(writeConclusion).isDisplayed();
            System.out.println("Консультация завершена");
        }
        catch (NoSuchElementException exception){
            System.out.println("Консультация не завершена");
        }
    }


}

