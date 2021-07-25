package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComputersPage extends AbstractPage {
    public ComputersPage() {
        super();
    }

    @FindBy(xpath = "//ul[@class='top-menu notmobile']//a[text()='Computers ']")
    private WebElement computersMenu;
    @FindBy(xpath = "//div[@class='page category-page']")
    private WebElement categoryMenu;

    public WebElement getCategoryMenu() {
        return categoryMenu;
    }

    public void clickComputersMenu() {
        computersMenu.click();
    }

    public ComputersPage openComputersPage() {
        clickComputersMenu();
        wait.until(ExpectedConditions.elementToBeClickable(categoryMenu));
        waitUntilPageIsFullyLoad(wait);
        System.out.println("Computers page is loaded");

        performTiming.writeToInflux("Computers page");
        performTiming.getAllTimings();
        System.out.println("Start timings:" + performTiming.getNavigationStart());
        return this;
    }
}
