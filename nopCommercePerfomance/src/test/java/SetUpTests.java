import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.ComputersPage;
import pages.DesktopsPage;
import pages.MainPage;
import utils.Constants;
import utils.WebDriverHolder;

import static utils.Constants.*;

public class SetUpTests {
    private WebDriver driver;

    @BeforeClass
    protected void setUpBrowser() {
        WebDriverManager.chromedriver().setup();
        Constants.SCENARIO_NAME = this.getClass().getSimpleName();
        WebDriverHolder.setDriver(BROWSER_FACTORY.startBrowser(BROWSER_NAME, driver));
        WebDriverHolder.getDriver().get(BASE_URL);
    }

    @BeforeMethod
    protected void setUp() {

        MAIN_PAGE = new MainPage();
        COMPUTERS_PAGE = new ComputersPage();
        DESKTOPS_PAGE = new DesktopsPage();
    }

    @AfterClass
    protected void tearDownBrowser() {
        if (WebDriverHolder.getDriver() != null) {
            WebDriverHolder.getDriver().quit();
        }
    }

}
