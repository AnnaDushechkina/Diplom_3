import api.UserApi;
import data.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrPage;

public class RegistrTest extends BaseUITest {

    String name;
    String email;
    String password;

    MainPage mainPage;
    LoginPage loginPage;
    RegistrPage registrPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrPage = new RegistrPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickRegisterButton();
        registrPage.waitForLoadRegisterPage();
    }

    @Test
    public void checkSuccessRegistrTest() {
        name = "Valdis";
        email = "Valdis@mail.ru";
        password = "123456";
        registrPage.fillRegistrForm(name, email, password);
        registrPage.clickRegisterButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Регистрации нет", mainPage.isOrderButtonVisible());
    }

    @Test
    public void checkForBadPasswordLoginTest() {
        name = "Valdis";
        email = "Valdis@mail.ru";
        password = "12345";
        registrPage.fillRegistrForm(name, email, password);
        registrPage.clickRegisterButton();
        Assert.assertTrue("Ошибка не появилась",
                registrPage.isIncorrectPasswordLabelVisible());
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