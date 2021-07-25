package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbstractPage {
    public MainPage() {
        super();
    }

    @FindBy(xpath = "//div[@class='header-menu']")
    private WebElement header;

    public WebElement getHeader() {
        return header;
    }

    public MainPage openMainPage() {
        wait.until(ExpectedConditions.elementToBeClickable(header));
        waitUntilPageIsFullyLoad(wait);
        System.out.println("Main page is loaded");
        return this;

    }
}
