import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseUITest {

    protected WebDriver driver;

    @Before
    public void startUp() {
        // initChrome();
        initYandex();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void initChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=");
        System.setProperty("webdriver.chrome.driver", "D:\\progs\\WebDriver\\bin\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void initYandex() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "D:\\progs\\WebDriver\\bin\\yandexdriver-win64\\yandexdriver.exe");
        options.setBinary("C:\\Users\\annet\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

}