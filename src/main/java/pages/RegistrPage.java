package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrPage extends BasePage {

    private final By registerLabelLocator = By.xpath("//h2[text()='Регистрация']");
    private final By nameFieldLocator = By.xpath("//label[text()='Имя']/../input");
    private final By emailFieldLocator = By.xpath("//label[text()='Email']/../input");
    private final By passwordFieldLocator = By.xpath("//label[text()='Пароль']/../input");
    private final By registerButtonLocator = By.xpath("//button[@class='button_button__33qZ0 " +
            "button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By loginButtonLocator = By.xpath("//a[@href='/login']");
    private final By incorrectPasswordLabelLocator = By.xpath("//p[@class='input__error text_type_main-default']");

    public RegistrPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы Регистрации")
    public void waitForLoadRegisterPage() {
        waitForVisibility(registerLabelLocator);
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrForm(String name, String email, String password) {
        driver.findElement(nameFieldLocator).sendKeys(name);
        driver.findElement(emailFieldLocator).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }

    @Step("Проверка видимости надписи 'Неправильный пароль'")
    public boolean isIncorrectPasswordLabelVisible() {
        return driver.findElement(incorrectPasswordLabelLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }
}