import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;

public class NopCommerceTests extends SetUpTests {

    @Test
    public void checkMainPage() {
        Constants.MAIN_PAGE.openMainPage();
        Assert.assertTrue(Constants.MAIN_PAGE.getHeader().isDisplayed(), "Header is not present");

    }

    @Test
    public void checkComputersPage() {
        Constants.COMPUTERS_PAGE.openComputersPage();
        Assert.assertTrue(Constants.COMPUTERS_PAGE.getCategoryMenu().isDisplayed());

    }

    @Test
    public void checkDesktopPage() {
        Constants.DESKTOPS_PAGE.openDesktopsPage();
        Assert.assertEquals(Constants.DESKTOPS_PAGE.getDesktopTitle().getText(), "Desktops");

    }
}
