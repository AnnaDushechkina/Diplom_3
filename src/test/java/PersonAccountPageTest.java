import api.UserApi;
import data.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersAccountPage;
import pages.RegistrPage;


public class PersonAccountPageTest extends BaseUITest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrPage registrPage;
    User user;
    PersAccountPage persAccountPage;
    String name;
    String email;
    String password;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrPage = new RegistrPage(driver);
        persAccountPage = new PersAccountPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        name = "Valdis";
        email = "Valdis@mail.ru";
        password = "123456";
        user = new User(email, password, name);
        UserApi.createUser(user);

        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
    }

    @Test
    public void clickAccountTest() {
        mainPage.clickPersonalButton();
        persAccountPage.waitForLoadMainPage();
        Assert.assertTrue("Вход в личный кабинет не выполнен", persAccountPage.isProfileButtonVisible());
    }

    @Test
    public void checkEnterConstructorButtonTest() {
        mainPage.clickPersonalButton();
        persAccountPage.waitForLoadMainPage();
        persAccountPage.clickConstructorButton();
        mainPage.waitForLoad();
        Assert.assertTrue("Переход в конструктор не произошел", mainPage.isOrderButtonVisible());
    }

    @Test
    public void checkEnterConstructorByLogoTest() {
        mainPage.clickPersonalButton();
        persAccountPage.waitForLoadMainPage();
        persAccountPage.clickLogo();
        mainPage.waitForLoad();
        Assert.assertTrue("Переход в конструктор не произошел", mainPage.isOrderButtonVisible());
    }

    @Test
    public void checkExitButtonTest() {
        mainPage.clickPersonalButton();
        persAccountPage.waitForLoadMainPage();
        persAccountPage.clickLogoutButton();
        loginPage.waitForLoad();
        Assert.assertTrue("Выход не произошел", loginPage.isEnterLabelVisible());
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