package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    private final By loginButtonLocator = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalButtonLocator = By.linkText("Личный Кабинет");
    private final By activeBunButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    private final By inactiveBunButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    private final By activeSauceButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    private final By inactiveSauceButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    private final By activeFillingButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");
    private final By inactiveFillingButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");
    private final By makeBurgerLabelLocator = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10']");
    private final By orderButtonLocator = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoad() {
        waitForVisibility(makeBurgerLabelLocator);
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        return driver.findElement(orderButtonLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Личный кабинет'")
    public void clickPersonalButton() {
        driver.findElement(personalButtonLocator).click();
    }

    @Step("Клик на кнопку 'Соусы'")
    public void clickSauceButton() {
        driver.findElement(inactiveSauceButtonLocator).click();
    }

    @Step("Проверка активности кнопки 'Соусы'")
    public boolean isSauceButtonActive() {
        return driver.findElement(activeSauceButtonLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Начинки'")
    public void clickFillingButton() {
        driver.findElement(inactiveFillingButtonLocator).click();
    }

    @Step("Проверка активности кнопки 'Начинки'")
    public boolean isFillingButtonActive() {
        return driver.findElement(activeFillingButtonLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Булки'")
    public void clickBunButton() {
        driver.findElement(inactiveBunButtonLocator).click();
    }

    @Step("Проверка активности кнопки 'Булки'")
    public boolean isBunButtonActive() {
        return driver.findElement(activeBunButtonLocator).isDisplayed();
    }
}