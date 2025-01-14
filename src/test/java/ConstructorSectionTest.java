import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;


public class ConstructorSectionTest extends BaseUITest {

    MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    public void checkSaucesSectionTest() {
        mainPage.clickSauceButton();
        Assert.assertTrue("Соуса нет", mainPage.isSauceButtonActive());
    }

    @Test
    public void checkFillingSectionTest() {
        mainPage.clickFillingButton();
        Assert.assertTrue("Начинки нет", mainPage.isFillingButtonActive());
    }

    @Test
    public void checkBunSectionTest() {
        mainPage.clickFillingButton();
        mainPage.clickBunButton();
        Assert.assertTrue("Булок нет", mainPage.isBunButtonActive());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}