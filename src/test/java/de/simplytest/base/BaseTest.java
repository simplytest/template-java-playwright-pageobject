package de.simplytest.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import de.simplytest.factory.BrowserFactory;
import de.simplytest.pages.HomePage;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseTest {
    private final BrowserFactory browserFactory = new BrowserFactory();
    protected Properties properties = browserFactory.initializeConfigProperties();
    protected Page page;
    protected HomePage homepage;
    private String browserName;

    @BeforeMethod
    @Parameters({"browserName", "headless"})
    public void setUp(@Optional("chrome") String browserName, @Optional("false") String headless) throws InterruptedException {
        this.browserName = browserName;

        page = browserFactory.initializeBrowser(browserName, headless);

        page.navigate(properties.getProperty("BASE_URL").trim());

        homepage = new HomePage(page);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String formattedDateTime = formatter.format(currentDateTime);
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./target/screenshots/" + result.getName() + "_" + browserName + "_" + formattedDateTime + ".png")));
        }

        page.context().browser().close();
    }
}
