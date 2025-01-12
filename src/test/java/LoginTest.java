import api.UserApi;
import constant.Constant;
import data.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PasswordRecoveryPage;
import pages.RegistrPage;


public class LoginTest extends BaseUITest {
    MainPage mainPage;
    LoginPage loginPage;
    RegistrPage registrationPage;
    User user;
    PasswordRecoveryPage passwordRecoveryPage;
    UserApi userApi;
    String name;
    String email;
    String password;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        driver.get(Constant.BURGER_URL);
        name = "Valdis";
        email = "Valdis@mail.ru";
        password = "123456";
        user = new User(email, password, name);
        userApi.createUser(user);
    }

    @Test
    public void checkLoginMainPageTest() {
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    public void checkLoginPersonalAccountButtonTest() {
        mainPage.clickPersonalButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    public void checkLoginRegistrPageTest() {
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickRegisterButton();
        registrationPage.waitForLoadRegisterPage();
        registrationPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    public void checkLoginPasswordRecoveryPageTest() {
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickResetPasswordButton();
        passwordRecoveryPage.waitForLoadPage();
        passwordRecoveryPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @After
    public void tearDown() {
        String accessToken = UserApi.loginUser(new User(email, password)).then().extract().path("accessToken");
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driver.quit();
    }
}