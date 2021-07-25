package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DesktopsPage extends AbstractPage {
    public DesktopsPage() {
        super();
    }


    @FindBy(xpath = "//div[@class='sub-category-item']//a[text()=' Desktops ']")
    private WebElement desktopMenu;

    @FindBy(xpath = "//div[@class='item-box']")
    private List<WebElement> desktopItems;

    @FindBy(xpath = "//h1")
    private WebElement desktopTitle;

    public WebElement getDesktopTitle() {
        return desktopTitle;
    }

    public void clickDesktopMenu() {
        desktopMenu.click();
    }

    public DesktopsPage openDesktopsPage() {
        clickDesktopMenu();
        wait.until(ExpectedConditions.elementToBeClickable(desktopItems.get(0)));
        waitUntilPageIsFullyLoad(wait);
        System.out.println("Desktops page is loaded");
        return this;
    }

}
