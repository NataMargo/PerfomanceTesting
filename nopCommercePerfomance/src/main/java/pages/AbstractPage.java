package pages;

import navigationTiming.PerformTiming;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.WebDriverHolder.getDriver;

public class AbstractPage {
    protected WebDriverWait wait;

    public AbstractPage() {

        wait = new WebDriverWait(getDriver(), 50);
        PageFactory.initElements(getDriver(), this);
    }

    protected PerformTiming performTiming = new PerformTiming();

    protected void waitUntilPageIsFullyLoad(WebDriverWait wait) {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
